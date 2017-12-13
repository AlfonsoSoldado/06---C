package controllers.manager;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ManagerService;
import services.NoteService;
import controllers.AbstractController;
import domain.Manager;
import domain.Note;

@Controller
@RequestMapping("/note/manager")
public class NoteManagerController extends AbstractController{

	// Services -------------------------------------------------------------

		@Autowired
		private NoteService noteService;
		
		@Autowired
		private ManagerService managerService;

		// Constructors ---------------------------------------------------------

		public NoteManagerController() {
			super();
		}

		// Listing --------------------------------------------------------------
		
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list() {
			ModelAndView result;
			Collection<Note> notes;

			Manager manager = managerService.findByPrincipal();
			
			notes = noteService.findNotesByManager(manager);
			
			result = new ModelAndView("note/list");
			result.addObject("note", notes);
			result.addObject("requestURI", "note/manager/list.do");

			return result;
		}
}
