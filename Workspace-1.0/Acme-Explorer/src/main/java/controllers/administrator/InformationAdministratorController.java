package controllers.administrator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;

import controllers.AbstractController;

@Controller
@RequestMapping("/information/administrator")
public class InformationAdministratorController extends AbstractController {
	
	// Services -------------------------------------------------------------

	@Autowired
	private AdministratorService administratorService;

	// Constructors ---------------------------------------------------------

	public InformationAdministratorController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Object[] applicationsPerTrip;
		
		applicationsPerTrip = administratorService.avgMinMaxSqtr();
		
		result = new ModelAndView("administrator/list");
		result.addObject("informationApplication", applicationsPerTrip);
		//result.addObject("requestURI", "administrator/list.do");
		
		return result;
	}
	
	
	
	
	
}