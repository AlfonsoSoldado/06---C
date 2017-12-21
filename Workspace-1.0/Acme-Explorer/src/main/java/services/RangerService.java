package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FolderRepository;
import repositories.RangerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Curriculum;
import domain.Folder;
import domain.Message;
import domain.Ranger;
import domain.SocialId;
import domain.Trip;

@Service
@Transactional
public class RangerService {

	// Managed repository

	@Autowired
	private RangerRepository rangerRepository;

	// Supporting services

	@Autowired
	private FolderRepository folderRepository;

	// Constructors

	public RangerService() {
		super();
	}

	// Simple CRUD methods

	public Ranger create() {
		Ranger res = new Ranger();

		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		Collection<SocialId> socialId = new ArrayList<SocialId>();
		Collection<Folder> folder = new ArrayList<Folder>();
		Curriculum curriculum = new Curriculum();
		Collection<Trip> trip = new ArrayList<Trip>();
		
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
		
		authority.setAuthority(Authority.RANGER);
		userAccount.addAuthority(authority);
		res.setUserAccount(userAccount);
		res.setSocialId(socialId);
		res.setFolders(folder);
		res.setCurriculum(curriculum);
		res.setTrip(trip);
		res.setSuspicious(false);

		return res;
	}

	public Collection<Ranger> findAll() {
		Collection<Ranger> res;
		res = this.rangerRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Ranger findOne(int ranger) {
		Assert.isTrue(ranger != 0);
		Ranger res;
		res = this.rangerRepository.findOne(ranger);
		Assert.notNull(res);
		return res;
	}

	public Ranger save(Ranger ranger) {
		Ranger res;
		
		if (ranger.getId() == 0) {
			String pass = ranger.getUserAccount().getPassword();
			
			final Md5PasswordEncoder code = new Md5PasswordEncoder();
			
			pass = code.encodePassword(pass, null);
			
			ranger.getUserAccount().setPassword(pass);
		}
		
		res = this.rangerRepository.save(ranger);
		return res;
	}

	public void delete(Ranger ranger) {
		Assert.notNull(ranger);
		Assert.isTrue(ranger.getId() != 0);
		Assert.isTrue(this.rangerRepository.exists(ranger.getId()));
		this.rangerRepository.delete(ranger);
	}

	// Other business methods

	// 35.1

	public Collection<Ranger> rangersSuspicious() {
		Collection<Ranger> res = new ArrayList<Ranger>();
		res.addAll(rangerRepository.rangersSuspicious());
		Assert.notNull(res);
		return res;
	}

	public Ranger findByPrincipal() {
		Ranger res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		res = this.rangerRepository.findRangerByUserAccountId(userAccount
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
		res.setAuthority("RANGER");
		Assert.isTrue(authority.contains(res));
	}
}
