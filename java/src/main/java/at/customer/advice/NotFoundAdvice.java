package at.customer.advice;


import at.customer.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class NotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String customerNotFoundHandler(CustomerNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CustomerDuplicateNameException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String customerNameAlreadyExistsHandler(CustomerDuplicateNameException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CustomerNameNotNullException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String customerNameIsEmpty(CustomerNameNotNullException ex) {
        return ex.getMessage();
    }



}
