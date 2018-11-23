package com.niftysoft.example.model.views;

import com.niftysoft.example.model.Sprocket;

import java.util.List;

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
