package controllers.administrator;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import controllers.AbstractController;
import domain.Trip;

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
		Object avgMinMaxSqtrManager[];
		Object avgMinMaxSqtr2[];
		Object avgMinMaxSqtrRanger[];
		Double applicationPending;
		Double applicationDue;
		Double applicationAccepted;
		Double applicationCancelled;
		Double ratioTripsCancelled;
		Collection<Trip> tripsThanAverage;
		Double tripsLegalTextReferenced;
		
		applicationsPerTrip = this.administratorService.avgMinMaxSqtr();
		avgMinMaxSqtrManager = this.administratorService.avgMinMaxSqtrManager();
		avgMinMaxSqtr2 = this.administratorService.avgMinMaxSqtr2();
		avgMinMaxSqtrRanger = this.administratorService.avgMinMaxSqtrRanger();
		applicationPending = this.administratorService.applicationPending();
		applicationDue = this.administratorService.applicationDue();
		applicationAccepted = this.administratorService.applicationAccepted();
		applicationCancelled = this.administratorService.applicationCancelled();
		ratioTripsCancelled = this.administratorService.ratioTripsCancelled();
		tripsThanAverage = this.administratorService.tripsThanAverage();
		tripsLegalTextReferenced = this.administratorService.tripsLegalTextReferenced();
		
		result.addObject("informationApplication",applicationsPerTrip);
		result.addObject("avgMinMaxSqtrManager",avgMinMaxSqtrManager);
		result.addObject("avgMinMaxSqtr2",avgMinMaxSqtr2);
		result.addObject("avgMinMaxSqtrRanger",avgMinMaxSqtrRanger);
		result.addObject("applicationPending",applicationPending);
		result.addObject("applicationDue",applicationDue);
		result.addObject("applicationAccepted",applicationAccepted);
		result.addObject("applicationCancelled",applicationCancelled);
		result.addObject("ratioTripsCancelled",ratioTripsCancelled);
		result.addObject("tripsThanAverage",tripsThanAverage);
		result.addObject("tripsLegalTextReferenced",tripsLegalTextReferenced);
		
		
		
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
