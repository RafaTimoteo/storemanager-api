package br.com.storemanager.storemanagerapi.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler implements AuthenticationFailureHandler {

    @ExceptionHandler(UsernameExistsException.class)
    ResponseEntity<Object> usernameExistsException(UsernameExistsException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CriptoExistsException.class)
    ResponseEntity<Object> criptoExsistsException(CriptoExistsException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DeleteExistsException.class)
    ResponseEntity<Object> deleteExistsException(DeleteExistsException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(EmailExistsException.class)
    ResponseEntity<Object> emailExistException(EmailExistsException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(LoginExistsException.class)
    ResponseEntity<Object> loginExistsException(LoginExistsException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(IdNullExistsException.class)
    ResponseEntity<Object> idNullExistsException(IdNullExistsException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
            Integer status = HttpStatus.FORBIDDEN.value();
            response.setStatus(status);
            response.setContentType("application/json");
            ErrorDetails errorDetails = new ErrorDetails(new Date(), "Email ou senha invalidos!", Integer.toString(status));
            response.getWriter().append(errorDetails.toJson());

            throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationFailure'");
    }

}
