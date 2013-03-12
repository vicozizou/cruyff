package com.aureabox.webcore.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.aureabox.webcore.model.Email;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "emails", formBackingObject = Email.class)
@RequestMapping("/emails")
@Controller
public class EmailController {
}
