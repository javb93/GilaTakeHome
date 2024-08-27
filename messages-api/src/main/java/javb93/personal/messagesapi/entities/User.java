package javb93.personal.messagesapi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import javb93.personal.messagesapi.enums.NotificationType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name="message_users")
public class User {


    private@Id
    @GeneratedValue
    @Column(name="id") Long id;


    @Column
    @NotEmpty(message = "Name can not be empty")
    private  String name;
   @Column
   @Length(min = 1)
    private  String email;
   @Column
   private  String phone;
    // If we want to add another channel, we would need more logic so an Enum helps enforce that, needing to update the enum
    @ElementCollection
    @Column
    private  List<NotificationType> channelsList;
    //TODO decide if we want categories to be an enum here as well, currently enforced on UI, if this were an enum would have more safety
    // But would need to update if we want more categories, since categories don't have more logic here makes sense to have them be strings
    @ElementCollection
    @Column
    private  List<String> subscribedCategories;

    public User() {

    }

    public User(String name, String email, String phone, List<NotificationType> channelsList, List<String> subscribedCategories) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.channelsList = channelsList;
        this.subscribedCategories = subscribedCategories;
    }
}


