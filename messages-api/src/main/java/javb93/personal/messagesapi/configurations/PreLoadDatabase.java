package javb93.personal.messagesapi.configurations;

import javb93.personal.messagesapi.entities.History;
import javb93.personal.messagesapi.entities.User;
import javb93.personal.messagesapi.enums.NotificationType;
import javb93.personal.messagesapi.repositories.HistoryRepository;
import javb93.personal.messagesapi.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class PreLoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(PreLoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(HistoryRepository historyRepository, UserRepository userRepository){
        return args -> {
            log.info("Preloading" + historyRepository.save(new History("Test of message log","Finance",NotificationType.SMS,"MockUser","MockeEmail","Mopck phone", new Date())));
            //HARD CODED USER DATA, THIS COULD BE MOVED
            List<String> subscribed = new ArrayList<String>();
            subscribed.add("Sports");
            subscribed.add("Finance");
            List<NotificationType> notificationTypeList = new ArrayList<NotificationType>();
            notificationTypeList.add(NotificationType.PUSH);
            notificationTypeList.add(NotificationType.SMS);
            User user1= new User("Name","foo@foo.com","61112201",notificationTypeList, subscribed);
            User user2= new User("Test2","foo@faa.com","123456789",notificationTypeList,subscribed);
            subscribed = new ArrayList<String>();
            subscribed.add("Movies");
            notificationTypeList = new ArrayList<NotificationType>();
            notificationTypeList.add(NotificationType.EMAIL);
            User user3 =new User("ONLY MOVIES","movies@faa.com","0000000",notificationTypeList,subscribed);
            log.info("preloading" +userRepository.save(user1));
            log.info("preloading" +userRepository.save(user2));
            log.info("preloading" +userRepository.save(user3));
        };
    }
}
