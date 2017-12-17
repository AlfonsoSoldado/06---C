package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.FinderService;
import domain.Finder;

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

	// Searching ----------------------------------------------------

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search() {
		final ModelAndView result;
		Finder finder;
		finder = this.finderService.create();
		result = this.createEditModelAndView(finder);
		return result;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Finder finder, BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			res = this.createEditModelAndView(finder, "finder.params.error");
		} else
			try {
				this.finderService.save(finder);
				res = new ModelAndView(
						"redirect:../trip/finder/list.do");
			} catch (final Throwable oops) {
				System.out.println(oops.getMessage());
				res = this
						.createEditModelAndView(finder, "finder.commit.error");
			}

		return res;
	}

	// Ancillary methods -------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Finder finder) {
		ModelAndView result;
		result = this.createEditModelAndView(finder, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Finder finder,
			final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("finder/search");
		result.addObject("finder", finder);
		result.addObject("message", messageCode);
		result.addObject("requestUri", "finder/search.do");
		return result;
	}
}
