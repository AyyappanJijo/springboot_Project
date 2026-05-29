package com.infy.infyinterns.utility;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

/**
 * Standard error response body returned by ExceptionControllerAdvice.
 *
 * Example JSON:
 * {
 *   "errorCode": 400,
 *   "errorMessage": "Mentor details not found",
 *   "timestamp": "2024-06-15 10:35:22"
 * }
 */
public class ErrorInfo {

    private Integer errorCode;
    private String  errorMessage;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    public ErrorInfo() {
        this.timestamp = LocalDateTime.now();
    }

    public Integer getErrorCode() { return errorCode; }
    public void setErrorCode(Integer errorCode) { this.errorCode = errorCode; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}