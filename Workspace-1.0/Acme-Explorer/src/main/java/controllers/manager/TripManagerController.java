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
import services.LegalTextService;
import services.ManagerService;
import services.RangerService;
import services.StageService;
import services.TripService;
import services.ValueService;
import controllers.AbstractController;
import domain.Category;
import domain.LegalText;
import domain.Manager;
import domain.Ranger;
import domain.Stage;
import domain.Trip;
import domain.Value;

@Controller
@RequestMapping("/trip/manager")
public class TripManagerController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private TripService tripService;
	
	@Autowired
	private ManagerService managerService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private StageService stageService;
	
	@Autowired
	private RangerService rangerService;
	
	@Autowired
	private LegalTextService legalTextService;
	
	@Autowired
	private ValueService valueService;

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
	
	@RequestMapping(value = "/reason", method = RequestMethod.GET)
	public ModelAndView reason(@RequestParam final int tripId) {
		ModelAndView result;
		Trip trip;
		trip = tripService.findOne(tripId);
		result = this.createCancelModelAndView(trip);
		return result;
	}
	
	@RequestMapping(value = "/reason", method = RequestMethod.POST, params = "save")
	public ModelAndView saveReason(@Valid Trip trip, BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()){
			res = this.createEditModelAndView(trip, "trip.params.error");
		}
		else
			try {
				this.tripService.save(trip);
				this.tripService.cancelTrip(trip);
				res = new ModelAndView("redirect:../list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(trip, "trip.commit.error");
			}

		return res;
	}

	@RequestMapping(value = "/reason", method = RequestMethod.POST, params = "delete")
	public ModelAndView deleteReason(Trip trip, BindingResult binding) {
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
		final Collection<Category> category;
		Collection<Stage> stages;
		Collection<LegalText> legaltext;
		Collection<Value> value;
		String ticker = this.tripService.generatedTicker();
		Collection<Ranger> ranger = this.rangerService.findAll();
		Manager manager = this.managerService.findByPrincipal();
		category = this.categoryService.findAll();
		stages = this.stageService.findAll();
		legaltext = this.legalTextService.findAll();
		value = this.valueService.findAll();

		result = new ModelAndView("trip/edit");
		result.addObject("manager", manager);
		result.addObject("ranger", ranger);
		result.addObject("category", category);
		result.addObject("trip", trip);
		result.addObject("stage", stages);
		result.addObject("ticker", ticker);
		result.addObject("value", value);
		result.addObject("legalText", legaltext);
		result.addObject("message", message);

		return result;

	}
	
	protected ModelAndView createCancelModelAndView(final Trip trip) {
		ModelAndView result;

		result = this.createCancelModelAndView(trip, null);

		return result;
	}

	protected ModelAndView createCancelModelAndView(
			final Trip trip, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("trip/reason");
		result.addObject("reason", trip);
		result.addObject("message", messageCode);
		return result;
	}
}
