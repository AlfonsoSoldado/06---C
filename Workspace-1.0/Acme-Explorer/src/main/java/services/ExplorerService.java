package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ExplorerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Application;
import domain.Emergency;
import domain.Explorer;
import domain.Finder;
import domain.Folder;
import domain.SocialId;
import domain.Story;

@Service
@Transactional
public class ExplorerService {

	// Managed repository

	@Autowired
	private ExplorerRepository explorerRepository;

	// Supporting services

	@Autowired
	private FolderService folderService;

	// Constructors

	public ExplorerService() {
		super();
	}

	// Simple CRUD methods

	public Explorer create() {
		Explorer res = new Explorer();
		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		Collection<SocialId> socialId = new ArrayList<SocialId>();
		Collection<Folder> folder = new ArrayList<Folder>();
		Collection<Story> story = new ArrayList<Story>();
		Collection<Application> application = new ArrayList<Application>();
		Finder finder = new Finder();
		Collection<Emergency> emergency = new ArrayList<Emergency>();
		folder = this.folderService.systemFolders();
		
		authority.setAuthority(Authority.EXPLORER);
		userAccount.addAuthority(authority);
		res.setUserAccount(userAccount);
		res.setSocialId(socialId);
		res.setFolders(folder);
		res.setStories(story);
		res.setApplication(application);
		res.setFinder(finder);
		res.setEmergency(emergency);
		res.setSuspicious(false);
		
		return res;
	}

	public Collection<Explorer> findAll() {
		Collection<Explorer> res;
		res = this.explorerRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Explorer findOne(int explorer) {
		Assert.isTrue(explorer != 0);
		Explorer res;
		res = this.explorerRepository.findOne(explorer);
		Assert.notNull(res);
		return res;
	}

	public Explorer save(Explorer explorer) {
		Explorer res;
		
		if (explorer.getId() == 0) {
			String pass = explorer.getUserAccount().getPassword();
			
			final Md5PasswordEncoder code = new Md5PasswordEncoder();
			
			pass = code.encodePassword(pass, null);
			
			explorer.getUserAccount().setPassword(pass);
			
			
		}
		
		res = this.explorerRepository.save(explorer);
		
//		Collection<Folder> folder = new ArrayList<Folder>();
//		folder = this.folderService.systemFolders();
//		
//		for (Folder f : folder) {
//			folderService.save(f);
//		}
//		
//		res.setFolders(folder);
//		System.out.println(res.getFolders());
		
		return res;
	}

	public void delete(Explorer explorer) {
		Assert.notNull(explorer);
		Assert.isTrue(explorer.getId() != 0);
		Assert.isTrue(this.explorerRepository.exists(explorer.getId()));
		this.explorerRepository.delete(explorer);
	}

	// Other business methods

	public Explorer findByPrincipal() {
		Explorer res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		res = this.explorerRepository.findExplorerByUserAccountId(userAccount
				.getId());
		Assert.notNull(res);
		return res;
	}

	public void checkAuthority() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		Collection<Authority> authority = userAccount.getAuthorities();
		Assert.notNull(authority);
		Authority res = new Authority();
		res.setAuthority("EXPLORER");
		Assert.isTrue(authority.contains(res));
	}
	
//	public Collection<Explorer> findExplorerByEmergency(Emergency emergency){
//		Collection<Explorer> explorers;
//		
//		explorers = explorerRepository.findExplorerByEmergency(emergency.getId());
//		Assert.notNull(explorers);
//		
//		return explorers;
//	}
}
