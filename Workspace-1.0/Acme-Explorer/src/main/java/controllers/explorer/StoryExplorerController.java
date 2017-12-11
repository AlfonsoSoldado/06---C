package controllers.explorer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ExplorerService;
import services.StoryService;
import controllers.AbstractController;
import domain.Explorer;
import domain.Story;

@Controller
@RequestMapping("/story/explorer")
public class StoryExplorerController extends AbstractController{

	// Services -------------------------------------------------------
	
		@Autowired
		private StoryService storyService;
		
		@Autowired
		private ExplorerService explorerService;
		
		
	// Constructors -------------------------------------------------------
		
		public StoryExplorerController(){
			super();
		}
		
	// Listing --------------------------------------------------------------
		
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list(){
			ModelAndView result;
			Collection<Story> stories;
			
			stories = storyService.findAll();
			
			result = new ModelAndView("story/explorer/list");
			result.addObject("story",stories);
			result.addObject("requestURI", "story/explorer/list.do");
			
			return result;
			
		}
		
		
		
	// Ancillary methods --------------------------------------------------
		
		protected ModelAndView createEditModelAndView(final Story story) {
			ModelAndView result;

			result = this.createEditModelAndView(story, null);

			return result;
		}
		
		protected ModelAndView createEditModelAndView(final Story story,
				final String message) {
			ModelAndView result;
			final Collection<Explorer> explorer;
			explorer = this.explorerService.findAll();
			result = new ModelAndView("story/explorer/edit");
			result.addObject("explorer", explorer);
			result.addObject("story", story);
			result.addObject("message", message);
			
			return result;
		}
	
}
