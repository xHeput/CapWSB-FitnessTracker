package pl.wsb.fitnesstracker.event.api;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.wsb.fitnesstracker.event.Event;

import java.util.List;
import java.util.Optional;

@Repository
public class EventRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public Optional<Event> findById(Long id) {
        return Optional.ofNullable(em.find(Event.class, id));
    }

    @Transactional
    public Event save(Event event) {
        if (event.getId() == null) {
            em.persist(event);
            return event;
        }
        return em.merge(event);
    }

    @Transactional
    public void deleteById(Long id) {
        findById(id).ifPresent(em::remove);
    }

    @Transactional(readOnly = true)
    public List<Event> findByName(String name) {
        return em.createQuery(
                        "select e from Event e where e.name = :name", Event.class)
                .setParameter("name", name)
                .getResultList();
    }
}
