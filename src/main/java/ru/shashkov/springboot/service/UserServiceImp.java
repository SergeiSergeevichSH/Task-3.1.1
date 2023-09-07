package ru.shashkov.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shashkov.springboot.model.User;
import ru.shashkov.springboot.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(long id, User user) {
        user.setId(id);
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public User getUserById(long id) {
        Optional<User> foundUser;
        foundUser = userRepository.findById(id);
        return foundUser.orElse(null);
    }

    @Transactional
    public List<User> listUsers() {
        Sort sort = Sort.by(Sort.Direction.ASC, "id"); // Сортировка по возрастанию id
        return userRepository.findAll(sort);
    }
}
