package com.example.study.global.apiPayload.code;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ErrorReasonDTO {

    private HttpStatus httpStatus;

    private final boolean isSuccess;
    private final String coode;
    private final String message;

    public boolean getIsSuccess(){return isSuccess;}
}
