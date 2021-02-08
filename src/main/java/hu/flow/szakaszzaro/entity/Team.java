package hu.flow.szakaszzaro.entity;

import jdk.jshell.Snippet;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Team {
    String id;
    String name;
    Universe universe;
    Kind kind;
}
