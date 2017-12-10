package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CurriculumService;
import domain.Curriculum;

@Controller
@RequestMapping("/curriculum")
public class CurriculumController {

	//Services -------------------------------------------------------------
	
		@Autowired
		private CurriculumService curriculumService;
		
		//Constructors ---------------------------------------------------------
		
		public CurriculumController(){
			super();
		}
	
		//Listing --------------------------------------------------------------
		
		@RequestMapping(value = "/display", method = RequestMethod.GET)
		public ModelAndView list(){
			ModelAndView result;
			Collection<Curriculum> curriculums;
			
			curriculums = curriculumService.findAll();
			
			result = new ModelAndView("curriculum/display");
			result.addObject("curriculum", curriculums);
			result.addObject("requestURI", "curriculum/display.do");
			
			return result;
		}
}
