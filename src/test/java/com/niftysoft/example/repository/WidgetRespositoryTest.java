package com.niftysoft.example.repository;

import com.niftysoft.example.model.Category;
import com.niftysoft.example.model.Sprocket;
import com.niftysoft.example.model.Widget;
import com.niftysoft.example.model.views.WidgetViews;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WidgetRespositoryTest {
    @Inject
    WidgetRepository underTest;

    @Test
    public void testSave() {
        Widget w = new Widget(Widget.Type.BETA);

        underTest.save(w);

        assertThat(w.getId()).isNotNull();
        assertThat(underTest.existsById(w.getId())).isTrue();
        assertThat(underTest.findById(w.getId())).contains(w);
    }

    @Test
    public void testFindAllSprocketsOnlyById() {
        Widget w = new Widget(Widget.Type.ALPHA);

        List<Sprocket> sprockets = new ArrayList<>();

        sprockets.add(new Sprocket(Sprocket.Type.ALEPH));
        sprockets.add(new Sprocket(Sprocket.Type.BET));

        w.setSprockets(sprockets);

        // This save should cascade, even though Sprockets were not saved individually.
        underTest.save(w);

        WidgetViews.SprocketsOnly sprocketsFound = underTest.findAllSprocketsOnlyById(w.getId()).get();

        assertThat(sprocketsFound.getSprockets()).containsExactlyElementsOf(sprockets);
    }

    @Test
    public void testFindAllBySprocketCategory() {
        Sprocket s1 = new Sprocket(Sprocket.Type.ALEPH),
                 s2 = new Sprocket(Sprocket.Type.BET),
                 s3 = new Sprocket(Sprocket.Type.GIMEL),
                 s4 = new Sprocket(Sprocket.Type.GIMEL),
                 s5 = new Sprocket(Sprocket.Type.GIMEL),
                 s6 = new Sprocket(Sprocket.Type.GIMEL);

        Category c1 = new Category(Category.Color.BLUE),
                 c2 = new Category(Category.Color.RED);

        Widget w1 = new Widget(Widget.Type.ALPHA),
                w2 = new Widget(Widget.Type.BETA),
                w3 = new Widget(Widget.Type.GAMMA),
                w4 = new Widget(Widget.Type.ALPHA);

        s1.addCategory(c1);

        s2.addCategory(c1);
        s2.addCategory(c2);

        w1.getSprockets().add(s1);
        w1.getSprockets().add(s2);

        s3.addCategory(c1);

        w2.getSprockets().add(s3);

        s4.addCategory(c2);

        w3.getSprockets().add(s4);

        s5.addCategory(c1);
        w4.getSprockets().add(s5);

        s6.addCategory(c2);
        w4.getSprockets().add(s6);

        underTest.save(w1);
        underTest.save(w2);
        underTest.save(w3);
        underTest.save(w4);

        List<Widget> c1Widgets = underTest.findAllBySprocketCategory(c1);
        List<Widget> c2Widgets = underTest.findAllBySprocketCategory(c2);

        assertThat(c1Widgets).containsExactlyInAnyOrder(w1, w2, w4);
        assertThat(c2Widgets).containsExactlyInAnyOrder(w1, w3, w4);

        c1Widgets = underTest.findDistinctBySprocketsCategories(c1);
        c2Widgets = underTest.findDistinctBySprocketsCategories(c2);

        assertThat(c1Widgets).containsExactlyInAnyOrder(w1, w2, w4);
        assertThat(c2Widgets).containsExactlyInAnyOrder(w1, w3, w4);
    }

    @Test
    public void testFindAllStreamBy() {
        Widget[] widgets = new Widget[1000];

        for (int i = 0; i < widgets.length; ++i) {
            widgets[i] = new Widget(Widget.Type.ALPHA);
        }

        underTest.saveAll(Arrays.asList(widgets));

        // Don't do this, when there is a Count method.
        long count = underTest.findAllStreamBy().count();

        assertThat(count).isEqualTo(1000L);
    }

    @Test
    public void testCountAsyncBy() throws Exception {
        Widget[] widgets = new Widget[1000];

        for (int i = 0; i < widgets.length; ++i) {
            widgets[i] = new Widget(Widget.Type.ALPHA);
        }

        underTest.saveAll(Arrays.asList(widgets));

        // Don't do this, when there is a Count method.
        Future<Long> future = underTest.countAsyncBy();

        assertThat(future.get()).isEqualTo(1000L);
        assertThat(future).isDone()
                .isNotCancelled();
    }

    private void persistScenario1() {
        Sprocket s1 = new Sprocket(Sprocket.Type.ALEPH),
                s2 = new Sprocket(Sprocket.Type.BET),
                s3 = new Sprocket(Sprocket.Type.GIMEL),
                s4 = new Sprocket(Sprocket.Type.GIMEL),
                s5 = new Sprocket(Sprocket.Type.GIMEL),
                s6 = new Sprocket(Sprocket.Type.GIMEL);

        Category c1 = new Category(Category.Color.BLUE),
                c2 = new Category(Category.Color.RED);

        Widget w1 = new Widget(Widget.Type.ALPHA),
                w2 = new Widget(Widget.Type.BETA),
                w3 = new Widget(Widget.Type.GAMMA),
                w4 = new Widget(Widget.Type.ALPHA);

        s1.addCategory(c1);

        s2.addCategory(c1);
        s2.addCategory(c2);

        w1.getSprockets().add(s1);
        w1.getSprockets().add(s2);

        s3.addCategory(c1);

        w2.getSprockets().add(s3);

        s4.addCategory(c2);

        w3.getSprockets().add(s4);

        s5.addCategory(c1);
        w4.getSprockets().add(s5);

        s6.addCategory(c2);
        w4.getSprockets().add(s6);

        underTest.save(w1);
        underTest.save(w2);
        underTest.save(w3);
        underTest.save(w4);
    }
}
