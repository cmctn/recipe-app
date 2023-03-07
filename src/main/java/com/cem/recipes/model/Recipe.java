package com.cem.recipes.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString(exclude = "ingredients")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "RECIPES")
public class Recipe {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "NAME")
    String name;

    @Column(name = "VEGETARIAN", columnDefinition = "TINYINT(1)")
    Boolean vegetarian;

    @Column(name = "NUMBER_OF_SERVINGS")
    Integer numberOfServings;

    @Column(name = "INSTRUCTIONS")
    String instructions;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    protected List<Ingredient> ingredients = new ArrayList<>();


    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;

        for(Ingredient ingredient : ingredients) {
            ingredient.setRecipe(this);
        }
    }

    public void addIngredient(Ingredient ingredient) {
        if (null != ingredients) {
            ingredients.add(ingredient);
        }
    }

    public void removeIngredient(Ingredient ingredient) {
        if (null != ingredients) {
            ingredients.remove(ingredient);
        }
    }

}