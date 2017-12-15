package controllers.administrator;

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

import services.ActorService;
import services.AdministratorService;
import services.AuditorService;
import services.ExplorerService;
import services.ManagerService;
import services.RangerService;
import services.SponsorService;
import controllers.AbstractController;
import domain.Actor;
import domain.Administrator;
import domain.Auditor;
import domain.Explorer;
import domain.Manager;
import domain.Ranger;
import domain.Sponsor;

@Controller
@RequestMapping("/administrator")
public class SuspiciousAdministratorController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private AdministratorService administatorService;

	@Autowired
	private ActorService actorService;
	
	@Autowired
	private RangerService rangerService;
	
	@Autowired
	private SponsorService sponsorService;
	
	@Autowired
	private AuditorService auditorService;
	
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private ExplorerService explorerService;

	// Constructors ---------------------------------------------------------

	public SuspiciousAdministratorController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/suspicious", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Actor> actoresSospechosos;

		actoresSospechosos = actorService.actorsSuspicious();

		result = new ModelAndView("administrator/suspicious");
		result.addObject("suspicious", actoresSospechosos);

		return result;
	}

	// Editing ---------------------------------------------------------------

	@RequestMapping(value = "/editSuspicious", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int actorId) {
		ModelAndView result;
		Manager actor;

		actor = managerService.findOne(actorId);
		Assert.notNull(actor);
		result = this.createEditModelAndView(actor);

		return result;
	}

	// Saving ---------------------------------------------------------------

	@RequestMapping(value = "/editSuspicious", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Manager actor, final BindingResult binding) {
		ModelAndView res;
		
		if (binding.hasErrors())
			res = this.createEditModelAndView(actor, "administrator.params.error");
		else
			try {
				this.managerService.save(actor);
				res = new ModelAndView("redirect:suspicious.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(actor, "administrator.commit.error");
			}

		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Manager actor) {
		ModelAndView result;

		result = this.createEditModelAndView(actor, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Manager actor,
			final String message) {
		ModelAndView result;
		result = new ModelAndView("administrator/editSuspicious");
		result.addObject("actor", actor);
		result.addObject("message", message);
		return result;
	}

}
