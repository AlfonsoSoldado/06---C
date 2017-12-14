package controllers.manager;

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

import services.SurvivalService;
import services.TripService;
import controllers.AbstractController;
import domain.Survival;
import domain.Trip;

@Controller
@RequestMapping("/survival/manager")
public class SurvivalManagerController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private SurvivalService survivalService;
	
	@Autowired
	private TripService tripService;
	
	// Constructors ---------------------------------------------------------

	public SurvivalManagerController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Survival> survival;

		survival = survivalService.findSurvivalByTrips();

		result = new ModelAndView("survival/list");
		result.addObject("survival", survival);
		result.addObject("requestURI", "survival/manager/list.do");

		return result;
	}

	// Editing --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int survivalId) {
		ModelAndView result;
		Survival survival;

		survival = survivalService.findOne(survivalId);
		Assert.notNull(survival);
		result = this.createEditModelAndView(survival);

		return result;
	}

	// Saving --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Survival survival,
			final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			System.out.println("Binding");
			res = this
					.createEditModelAndView(survival, "survival.params.error");
		} else
			try {
				System.out.println("Try");
				this.survivalService.save(survival);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(survival,
						"survival.commit.error");
			}

		return res;
	}

	// Deleting --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Survival survival,
			final BindingResult binding) {
		ModelAndView res;

		try {
			this.survivalService.delete(survival);
			res = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			res = this
					.createEditModelAndView(survival, "survival.commit.error");
		}

		return res;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Survival survival;

		survival = this.survivalService.create();
		result = this.createEditModelAndView(survival);

		return result;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Survival survival) {
		ModelAndView result;

		result = this.createEditModelAndView(survival, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(
			final Survival survival, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("survival/edit");
		Collection<Trip> trips = tripService.findAll();
		result.addObject("trip", trips);
		result.addObject("survival", survival);
		result.addObject("message", messageCode);
		return result;
	}

}
