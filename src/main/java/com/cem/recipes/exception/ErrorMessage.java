package com.cem.recipes.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import java.util.Date;


@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorMessage {

    int statusCode;
    Date timestamp;
    String message;
    String description;

    public ErrorMessage(int statusCode, Date timestamp, String message, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }

}