package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.FolderService;
import domain.Actor;
import domain.Folder;

@Controller
@RequestMapping("/folder")
public class FolderController extends AbstractController {

	// Services -------------------------------------------------------
	
	@Autowired
	FolderService folderService;
	
	@Autowired
	ActorService actorService;


	// Constructors -----------------------------------------------------------

	public FolderController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Folder> folders;

		result = new ModelAndView("folder/list");

		folders = this.folderService.findFolders();
		result.addObject("folders", folders);
		result.addObject("father", null);

		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, params = "folderId")
	public ModelAndView list(@RequestParam final int folderId) {
		ModelAndView result;
		Folder folder;
		Collection<Folder> folders;
		result = new ModelAndView("folder/list");
		folder = this.folderService.findOne(folderId);
		folders = folder.getFolders();
		result.addObject("folders", folders);
		result.addObject("customFolder", folder);
		return result;
	}

	// Editing -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int folderId) {
		ModelAndView result;
		Folder folder;
		folder = this.folderService.findOne(folderId);
		result = this.createEditModelAndView(folder);
		return result;
	}

	// Saving -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Folder folder, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(folder, "folder.params.error");
		else
			try {
				this.folderService.save(folder);
				result = new ModelAndView("redirect:list.do");

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(folder, "folder.commit.error");
			}
		return result;
	}

	// Deleting ------------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Folder folder, final BindingResult binding) {
		ModelAndView result;

		try {
			this.folderService.delete(folder);
			result = new ModelAndView("redirect:list.do");

		} catch (final Throwable oops) {
			result = this.createEditModelAndView(folder, "folder.commit.error");
		}
		return result;
	}

	// Creating -----------------------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		Folder folder;
		folder = this.folderService.create();
		result = this.createEditModelAndView(folder);
		return result;
	}

	// Ancillary methods ---------------------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Folder folder) {
		ModelAndView result;
		result = this.createEditModelAndView(folder, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Folder folder, final String messageCode) {
		ModelAndView result;
		Actor actor;
		Collection<Folder> folders;
		actor = this.actorService.findByPrincipal();
		result = new ModelAndView("folder/edit");
		folders = actor.getFolders();
		folders.removeAll(folder.getFolders());
		result.addObject("folder", folder);
		result.addObject("folders", folders);
		result.addObject("message", messageCode);
		return result;
	}
}