package service;

import dto.RegistrationDto;
import model.User;

import java.util.List;

public interface UserService {
    User registerNewUser(RegistrationDto dto);
    List<User> findAll();
}
