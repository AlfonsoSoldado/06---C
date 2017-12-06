package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.TripService;
import domain.Trip;

@Controller
@RequestMapping("/trip")
public class TripController {

	
	//Services -------------------------------------------------------------
	
	@Autowired
	private TripService tripService;
	
	//Constructors ---------------------------------------------------------
	
	public TripController(){
		super();
	}
	
	//Listing --------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Trip> trips;
		
		trips = tripService.findAll();
		
		result = new ModelAndView("trip/list");
		result.addObject("trips", trips);
		result.addObject("requestURI", "trip/list.do");
		
		return result;
	}
}
