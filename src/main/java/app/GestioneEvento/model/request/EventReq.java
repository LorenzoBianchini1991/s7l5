package app.GestioneEvento.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventReq {
    @NotBlank(message = "title required")
    private String title;
    @NotBlank(message = "description required")
    private String description;
    @NotNull(message = "date required")
    private LocalDate date;
    @NotBlank(message = "location required")
    private String location;
    @Min(3)
    private Integer nrPlace;
}
