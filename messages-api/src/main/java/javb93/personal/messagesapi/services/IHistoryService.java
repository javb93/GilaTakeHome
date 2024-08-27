package javb93.personal.messagesapi.services;


import javb93.personal.messagesapi.entities.History;

import java.util.List;


public interface IHistoryService {
    List<History> findAll();
    History saveHistory(History historyRecord);
 }
