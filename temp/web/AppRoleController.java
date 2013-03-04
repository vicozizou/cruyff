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

import com.aureabox.webcore.model.AppRole;
import com.aureabox.webcore.service.UserService;

@RooWebScaffold(path = "approles", formBackingObject = AppRole.class)
@RequestMapping("/approles")
@Controller
public class AppRoleController extends BaseController {
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid AppRole appRole, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("appRole", appRole);
			return "approles/create";
		}

		uiModel.asMap().clear();
		//userService.createAppRole(appRole);
		return "redirect:/approles/" + encodeUrlPathSegment(appRole.getId().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(Model uiModel) {
		uiModel.addAttribute("appRole", new AppRole());
		return "approles/create";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model uiModel) {
		//uiModel.addAttribute("approle", userService.findAppRole(id));
		uiModel.addAttribute("itemId", id);
		return "approles/show";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			//uiModel.addAttribute("approles", userService.listAppRoles(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
			//float nrOfPages = (float) userService.countAppRoles() / sizeNo;
			//uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		} else {
			//uiModel.addAttribute("approles", userService.listAppRoles());
		}
		return "approles/list";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid AppRole appRole, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("appRole", appRole);
			return "approles/update";
		}
		uiModel.asMap().clear();
		appRole.merge();
		return "redirect:/approles/" + encodeUrlPathSegment(appRole.getId().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model uiModel) {
		uiModel.addAttribute("appRole", null);//userService.findAppRole(id));
		return "approles/update";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		//userService.findAppRole(id).remove();
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/approles";
	}

	@RequestMapping(params = { "find=ByName", "form" }, method = RequestMethod.GET)
	public String findAppRolesByNameForm(Model uiModel) {
		return "approles/findAppRolesByName";
	}

	@RequestMapping(params = "find=ByName", method = RequestMethod.GET)
	public String findAppRolesByName(@RequestParam("name") String name, Model uiModel) {
		//uiModel.addAttribute("approles", userService.findAppRolesByName(name));
		return "approles/list";
	}

	@ModelAttribute("approles")
	public Collection<AppRole> populateAppRoles() {
		return null;//userService.listAppRoles();
	}
}
