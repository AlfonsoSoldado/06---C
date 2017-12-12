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

import services.CategoryService;
import services.StageService;
import services.TripService;
import controllers.AbstractController;
import domain.Category;
import domain.Stage;
import domain.Trip;

@Controller
@RequestMapping("/trip/manager")
public class TripManagerController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private TripService tripService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private StageService stageService;

	// Constructors ---------------------------------------------------------

	public TripManagerController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int tripId) {
		ModelAndView result;
		Trip t;

		t = tripService.findOne(tripId);
		Assert.notNull(t);
		result = this.createEditModelAndView(t);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Trip trip, BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()){
			res = this.createEditModelAndView(trip, "trip.params.error");
		}
		else
			try {
				this.tripService.save(trip);
				res = new ModelAndView("redirect:../list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(trip, "trip.commit.error");
			}

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Trip trip, BindingResult binding) {
		ModelAndView res;

		try {
			this.tripService.delete(trip);
			res = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(trip, "trip.commit.error");
		}

		return res;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Trip t;

		t = this.tripService.create();
		result = this.createEditModelAndView(t);

		return result;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Trip trip) {
		ModelAndView result;

		result = this.createEditModelAndView(trip, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Trip trip,
			final String message) {
		ModelAndView result;
		// final Collection<LegalText> legalTexts;
		final Collection<Category> category;
		Collection<Stage> stages;
		String ticker = this.tripService.generatedTicker();
		// Collection<Ranger> rangers;
		// final Collection<Value> values;

		// rangers = this.rangerService.findAll();
		// legalTexts = this.legalTextService.findAll();
		// values = this.valueService.findAll();
		category = this.categoryService.findAll();
		stages = this.stageService.findAll();

		result = new ModelAndView("trip/edit");
		// result.addObject("legalTexts", legalTexts);
		result.addObject("category", category);
		// result.addObject("rangers", rangers);
		// result.addObject("values", values);
		result.addObject("trip", trip);
		result.addObject("stage", stages);
		result.addObject("ticker", ticker);
		result.addObject("message", message);

		return result;

	}
}
