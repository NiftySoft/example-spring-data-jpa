package com.niftysoft.example.model.views;

import com.niftysoft.example.model.Sprocket;

import java.util.List;

/**
 * WidgetViews provides a common namespace for interfaces used as separate views of a Widget. Spring Data JPA *should*
 * only query the data required to "fill out" the interface (otherwise, why is this a feature?)
 */
public abstract class WidgetViews {
    /**
     * IdOnly is a view interface.
     */
    public interface IdOnly {
        Long getId();
    }

    /**
     * SprocketsOnly is a view interface for
     */
    public interface SprocketsOnly {
        List<Sprocket> getSprockets();
    }
}
