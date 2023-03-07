package com.cem.recipes.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredientResponseDTO {

    String name;
}