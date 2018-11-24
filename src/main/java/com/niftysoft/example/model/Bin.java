package com.niftysoft.example.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@EqualsAndHashCode
public class Bin {
    public enum Color {
        RED,
        BLUE,
        GREEN
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToMany
    @JoinTable(
            name="SprocketBin",
            joinColumns= {@JoinColumn(name="bin_id")},
            inverseJoinColumns = {@JoinColumn(name="sprocket_id")}
    )
    private Set<Sprocket> sprockets = new HashSet<>();

    private Color color;

    protected Bin() { }

    public Bin(Color color) {
        this.color = color;
    }
}
