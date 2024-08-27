package javb93.personal.messagesapi.entities;

import jakarta.persistence.*;
import javb93.personal.messagesapi.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="history")
@Getter
@Setter
@AllArgsConstructor
public class History {
    private @Id
    @GeneratedValue
    @Column(name="id") Long id;

    @Length(min = 1)
    @Column(name="message_text")
    private String messageText;
    @Column(name="message_category")
    private String messageCategory;
    @Column(name="notification_type")
    private NotificationType notificationType;
    //Could user reference to user here, using destructured values for now
    @Column(name="user_name")
    private String userName;
    @Column(name="user_email")
    private String userEmail;
    @Column(name="user_phone")
    private String userPhone;
    @Column(name="timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;


    public History(String messageText, String messageCategory, NotificationType notificationType, String userName, String userEmail, String userPhone, Date timestamp) {
        this.messageText = messageText;
        this.messageCategory = messageCategory;
        this.notificationType = notificationType;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.timestamp = timestamp;
    }

    public History() {

    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return Objects.equals(id, history.id) && Objects.equals(messageText, history.messageText) && Objects.equals(messageCategory, history.messageCategory) && notificationType == history.notificationType && Objects.equals(userName, history.userName) && Objects.equals(userEmail, history.userEmail) && Objects.equals(userPhone, history.userPhone) && Objects.equals(timestamp, history.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp);
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", messageText='" + messageText + '\'' +
                ", messageCategory='" + messageCategory + '\'' +
                ", notificationType=" + notificationType +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
