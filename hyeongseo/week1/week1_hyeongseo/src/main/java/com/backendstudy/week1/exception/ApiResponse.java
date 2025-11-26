package com.backendstudy.week1.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

// 통일된 API Response Wrapper Class

@JsonInclude(JsonInclude.Include.NON_NULL) // ★ null 필드 자동 제외
@JsonPropertyOrder({ "status", "success", "message", "errors", "data" }) // 출력 순서 명시
@Getter
@Builder
public class ApiResponse<T> {
    private int status; // 상태 코드
    private boolean success; // 성공 여부
    private String message; // 메세지
    private T data; // 성공 시 데이터
    private List<ApiError> errors; // 실패 시 오류 리스트

    // 성공했을 때
    public static <T> ApiResponse<T> success(int status, String message, T data) {
        return ApiResponse.<T>builder()
                .status(status)
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    // 실패했을 때
    public static <T> ApiResponse<T> fail(int status, String message, List<ApiError> errors) {
        return ApiResponse.<T>builder()
                .status(status)
                .success(false)
                .message(message)
                .errors(errors)
                .build();
    }
}
