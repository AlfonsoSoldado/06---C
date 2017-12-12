package controllers.manager;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ManagerService;
import controllers.AbstractController;
import domain.Manager;

@Controller
@RequestMapping("/actor/manager")
public class ActorManagerController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private ManagerService managerService;

	// Constructors ---------------------------------------------------------

	public ActorManagerController() {
		super();
	}

	// Editing ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Manager manager;

		manager = this.managerService.findByPrincipal();
		result = this.createEditModelAndView(manager);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Manager manager,
			final BindingResult binding) {
		ModelAndView res;

//		if (binding.hasErrors())
//			res = this.createEditModelAndView(manager, "actor.params.error");
//		else
			try {
				this.managerService.save(manager);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(manager, "actor.commit.error");
			}

		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Manager manager) {
		ModelAndView result;

		result = this.createEditModelAndView(manager, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Manager manager,
			final String message) {
		ModelAndView result;
		
		result = new ModelAndView("actor/edit");
		result.addObject("actor", manager);
		result.addObject("message", message);
		result.addObject("requestUri", "actor/manager/edit.do");
		return result;
	}
}
