package com.aureabox.webcore.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.aureabox.webcore.model.Place;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "places", formBackingObject = Place.class)
@RequestMapping("/places")
@Controller
public class PlaceController {
}
