package com.niftysoft.example.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@EqualsAndHashCode(exclude={"sprockets", "color"})
public class Category {
    public enum Color {
        RED,
        BLUE,
        GREEN
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    /**
     * One half of the JPA needed to setup a many-to-many relationship. The value of mappedBy refers to Sprocket::categories.
     */
    @ManyToMany(mappedBy="categories")
    private Set<Sprocket> sprockets = new HashSet<>();

    private Color color;

    protected Category() { }

    public Category(Color color) {
        this.color = color;
    }
}
