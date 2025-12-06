package pl.wsb.fitnesstracker.user.api;
import java.time.LocalDate;

public record UpdateUserRequest(String firstName, String lastName, LocalDate birthdate, String email) {

}
