package app.GestioneEvento.controller;

import app.GestioneEvento.exception.NotFoundException;
import app.GestioneEvento.model.entities.User;
import app.GestioneEvento.model.request.LoginReq;
import app.GestioneEvento.model.request.UserReq;
import app.GestioneEvento.service.UserSvc;
import app.GestioneEvento.security.JwtTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserCtrl {

    @Autowired
    private UserSvc userSvc;
    @Autowired
    @Qualifier("BCript")
    private PasswordEncoder encoder;
    @Autowired
    private JwtTools jwtTools;

    @PostMapping("/auth/register")
    public ResponseEntity<CustomResponse> registerUser(@RequestBody @Validated UserReq userReq, BindingResult result){
        if (result.hasErrors()){
            return CustomResponse.failure(result.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
        }else {
            User user = userSvc.saveUser(userReq);
            return CustomResponse.success(HttpStatus.OK.toString(),user,HttpStatus.OK);
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<CustomResponse> loginUser (@RequestBody @Validated LoginReq loginReq, BindingResult result)
            throws NotFoundException {

        if (result.hasErrors())
            return CustomResponse.failure(result.getAllErrors().toString(),HttpStatus.BAD_REQUEST);

        User user = userSvc.getUserByUserName(loginReq.getUsername());
        if (encoder.matches(loginReq.getPassword(),user.getPassword())){
            String token = jwtTools.createToken(user);
            return CustomResponse.success(HttpStatus.OK.toString(),token,HttpStatus.OK);
        }else
            return CustomResponse.failure(new NotFoundException("UserName/Password not found").getMessage(),HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/user/addEvent/{idEvent}/{idUser}")
    public ResponseEntity<CustomResponse> saveEvent (@PathVariable int idEvent,@PathVariable int idUser){
        try{
            userSvc.saveEvent(idEvent,idUser);
            return CustomResponse.emptyResponse("Event with id: "+idEvent+"saved in userId: "+idUser,HttpStatus.OK);
        } catch (NotFoundException e) {
            return CustomResponse.failure(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
















