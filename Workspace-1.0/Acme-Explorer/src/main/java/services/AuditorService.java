package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AuditorRepository;
import repositories.FolderRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Audit;
import domain.Auditor;
import domain.Folder;
import domain.Message;
import domain.Note;
import domain.SocialId;

@Service
@Transactional
public class AuditorService {

	// Managed repository

	@Autowired
	private AuditorRepository auditorRepository;

	// Supporting services

	@Autowired
	private FolderRepository folderRepository;

	// Constructors

	public AuditorService() {
		super();
	}

	// Simple CRUD methods

	public Auditor create() {
		Auditor res = new Auditor();
		
		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		Collection<SocialId> socialId = new ArrayList<SocialId>();
		Collection<Folder> folder = new ArrayList<Folder>();
		Collection<Note> note = new ArrayList<Note>();
		Collection<Audit> audit = new ArrayList<Audit>();
		
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
		
		authority.setAuthority(Authority.AUDITOR);
		userAccount.addAuthority(authority);
		res.setUserAccount(userAccount);
		res.setSocialId(socialId);
		res.setFolders(folder);
		res.setNote(note);
		res.setAudit(audit);
		res.setSuspicious(false);
		return res;
	}

	public Collection<Auditor> findAll() {
		Collection<Auditor> res;
		res = this.auditorRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Auditor findOne(int auditorId) {
		Assert.isTrue(auditorId != 0);
		Auditor res;
		res = this.auditorRepository.findOne(auditorId);
		Assert.notNull(res);
		return res;
	}

	public Auditor save(Auditor auditor) {
		Auditor res;
		
		if (auditor.getId() == 0) {
			String pass = auditor.getUserAccount().getPassword();
			
			final Md5PasswordEncoder code = new Md5PasswordEncoder();
			
			pass = code.encodePassword(pass, null);
			
			auditor.getUserAccount().setPassword(pass);
		}
		res = this.auditorRepository.save(auditor);
		return res;
	}

	public void delete(Auditor auditor) {
		Assert.notNull(auditor);
		Assert.isTrue(auditor.getId() != 0);
		Assert.isTrue(this.auditorRepository.exists(auditor.getId()));
		this.auditorRepository.delete(auditor);
	}

	// Other business methods

	public Auditor findByPrincipal() {
		Auditor res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		res = this.auditorRepository.findAuditorByUserAccountId(userAccount
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
		res.setAuthority("AUDITOR");
		Assert.isTrue(authority.contains(res));
	}
}
