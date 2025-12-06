package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.*;

@Component
public class UserMapper {

    public UserDto toDto(User u) {
        return new UserDto(u.getId(), u.getFirstName(), u.getLastName(), u.getBirthdate(), u.getEmail());
    }

    public BasicUserDto toBasic(User u) {
        return new BasicUserDto(u.getFirstName(), u.getLastName());
    }

    public UserEmailDto toEmail(User u) {
        return new UserEmailDto(u.getId(), u.getEmail());
    }

    public User fromCreate(CreateUserRequest r) {
        return new User(r.firstName(), r.lastName(), r.birthdate(), r.email());
    }

    public void applyUpdate(User u, UpdateUserRequest r) {
        if (r.firstName() != null) u.setFirstName(r.firstName());
        if (r.lastName()  != null) u.setLastName(r.lastName());
        if (r.birthdate() != null) u.setBirthdate(r.birthdate());
        if (r.email()     != null) u.setEmail(r.email());
    }
}
