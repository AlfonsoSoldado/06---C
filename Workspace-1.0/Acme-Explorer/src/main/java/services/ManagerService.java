package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ManagerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Application;
import domain.Folder;
import domain.Manager;
import domain.SocialId;
import domain.Trip;

@Service
@Transactional
public class ManagerService {

	// Managed repository

	@Autowired
	private ManagerRepository managerRepository;

	// Supporting services

	@Autowired
	private FolderService folderService;
	
	@Autowired
	private AdministratorService administratorService;

	// Constructors

	public ManagerService() {
		super();
	}

	// Simple CRUD methods
	
	public Manager create() {
		Manager res = new Manager();
		
		administratorService.checkAuthority();
		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		Collection<SocialId> socialId = new ArrayList<SocialId>();
		Collection<Folder> folder = new ArrayList<Folder>();
		Collection<Application> application = new ArrayList<Application>();
		Collection<Trip> trip = new ArrayList<Trip>();
		folder = this.folderService.systemFolders();
		
		authority.setAuthority(Authority.MANAGER);
		userAccount.addAuthority(authority);
		res.setUserAccount(userAccount);
		res.setSocialId(socialId);
		res.setFolders(folder);
		res.setApplication(application);
		res.setTrip(trip);
		res.setSuspicious(false);
		
		return res;
	}

	public Collection<Manager> findAll() {
		Collection<Manager> res;
		res = this.managerRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Manager findOne(int manager) {
		Assert.isTrue(manager != 0);
		Manager res;
		res = this.managerRepository.findOne(manager);
		Assert.notNull(res);
		return res;
	}

	public Manager save(Manager manager) {
		Assert.notNull(manager);
		Manager res;
		if (manager.getId() == 0) {
			String pass = manager.getUserAccount().getPassword();
			
			final Md5PasswordEncoder code = new Md5PasswordEncoder();
			
			pass = code.encodePassword(pass, null);
			
			manager.getUserAccount().setPassword(pass);
		}
		res = this.managerRepository.save(manager);
		return res;
	}

	public void delete(Manager manager) {
		Assert.notNull(manager);
		Assert.isTrue(manager.getId() != 0);
		Assert.isTrue(this.managerRepository.exists(manager.getId()));
		this.managerRepository.delete(manager);
	}

	// Other business methods
	
	public Manager findByPrincipal() {
		Manager res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		res = this.managerRepository.findManagerByUserAccountId(userAccount.getId());
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
		res.setAuthority("MANAGER");
		Assert.isTrue(authority.contains(res));
	}
}
