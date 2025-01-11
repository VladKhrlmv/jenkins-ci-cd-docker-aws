package pwr.piisw.cinema.application.service;

import org.springframework.stereotype.Service;
import pwr.piisw.cinema.application.entity.User;
import pwr.piisw.cinema.application.repository.UserRepository;
import pwr.piisw.cinema.application.utils.RoleType;
import pwr.piisw.cinema.exception.CinemaException;
import pwr.piisw.cinema.exception.CinemaExceptionType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CinemaException(CinemaExceptionType.USER_NOT_FOUND));
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByKeycloakId(UUID keycloakId) {
        return userRepository.findByKeycloakId(keycloakId);
    }

    public List<User> findUsersByRole(RoleType role) {
        return userRepository.findUsersByRole(role);
    }

    public List<User> searchUsers(String keyword) {
        return userRepository.searchUsers(keyword);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new CinemaException(CinemaExceptionType.USER_NOT_FOUND);
        }
        userRepository.deleteById(id);
    }
}
