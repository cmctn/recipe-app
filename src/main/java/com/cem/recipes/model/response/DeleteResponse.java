package com.cem.recipes.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeleteResponse {

    Long id;
    String message;

}