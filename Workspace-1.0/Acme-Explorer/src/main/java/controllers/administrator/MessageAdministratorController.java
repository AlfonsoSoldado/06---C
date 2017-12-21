package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.MessageService;
import controllers.AbstractController;
import domain.Actor;
import domain.Message;

@Controller
@RequestMapping("/message/administrator")
public class MessageAdministratorController extends AbstractController {

	// Services -------------------------------------------------------

	@Autowired
	private MessageService messageService;

	@Autowired
	private ActorService actorService;

	// Constructors ---------------------------------------------------------

	public MessageAdministratorController() {
		super();
	}
	
	@RequestMapping(value = "/editAdministrator", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int messageId) {
		ModelAndView result;
		Message message;

		message = messageService.findOne(messageId);
		Assert.notNull(message);
		result = this.createEditModelAndView(message);

		return result;
	}
	
	@RequestMapping(value = "/editAdministrator", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Message message,
			final BindingResult binding) {
		ModelAndView result;
		
		System.out.println(binding.getFieldError());
		if (binding.hasErrors())
			result = this.createEditModelAndView(message,
					"message.params.error");
		else
			try {
				messageService.Notification(message);
				result = new ModelAndView("redirect:../../folder/list.do");
			} catch (final Throwable oops) {
				System.out.println(oops.getMessage());
				System.out.println(oops.getCause());
				System.out.println(oops.getLocalizedMessage());
				result = this.createEditModelAndView(message,
						"message.commit.error");
			}
		return result;
	}
	
	@RequestMapping(value = "/editAdministrator", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Message message, BindingResult binding) {
		ModelAndView res;

		try {
			this.messageService.delete(message);
			res = new ModelAndView("redirect:../../folder/list.do");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(message, "message.commit.error");
		}

		return res;
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

	protected ModelAndView createEditModelAndView(final Message message,
			final String messageCode) {
		ModelAndView result;
		Collection<Actor> actor;
		actor = this.actorService.findAll();
		result = new ModelAndView("message/editAdministrator");
		result.addObject("msg", message);
		result.addObject("actor", actor);
		result.addObject("message", messageCode);
		result.addObject("requestUri", "message/administrator/editAdministrator.do");
		return result;
	}
}
