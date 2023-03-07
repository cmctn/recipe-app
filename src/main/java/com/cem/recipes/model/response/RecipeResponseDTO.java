package com.cem.recipes.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeResponseDTO {

    Long id;
    String name;
    Boolean vegetarian;
    Integer numberOfServings;
    String instructions;
    List<IngredientResponseDTO> ingredients;
}