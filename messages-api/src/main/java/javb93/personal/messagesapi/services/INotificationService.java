package javb93.personal.messagesapi.services;

import javb93.personal.messagesapi.entities.User;

public interface INotificationService {
    public Boolean notify(String message_content, String category, User user);

}
