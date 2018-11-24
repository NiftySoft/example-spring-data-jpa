package com.niftysoft.example.controller;

import com.niftysoft.example.Application;
import com.niftysoft.example.model.Sprocket;
import com.niftysoft.example.model.Widget;
import com.niftysoft.example.repository.WidgetRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes= Application.class
)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class WidgetControllerIT {

    @Inject
    private MockMvc mockMvc;

    @Inject
    private WidgetRepository widgetRepository;

    @Test
    public void getWidgetTest() throws Exception {
        Widget w = new Widget(Widget.Type.ALPHA);

        List<Sprocket> sprockets = new ArrayList<>();
        Sprocket s1 = new Sprocket(Sprocket.Type.ALEPH);
        Sprocket s2 = new Sprocket(Sprocket.Type.BET);
        sprockets.add(s1);
        sprockets.add(s2);

        w.setSprockets(sprockets);

        widgetRepository.save(w);

        mockMvc.perform(get("/widget/" + w.getId())
                            .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getNonExistentWidgetTest() throws Exception {

        mockMvc.perform(get("/widget/1")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
