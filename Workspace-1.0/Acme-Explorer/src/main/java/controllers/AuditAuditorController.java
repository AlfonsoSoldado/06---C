package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AuditService;
import services.AuditorService;
import domain.Audit;

@Controller
@RequestMapping("/audit/auditor")
public class AuditAuditorController extends AbstractController {

	//Services -------------------------------------------------------------
	
	@Autowired
	private AuditService auditService;
	
	@Autowired
	private AuditorService auditorService;
	
	//Constructors ---------------------------------------------------------
	
	public AuditAuditorController(){
		super();
	}
	
	//Listing --------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Audit> audits;
		
		audits = auditService.findAll();
		
		result = new ModelAndView("audit/auditor/list");
		result.addObject("auditsAuditor", audits);
		result.addObject("requestURI", "audit/auditor/list.do");
		
		return result;
	}
	
}
