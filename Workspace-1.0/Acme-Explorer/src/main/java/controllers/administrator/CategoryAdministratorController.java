package controllers.administrator;

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

import services.CategoryService;
import controllers.AbstractController;
import domain.Category;
import domain.Trip;

@Controller
@RequestMapping("/category/administrator")
public class CategoryAdministratorController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private CategoryService categoryService;

	// Constructors ---------------------------------------------------------

	public CategoryAdministratorController() {
		super();
	}

	// Editing --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int categoryId) {
		ModelAndView result;
		Category category;

		category = categoryService.findOne(categoryId);
		Assert.notNull(category);
		result = this.createEditModelAndView(category);

		return result;
	}
	
	// Saving --------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Category category, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()){
			System.out.println("Binding");
			res = this.createEditModelAndView(category, "category.params.error");
		}
		else
			try {
				System.out.println("Try");
				this.categoryService.save(category);
				res = new ModelAndView("redirect:/category/list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(category, "category.commit.error");
			}

		return res;
	}
	
	// Deleting --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Category category,
			final BindingResult binding) {
		ModelAndView res;

		try {
			this.categoryService.delete(category);
			res = new ModelAndView("redirect:/category/list.do");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(category, "category.commit.error");
		}

		return res;
	}

	// Creating ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Category category;

		category = this.categoryService.create();
		result = this.createEditModelAndView(category);

		return result;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Category category) {
		ModelAndView result;

		result = this.createEditModelAndView(category, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Category category,
			final String message) {
		ModelAndView result;
		Collection<Category> categories;
		Collection<Trip> trips;
		//Category categoryChildren;
		
		trips = category.getTrip();
		//categoryChildren = this.categoryService.getCategoryRoot();
		categories = this.categoryService.findAll();
		
		result = new ModelAndView("category/edit");
		result.addObject("trips", trips);
		//result.addObject("categoryChildren", categoryChildren);
		result.addObject("category", category);
		result.addObject("categories", categories);
		result.addObject("message", message);

		return result;

	}
}
