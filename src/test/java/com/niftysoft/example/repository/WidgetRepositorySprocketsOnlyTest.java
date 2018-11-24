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

/**
 * Tests WidgetRepository.SprocketsOnly
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class WidgetRepositorySprocketsOnlyTest {

    @Inject
    WidgetRepository widgetRepo;

    @Inject
    WidgetRepository.SprocketsOnly underTest;

    @Test
    public void testCascadingSaveWithSprocketsView() {
        Widget w = new Widget(Widget.Type.ALPHA);

        List<Sprocket> sprockets = new ArrayList<>();

        sprockets.add(new Sprocket(Sprocket.Type.ALEPH));
        sprockets.add(new Sprocket(Sprocket.Type.BET));

        w.setSprockets(sprockets);

        // This save should cascade, even though Sprockets were not saved individually.
        widgetRepo.save(w);

        WidgetViews.SprocketsOnly sprocketsFound = underTest.findById(w.getId()).get();

        assertThat(sprocketsFound.getSprockets()).containsExactlyElementsOf(sprockets);
    }
}
