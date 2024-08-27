package javb93.personal.messagesapi;

import javb93.personal.messagesapi.entities.History;
import javb93.personal.messagesapi.entities.User;
import javb93.personal.messagesapi.enums.NotificationType;
import javb93.personal.messagesapi.repositories.HistoryRepository;
import javb93.personal.messagesapi.repositories.UserRepository;
import javb93.personal.messagesapi.services.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class IntegrationTest {
    @Autowired
    private IHistoryService historyService;

    @Autowired
    private IUserService userService;
    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private NotificationFactory notificationFactory;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testGetHistoryRecords(){
        List<History> records = historyService.findAll();

        assertNotNull(records);

    }
    //Test notification factory, incluiding test for if someone adds element to enum but doesnt implement logic
    @Test
    public void testNotificationFactorySms(){
         INotificationService smsService = notificationFactory.getService(NotificationType.SMS);

        assertNotNull(smsService);
        assertInstanceOf(SmsService.class,smsService);
    }
    @Test
    public void testNotificationFactoryPush(){
        INotificationService pushService = notificationFactory.getService(NotificationType.PUSH);

        assertNotNull(pushService);
        assertInstanceOf(PushService.class,pushService);
    }
    @Test
    public void testNotificationFactoryEmail(){
        INotificationService emailService = notificationFactory.getService(NotificationType.PUSH);

        assertNotNull(emailService);
        assertInstanceOf(EmailService.class,emailService);

    }
    @Test
    public void testFindUsers(){
        List<User> records = userService.findAll();

        assertNotNull(records);
    }
    @Test
    public void testCreateUser(){
        List<String> subscribed = new ArrayList<String>();
        subscribed.add("Sports");
        subscribed.add("Finance");
        List<NotificationType> notificationTypeList = new ArrayList<NotificationType>();
        notificationTypeList.add(NotificationType.PUSH);
        notificationTypeList.add(NotificationType.SMS);
        User user= new User("Tester","foo@foo.com","61112201",notificationTypeList, subscribed);

        User createdUser = userService.saveUser(user);

        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());

        User retrievedUserFromRepo = userRepository.findById(createdUser.getId()).orElse(null);
        assertNotNull(retrievedUserFromRepo);
        assertEquals(user.getName(),retrievedUserFromRepo.getName());
    }
    @Test
    public void testCreateHistoryRecord(){
        History historyRecord= new History("Test of message log","Finance",NotificationType.SMS,"JohnDow","email@foo.com","000", new Date());
        History createdHistoryRecord= historyService.saveHistory(historyRecord);
        assertNotNull(createdHistoryRecord);
        assertNotNull(createdHistoryRecord.getId());
        History retrievedHistoryRecordFromRepo = historyRepository.findById(createdHistoryRecord.getId()).orElse(null);
        assertNotNull(retrievedHistoryRecordFromRepo);
        assertEquals(historyRecord.getUserName(),retrievedHistoryRecordFromRepo.getUserName());

    }

}
