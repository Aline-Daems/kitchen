package be.technobel.kitchen.dal.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long recetteId;

    private String instructions;

    @ManyToOne
    @JoinColumn(name = "authorId")
    private Author author;

    @OneToMany(mappedBy = "recipe")
    private List<Contains> containsList;



    public Recipe(Long recetteId, String instructions, Author author) {
    }
    public Recipe(){

    }
}


