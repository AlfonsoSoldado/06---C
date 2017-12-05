package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AuditService;
import domain.Audit;

@Controller
@RequestMapping("/audit")
public class AuditController {
	//Services -------------------------------------------------------------
	
	@Autowired
	private AuditService auditService;
	
	//Constructors ---------------------------------------------------------
	
	public AuditController(){
		super();
	}
	
	//Listing --------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Audit> audits;
		
		audits = auditService.findAll();
		
		result = new ModelAndView("audit/list");
		result.addObject("audits", audits);
		result.addObject("requestURI", "audit/list.do");
		
		return result;
	}
	
}
