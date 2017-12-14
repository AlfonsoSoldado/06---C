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
		Actor actor;

		actor = actorService.findOne(actorId);
		Assert.notNull(actor);
		result = this.createEditModelAndView(actor);

		return result;
	}

	// Saving ---------------------------------------------------------------

	@RequestMapping(value = "/editSuspicious", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Actor actor, final BindingResult binding) {
		ModelAndView res;
		Actor ac = null;
		Collection<Ranger> rangers = new ArrayList<Ranger>();
		Collection<Administrator> administrators = new ArrayList<Administrator>();;
		Collection<Sponsor> sponsors = new ArrayList<Sponsor>();;
		Collection<Auditor> auditors = new ArrayList<Auditor>();;
		Collection<Manager> managers = new ArrayList<Manager>();;
		Collection<Explorer> explorers = new ArrayList<Explorer>();;
		
		rangers.addAll(rangerService.findAll());
		administrators.addAll(administatorService.findAll());
		sponsors.addAll(sponsorService.findAll());
		auditors.addAll(auditorService.findAll());
		managers.addAll(managerService.findAll());
		explorers.addAll(explorerService.findAll());
		
		if (binding.hasErrors())
			res = this.createEditModelAndView(actor, "administrator.params.error");
		else
			try {
				
				Ranger r;
				Administrator  a;
				Sponsor s;
				Auditor au;
				Manager m;
				Explorer e;
				
				if(rangers.contains(actor)){
					r = (Ranger) actor;
					this.rangerService.save(r);
					ac = (Actor) r;
				}else if(administrators.contains(actor)){
					a = (Administrator) actor;
					this.administatorService.save(a);
					ac = (Actor) a;
				}else if(sponsors.contains(actor)){
					s = (Sponsor) actor;
					this.sponsorService.save(s);
					ac = (Actor) s;
				}else if(auditors.contains(actor)){
					au = (Auditor) actor;
					this.auditorService.save(au);
					ac = (Actor) au;
				}else if(managers.contains(actor)){
					m = (Manager) actor;
					this.managerService.save(m);
					ac = (Actor) m;
				}else {
					e = (Explorer) actor;
					this.explorerService.save(e);
					ac = (Actor) e;
				}
				
				
				res = new ModelAndView("redirect:suspicious.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(ac, "administrator.commit.error");
			}

		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Actor actor) {
		ModelAndView result;

		result = this.createEditModelAndView(actor, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Actor actor,
			final String message) {
		ModelAndView result;
		result = new ModelAndView("administrator/editSuspicious");
		result.addObject("actor", actor);
		result.addObject("message", message);
		return result;
	}

}
