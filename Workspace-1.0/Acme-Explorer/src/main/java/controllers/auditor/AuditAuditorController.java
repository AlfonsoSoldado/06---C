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

import services.AuditService;
import services.AuditorService;
import services.TripService;
import controllers.AbstractController;
import domain.Audit;
import domain.Auditor;
import domain.Trip;

@Controller
@RequestMapping("/audit/auditor")
public class AuditAuditorController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private AuditService auditService;

	@Autowired
	private AuditorService auditorService;

	@Autowired
	private TripService tripService;

	// Constructors ---------------------------------------------------------

	public AuditAuditorController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Audit> audits;

		audits = auditService.findAll();

		result = new ModelAndView("audit/auditor/list");
		result.addObject("auditsAuditor", audits);
		result.addObject("requestURI", "audit/auditor/list.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int auditId) {
		ModelAndView result;
		Audit audit;

		audit = auditService.findOne(auditId);
		Assert.notNull(audit);
		result = this.createEditModelAndView(audit);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Audit audit,
			final BindingResult binding) {
		ModelAndView res;
		System.out.println(binding.getFieldError());
		if (binding.hasErrors())
			res = this.createEditModelAndView(audit, "audit.params.error");
		else
			try {
				this.auditService.save(audit);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(audit, "audit.commit.error");
			}

		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Audit audit;

		audit = this.auditService.create();
		result = this.createEditModelAndView(audit);

		return result;
	}
	
	// Deleting -------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Audit audit, BindingResult binding) {
		ModelAndView res;

		try {
			this.auditService.delete(audit);
			res = new ModelAndView("redirect:../../audit/auditor/list.do");
		} catch (Throwable oops) {
			res = createEditModelAndView(audit, "audit.commit.error");
		}

		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Audit audit) {
		ModelAndView result;

		result = this.createEditModelAndView(audit, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Audit audit,
			final String message) {
		ModelAndView result;
		final Collection<Trip> trip;
		final Collection<Auditor> auditor;
		trip = this.tripService.findAll();
		auditor = this.auditorService.findAll();
		result = new ModelAndView("audit/auditor/edit");
		result.addObject("trip", trip);
		result.addObject("auditor", auditor);
		result.addObject("audit", audit);
		result.addObject("message", message);
		return result;
	}
}
