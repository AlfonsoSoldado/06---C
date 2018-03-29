package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FolderRepository;
import domain.Actor;
import domain.Folder;
import domain.Message;

@Service
@Transactional
public class FolderService {

	// Managed repository

	@Autowired
	private FolderRepository folderRepository;

	// Supporting services

	@Autowired
	private ActorService actorService;

	@Autowired
	private MessageService messageService;
	
	// Constructors

	public FolderService() {
		super();
	}

	// Simple CRUD methods

	public Folder create() {
		Folder folder = new Folder();
		Collection<Message> message = new ArrayList<Message>();
		Collection<Folder> folders = new ArrayList<Folder>();
		folder.setMessages(message);
		folder.setFolders(folders);
		folder.setSystemFolder(false);
		return folder;
	}

	public Collection<Folder> findAll() {
		Collection<Folder> res;
		res = this.folderRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Folder findOne(int folder) {
		Assert.isTrue(folder != 0);
		Folder res;
		res = this.folderRepository.findOne(folder);
		Assert.notNull(res);
		return res;
	}

	public Folder save(Folder folder) {
		Assert.isTrue(folder.getSystemFolder()==false);
		Assert.notNull(folder);
		Folder res;
		if(folder.getId()==0){
			folder.setSystemFolder(false);
		}
		res = this.folderRepository.save(folder);
		return res;
	}

	public void delete(Folder folder) {
		Assert.isTrue(folder.getSystemFolder()==false);
		Actor actor;
		actor = this.actorService.findByPrincipal();
		for(Message m : folder.getMessages()){
			this.messageService.delete(m);
		}
		actor.getFolders().remove(folder);
		this.folderRepository.delete(folder);
		
		
	}

	// Other business methods

	public Collection<Folder> systemFolders() {
		Collection<Folder> folders = new ArrayList<Folder>();
		
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
		
		inBox = folderRepository.save(inBox);
		outBox = folderRepository.save(outBox);
		notification = folderRepository.save(notification);
		trash = folderRepository.save(trash);
		spam = folderRepository.save(spam);
		
		folders.add(inBox);
		folders.add(outBox);
		folders.add(notification);
		folders.add(trash);
		folders.add(inBox);
		
		return folders;
	}
	
	public Collection<Folder> findFolders() {
		final Actor a;
		Collection<Folder> result;
		a = this.actorService.findByPrincipal();
		Assert.notNull(a);
		result = this.folderRepository.findFolders(a.getId());
		Assert.notNull(result);
		return result;
	}
	
	public Folder findFolderName(String nombre, int id){
		Folder res = new Folder();
		res = folderRepository.findFolderName(nombre, id);
		return res;
	}
}
