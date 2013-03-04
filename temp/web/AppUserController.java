package com.aureabox.webcore.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.aureabox.webcore.model.AppUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "appusers", formBackingObject = AppUser.class)
@RequestMapping("/appusers")
@Controller
public class AppUserController {
}
