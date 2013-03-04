// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.aureabox.webcore.web;

import com.aureabox.webcore.model.Person;
import com.aureabox.webcore.model.Phone;
import com.aureabox.webcore.model.PhoneType;
import java.io.UnsupportedEncodingException;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Arrays;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect PhoneController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST)
    public String PhoneController.create(@Valid Phone phone, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("phone", phone);
            return "phones/create";
        }
        uiModel.asMap().clear();
        phone.persist();
        return "redirect:/phones/" + encodeUrlPathSegment(phone.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String PhoneController.createForm(Model uiModel) {
        uiModel.addAttribute("phone", new Phone());
        return "phones/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String PhoneController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("phone", Phone.findPhone(id));
        uiModel.addAttribute("itemId", id);
        return "phones/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String PhoneController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            uiModel.addAttribute("phones", Phone.findPhoneEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Phone.countPhones() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("phones", Phone.findAllPhones());
        }
        return "phones/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String PhoneController.update(@Valid Phone phone, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("phone", phone);
            return "phones/update";
        }
        uiModel.asMap().clear();
        phone.merge();
        return "redirect:/phones/" + encodeUrlPathSegment(phone.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String PhoneController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("phone", Phone.findPhone(id));
        return "phones/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String PhoneController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Phone.findPhone(id).remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/phones";
    }
    
    @ModelAttribute("people")
    public Collection<Person> PhoneController.populatePeople() {
        return Person.findAllPeople();
    }
    
    @ModelAttribute("phones")
    public Collection<Phone> PhoneController.populatePhones() {
        return Phone.findAllPhones();
    }
    
    @ModelAttribute("phonetypes")
    public Collection<PhoneType> PhoneController.populatePhoneTypes() {
        return Arrays.asList(PhoneType.class.getEnumConstants());
    }
    
    String PhoneController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        }
        catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
