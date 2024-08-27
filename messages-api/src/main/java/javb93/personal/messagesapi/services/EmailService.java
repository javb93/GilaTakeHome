package javb93.personal.messagesapi.services;

import javb93.personal.messagesapi.entities.User;
import org.springframework.stereotype.Component;

@Component
public class EmailService implements INotificationService{

    @Override
    public Boolean notify(String message_content, String category, User user) {
        System.out.println("Notifying trough Email to " + user.getName());
        System.out.println("Using email " + user.getEmail());
        return true;
    }
}
