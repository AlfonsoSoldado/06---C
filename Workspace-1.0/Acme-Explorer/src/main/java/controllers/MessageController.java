package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.FolderService;
import services.MessageService;
import domain.Actor;
import domain.Folder;
import domain.Message;

@Controller
@RequestMapping("/message")
public class MessageController extends AbstractController {

	// Services -------------------------------------------------------
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	ActorService actorService;
	
	@Autowired
	FolderService folderService;

	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET, params = "folderId")
	public ModelAndView list(@RequestParam final int folderId) {
		final ModelAndView result;
		final Folder folder;
		Collection<Message> messages;
		folder = this.folderService.findOne(folderId);
		messages = folder.getMessages();
		result = new ModelAndView("message/list");
		result.addObject("folder", folder);
		result.addObject("messages", messages);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Message message, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(message, "message.params.error");
		else
			try {
				this.actorService.sendMessage(message.getRecipient(), message.getSender(), message);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(message, "message.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		Message message;
		message = this.messageService.create();
		result = this.createEditModelAndView(message);
		return result;
	}
	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Message message) {
		ModelAndView result;
		result = this.createEditModelAndView(message, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Message message, final String messageCode) {
		ModelAndView result;
		final Collection<Actor> actor;
		actor = this.actorService.findAll();
		result = new ModelAndView("message/edit");
		result.addObject("row", message);
		result.addObject("actor", actor);
		result.addObject("message", messageCode);
		return result;
	}
}
