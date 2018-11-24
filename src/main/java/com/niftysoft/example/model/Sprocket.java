package com.niftysoft.example.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A Sprocket is an abstract entity which has an id and a type.
 */
@Entity
@Getter @Setter
@EqualsAndHashCode
public class Sprocket {
    public enum Type {
        ALEPH,
        BET,
        GIMEL
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Type type;

    /**
     * One half of the JPA needed to set up a Many-to-Many relationship. Whichever class contains the @JoinTable
     * annotation is said to "own" the relationship. See also Category::sprockets
     */
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "SprocketCategory",
            inverseJoinColumns = {@JoinColumn(name="category_id")},
            joinColumns = {@JoinColumn(name="sprocket_id")}
    )
    private List<Category> categories = new ArrayList<>();

    public void addCategory(Category category) {
        this.categories.add(category);
        category.getSprockets().add(this);
    }

    public void removeCategory(Category category) {
        this.categories.remove(category);
        category.getSprockets().remove(this);
    }

    protected Sprocket() { }

    public Sprocket(Type type) { this.type = type; }
}
