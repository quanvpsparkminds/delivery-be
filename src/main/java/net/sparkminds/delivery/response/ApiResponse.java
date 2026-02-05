package net.sparkminds.delivery.response;


import lombok.Data;

@Data
public class ApiResponse<T> {
    private String code;
    private String message;
    private T data;
    private Object errors;
    public ApiResponse(String code, String message, T data, Object errors) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.errors = errors;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("SUCCESS", "Request successful", data, null);
    }

    public static ApiResponse<?> error(String code, String message, Object errors) {
        return new ApiResponse<>(code, message, null, errors);
    }
}
