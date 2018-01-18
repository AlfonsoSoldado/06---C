/*
 * AbstractController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import services.ConfigurationService;
import domain.Configuration;

@Controller
public class AbstractController {

	@Autowired
	private ConfigurationService configurationService;
	
	// Panic handler ----------------------------------------------------------

	@ExceptionHandler(Throwable.class)
	public ModelAndView panic(final Throwable oops) {
		ModelAndView result;

		result = new ModelAndView("misc/panic");
		result.addObject("name", ClassUtils.getShortName(oops.getClass()));
		result.addObject("exception", oops.getMessage());
		result.addObject("stackTrace", ExceptionUtils.getStackTrace(oops));

		return result;
	}
	
//	public ModelAndView banner(final String res){
//		String banner;
//		ModelAndView result;
//		Configuration configuration;
//		
//		Integer id = configurationService.resId();
//		configuration = configurationService.findOne(id);
//		banner = configuration.getBanner();
//		
//		result = new ModelAndView(res);
//		result.addObject("bannerShowImage", banner);
//		
//		return result;
//	}
	
	@ModelAttribute(value = "bannerShowImage")
	protected String banner(){
		String banner;
		Configuration configuration;
		
		Integer id = configurationService.resId();
		configuration = configurationService.findOne(id);
		banner = configuration.getBanner();
		
		return banner;
	}

}
