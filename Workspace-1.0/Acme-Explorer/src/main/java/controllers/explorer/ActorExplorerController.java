package controllers.explorer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ExplorerService;
import controllers.AbstractController;
import domain.Explorer;

@Controller
@RequestMapping("/actor/explorer")
public class ActorExplorerController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private ExplorerService explorerService;

	// Constructors ---------------------------------------------------------

	public ActorExplorerController() {
		super();
	}

	// Editing ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Explorer explorer;

		explorer = this.explorerService.findByPrincipal();
		result = this.createEditModelAndView(explorer);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Explorer explorer,
			final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createEditModelAndView(explorer, "actor.params.error");
		else
			try {
				this.explorerService.save(explorer);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(explorer, "actor.commit.error");
			}

		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Explorer explorer) {
		ModelAndView result;

		result = this.createEditModelAndView(explorer, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Explorer explorer,
			final String message) {
		ModelAndView result;
		result = new ModelAndView("actor/edit");
		result.addObject("actor", explorer);
		result.addObject("message", message);
		result.addObject("requestUri", "actor/explorer/edit.do");
		return result;

	}
}
