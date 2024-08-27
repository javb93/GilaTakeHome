package javb93.personal.messagesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("javb93.personal.messagesapi.repositories")
public class MessagesApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessagesApiApplication.class, args);
    }

}
