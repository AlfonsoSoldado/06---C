package controllers.ranger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.RangerService;
import controllers.AbstractController;
import domain.Ranger;

@Controller
@RequestMapping("/actor/ranger")
public class ActorRangerController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private RangerService rangerService;


	// Constructors ---------------------------------------------------------

	public ActorRangerController() {
		super();
	}

	// Editing ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Ranger ranger;

		ranger = this.rangerService.findByPrincipal();
		result = this.createEditModelAndView(ranger);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Ranger ranger,
			final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createEditModelAndView(ranger, "actor.params.error");
		else
			try {
				this.rangerService.save(ranger);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(ranger, "actor.commit.error");
			}

		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Ranger ranger) {
		ModelAndView result;

		result = this.createEditModelAndView(ranger, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Ranger ranger,
			final String message) {
		ModelAndView result;
		
		result = new ModelAndView("actor/edit");
		result.addObject("actor", ranger);
		result.addObject("message", message);
		result.addObject("requestUri", "actor/ranger/edit.do");
		return result;

	}
}
