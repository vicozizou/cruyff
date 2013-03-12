package com.aureabox.webcore.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aureabox.webcore.model.Location;
import com.aureabox.webcore.model.Nationality;
import com.aureabox.webcore.service.LocationService;

@RooWebScaffold(path = "nationalities", formBackingObject = Nationality.class)
@RequestMapping("/nationalities")
@Controller
public class NationalityController extends BaseController {
	/*@Autowired
	LocationService locationService;

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Nationality nationality, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("nationality", nationality);
			return "nationalities/create";
		}

		uiModel.asMap().clear();
		locationService.createNationality(nationality);
		return "redirect:/nationalities/" + encodeUrlPathSegment(nationality.getId().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(Model uiModel) {
		uiModel.addAttribute("nationality", new Nationality());
		List<String[]> dependencies = new ArrayList<String[]>();

		if (Location.countLocations() == 0) {
			dependencies.add(new String[] { "location", "locations" });
		}

		uiModel.addAttribute("dependencies", dependencies);
		return "nationalities/create";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model uiModel) {
		uiModel.addAttribute("nationality", locationService.findNationality(id));
		uiModel.addAttribute("itemId", id);
		return "nationalities/show";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			uiModel.addAttribute("nationalities", locationService.listNationalities(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
			float nrOfPages = (float) locationService.countNationalities() / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		} else {
			uiModel.addAttribute("nationalities", locationService.listNationalities());
		}

		return "nationalities/list";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid Nationality nationality, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("nationality", nationality);
			return "nationalities/update";
		}

		uiModel.asMap().clear();
		nationality.merge();
		return "redirect:/nationalities/" + encodeUrlPathSegment(nationality.getId().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model uiModel) {
		uiModel.addAttribute("nationality", locationService.findNationality(id));
		return "nationalities/update";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		locationService.findNationality(id).remove();
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/nationalities";
	}

	@RequestMapping(params = { "find=ByName", "form" }, method = RequestMethod.GET)
	public String findNationalitiesByNameForm(Model uiModel) {
		return "nationalities/findNationalitiesByName";
	}

	@RequestMapping(params = "find=ByName", method = RequestMethod.GET)
	public String findNationalitiesByName(@RequestParam("name") String name, Model uiModel) {
		uiModel.addAttribute("nationalities", locationService.findNationalitiesByName(name));
		return "nationalities/list";
	}

	@ModelAttribute("locations")
	public Collection<Location> populateLocations() {
		return locationService.listLocations();
	}

	@ModelAttribute("nationalities")
	public java.util.Collection<Nationality> populateNationalities() {
		return locationService.listNationalities();
	}*/
}
