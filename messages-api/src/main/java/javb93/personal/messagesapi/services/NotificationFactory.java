package javb93.personal.messagesapi.services;

import javb93.personal.messagesapi.enums.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class NotificationFactory {
    @Autowired
    @Qualifier("smsService")
    private INotificationService sms;

    @Autowired
    @Qualifier("emailService")
    private INotificationService email;

    @Autowired
    @Qualifier("pushService")
    private INotificationService push;

    public INotificationService getService(NotificationType notificationType){
        switch (notificationType){
            case EMAIL:
                return email;
            case SMS:
                return sms;
            case PUSH:
                return push;
            default:
                throw new IllegalArgumentException("No bean available for the type " + notificationType);
        }

    }

}
