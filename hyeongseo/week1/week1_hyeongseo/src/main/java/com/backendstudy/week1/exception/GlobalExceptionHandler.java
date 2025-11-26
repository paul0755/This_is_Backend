package com.backendstudy.week1.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

// Validation 실패를 처리하기 위한 글로벌 예외 처리 코드

@Slf4j
@RestControllerAdvice // 모든 Controller에서 발생하는 예외를 전역적으로 처리함
public class GlobalExceptionHandler {

    // Validation (@Valid) 오류 처리
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException e) {
        List<ApiError> errors = e.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> ApiError.builder()
                        .field(fieldError.getField())
                        .rejectedValue(String.valueOf(fieldError.getRejectedValue()))
                        .reason(fieldError.getDefaultMessage()) // getDefaultMessage() : MemberResDTO의 필드 값의 어노테이션에 설정한 메시지 출력
                        .build())
                .toList();

        return ResponseEntity.badRequest()
                .body(ApiResponse.fail(
                        HttpStatus.BAD_REQUEST.value(),
                        "유효성 검증 실패",
                        errors
                ));
    }

    // 회원 조회 실패 예외 처리
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<?> handleMemberNotFound(MemberNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.fail(
                        HttpStatus.NOT_FOUND.value(),
                        e.getMessage(),
                        null
                ));
    }

    // 이메일 중복 회원 가입 예외 처리
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<?> handleDuplicateEmail(DuplicateEmailException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.fail(
                        HttpStatus.BAD_REQUEST.value(),
                        e.getMessage(),
                        null
                ));
    }

    // URL / 쿼리 파라미터 타입 불일치 처리
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        String requiredType = e.getRequiredType() != null ? e.getRequiredType().getSimpleName() : "알 수 없음";
        String message = String.format("'%s' 파라미터 값이 유효하지 않습니다. (요구 타입: %s)",
                e.getName(),
                requiredType);

        return ResponseEntity.badRequest()
                .body(ApiResponse.fail(
                        HttpStatus.BAD_REQUEST.value(),
                        message,
                        null
                ));
    }

    // JSON 요청 본문 파싱 오류 처리
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleTypeMismatch(HttpMessageNotReadableException e) {

        return ResponseEntity.badRequest()
                .body(ApiResponse.fail(
                        HttpStatus.BAD_REQUEST.value(),
                        "요청 본문 (JSON) 형식이 올바르지 않거나 데이터 타입이 맞지 않습니다.",
                        null
                ));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleServerException(Exception e) {
        log.error("서버 내부 오류 발생", e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.fail(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "서버 내부 오류가 발생했습니다.",
                        null
                ));
    }


}
