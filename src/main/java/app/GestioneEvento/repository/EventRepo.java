package app.GestioneEvento.repository;

import app.GestioneEvento.model.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepo extends JpaRepository<Event,Integer> {
}
