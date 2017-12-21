package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Actor;
import domain.Folder;
import domain.Message;

@Service
@Transactional
public class MessageService {

	// Managed repository

	@Autowired
	private MessageRepository messageRepository;

	// Supporting services
	@Autowired
	private ActorService actorService;

	@Autowired
	private FolderService folderService;

	// Constructors

	public MessageService() {
		super();
	}

	// Simple CRUD methods

	public Message create() {
		// actorService.checkAuthority();

		Message message;
		message = new Message();
		Actor sender = this.actorService.findByPrincipal();
		Date moment;
		Collection<Actor> recipient;
		Folder folder = new Folder();
		recipient = new ArrayList<Actor>();
		moment = new Date(System.currentTimeMillis() - 1);
		message.setSender(sender);
		message.setMoment(moment);
		message.setRecipient(recipient);
		message.setSpam(false);
		message.setFolder(folder);
		return message;
	}

	public Collection<Message> findAll() {
		// actorService.checkAuthority();

		Collection<Message> res;
		res = this.messageRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Message findOne(int message) {
		// actorService.checkAuthority();

		Assert.isTrue(message != 0);
		Message res;
		res = this.messageRepository.findOne(message);
		Assert.notNull(res);
		return res;
	}

	public Message save(Message message) {
		Message res;

		Actor sender = actorService.findByPrincipal();

		Folder f = folderService.findFolderName("Out Box", sender.getId());

		message.setFolder(f);

		Collection<Message> msgs = new ArrayList<Message>();
		msgs.addAll(f.getMessages());
		msgs.add(message);

		f.setMessages(msgs);

		res = this.messageRepository.save(message);
		
		Collection<Actor> recipient = res.getRecipient();

		for (Actor a : recipient) {
			Folder fo = folderService.findFolderName("In Box", a.getId());
			Collection<Message> messages = new ArrayList<Message>();
			msgs.addAll(fo.getMessages());
			msgs.add(message);
			fo.setMessages(messages);
		}

		return res;
	}

	public void delete(Message message) {
		// actorService.checkAuthority();

		Assert.notNull(message);
		Assert.isTrue(message.getId() != 0);
		Assert.isTrue(this.messageRepository.exists(message.getId()));
		this.messageRepository.delete(message);
	}

	// Other business methods

}
