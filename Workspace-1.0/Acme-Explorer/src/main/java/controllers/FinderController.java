package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.FinderService;
import domain.Finder;
import domain.Trip;

@Controller
@RequestMapping("/finder")
public class FinderController extends AbstractController {

	// Services -------------------------------------------------------

	@Autowired
	FinderService finderService;

	// Constructors ---------------------------------------------------------

	public FinderController() {
		super();
	}
	
	// Searching ----------------------------------------------------------------
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		final ModelAndView result;
		Finder finder;
		finder = this.finderService.create();
		//result = this.createEditModelAndView(finder);
		result = new ModelAndView("finder/display");
		result.addObject(finder);
		return result;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search() {
		final ModelAndView result;
		Finder finder;
		finder = this.finderService.create();
		result = this.createEditModelAndView(finder);
		return result;
	}
	
//	@RequestMapping(value = "/search", method = RequestMethod.POST, params = "save")
//	public ModelAndView save(@Valid final Finder finder,
//			final BindingResult binding) {
//		ModelAndView result;
//		if (binding.hasErrors())
//			result = this.createEditModelAndView(finder, "finder.params.error");
//		else
//			try {
//				this.finderService.findSearchSingleKey(finder.getSingleKey());
//				result = new ModelAndView("redirect:display.do");
//			} catch (final Throwable oops) {
//				result = this.createEditModelAndView(finder, "finder.commit.error");
//			}
//		return result;
//	}
	
	// Ancillary methods ---------------------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(final Finder finder) {
		ModelAndView result;
		result = this.createEditModelAndView(finder, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Finder finder, final String messageCode) {
		ModelAndView result;
		Collection<Trip> trips;
//		trips = finder.getTrip();
		trips = finderService.findSearchSingleKey(finder.getSingleKey());
		result = new ModelAndView("finder/search");
		result.addObject("trips", trips);
		result.addObject("finder", finder);
		result.addObject("message", messageCode);
		return result;
	}
}
