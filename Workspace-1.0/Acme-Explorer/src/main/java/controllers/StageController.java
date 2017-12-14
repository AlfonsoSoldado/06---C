package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Stage;

import services.StageService;


@Controller
@RequestMapping("/stage")
public class StageController extends AbstractController {
	
	//Services -------------------------------------------------------------
	
	@Autowired
	private StageService stageService;
	
	
	//Constructors ---------------------------------------------------------
	
	public StageController(){
		super();
	}

	//Listing --------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int tripId){
		ModelAndView res;
		Collection<Stage> stage;
		
		stage = stageService.findStageByTrip(tripId);
		
		res = new ModelAndView("stage/list");
		res.addObject("stage", stage);
		res.addObject("requestURI", "stage/list.do");
		
		return res;
	}
	
}
