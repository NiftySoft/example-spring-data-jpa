package com.niftysoft.example.model;

import com.niftysoft.example.model.views.WidgetViews;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Widget is an abstract aggregate which consists of an id, a list of Sprocket entities, and a Type.
 */
@Entity
@Getter @Setter
@EqualsAndHashCode
public class Widget implements WidgetViews.SprocketsOnly, WidgetViews.IdOnly {

    public enum Type {
        ALPHA,
        BETA,
        GAMMA
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Type type;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="widgetId")
    private List<Sprocket> sprockets = new ArrayList<>();

    protected Widget() { }

    public Widget(Type type) {
        this.type = type;
    }
}
