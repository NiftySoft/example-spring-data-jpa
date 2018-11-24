package com.niftysoft.example.controller;

import com.niftysoft.example.controller.exceptions.EntityNotFoundException;
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
import java.util.Optional;

/**
 * WidgetController makes Widget entities available via a simple and easy REST API.
 */
@Controller
@RequestMapping(value = "/widget", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Slf4j
public class WidgetController {

    private final WidgetRepository widgetRepository;

    // N.B. No-one said it was a great idea to connect the Controllers directly to the Persistence Layer...
    // OTOH, that is all that is needed for very simple controllers like this one. KISS.
    @Inject
    public WidgetController(WidgetRepository widgetRepository){
        this.widgetRepository = widgetRepository;
    }

    /**
     * This controller magically converts the Widget ID to a Widget object using an auto-configured DomainClassConverter
     * https://docs.spring.io/spring-data/jpa/docs/current-SNAPSHOT/reference/html/#core.web.basic
     *
     * The Widget entity in the return value is automatically converted to JSON via the ObjectMapper bean autoconfigured
     * as part of spring-boot-starter-json
     * https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-json-jackson
     * https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-customize-the-jackson-objectmapper
     * @param widget
     * @return
     * @throws EntityNotFoundException
     */
    @ResponseBody
    @GetMapping(value = "/{id}")
    Widget getWidget(@PathVariable("id") Optional<Widget> widget) throws EntityNotFoundException {
        if(!widget.isPresent()) {
            throw new EntityNotFoundException("Could not find Widget with given id");
        }
        log.info("Found widget: ", widget.toString());

        return widget.get();
    }

}
