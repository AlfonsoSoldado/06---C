package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.EmergencyService;
import services.ExplorerService;
import services.FinderService;
import services.StoryService;
import services.SurvivalService;
import domain.Application;
import domain.Emergency;
import domain.Explorer;
import domain.Finder;
import domain.Story;
import domain.Survival;

@Controller
@RequestMapping("/explorer")
public class RegisterExplorerController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private ExplorerService explorerService;
	
	@Autowired
	private FinderService finderService;
	
	@Autowired
	private StoryService storyService;
	
	@Autowired
	private EmergencyService emergencyService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private SurvivalService survivalService;

	// Constructors ---------------------------------------------------------

	public RegisterExplorerController() {
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
	
	@RequestMapping(value = "/register_Explorer", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Explorer explorer,
			final BindingResult binding){
		ModelAndView res;
		
		if(binding.hasErrors())
			res = this.createEditModelAndView(explorer, "actor.params.error");
		else
			try{
				this.explorerService.save(explorer);
				res = new ModelAndView("redirect:../../");
			}catch (final Throwable oops) {
				res = this.createEditModelAndView(explorer, "actor.commit.error");
			}
		
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

			result = new ModelAndView("explorer/register_Explorer");
			result.addObject("explorer", explorer);
			result.addObject("message", message);
			
			return result;
		}
}
