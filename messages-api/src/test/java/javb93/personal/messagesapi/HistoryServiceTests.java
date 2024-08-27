package javb93.personal.messagesapi;

import javb93.personal.messagesapi.entities.History;
import javb93.personal.messagesapi.enums.NotificationType;
import javb93.personal.messagesapi.repositories.HistoryRepository;
import javb93.personal.messagesapi.services.HistoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class HistoryServiceTests {
    @Mock
    private HistoryRepository historyRepository;

    @InjectMocks
    private HistoryService historyService;

    @Test
    public void testGetAllRecords(){
        //Verify getting the logs of message is called with the sorting
        //prepare
        when(historyRepository.findAll(Sort.by(Sort.Direction.DESC,"timestamp"))).thenReturn(Arrays.asList(
                new History("ExampleMessage","Finance", NotificationType.SMS,"JohnDow","Email@foo.com","1112223333",new Date()),
                new History("SecondMessage","Movies", NotificationType.SMS,"JohnDow","Email@foo.com","1112223333",new Date())
        ));

        //action
        List<History> records = historyService.findAll();

        verify(historyRepository, times(1)).findAll(Sort.by(Sort.Direction.DESC,"timestamp"));
        assertEquals(records.size(),2);
        assertEquals(records.getFirst().getUserName(),"JohnDow");
    }
    @Test
    public void createHistoryRecord(){
        // Arrange
        History newRecord =new History("ExampleMessage","Finance", NotificationType.SMS,"JohnDow","Email@foo.com","1112223333",new Date());

        when(historyRepository.save(newRecord)).thenReturn(new  History(1L, "ExampleMessage","Finance", NotificationType.SMS,"JohnDow","Email@foo.com","1112223333",new Date()));

        // Act
         historyService.saveHistory(newRecord);

        // Assert
        verify(historyRepository, times(1)).save(newRecord);

    }
}
