package com.cem.recipes.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredientRequestDTO {


    @NotBlank(message = "Name is mandatory, can't be blank")
    String name;

}