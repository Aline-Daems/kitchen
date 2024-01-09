package be.technobel.kitchen.dal.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Ingredients {
    @Id
    private String name;
    private String measure;

    @OneToMany(mappedBy = "ingredients")
    private List<Contains> containsList;


}
