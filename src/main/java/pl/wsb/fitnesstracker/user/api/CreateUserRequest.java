package pl.wsb.fitnesstracker.user.api;
import java.time.LocalDate;

public record CreateUserRequest(String firstName, String lastName, LocalDate birthdate, String email) {

}
