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

import services.ApplicationService;
import services.ManagerService;
import controllers.AbstractController;
import domain.Application;
import domain.Manager;

@Controller
@RequestMapping("/application/manager")
public class ApplicationManagerController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private ManagerService managerService;
	
	// Constructors ---------------------------------------------------------

	public ApplicationManagerController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Application> applications;
		Manager manager;
		manager= managerService.findByPrincipal();
		applications = applicationService.findListApplication(manager);

		result = new ModelAndView("application/list");
		result.addObject("applicationManager", applications);
		result.addObject("requestURI", "application/manager/list.do");

		return result;
	}
	
	
	// Editing ---------------------------------------------------------------

		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam final int applicationId) {
			ModelAndView result;
			Application application;

			application = applicationService.findOne(applicationId);
			Assert.notNull(application);
			result = this.createEditModelAndView(application);

			return result;
		}
		
	// Saving -------------------------------------------------------------

		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid final Application application,
				final BindingResult binding) {
			ModelAndView res;

			if (binding.hasErrors())
				res = this.createEditModelAndView(application,
						"application.params.error");
			else
				try {
					this.applicationService.save(application);
					res = new ModelAndView("redirect:list.do");
				} catch (final Throwable oops) {
					res = this.createEditModelAndView(application,
							"application.commit.error");
				}

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
		result = new ModelAndView("application/manager/edit");
		result.addObject("application", application);
		result.addObject("message", message);
		return result;
	}
}
