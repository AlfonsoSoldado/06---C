package controllers;

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

import services.ActorService;
import services.FolderService;
import domain.Actor;
import domain.Folder;

@Controller
@RequestMapping("/folder")
public class FolderController extends AbstractController {

	// Services -------------------------------------------------------

		@Autowired
		private FolderService folderService;

		@Autowired
		private ActorService actorService;

		// Constructors ---------------------------------------------------------

		public FolderController() {
			super();
		}

		// Listing ----------------------------------------------------------

		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list(){
			ModelAndView result;
			Collection<Folder> folders;
			
			Actor actor = actorService.findByPrincipal();
			
			folders = actor.getFolders();
			
			result = new ModelAndView("folder/list");
			result.addObject("folders", folders);
			
			return result;
		}
		
		//list de las subfolders
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
		
		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam final int folderId) {
			ModelAndView result;
			Folder folder;

			folder = folderService.findOne(folderId);
			Assert.notNull(folder);
			result = this.createEditModelAndView(folder);

			return result;
		}

		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid final Folder folder,
				final BindingResult binding) {
			ModelAndView result;
			System.out.println(binding.getFieldError());
			if (binding.hasErrors())
				result = this.createEditModelAndView(folder,
						"folder.params.error");
			else
				try {
					folderService.save(folder);
					result = new ModelAndView("redirect:../folder/list.do");
				} catch (final Throwable oops) {
					System.out.println(oops.getCause());
					System.out.println(oops.getMessage());
					result = this.createEditModelAndView(folder,
							"folder.commit.error");
				}
			return result;
		}
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
		public ModelAndView delete(Folder folder, BindingResult binding) {
			ModelAndView res;

			try {
				this.folderService.delete(folder);
				res = new ModelAndView("redirect:../folder/list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(folder, "folder.commit.error");
			}

			return res;
		}

		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create() {
			final ModelAndView result;
			Folder folder;
			folder = this.folderService.create();
			result = this.createEditModelAndView(folder);
			return result;
		}

		// Ancillary methods --------------------------------------------------

		protected ModelAndView createEditModelAndView(final Folder folder) {
			ModelAndView result;
			result = this.createEditModelAndView(folder, null);
			return result;
		}

		protected ModelAndView createEditModelAndView(final Folder folder,
				final String messageCode) {
			ModelAndView result;
			
			Collection<Folder> folders;
			
			Actor actor = actorService.findByPrincipal();
			
			folders = actor.getFolders();
			
			result = new ModelAndView("folder/edit");
			result.addObject("folder", folder);
			result.addObject("folders", folders);
			result.addObject("message", messageCode);
			return result;
		}
}