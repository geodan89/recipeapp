package geo.springframework.recipeapp.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob //so as not to worry about limitations on the size of the recipeNotes;
    private String recipeNotes;


}
