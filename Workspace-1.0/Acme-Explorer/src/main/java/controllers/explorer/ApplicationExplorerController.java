package controllers.explorer;

import java.util.ArrayList;
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
import services.ExplorerService;
import services.ManagerService;
import services.TripService;
import controllers.AbstractController;
import domain.Application;
import domain.Explorer;
import domain.Manager;
import domain.Trip;

@Controller
@RequestMapping("/application/explorer")
public class ApplicationExplorerController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private ExplorerService explorerService;
	
	@Autowired
	private TripService tripService;
	
	@Autowired
	private ManagerService managerService;

	// Constructors ---------------------------------------------------------

	public ApplicationExplorerController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Application> applications;
		
		Explorer explorer = explorerService.findByPrincipal();

		applications = applicationService.findApplicationByExplorer(explorer);

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
	
	@RequestMapping(value = "/editAccepted", method = RequestMethod.GET)
	public ModelAndView editAccepted(@RequestParam final int applicationId) {
		ModelAndView result;
		Application application;

		application = applicationService.findOne(applicationId);
		Assert.notNull(application);
		result = this.editAcceptedModelAndView(application);

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
				applicationService.applicationAccepted(application);
				this.applicationService.save(application);
				
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(application,
						"application.commit.error");
			}

		return res;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid final Application application,
			final BindingResult binding) {
		ModelAndView res;
		System.out.println(binding.getFieldError());
		if (binding.hasErrors())
			res = this.createModelAndView(application,
					"application.params.error");
		else
			try {
				this.applicationService.save(application);
				
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				System.out.println(oops.getMessage());
				System.out.println(oops.getCause());
				res = this.createModelAndView(application,
						"application.commit.error");
			}

		return res;
	}
	
	@RequestMapping(value = "/editAccepted", method = RequestMethod.POST, params = "save")
	public ModelAndView saveAccepted(@Valid final Application application,
			final BindingResult binding) {
		ModelAndView res;
		System.out.println(binding.getFieldError());
		if (binding.hasErrors())
			res = this.editAcceptedModelAndView(application,
					"application.params.error");
		else
			try {
				this.applicationService.save(application);
				
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				System.out.println(oops.getMessage());
				System.out.println(oops.getCause());
				res = this.editAcceptedModelAndView(application,
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
		res = this.createModelAndView(application);
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
	
	protected ModelAndView createModelAndView(final Application application) {
		ModelAndView result;

		result = this.createModelAndView(application, null);

		return result;
	}

	protected ModelAndView createModelAndView(
			final Application application, final String message) {
		ModelAndView result;
		result = new ModelAndView("application/explorer/edit");
		
		Collection<Trip> trips = new ArrayList<Trip>();
		
		trips = tripService.findAll();
		
		Collection<Manager> manager = managerService.findAll();
		
		result.addObject("trip", trips);
		result.addObject("manager", manager);
		result.addObject("application", application);
		result.addObject("message", message);
		return result;
	}
	
	protected ModelAndView editAcceptedModelAndView(final Application application) {
		ModelAndView result;

		result = this.editAcceptedModelAndView(application, null);

		return result;
	}

	protected ModelAndView editAcceptedModelAndView(
			final Application application, final String message) {
		ModelAndView result;
		result = new ModelAndView("application/explorer/editAccepted");
		
		result.addObject("application", application);
		result.addObject("message", message);
		return result;
	}
}
