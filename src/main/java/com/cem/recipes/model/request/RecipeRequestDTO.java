package com.cem.recipes.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeRequestDTO {

    @NotBlank(message = "Name is mandatory, can't be blank")
    String name;

    @NotNull(message = "Vegetarian is mandatory, can't be null")
    Boolean vegetarian;

    @NotNull(message = "NumberOfServings is mandatory, can't be null")
    Integer numberOfServings;

    @NotBlank(message = "Instructions is mandatory, can't be blank")
    String instructions;

    @NotNull(message = "Ingredients is mandatory, can't be null")
    protected List<IngredientRequestDTO> ingredients = new ArrayList<>();

}
