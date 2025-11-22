package pl.wsb.fitnesstracker.userevent;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.event.Event;

@Entity
@Table(name = "user_event")
public class UserEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(name = "status", nullable = false)
    private String status = "REGISTERED";

    @Column(name = "registered_at", nullable = false)
    private LocalDateTime registeredAt = LocalDateTime.now();

    // --- konstruktory ---
    public UserEvent() {
    }

    public UserEvent(User user, Event event, String status, LocalDateTime registeredAt) {
        this.user = user;
        this.event = event;
        this.status = status;
        this.registeredAt = registeredAt;
    }

    // --- gettery / settery ---
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }
}
