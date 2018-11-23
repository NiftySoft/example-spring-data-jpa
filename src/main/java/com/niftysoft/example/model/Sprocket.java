package com.niftysoft.example.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
        GIMMEL
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Type type;

    protected Sprocket() { }

    public Sprocket(Type type) { this.type = type; }
}
