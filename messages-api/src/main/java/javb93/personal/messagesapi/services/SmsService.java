package javb93.personal.messagesapi.services;

import javb93.personal.messagesapi.entities.User;
import org.springframework.stereotype.Component;

@Component
public class SmsService implements INotificationService{

    @Override
    public Boolean notify(String message_content, String category, User user) {
        System.out.println("Notifying trough SMS to " + user.getName());
        System.out.println(message_content);
        System.out.println(category);
        return true;
    }
}
