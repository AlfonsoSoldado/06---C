package controllers.explorer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ExplorerService;
import services.SurvivalService;
import controllers.AbstractController;
import domain.Explorer;
import domain.Survival;

@Controller
@RequestMapping("/survival/explorer")
public class SurvivalExplorerController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private SurvivalService survivalService;
	
	@Autowired
	private ExplorerService explorerService;
	
	// Constructors ---------------------------------------------------------

	public SurvivalExplorerController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Survival> survival;
		
		Explorer explorer;
		explorer = explorerService.findByPrincipal();
		survival = survivalService.findSurvivalByExplorerId(explorer.getId());
		
		result = new ModelAndView("survival/list");
		result.addObject("survival", survival);
		result.addObject("requestURI", "survival/explorer/list.do");

		return result;
	}
	
	@RequestMapping(value = "/enrol", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam final int survivalId) {
		ModelAndView result;
		Survival survival;
		survival = this.survivalService.changeExplorer(survivalId);
		result = this.createEditModelAndView(survival);
		return result;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Survival survival) {
		ModelAndView result;

		result = this.createEditModelAndView(survival, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(
			final Survival survival, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("survival/enrol");
		result.addObject("enrol", survival);
		result.addObject("message", messageCode);
		return result;
	}

}
