package controllers.manager;

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
		
		// Creation ---------------------------------------------------------------

		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid final Note note,
				final BindingResult binding) {
			ModelAndView res;

			if (binding.hasErrors())
				res = this.createEditModelAndView(note, "note.params.error");
			else
				try {
					this.noteService.save(note);
					res = new ModelAndView("redirect:../manager/list.do");
				} catch (final Throwable oops) {
					System.out.println(oops.getMessage());
					res = this.createEditModelAndView(note, "note.commit.error");
				}

			return res;
		}
		
		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam final int noteId) {
			ModelAndView result;
			Note n;

			n = noteService.findOne(noteId);
			Assert.notNull(n);
			result = this.createEditModelAndView(n);

			return result;
		}
		
		// Ancillary methods --------------------------------------------------

		protected ModelAndView createEditModelAndView(final Note note) {
			ModelAndView result;

			result = this.createEditModelAndView(note, null);

			return result;
		}

		protected ModelAndView createEditModelAndView(final Note note,
				final String message) {
			ModelAndView result;
			result = new ModelAndView("note/edit");
			result.addObject("note", note);
			result.addObject("message", message);
			result.addObject("requestURI", "note/manager/edit.do");
			return result;
		}
}
