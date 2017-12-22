package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import repositories.FolderRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Folder;
import domain.Message;
import domain.SocialId;
import domain.Trip;

@Service
@Transactional
public class AdministratorService {

	// Managed repository

	@Autowired
	private AdministratorRepository administratorRepository;

	// Supporting services

	@Autowired
	private FolderRepository folderRepository;

	// Constructors

	public AdministratorService() {
		super();
	}

	// Simple CRUD methods

	public Administrator create() {
		Administrator res = new Administrator();

		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		Collection<SocialId> socialId = new ArrayList<SocialId>();
		Collection<Folder> folder = new ArrayList<Folder>();
		
		Collection<Message> message = new ArrayList<Message>();
		Folder inBox = new Folder();
		Folder outBox = new Folder();
		Folder notification = new Folder();
		Folder trash = new Folder();
		Folder spam = new Folder();
		inBox.setName("In Box");
		outBox.setName("Out Box");
		notification.setName("Notification");
		trash.setName("Trash");
		spam.setName("Spam");
		inBox.setSystemFolder(true);
		outBox.setSystemFolder(true);
		notification.setSystemFolder(true);
		trash.setSystemFolder(true);
		spam.setSystemFolder(true);
		inBox.setMessages(message);
		outBox.setMessages(message);
		notification.setMessages(message);
		trash.setMessages(message);
		spam.setMessages(message);
		inBox = folderRepository.save(inBox);
		outBox = folderRepository.save(outBox);
		notification = folderRepository.save(notification);
		trash = folderRepository.save(trash);
		spam = folderRepository.save(spam);
		folder.add(inBox);
		folder.add(outBox);
		folder.add(notification);
		folder.add(trash);
		folder.add(inBox);
		folder.add(spam);
		
		authority.setAuthority(Authority.ADMIN);
		userAccount.addAuthority(authority);
		res.setUserAccount(userAccount);
		res.setSocialId(socialId);
		res.setFolders(folder);
		res.setSuspicious(false);
		return res;
	}

	public Collection<Administrator> findAll() {
		Collection<Administrator> res;
		res = this.administratorRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Administrator findOne(int administratorId) {
		Assert.isTrue(administratorId != 0);
		Administrator res;
		res = this.administratorRepository.findOne(administratorId);
		Assert.notNull(res);
		return res;
	}

	public Administrator save(Administrator administrator) {
		Administrator res;
		
		if (administrator.getId() == 0) {
			String pass = administrator.getUserAccount().getPassword();
			
			final Md5PasswordEncoder code = new Md5PasswordEncoder();
			
			pass = code.encodePassword(pass, null);
			
			administrator.getUserAccount().setPassword(pass);
		}
		
		res = this.administratorRepository.save(administrator);
		return res;
	}

	public void delete(Administrator administrator) {
		Assert.notNull(administrator);
		Assert.isTrue(administrator.getId() != 0);
		Assert.isTrue(this.administratorRepository.exists(administrator.getId()));
		this.administratorRepository.delete(administrator);
	}

	// Other business methods

	public Administrator findByPrincipal() {
		Administrator res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		res = this.administratorRepository
				.findAdministratorByUserAccountId(userAccount.getId());
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
		res.setAuthority("ADMIN");
		Assert.isTrue(authority.contains(res));
	}

	public Double applicationPending() {
		Double res;
		res = this.administratorRepository.applicationPending();
		return res;
	}

	public Double applicationDue() {
		Double res;
		res = this.administratorRepository.applicationDue();
		return res;
	}

	public Double applicationAccepted() {
		Double res;
		res = this.administratorRepository.applicationAccepted();
		return res;
	}

	public Double applicationCancelled() {
		Double res;
		res = this.administratorRepository.applicationCancelled();
		return res;
	}

	public Double ratioRangerEndorser() {
		Double res;
		res = this.administratorRepository.ratioRangerEndorser();
		return res;
	}

	public Object[] avgMinMaxSqtrManager() {
		Object[] res;
		res = this.administratorRepository.avgMinMaxSqtrManager();
		return res;
	}

	public Double ratioManagerSuspicious() {
		Double res;
		res = this.administratorRepository.ratioManagerSuspicious();
		return res;
	}

	public Object[] avgMinMaxSqtrRanger() {
		Object[] res;
		res = this.administratorRepository.avgMinMaxSqtrRanger();
		return res;
	}

	public Double ratioRangerCurriculum() {
		Double res;
		res = this.administratorRepository.ratioRangerCurriculum();
		return res;
	}

	public Double ratioSuspiciousRanger() {
		Double res;
		res = this.administratorRepository.ratioSuspiciousRanger();
		return res;
	}

	public Object[] avgMinMaxSqtr() {
		Object[] res;
		res = this.administratorRepository.avgMinMaxSqtr();
		return res;
	}

	public Object[] avgMinMaxSqtr2() {
		Object[] res;
		res = this.administratorRepository.avgMinMaxSqtr2();
		return res;
	}

	public Double ratioTripsCancelled() {
		Double res;
		res = this.administratorRepository.ratioTripsCancelled();
		return res;
	}

	public Collection<Trip> tripsThanAverage() {
		Collection<Trip> res;
		res = this.administratorRepository.tripsThanAverage();
		return res;
	}

	public Collection<Object> tripsLegalTextReferenced() {
		Collection<Object> res;
		res = this.administratorRepository.tripsLegalTextReferenced();
		return res;
	}
	//TODO: DESCOMENTAR
	public Double[] avgMinMaxSqtr3() {
		Double[] res;
		res = this.administratorRepository.avgMinMaxSqtr3();
		return res;
	}

	public Double[] avgMinMaxSqtr4() {
		Double[] res;
		res = this.administratorRepository.avgMinMaxSqtr4();
		return res;
	}

	public Double avgMinMaxSqtr5() {
		Double res;
		res = this.administratorRepository.avgMinMaxSqtr5();
		return res;
	}
	
	public Administrator findAdministratorById(int id){
		Administrator administrator;
		
		administrator = administratorRepository.findAdministratorById(id);
		
		return administrator;
	}
}
