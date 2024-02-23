package app.GestioneEvento.controller;
import app.GestioneEvento.exception.NotFoundException;
import app.GestioneEvento.model.entities.Event;
import app.GestioneEvento.model.request.EventReq;
import app.GestioneEvento.service.EventSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventCtrl {
    @Autowired
    EventSvc eventSvc;

    @PostMapping("/event")
    public ResponseEntity<CustomResponse> saveEvent (@RequestBody @Validated EventReq eventReq, BindingResult result){
        if (result.hasErrors()){
            return CustomResponse.failure(result.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
        }
            Event event = eventSvc.saveEvent(eventReq);
            return CustomResponse.success(HttpStatus.CREATED.toString(),event,HttpStatus.CREATED);
    }
    @PutMapping("/event/{id}")
    public ResponseEntity<CustomResponse> editEvent (@PathVariable int id, @RequestBody @Validated EventReq eventReq,
                                                     BindingResult result) throws NotFoundException {
        if (result.hasErrors()){
            return CustomResponse.failure(result.getAllErrors().stream().map(ob -> ob.getDefaultMessage())
                    .toList().toString(), HttpStatus.BAD_REQUEST);
        }else{
          Event event = eventSvc.upDateEvent(id,eventReq);
          return CustomResponse.success(HttpStatus.OK.toString(),event,HttpStatus.OK);
        }
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<CustomResponse> deleteEvent(@PathVariable int id) throws NotFoundException {
        eventSvc.deleteEvent(id);
        return CustomResponse.emptyResponse("Event: "+id+"deleted",HttpStatus.OK);
    }
}
