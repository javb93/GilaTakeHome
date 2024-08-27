package javb93.personal.messagesapi.controllers;

import javb93.personal.messagesapi.entities.History;
import javb93.personal.messagesapi.services.HistoryService;
import javb93.personal.messagesapi.services.IHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistoryController {

    IHistoryService historyService;

    public HistoryController(IHistoryService historyService) {
        this.historyService = historyService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/history")
    public ResponseEntity<List<History>> findAll(){
        List<History> allHistory = historyService.findAll();

        return new ResponseEntity<>(allHistory, HttpStatus.OK);
    }
}
