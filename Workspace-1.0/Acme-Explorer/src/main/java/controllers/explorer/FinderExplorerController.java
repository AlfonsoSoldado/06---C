package controllers.explorer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.FinderService;
import controllers.AbstractController;
import domain.Finder;

@Controller
@RequestMapping("/finder/explorer")
public class FinderExplorerController extends AbstractController {

	// Services -------------------------------------------------------

	@Autowired
	FinderService finderService;

	// Constructors ----------------------------------------------

	public FinderExplorerController() {
		super();
	}

	// Searching ------------------------------------------------

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		Finder finder;
		finder = this.finderService.create();
		result = this.createEditModelAndView(finder);
		return result;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Finder finder,
			final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(finder, "finder.params.error");
		else
			try {
				this.finderService.save(finder);
				result = new ModelAndView("redirect:../../trip/finder/explorer/list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(finder,
						"finder.commit.error");
			}
		return result;
	}

	// Ancillary methods -----------------------------------------------------

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
		result.addObject("requestUri", "finder/explorer/search.do");
		return result;
	}

}
