package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.FinderService;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {
	
	// Services -------------------------------------------------------

	@Autowired
	FinderService administratorService;
	
	
	// Constructors ---------------------------------------------------------

	public AdministratorController() {
		super();
	}
	
	
	
	
	
	
	
	
}
