package javb93.personal.messagesapi.services;

import javb93.personal.messagesapi.entities.User;
import org.springframework.stereotype.Component;

@Component
public class PushService implements INotificationService{
    @Override
    public Boolean notify(String message_content, String category, User user) {
        //Here we would implement logic for Push notification, possibly calling an external service or spliting intro micro services
        System.out.println("Notifying trough PUSH to " + user.getName() );
        System.out.println(message_content);
        System.out.println(category);
        return true;
    }
}
