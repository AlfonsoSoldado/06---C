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

import services.StageService;
import controllers.AbstractController;
import domain.Stage;


@Controller
@RequestMapping("/stage/manager")
public class StageManagerController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private StageService stageService;
	
	
	// Constructors ---------------------------------------------------------
	
	public StageManagerController(){
		super();
	}
	
	
	// Listing --------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView res;
		Collection<Stage> stage;
		
		stage = stageService.findAll();
		
		res = new ModelAndView("stage/list");
		res.addObject("stage",stage);
		res.addObject("requestURI", "stage/manager/list.do");
		
		return res;
	}
	
	
	// Editing --------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int stageId){
		ModelAndView res;
		Stage stage;
		
		stage = stageService.findOne(stageId);
		Assert.notNull(stage);
		res = this.createEditModelAndView(stage);
		
		return res;
	}
	
	
	// Saving --------------------------------------------------------------
	
	@RequestMapping(value = "edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Stage stage, 
						final BindingResult binding){
		ModelAndView res;
		
		if(binding.hasErrors()){
			System.out.println("Binding");
			res = this.createEditModelAndView(stage, "stage.params.error");
		}else{
			try{
				System.out.println("Try");
				this.stageService.save(stage);
				res = new ModelAndView("redirect:list.do");
			}catch (final Throwable oops) {
				res = this.createEditModelAndView(stage, 
						"stage.commit.error");
			}
		}
		return res;
	}
	
	
	// Deleting --------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Stage stage,
			final BindingResult binding){
		ModelAndView res;
		
		try{
			this.stageService.delete(stage);
			res = new ModelAndView("redirect:list.do");
		}catch (final Throwable oops) {
			res = this.createEditModelAndView(stage, "stage.commit.error");
		}
		
		return res;
	}
	
	
	// Creation ---------------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView res;
		Stage stage;
		
		stage = this.stageService.create();
		res = this.createEditModelAndView(stage);
		
		return res;
	}
	
	// Ancillary methods --------------------------------------------------
	
	protected ModelAndView createEditModelAndView(final Stage stage) {
		ModelAndView res;
		
		res = this.createEditModelAndView(stage, null);
		
		return res;
	}


	protected ModelAndView createEditModelAndView(
						final Stage stage, final String messageCode) {
		ModelAndView res;
		res = new ModelAndView("stage/edit");
		res.addObject("stageManager", stage);
		res.addObject("message",messageCode);
		
		return res;
	}
	
}
