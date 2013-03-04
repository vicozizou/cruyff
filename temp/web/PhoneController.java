package com.aureabox.webcore.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.aureabox.webcore.model.Phone;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "phones", formBackingObject = Phone.class)
@RequestMapping("/phones")
@Controller
public class PhoneController {
}
