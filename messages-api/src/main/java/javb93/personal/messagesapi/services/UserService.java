package javb93.personal.messagesapi.services;

import javb93.personal.messagesapi.entities.User;
import javb93.personal.messagesapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    UserRepository userRepository;
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User userRecord) {
        userRepository.save(userRecord);
        return userRecord;
    }
}
