package com.niftysoft.example.repository;

import com.niftysoft.example.model.Sprocket;
import com.niftysoft.example.model.Widget;
import com.niftysoft.example.model.views.WidgetViews;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WidgetRespositoryTest {
    @Inject
    WidgetRepository underTest;

    @Inject
    WidgetRepository.SprocketsOnly underTestSprocketsOnly;

    @Test
    public void testSave() {
        Widget w = new Widget(Widget.Type.BETA);

        underTest.save(w);

        assertThat(w.getId()).isNotNull();
        assertThat(underTest.existsById(w.getId())).isTrue();
        assertThat(underTest.findById(w.getId())).contains(w);
    }

    @Test
    public void testCascadingSaveWithSprocketsView() {
        Widget w = new Widget(Widget.Type.ALPHA);

        List<Sprocket> sprockets = new ArrayList<>();

        sprockets.add(new Sprocket(Sprocket.Type.ALEPH));
        sprockets.add(new Sprocket(Sprocket.Type.BET));

        w.setSprockets(sprockets);

        underTest.save(w);

        WidgetViews.SprocketsOnly sprocketsFound = underTestSprocketsOnly.findById(w.getId());

        assertThat(sprocketsFound.getSprockets()).containsExactlyElementsOf(sprockets);
    }
}
