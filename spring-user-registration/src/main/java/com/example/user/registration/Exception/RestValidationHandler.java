package com.example.user.registration.Exception;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import com.example.user.registration.Exception.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.user.registration.Exception.FieldValidationError.MessageType;

@ControllerAdvice
public class RestValidationHandler {
	
	private MessageSource messageSource;
	
	@Autowired
	public RestValidationHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
		System.out.println("Inside RestValidationHandler constructor"+messageSource.toString());
	}
	
    @ExceptionHandler({UserNotFoundException.class})
//    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
	
	//method to handle validation error
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<FieldValidationErrorDetails> handleValidationError(
			MethodArgumentNotValidException mNotValidException, HttpServletRequest request){
		FieldValidationErrorDetails fErrorDetails = new FieldValidationErrorDetails();
		
		fErrorDetails.setError_detail("Input Field Validation Failed");
		fErrorDetails.setError_tittle("Field Validation Error");
		fErrorDetails.setError_timestamp(new Date().getTime());
		fErrorDetails.setError_status(HttpStatus.BAD_REQUEST.value());
		fErrorDetails.setError_developer_message(mNotValidException.getClass().getName());
		fErrorDetails.setError_path(request.getRequestURI());
		
		BindingResult result = mNotValidException.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		
		for (FieldError error : fieldErrors) {
			FieldValidationError fError = processFieldError(error);
			List<FieldValidationError> fValidationErrorsList =
			fErrorDetails.getErrors().get(error.getField());
			if (fValidationErrorsList == null) {
				fValidationErrorsList =
						new ArrayList<FieldValidationError>();
			}
			fValidationErrorsList.add(fError);
			fErrorDetails.getErrors().put(
					error.getField(), fValidationErrorsList);
		}
		
		return new ResponseEntity<FieldValidationErrorDetails>(fErrorDetails,HttpStatus.BAD_REQUEST);
	}
	
	// method to process field error
	private FieldValidationError processFieldError(final FieldError error) {
		FieldValidationError fieldValidationError =
		new FieldValidationError();
			if (error != null) {
				Locale currentLocale = LocaleContextHolder.getLocale();
				String msg = messageSource.getMessage(error.getDefaultMessage(), null, currentLocale);
				
				fieldValidationError.setField(error.getField());
				fieldValidationError.setType(MessageType.ERROR);
				fieldValidationError.setMessage(msg);
			}
		return fieldValidationError;
	}
}
