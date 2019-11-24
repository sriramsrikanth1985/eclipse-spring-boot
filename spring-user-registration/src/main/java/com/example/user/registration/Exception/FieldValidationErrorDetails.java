package com.example.user.registration.Exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * {
    "error_tittle": "Field Validation Error",
    "error_status": 400,
    "error_detail": "Input Field Validation Failed",
    "error_timestamp": 1574556513591,
    "error_path": "/api/user/",
    "error_developer_message": "org.springframework.web.bind.MethodArgumentNotValidException",
    "errors": {
        "fname": [
            {
                "field": "fname",
                "message": "length must be between 2 and 2147483647",
                "type": "ERROR"
            }
        ],
        "uname": [
            {
                "field": "uname",
                "message": "not a well-formed email address",
                "type": "ERROR"
            }
        ]
    }
}
 * @author Sriram
 *
 */

public class FieldValidationErrorDetails {
	private String error_tittle;
	private int error_status;
	private String error_detail;
	private long error_timestamp;
	private String error_path;
	private String error_developer_message;
	private Map<String, List<FieldValidationError>> errors=
			new HashMap<String, List<FieldValidationError>>();
	public String getError_tittle() {
		return error_tittle;
	}
	public void setError_tittle(String error_tittle) {
		this.error_tittle = error_tittle;
	}
	public int getError_status() {
		return error_status;
	}
	public void setError_status(int error_status) {
		this.error_status = error_status;
	}
	public String getError_detail() {
		return error_detail;
	}
	public void setError_detail(String error_detail) {
		this.error_detail = error_detail;
	}
	public long getError_timestamp() {
		return error_timestamp;
	}
	public void setError_timestamp(long error_timestamp) {
		this.error_timestamp = error_timestamp;
	}
	public String getError_path() {
		return error_path;
	}
	public void setError_path(String error_path) {
		this.error_path = error_path;
	}
	public String getError_developer_message() {
		return error_developer_message;
	}
	public void setError_developer_message(String error_developer_message) {
		this.error_developer_message = error_developer_message;
	}
	public Map<String, List<FieldValidationError>> getErrors() {
		return errors;
	}
	public void setErrors(Map<String, List<FieldValidationError>> errors) {
		this.errors = errors;
	}
	
	

}
