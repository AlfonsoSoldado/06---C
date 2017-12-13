package controllers.auditor;

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

import controllers.AbstractController;

import services.AuditorService;
import services.NoteService;
import services.TripService;
import domain.Auditor;
import domain.Note;
import domain.Trip;

@Controller
@RequestMapping("/note/auditor")
public class NoteAuditorController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private NoteService noteService;

	@Autowired
	private AuditorService auditorService;

	@Autowired
	private TripService tripService;

	// Constructors ---------------------------------------------------------

	public NoteAuditorController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Note> notes;

		Auditor auditor = auditorService.findByPrincipal();
		int auditorId = auditor.getId();
		notes = noteService.findNotesByAuditor(auditorId);
		

		result = new ModelAndView("note/list");
		result.addObject("note", notes);
		result.addObject("requestURI", "note/auditor/list.do");

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
				res = new ModelAndView("redirect:../auditor/list.do");
			} catch (final Throwable oops) {
				System.out.println(oops.getMessage());
				System.out.println(oops.getCause());
				res = this.createEditModelAndView(note, "note.commit.error");
			}

		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Note note;

		note = this.noteService.create();
		result = this.createEditModelAndView(note);

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
		final Collection<Trip> trip;
		final Collection<Auditor> auditor;
		trip = this.tripService.findAll();
		auditor = this.auditorService.findAll();
		result = new ModelAndView("note/edit");
		result.addObject("trip", trip);
		result.addObject("auditor", auditor);
		result.addObject("note", note);
		result.addObject("message", message);
		return result;
	}

}
