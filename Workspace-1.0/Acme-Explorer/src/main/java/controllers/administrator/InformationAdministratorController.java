package controllers.administrator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;

import controllers.AbstractController;

@Controller
@RequestMapping("/administrator")
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
		result  = new ModelAndView("administrator/list");
		
		Object applicationsPerTrip[];
		applicationsPerTrip = this.administratorService.avgMinMaxSqtr();
		result.addObject("informationApplication",applicationsPerTrip);
		
		return result;
		
		
//		Object[] applicationsPerTrip;
//		
//		applicationsPerTrip = administratorService.avgMinMaxSqtr();
//		
//		result = new ModelAndView("information/list");
//		Object avg, min, max, sd = null;
//		avg = applicationsPerTrip[0];
//		min = applicationsPerTrip[1];
//		max = applicationsPerTrip[2];
//		sd = applicationsPerTrip[3];
////		result.addObject("average", avg);
////		result.addObject("minimum", min);
////		result.addObject("maximum", max);
////		result.addObject("standardDeviation", sd);
//		result.addObject("informationApplication", applicationsPerTrip);
//		//result.addObject("requestURI", "administrator/list.do");
//		return result;
	}
	
	
	
	
	
}
