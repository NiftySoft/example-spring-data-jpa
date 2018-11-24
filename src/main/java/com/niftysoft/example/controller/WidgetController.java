package com.niftysoft.example.controller;

import com.niftysoft.example.model.Widget;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/widget")
public class WidgetController {

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Widget getWidget(@PathVariable("id") Widget widget) {
        return widget;
    }

}
