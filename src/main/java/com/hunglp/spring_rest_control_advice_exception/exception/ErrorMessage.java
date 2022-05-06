package com.hunglp.spring_rest_control_advice_exception.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorMessage {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;




}
