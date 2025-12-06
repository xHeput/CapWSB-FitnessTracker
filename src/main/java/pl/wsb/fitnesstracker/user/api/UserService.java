package pl.wsb.fitnesstracker.user.api;

import java.util.List;
/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param user The user to be created
     * @return The created user
     */
    User createUser(User user);
    List<BasicUserDto> listBasic();                  // GET /v1/users?projection=basic
    UserDto getById(Long id);                        // GET /v1/users/{id}
    UserDto create(CreateUserRequest req);           // POST /v1/users
    UserDto update(Long id, UpdateUserRequest req);  // PATCH /v1/users/{id}
    void delete(Long id);                            // DELETE /v1/users/{id}
    List<UserEmailDto> searchByEmailContains(String fragment); // GET /v1/users/search?email=...
    List<UserDto> findOlderThan(int ageYears);

}
