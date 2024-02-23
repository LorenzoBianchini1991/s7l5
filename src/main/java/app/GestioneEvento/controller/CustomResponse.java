package app.GestioneEvento.controller;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Data
public class CustomResponse {
    private String message;
    private LocalDateTime dateTime;
    private Object response;

    public CustomResponse(String message, Object response) {
        this.message = message;
        this.dateTime = LocalDateTime.now();
        this.response = response;
    }
    public CustomResponse(String message) {
        this.message = message;
        this.dateTime = LocalDateTime.now();
        this.response = null;
    }

    public static ResponseEntity<CustomResponse> emptyResponse(String message, HttpStatus httpStatus) {
        CustomResponse customResponse = new CustomResponse(message);
        return new ResponseEntity<>(customResponse, httpStatus);
    }

    public static ResponseEntity<CustomResponse> success (String message, Object obj, HttpStatus httpStatus){
        CustomResponse customResponse = new CustomResponse(message, obj);
        return new ResponseEntity<>(customResponse, httpStatus);
    }

    public static ResponseEntity<CustomResponse> failure (String message,HttpStatus httpStatus){
        CustomResponse customResponse = new CustomResponse(message);
        return new ResponseEntity<>(customResponse, httpStatus);
    }

    public static ResponseEntity<CustomResponse> failure (HttpStatus httpStatus){
        CustomResponse customResponse = new CustomResponse(httpStatus.toString());
        return new ResponseEntity<>(customResponse,httpStatus);
    }

}
