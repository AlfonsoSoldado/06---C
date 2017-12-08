package controllers.sponsor;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

import services.ActorService;
import domain.Sponsor;

@Controller
@RequestMapping("/actor/sponsor")
public class ActorSponsorController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private ActorService actorService;

	// Constructors ---------------------------------------------------------

	public ActorSponsorController() {
		super();
	}

	// Editing ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Sponsor sponsor;

		sponsor = (Sponsor) this.actorService.findByPrincipal();
		Assert.notNull(sponsor);
		result = this.createEditModelAndView(sponsor);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Sponsor sponsor,
			final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(sponsor, "actor.params.error");
		else
			try {
				this.actorService.save(sponsor);
				res = new ModelAndView("redirect:index.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(sponsor, "actor.commit.error");
			}

		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Sponsor sponsor) {
		ModelAndView result;

		result = this.createEditModelAndView(sponsor, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Sponsor sponsor,
			final String message) {
		ModelAndView result;
		result = new ModelAndView("actor/edit");
		result.addObject("actor", sponsor);
		result.addObject("message", message);
		result.addObject("requestUri", "actor/sponsor/edit.do");
		return result;

	}
}
