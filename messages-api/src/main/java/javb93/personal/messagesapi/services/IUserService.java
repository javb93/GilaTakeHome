package javb93.personal.messagesapi.services;


import javb93.personal.messagesapi.entities.User;

import java.util.List;

public interface IUserService {

        List<User> findAll();
        User saveUser(User userRecord);

}
