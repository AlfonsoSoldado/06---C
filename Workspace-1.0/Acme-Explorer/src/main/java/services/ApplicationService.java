package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import repositories.MessageRepository;
import domain.Actor;
import domain.Application;
import domain.Explorer;
import domain.Folder;
import domain.Manager;
import domain.Message;

@Service
@Transactional
public class ApplicationService {

	// Managed repository

	@Autowired
	private ApplicationRepository applicationRepository;
	
	@Autowired
	private MessageRepository messageRepository;

	// Supporting services

	@Autowired
	private ManagerService managerService;

	@Autowired
	private ExplorerService explorerService;
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private AdministratorService administratorService;

	// Constructors

	public ApplicationService() {
		super();
	}

	// Simple CRUD methods

	public Application create() {
		Application res = new Application();
		
		String status;
		status = "PENDING";
		String comment = new String();
		
		Explorer explorer = new Explorer();
		explorer = explorerService.findByPrincipal();
		res.setExplorer(explorer);
		
		Date moment = new Date(System.currentTimeMillis() - 1);
		res.setMoment(moment);
		
		res.setStatus(status);
		res.setComment(comment);
		return res;
	}

	public Collection<Application> findAll() {
		Collection<Application> res;
		res = this.applicationRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Application findOne(int applicationId) {
		Assert.isTrue(applicationId != 0);
		Application res;
		res = this.applicationRepository.findOne(applicationId);
		Assert.notNull(res);
		return res;
	}

	public Application save(Application application) {
		Application res;
		
		res = this.applicationRepository.save(application);
		Explorer explorer = new Explorer();
		explorer = res.getExplorer();
		Collection<Application> applications = new ArrayList<Application>();
		applications = explorer.getApplication();
		applications.add(res);
		return res;
	}

	public void delete(Application application) {
		Assert.notNull(application);
		Assert.isTrue(application.getId() != 0);
		Assert.isTrue(this.applicationRepository.exists(application.getId()));
		this.applicationRepository.delete(application);
	}

	// Other business methods

	// 12.2 (changing)
	
	 public void changingStatus(Application a, String status) {
		 managerService.checkAuthority();
		 Assert.notNull(a);
		 Assert.notNull(status);
		 Assert.isTrue(status.equals("REJECTED") || status.equals("DUE"));
		 
		 Manager m = this.managerService.findByPrincipal();
		 
		 Assert.notNull(m);
		 
		 Collection<Application> applications = new ArrayList<Application>();
		 applications = applicationRepository.findListApplicationPending(m.getId());
		 
		 if(applications.contains(a)){
			 a.setStatus(status);
		 }
	 }

	// 12.2 (listing)
	 
	public Collection<Application> findListApplication(Manager manager) {
		Collection<Application> res = new ArrayList<Application>();
		
		res.addAll(applicationRepository.findApplicationByManager(manager.getId()));
		
		return res;
	}

	// 13.2
	
	public Collection<Application> findApplicationByExplorer(Explorer explorer) {
		explorerService.checkAuthority();
		Collection<Application> res = new ArrayList<Application>();
		
		res.addAll(applicationRepository.findApplicationByExplorer(explorer.getId()));
		Assert.notNull(res);
		
		return res;
	}
	
	// 13.3
	
	public void applicationAccepted(Application application){
		explorerService.checkAuthority();
		Assert.notNull(application);
		
		if(application.getCreditCard() != null){
			application.setStatus("ACCEPTED");
		}
	}
	
	public boolean aplicationAcceptedStartingDateNotPassed(Application application){
		Boolean res = false;
		Date date = new Date(System.currentTimeMillis());
		if(application.getTrip().getTripStart().after(date)){
			res = true;
		}
		return res;
	}
	
	public Message statusNotificationExplorer(Application application){
		Message res;
		
		Message message = messageService.findOne(120);

		Actor sender = administratorService.findAdministratorByUsername("admin");
		System.out.println(sender);
		
		Actor explorer = explorerService.findApplicationOfExplorer(application.getId());
		Actor manager = managerService.findApplicationOfManager(application.getId());
		System.out.println(explorer);
		System.out.println(manager);
		Folder f = folderService.findFolderName("Out Box", sender.getId());


		Collection<Message> msgs = new ArrayList<Message>();
		msgs.addAll(f.getMessages());
		msgs.add(message);

		f.setMessages(msgs);

		res = this.messageRepository.save(message);
		
		Collection<Actor> recipient = new ArrayList<Actor>();
		recipient.add(explorer);
		
		res.setFolder(f);
		
		for (Actor a : recipient) {
			Message res2;
			res2 = this.messageRepository.save(message);
			
			Collection<Message> messages = new ArrayList<Message>();
			
			Folder inbox = folderService.findFolderName("Notification", a.getId());
			res2.setFolder(inbox);
			messages.addAll(inbox.getMessages());
			messages.add(res);
			inbox.setMessages(messages);
		}

		return res;
	}
	
	public Message statusNotificationManager(Application application){
		Message res;
		
		Message message = messageService.findOne(121);

		Actor sender = administratorService.findAdministratorByUsername("admin");
		System.out.println(sender);
		
		Actor explorer = explorerService.findApplicationOfExplorer(application.getId());
		Actor manager = managerService.findApplicationOfManager(application.getId());
		System.out.println(explorer);
		System.out.println(manager);
		Folder f = folderService.findFolderName("Out Box", sender.getId());


		Collection<Message> msgs = new ArrayList<Message>();
		msgs.addAll(f.getMessages());
		msgs.add(message);

		f.setMessages(msgs);

		res = this.messageRepository.save(message);
		
		Collection<Actor> recipient = new ArrayList<Actor>();
		recipient.add(manager);
		
		res.setFolder(f);
		
		for (Actor a : recipient) {
			Message res2;
			res2 = this.messageRepository.save(message);
			
			Collection<Message> messages = new ArrayList<Message>();
			
			Folder inbox = folderService.findFolderName("Notification", a.getId());
			res2.setFolder(inbox);
			messages.addAll(inbox.getMessages());
			messages.add(res);
			inbox.setMessages(messages);
		}

		return res;
	}
}
