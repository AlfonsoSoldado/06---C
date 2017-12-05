package controllers.explorer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;



import services.ApplicationService;
import services.ExplorerService;
import domain.Application;

@Controller
@RequestMapping("/application/explorer")
public class ApplicationExplorerController extends AbstractController{

	
	//Services -------------------------------------------------------------
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private ExplorerService explorerService;
	
	//Constructors ---------------------------------------------------------
	
	public ApplicationExplorerController(){
		super();
	}
	
	//Listing --------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Application> applications;
		
		applications = applicationService.findAll();
		
		result = new ModelAndView("application/explorer/list");
		result.addObject("applications", applications);
		result.addObject("requestURI", "application/explorer/list.do");
		
		return result;
	}
}
