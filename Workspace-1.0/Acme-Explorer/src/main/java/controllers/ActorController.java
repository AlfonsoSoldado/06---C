package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ExplorerService;
import domain.Explorer;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private ExplorerService explorerService;

	// Constructors ---------------------------------------------------------

	public ActorController() {
		super();
	}
	
	// Registering ----------------------------------------------------------
	
	@RequestMapping(value = "/register_Explorer", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView res;
		Explorer explorer;
		
		explorer = this.explorerService.create();
		res = this.createEditModelAndView(explorer);
		
		return res;
	}
	
	// Ancillary methods --------------------------------------------------
	
		protected ModelAndView createEditModelAndView(final Explorer explorer) {
			ModelAndView result;

			result = this.createEditModelAndView(explorer, null);

			return result;
		}
		
		protected ModelAndView createEditModelAndView(final Explorer explorer,
				final String message) {
			ModelAndView result;
			final Collection<Explorer> explorers;
			explorers = this.explorerService.findAll();
			result = new ModelAndView("register_Explorer");
			result.addObject("explorer", explorers);
			result.addObject("message", message);
			
			return result;
		}
}
