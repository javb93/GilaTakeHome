package javb93.personal.messagesapi;

import javb93.personal.messagesapi.entities.User;
import javb93.personal.messagesapi.enums.NotificationType;
import javb93.personal.messagesapi.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
public class UserRepositoryTests {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TestEntityManager testEntityManager;
    private User user1;
    private User user2;
    private User user3;

    @Test
    public void contextLoad() throws Exception{
        assertNotNull(userRepository);
    }
    @BeforeEach
    public void setupData(){
        //PreSteps
        List<String> subscribed = new ArrayList<>();
        subscribed.add("Sports");
        subscribed.add("Finance");
        List<NotificationType> notificationTypeList = new ArrayList<NotificationType>();
        notificationTypeList.add(NotificationType.PUSH);
        notificationTypeList.add(NotificationType.SMS);
        user1= userRepository.save(new User("Name","foo@foo.com","61112201",notificationTypeList, subscribed));
        user2= userRepository.save(new User("Test2","foo@faa.com","123456789",notificationTypeList,subscribed));
        subscribed = new ArrayList<>();
        subscribed.add("Movies");
        notificationTypeList = new ArrayList<NotificationType>();
        notificationTypeList.add(NotificationType.EMAIL);
        user3 =userRepository.save(new User("ONLY MOVIES","movies@faa.com","0000000",notificationTypeList,subscribed));

    }
    @Test
    public void testFindAll() throws Exception{

        //Do action
        List<User> results = userRepository.findAll();

        //Asserts
        assertEquals(3,results.size());
        assertTrue(results.contains(user1));
        assertTrue(results.contains(user2));
        assertTrue(results.contains(user3));
        assertEquals(user1.getChannelsList(),results.getFirst().getChannelsList());
        assertEquals(NotificationType.PUSH,results.get(1).getChannelsList().getFirst());

    }
    @Test
    public void insertNewUser(){
        List<String> subscribed = new ArrayList<>();
        subscribed.add("Sports");
        List<NotificationType> notificationTypeList = new ArrayList<NotificationType>();
        notificationTypeList.add(NotificationType.PUSH);
        User newUser = new User("Inserted","foo@foo.com","111222555",notificationTypeList, subscribed);
        User insertedUser= userRepository.save(newUser);
        assertThat(testEntityManager.find(User.class,insertedUser.getId())).isEqualTo(newUser);
    }
    @Test
    public void findById(){
        Optional<User> foundUser = userRepository.findById(user1.getId());
        assertThat(foundUser).contains(user1);
    }


}


