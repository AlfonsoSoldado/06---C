package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import domain.Note;

@Controller
@RequestMapping("/note")
public class NoteController extends AbstractController {
	//Services -------------------------------------------------------------
	
	@Autowired
	private ActorService actorService;
	
	//Constructors ---------------------------------------------------------
	
	public NoteController(){
		super();
	}
	
	//Listing --------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int tripId){
		ModelAndView result;
		Collection<Note> notes;
		
		notes = actorService.findNotesByTrip(tripId);
		
		result = new ModelAndView("note/list");
		result.addObject("note", notes);
		result.addObject("requestURI", "note/list.do");
		
		return result;
	}
	
}
