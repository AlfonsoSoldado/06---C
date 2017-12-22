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
import domain.Configuration;
import domain.Curriculum;
import domain.EducationRecord;
import domain.EndorserRecord;
import domain.Folder;
import domain.Message;
import domain.MiscellaneousRecord;
import domain.PersonalRecord;
import domain.ProfessionalRecord;
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
	
	public void suspiciousRanger(Ranger ranger, Configuration configuration){
		Collection<String> spamWords = configuration.getSpamWords();
		
		Collection<EducationRecord> ers = ranger.getCurriculum().getEducationRecord();
		Collection<EndorserRecord> endrs = ranger.getCurriculum().getEndorserRecord();
		Collection<MiscellaneousRecord> misrs = ranger.getCurriculum().getMiscellaneousRecord();
		PersonalRecord pe = ranger.getCurriculum().getPersonalRecord();
		Collection<ProfessionalRecord> profrs = ranger.getCurriculum().getProfessionalRecord();
		Collection<Folder> folders = ranger.getFolders();
		Collection<SocialId> socialIds = ranger.getSocialId();
		
		Ranger newRanger;
		
		for(String sM: spamWords){
			String s = sM.toLowerCase();
			if(ranger.getAddress().toLowerCase().contains(s) || ranger.getEmail().toLowerCase().contains(s) || ranger.getName().toLowerCase().contains(s) || ranger.getSurname().toLowerCase().contains(s) || ranger.getUserAccount().getUsername().toLowerCase().contains(s)){
				newRanger = ranger;
				newRanger.setSuspicious(true);
				this.save(newRanger);
			}
			if(pe.getEmail().toLowerCase().contains(s) || pe.getLikedln().toLowerCase().contains(s) || pe.getName().toLowerCase().contains(s) || pe.getPhoto().toLowerCase().contains(s)){
				newRanger = ranger;
				newRanger.setSuspicious(true);
				this.save(newRanger);
			}
			for(EducationRecord ed: ers){
				if(ed.getComment().toLowerCase().contains(s) || ed.getInstitution().toLowerCase().contains(s) || ed.getLink().toLowerCase().contains(s) || ed.getTitle().toLowerCase().contains(s)){
					newRanger = ranger;
					newRanger.setSuspicious(true);
					this.save(newRanger);
				}
			}
			for(EndorserRecord en: endrs){
				if(en.getComment().toLowerCase().contains(s) || en.getEmail().toLowerCase().contains(s) || en.getEndorserName().toLowerCase().contains(s) || en.getLikedln().toLowerCase().contains(s)){
					newRanger = ranger;
					newRanger.setSuspicious(true);
					this.save(newRanger);
				}
			}
			for(MiscellaneousRecord mi: misrs){
				if(mi.getComment().toLowerCase().contains(s) || mi.getLink().toLowerCase().contains(s) || mi.getTitle().toLowerCase().contains(s)){
					newRanger = ranger;
					newRanger.setSuspicious(true);
					this.save(newRanger);
				}
			}
			for(ProfessionalRecord pr: profrs){
				if(pr.getComment().toLowerCase().contains(s) || pr.getCompanyName().toLowerCase().contains(s) || pr.getLink().toLowerCase().contains(s) || pr.getRol().toLowerCase().contains(s)){
					newRanger = ranger;
					newRanger.setSuspicious(true);
					this.save(newRanger);
				}
			}
			for(Folder f: folders){
				if(f.getName().toLowerCase().contains(s)){
					newRanger = ranger;
					newRanger.setSuspicious(true);
					this.save(newRanger);
				}
			}
			for(SocialId sId: socialIds){
				if(sId.getNameSocialNetwork().toLowerCase().contains(s) || sId.getNick().toLowerCase().contains(s) || sId.getPhoto().toLowerCase().contains(s) || sId.getSocialNetwork().toLowerCase().contains(s)){
					newRanger = ranger;
					newRanger.setSuspicious(true);
					this.save(newRanger);
				}
			}
		}
	}
}
