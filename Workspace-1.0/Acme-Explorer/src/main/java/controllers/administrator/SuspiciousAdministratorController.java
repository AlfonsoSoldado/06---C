package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ConfigurationService;
import services.ExplorerService;
import services.RangerService;
import controllers.AbstractController;
import domain.Actor;
import domain.Configuration;
import domain.Explorer;
import domain.Ranger;

@Controller
@RequestMapping("/administrator")
public class SuspiciousAdministratorController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private ActorService actorService;
	
	@Autowired
	private ExplorerService explorerService;
	
	@Autowired
	private ConfigurationService configurationService;
	
	@Autowired
	private RangerService rangerService;
	
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
		
		Configuration configuration;
		Integer configurationId = configurationService.resId();
		configuration = configurationService.findOne(configurationId);
		
		Collection<Explorer> explorers = explorerService.findAll();
		Collection<Ranger> rangers = rangerService.findAll();
		
		for(Explorer explorer: explorers){
			explorerService.suspiciousExplorer(explorer, configuration);
		}
		for(Ranger ranger: rangers){
			rangerService.suspiciousRanger(ranger, configuration);
		}

		result = new ModelAndView("administrator/suspicious");
		result.addObject("suspicious", actoresSospechosos);

		return result;
	}
	
	@RequestMapping(value = "/ban", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam final int actorId) {
		ModelAndView result;
		Actor actor;
		actor = this.actorService.findOne(actorId);
		this.actorService.ban(actor);
		result = this.createEditModelAndView(actor);

		return result;
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
		result = new ModelAndView("administrator/ban");
		result.addObject("actor", actor);
		result.addObject("message", message);
		return result;
	}

}
