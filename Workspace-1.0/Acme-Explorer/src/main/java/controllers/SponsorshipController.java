package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.SponsorshipService;
import domain.Sponsorship;

@Controller
@RequestMapping("/sponsorship")
public class SponsorshipController extends AbstractController {
	//Services -------------------------------------------------------------
	
	@Autowired
	private SponsorshipService sponsorshipService;
	
	//Constructors ---------------------------------------------------------
	
	public SponsorshipController(){
		super();
	}
	
	//Listing --------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int tripId){
		ModelAndView result;
		Collection<Sponsorship> sponsorships;
		
		sponsorships = sponsorshipService.findSponsorshipByTrip(tripId);
		
		result = new ModelAndView("sponsorship/list");
		result.addObject("sponsorship", sponsorships);
		result.addObject("requestURI", "sponsorship/list.do");
		
		return result;
	}
	
}
