package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

import services.TagService;
import domain.Tag;

@Controller
@RequestMapping("/tag")
public class TagController extends AbstractController {
	// Services -------------------------------------------------------------

	@Autowired
	private TagService tagService;

	// Constructors ---------------------------------------------------------

	public TagController() {
		super();
	}

	// Listing --------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Tag> tags;
		
		tags = tagService.findAll();
		
		result = new ModelAndView("tag/list");
		result.addObject("tags", tags);
		result.addObject("requestURI", "tag/administrator/list.do");
		
		return result;
	}
}
