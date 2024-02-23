package app.GestioneEvento.service;

import app.GestioneEvento.enumP.Role;
import app.GestioneEvento.exception.NotFoundException;
import app.GestioneEvento.model.entities.Event;
import app.GestioneEvento.model.entities.User;
import app.GestioneEvento.model.request.UserReq;
import app.GestioneEvento.repository.EventRepo;
import app.GestioneEvento.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSvc {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    @Qualifier("BCript")
    private PasswordEncoder encoder;
    @Autowired
    private EventRepo eventRepo;

    public User saveUser(UserReq userReq) {
        User user = new User();
        user.setName(userReq.getName());
        user.setSurname(userReq.getSurname());
        user.setUsername(userReq.getUsername());
        user.setPassword(encoder.encode(userReq.getPassword()));
        user.setRole(Role.CLIENT);
        return userRepo.save(user);
    }

    public User getUserById (int id) throws NotFoundException {
        return userRepo.findById(id).orElseThrow(()-> new NotFoundException("User not found"));
    }

    public User getUserByUserName (String username) throws NotFoundException {
        return userRepo.findByUsername(username).orElseThrow(()-> new NotFoundException("UserName/Password not found"));
    }

}
