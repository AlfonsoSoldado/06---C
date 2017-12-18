package controllers.explorer;

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

import services.ApplicationService;
import controllers.AbstractController;
import domain.Application;

@Controller
@RequestMapping("/application/explorer")
public class ApplicationExplorerController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private ApplicationService applicationService;

	// Constructors ---------------------------------------------------------

	public ApplicationExplorerController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Application> applications;

		applications = applicationService.findAll();

		result = new ModelAndView("application/list");
		result.addObject("applicationExplorer", applications);
		result.addObject("requestURI", "application/explorer/list.do");

		return result;
	}

	// Editing ---------------------------------------------------------------

	@RequestMapping(value = "/editDue", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int applicationId) {
		ModelAndView result;
		Application application;

		application = applicationService.findOne(applicationId);
		Assert.notNull(application);
		result = this.createEditModelAndView(application);

		return result;
	}

	// Saving -------------------------------------------------------------

	@RequestMapping(value = "/editDue", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Application application,
			final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(application,
					"application.params.error");
		else
			try {
				this.applicationService.save(application);
				applicationService.applicationAccepted(application);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(application,
						"application.commit.error");
			}

		return res;
	}

	// Deleting -------------------------------------------------------------

	@RequestMapping(value = "/editDue", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Application application, BindingResult binding) {
		ModelAndView res;

		try {
			this.applicationService.delete(application);
			res = new ModelAndView(
					"redirect:../../application/explorer/list.do");
		} catch (Throwable oops) {
			res = createEditModelAndView(application,
					"application.commit.error");
		}

		return res;
	}

	// Creating ---------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Application application;
		application = this.applicationService.create();
		res = this.createEditModelAndView(application);
		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Application application) {
		ModelAndView result;

		result = this.createEditModelAndView(application, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(
			final Application application, final String message) {
		ModelAndView result;
		result = new ModelAndView("application/explorer/editDue");
		result.addObject("application", application);
		result.addObject("message", message);
		return result;
	}
}
