package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FolderRepository;
import repositories.SponsorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Folder;
import domain.Message;
import domain.SocialId;
import domain.Sponsor;
import domain.Sponsorship;

@Service
@Transactional
public class SponsorService {

	// Managed repository

	@Autowired
	private SponsorRepository sponsorRepository;

	// Supporting services

	@Autowired
	private FolderRepository folderRepository;

	// Constructors

	public SponsorService() {
		super();
	}

	// Simple CRUD methods

	public Sponsor create() {
		Sponsor res = new Sponsor();
		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		Collection<SocialId> socialId = new ArrayList<SocialId>();
		Collection<Folder> folder = new ArrayList<Folder>();
		Collection<Sponsorship> sponsorship = new ArrayList<Sponsorship>();
		
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
		
		res.setSocialId(socialId);
		res.setFolders(folder);
		authority.setAuthority(Authority.SPONSOR);
		userAccount.addAuthority(authority);
		res.setUserAccount(userAccount);
		res.setSponsorship(sponsorship);
		res.setSuspicious(false);
		return res;
	}

	public Collection<Sponsor> findAll() {
		Collection<Sponsor> res;
		res = this.sponsorRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Sponsor findOne(int sponsor) {
		Assert.isTrue(sponsor != 0);
		Sponsor res;
		res = this.sponsorRepository.findOne(sponsor);
		Assert.notNull(res);
		return res;
	}

	public Sponsor save(Sponsor sponsor) {
		Sponsor res;
		
		if (sponsor.getId() == 0) {
			String pass = sponsor.getUserAccount().getPassword();
			
			final Md5PasswordEncoder code = new Md5PasswordEncoder();
			
			pass = code.encodePassword(pass, null);
			
			sponsor.getUserAccount().setPassword(pass);
		}
		
		res = this.sponsorRepository.save(sponsor);
		return res;
	}

	public void delete(Sponsor sponsor) {
		Assert.notNull(sponsor);
		Assert.isTrue(sponsor.getId() != 0);
		Assert.isTrue(this.sponsorRepository.exists(sponsor.getId()));
		this.sponsorRepository.delete(sponsor);
	}

	// Other business methods

	public Sponsor findByPrincipal() {
		Sponsor res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		res = this.sponsorRepository.findSponsorByUserAccountId(userAccount
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
		res.setAuthority("SPONSOR");
		Assert.isTrue(authority.contains(res));
	}
}
