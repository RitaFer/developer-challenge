package com.diazero.developerchallenge.model.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private String title;
    private Integer status;
    private String message;
}