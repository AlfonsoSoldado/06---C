package controllers.explorer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import controllers.AbstractController;
import domain.Application;

@Controller
@RequestMapping("/application/explorer")
public class ApplicationExplorerController extends AbstractController {

	
	//Services -------------------------------------------------------------
	
	@Autowired
	private ApplicationService applicationService;
	
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
		
		result = new ModelAndView("application/list");
		result.addObject("applicationExplorer", applications);
		result.addObject("requestURI", "application/explorer/list.do");
		
		return result;
	}
}
