package com.niftysoft.example.repository;

import com.niftysoft.example.model.Widget;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

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

}
