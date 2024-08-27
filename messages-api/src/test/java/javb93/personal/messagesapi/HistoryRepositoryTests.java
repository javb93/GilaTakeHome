package javb93.personal.messagesapi;

import javb93.personal.messagesapi.entities.History;
import javb93.personal.messagesapi.enums.NotificationType;
import javb93.personal.messagesapi.repositories.HistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@DataJpaTest
public class HistoryRepositoryTests {
    @Autowired
    private HistoryRepository historyRepository;

    private History record1;
    private History record2;

    @Test
    public void contextLoads() throws Exception {
        assertNotNull(historyRepository);
    }
    @BeforeEach
    public void setupData(){
        record1 = new History("Test Message","Sports", NotificationType.SMS, "John", "Email@email.com", "123456789", new Date());
        record2 = new History("Test Message","Sports", NotificationType.PUSH, "John", "Email@email.com", "123456789", new Date());
        historyRepository.save(record1);
        historyRepository.save(record2);
    }
    @Test
    public void testSaveRecords() throws Exception{


        //Do action
        List<History> results = historyRepository.findAll();

        //Asserts
        assertEquals(2,results.size());
        assertTrue(results.contains(record1));
        assertTrue(results.contains(record2));

    }
    @Test
    public void testFindByNotificationType(){
        //Testing possible behaviour we might want in the future

        List<History> results = historyRepository.findByNotificationType(NotificationType.SMS);
        assertEquals(1,results.size());
        assertEquals(NotificationType.SMS,results.getFirst().getNotificationType());
    }

}
