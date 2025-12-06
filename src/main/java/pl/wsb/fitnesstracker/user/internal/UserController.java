package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserCrudService service;

    // GET /v1/users -> pełna lista
    @GetMapping
    public List<UserDto> listFull() {
        return service.listFull();
    }

    // GET /v1/users/simple -> tylko firstName/lastName
    @GetMapping("/simple")
    public List<BasicUserDto> listSimple() {
        return service.listBasic();
    }

    // GET /v1/users/{id}
    @GetMapping("/{id}")
    public UserDto get(@PathVariable Long id) {
        return service.getById(id);
    }

    // GET /v1/users/email?email=...
    @GetMapping("/email")
    public List<UserEmailDto> getByEmail(@RequestParam String email) {
        return service.findByExactEmail(email);
    }

    // GET /v1/users/older/{time}  (time w formacie yyyy-MM-dd)
    @GetMapping("/older/{time}")
    public List<UserDto> olderThanDate(@PathVariable("time") LocalDate time) {
        return service.findBornBefore(time);
    }

    // POST /v1/users
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody CreateUserRequest req) {
        return service.create(req);
    }

    // PUT /v1/users/{userId}
    @PutMapping("/{userId}")
    public UserDto update(@PathVariable Long userId, @RequestBody UpdateUserRequest req) {
        return service.update(userId, req);
    }

    // DELETE /v1/users/{userId}
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId) {
        service.delete(userId);
    }
}
