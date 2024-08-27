package javb93.personal.messagesapi.services;

import javb93.personal.messagesapi.entities.History;
import javb93.personal.messagesapi.repositories.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistoryService implements IHistoryService{
    @Autowired
    HistoryRepository historyRepository;
    @Override
    public List<History> findAll() {
        return historyRepository.findAll(Sort.by(Sort.Direction.DESC,"timestamp"));
    }

    @Override
    public History saveHistory(History historyRecord) {
        return historyRepository.save(historyRecord);
    }


}
