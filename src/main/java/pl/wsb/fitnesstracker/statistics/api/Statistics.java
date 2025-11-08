package pl.wsb.fitnesstracker.statistics.api;

import jakarta.persistence.*;
import pl.wsb.fitnesstracker.user.api.User;
@Entity
@Table(name = "statistics")
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_trainings", nullable = false)
    private Integer totalTrainings;

    @Column(name = "total_distance")
    private Double totalDistance;

    @Column(name = "total_calories_burned")
    private Integer totalCaloriesBurned;

}
