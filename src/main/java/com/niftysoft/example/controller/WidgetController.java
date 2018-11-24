package com.niftysoft.example.controller;

import com.niftysoft.example.model.Widget;
import com.niftysoft.example.repository.WidgetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

@Controller
@RequestMapping(value = "/widget", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Slf4j
public class WidgetController {

    private final WidgetRepository widgetRepository;

    @Inject
    public WidgetController(WidgetRepository widgetRepository){
        this.widgetRepository = widgetRepository;
    }

    // This controller automagically converts the Widget ID to a Widget object.
    @ResponseBody
    @GetMapping(value = "/{id}")
    Widget getWidget(@PathVariable("id") Widget widget) {
        log.info(widget.toString());
        return widget;
    }

}
