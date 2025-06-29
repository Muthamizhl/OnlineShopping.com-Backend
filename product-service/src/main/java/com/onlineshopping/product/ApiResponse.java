


package com.onlineshopping.product;

public class ApiResponse<T> {
    private String traceId;
    private boolean success;
    private T data;
    private ApiError error;

    public ApiResponse() {
    }

    public ApiResponse(String traceId, boolean success, T data, ApiError error) {
        this.traceId = traceId;
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ApiError getError() {
        return error;
    }

    public void setError(ApiError error) {
        this.error = error;
    }
}
