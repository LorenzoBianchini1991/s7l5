package app.GestioneEvento.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorMessage {
    String message;
    LocalDateTime date;
}
