package controllers.auditor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.FolderService;
import controllers.AbstractController;
import domain.Actor;
import domain.Folder;
import domain.Message;

@Controller
@RequestMapping("/folder/auditor")
public class FolderAuditorController extends AbstractController {
	
	// Services -------------------------------------------------------------

	@Autowired
	private FolderService folderService;

	// Constructors ---------------------------------------------------------

	public FolderAuditorController() {
		super();
	}
	
	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Folder> folders;

		folders = this.folderService.findFolders();
		result = new ModelAndView("folder/list");

		result.addObject("folders", folders);
		//result.addObject("father", null);
		result.addObject("requestURI", "folder/auditor/list.do");

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
			Collection<Message> m;
			m = folder.getMessages();
			result = new ModelAndView("folder/edit");
			result.addObject("folder", folder);
			result.addObject("folder", folder);
			result.addObject("message", m);
			result.addObject("messageCode", messageCode);
			result.addObject("requestURI", "folder/auditor/list.do");
			
//			ModelAndView result;
//			Actor actor;
//			Collection<Folder> folders;
//			actor = this.actorService.findByPrincipal();
//			result = new ModelAndView("folder/edit");
//			folders = actor.getFolders();
//			folders.removeAll(folder.getFolders());
//			result.addObject("folder", folder);
//			result.addObject("folders", folders);
//			result.addObject("message", messageCode);
			return result;
		}
	
	
	
}
