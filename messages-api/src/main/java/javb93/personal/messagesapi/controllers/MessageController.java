package javb93.personal.messagesapi.controllers;

import javb93.personal.messagesapi.entities.History;
import javb93.personal.messagesapi.entities.Message;
import javb93.personal.messagesapi.entities.User;
import javb93.personal.messagesapi.enums.NotificationType;
import javb93.personal.messagesapi.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class MessageController {

    NotificationFactory notificationFactory;


    IHistoryService historyService;

    IUserService userService;


    public MessageController(IHistoryService historyService,IUserService userService,NotificationFactory notificationFactory) {
        this.historyService = historyService;
        this.userService = userService;
        this.notificationFactory = notificationFactory;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/messages")
    public ResponseEntity<String> sendMessage (@RequestBody Message message){
        //TODO Currently a mock of users as instructed, could create a user service to get a list of users, follow same logic after
        List<User> usersList = userService.findAll();
        if(usersList.isEmpty()){
            return new ResponseEntity<>("No registered users found",HttpStatus.NO_CONTENT);
        }
        for (User user : usersList ){
            // Only need to know if user is subscribed to category, if he is, use each appropiate service to notify
            if(user.getSubscribedCategories().contains(message.getCategory())){
                for (NotificationType notificationType : user.getChannelsList()){
                    try{
                        //Service could be null wich would be internal server error
                        notificationFactory.getService(notificationType).notify(message.getMessage(),message.getCategory(),user);
                        //Could throw Data access exception when trying to save, currently having both on try catch block since notify is a simulation
                        historyService.saveHistory(new History(message.getMessage(),message.getCategory(),notificationType, user.getName(), user.getEmail(), user.getPhone(), new Date()));
                    }catch (DataAccessException | TransactionSystemException error){
                        return new ResponseEntity<>("Message was not saved to history",HttpStatus.BAD_REQUEST);
                    }catch (Exception error){
                        return new ResponseEntity<>("Unknown Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
                    }

                }
            }
        }
        return new ResponseEntity<>("Notifications Succesful",HttpStatus.OK);
    }

}
