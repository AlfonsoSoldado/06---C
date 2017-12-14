package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.SocialIdService;
import domain.Actor;
import domain.SocialId;

@Controller
@RequestMapping("/socialId")
public class SocialIdController extends AbstractController{

	// Services -------------------------------------------------------
	
		@Autowired
		SocialIdService socialIdService;
		
		@Autowired
		ActorService actorService;


	// Constructors -----------------------------------------------------------

		public SocialIdController() {
			super();
		}
		
	//Listing --------------------------------------------------------------
		
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list(){
			ModelAndView res;
			Collection<SocialId> socialId;
			
			socialId =socialIdService.findAll();
			
			res = new ModelAndView("socialId/list");
			res.addObject("socialId", socialId);
			res.addObject("requestURI", "socialId/list.do");
			
			return res;
		}
		
	// Editing ---------------------------------------------------------------
		
		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam final int socialIdId) {
			ModelAndView result;
			SocialId socialId;

			socialId = socialIdService.findOne(socialIdId);
			Assert.notNull(socialId);
			result = this.createEditModelAndView(socialId);

			return result;
		}
		
		
	// Ancillary methods ---------------------------------------------------------------------

		protected ModelAndView createEditModelAndView(final SocialId socialId) {
			ModelAndView result;
			result = this.createEditModelAndView(socialId, null);
			return result;
		}

		protected ModelAndView createEditModelAndView(final SocialId socialId, final String messageCode) {
			ModelAndView result;
			Actor actor;
			Collection<SocialId> socialIds;
			actor = this.actorService.findByPrincipal();
			result = new ModelAndView("socialId/edit");
			socialIds = actor.getSocialId();
			result.addObject("socialId", socialId);
			result.addObject("socialIds", socialIds);
			result.addObject("message", messageCode);
			result.addObject("actor", actor);
			return result;
		}
}
