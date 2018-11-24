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

    @ManyToMany(mappedBy="sprockets")
    private List<Bin> bins = new ArrayList<>();

    protected Sprocket() { }

    public Sprocket(Type type) { this.type = type; }
}
