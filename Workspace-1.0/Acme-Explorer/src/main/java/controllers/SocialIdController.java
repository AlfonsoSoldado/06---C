package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.SocialIdService;
import domain.Actor;
import domain.Folder;
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
			return result;
		}
}
