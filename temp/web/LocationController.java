package com.aureabox.webcore.web;

import java.util.Collection;

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
import com.aureabox.webcore.service.LocationService;

@RooWebScaffold(path = "locations", formBackingObject = Location.class)
@RequestMapping("/locations")
@Controller
public class LocationController extends BaseController {
	/*@Autowired
	private LocationService locationService;

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Location location, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("location", location);
			return "locations/create";
		}

		uiModel.asMap().clear();
		locationService.createLocation(location);
		return "redirect:/locations/" + encodeUrlPathSegment(location.getId().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(Model uiModel) {
		uiModel.addAttribute("location", new Location());
		return "locations/create";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model uiModel) {
		uiModel.addAttribute("location", locationService.findLocation(id));
		uiModel.addAttribute("itemId", id);
		return "locations/show";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			uiModel.addAttribute("locations", locationService.listLocations(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
			float nrOfPages = (float) locationService.countLocations() / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		} else {
			uiModel.addAttribute("locations", locationService.listLocations());
		}

		return "locations/list";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid Location location, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("location", location);
			return "locations/update";
		}

		uiModel.asMap().clear();
		locationService.updateLocation(location);
		return "redirect:/locations/" + encodeUrlPathSegment(location.getId().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model uiModel) {
		uiModel.addAttribute("location", locationService.findLocation(id));
		return "locations/update";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		locationService.deleteLocation(id);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/locations";
	}

	@RequestMapping(params = { "find=ByCode", "form" }, method = RequestMethod.GET)
	public String findLocationsByCodeForm(Model uiModel) {
		return "locations/findLocationsByCode";
	}

	@RequestMapping(params = "find=ByCode", method = RequestMethod.GET)
	public String findLocationsByCode(@RequestParam("code") String code, Model uiModel) {
		uiModel.addAttribute("locations", locationService.findLocationsByCode(code));
		return "locations/list";
	}

	@RequestMapping(params = { "find=ByName", "form" }, method = RequestMethod.GET)
	public String findLocationsByNameForm(Model uiModel) {
		return "locations/findLocationsByName";
	}

	@RequestMapping(params = "find=ByName", method = RequestMethod.GET)
	public String findLocationsByName(@RequestParam("name") String name, Model uiModel) {
		uiModel.addAttribute("locations", locationService.findLocationsByName(name));
		return "locations/list";
	}

	@RequestMapping(params = { "find=ByParent", "form" }, method = RequestMethod.GET)
	public String findLocationsByParentForm(Model uiModel) {
		uiModel.addAttribute("locations", locationService.listLocations());
		return "locations/findLocationsByParent";
	}

	@RequestMapping(params = "find=ByParent", method = RequestMethod.GET)
	public String findLocationsByParent(@RequestParam("parent") Location parent, Model uiModel) {
		uiModel.addAttribute("locations", locationService.findLocationsByParent(parent));
		return "locations/list";
	}

	@ModelAttribute("locations")
	public Collection<Location> populateLocations() {
		return locationService.listLocations();
	}*/

}
