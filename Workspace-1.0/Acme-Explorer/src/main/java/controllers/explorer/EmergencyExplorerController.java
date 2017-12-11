package controllers.explorer;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.EmergencyService;
import services.ExplorerService;
import controllers.AbstractController;
import domain.Emergency;
import domain.Explorer;

@Controller
@RequestMapping("/emergency/explorer")
public class EmergencyExplorerController extends AbstractController{

	// Services -------------------------------------------------------
	
	@Autowired
	private EmergencyService emergencyService;
	
	@Autowired
	private ExplorerService explorerService;
	
	
	// Constructors -------------------------------------------------------
	
	public EmergencyExplorerController(){
		super();
	}
	
	// Listing --------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Emergency> emergencies;
		
		emergencies = emergencyService.findAll();
		
		result = new ModelAndView("emergency/explorer/list");
		result.addObject("emergency",emergencies);
		result.addObject("requestURI", "emergency/explorer/list.do");
		
		return result;
		
	}
	
	// Creation ---------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int emergencyId){
		ModelAndView result;
		Emergency emergency;
		
		emergency = emergencyService.findOne(emergencyId);
		Assert.notNull(emergency);
		result = this.createEditModelAndView(emergency);
		
		return result;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Emergency emergency,
			final BindingResult binding){
		ModelAndView res;
		
		if(binding.hasErrors())
			res = this.createEditModelAndView(emergency, "emergency.commit.error");
		else
			try{
				this.emergencyService.save(emergency);
				res = new ModelAndView("redirect:list.do");
			}catch (final Throwable oops) {
				res = this.createEditModelAndView(emergency, "emergency.commit.error");
			}
		
		return res;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView res;
		Emergency emergency;
		
		emergency = this.emergencyService.create();
		res = this.createEditModelAndView(emergency);
		
		return res;
	}
	
	// Ancillary methods --------------------------------------------------
	
	protected ModelAndView createEditModelAndView(final Emergency emergency) {
		ModelAndView result;

		result = this.createEditModelAndView(emergency, null);

		return result;
	}
	
	protected ModelAndView createEditModelAndView(final Emergency emergency,
			final String message) {
		ModelAndView result;
		final Collection<Explorer> explorer;
		explorer = this.explorerService.findAll();
		result = new ModelAndView("emergency/explorer/edit");
		result.addObject("explorer", explorer);
		result.addObject("emergency", emergency);
		result.addObject("message", message);
		
		return result;
	}
	
	
}
