package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wsb.fitnesstracker.user.api.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
class UserCrudService {

    private final UserRepository repo;
    private final UserMapper mapper;

    @Transactional(readOnly = true)
    public List<UserDto> listFull() {
        return repo.findAll().stream().map(mapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<BasicUserDto> listBasic() {
        return repo.findAll().stream().map(mapper::toBasic).toList();
    }

    @Transactional(readOnly = true)
    public UserDto getById(Long id) {
        var u = repo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return mapper.toDto(u);
    }

    @Transactional
    public UserDto create(CreateUserRequest req) {
        var saved = repo.save(mapper.fromCreate(req));
        return mapper.toDto(saved);
    }

    @Transactional
    public UserDto update(Long id, UpdateUserRequest req) {
        var u = repo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        mapper.applyUpdate(u, req);
        return mapper.toDto(repo.save(u));
    }

    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) throw new UserNotFoundException(id);
        repo.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<UserEmailDto> findByExactEmail(String email) {
        return repo.findByEmail(email)
                .map(u -> List.of(mapper.toEmail(u)))
                .orElseGet(List::of);
    }

    @Transactional(readOnly = true)
    public List<UserEmailDto> searchByEmailContains(String fragment) {
        return repo.findByEmailContainingIgnoreCase(fragment).stream()
                .map(mapper::toEmail)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<UserDto> findBornBefore(LocalDate date) {
        return repo.findByBirthdateBefore(date).stream()
                .map(mapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<UserDto> findOlderThan(int ageYears) {
        LocalDate threshold = LocalDate.now().minus(Period.ofYears(ageYears));
        return findBornBefore(threshold);
    }
}
