package javb93.personal.messagesapi.repositories;

import javb93.personal.messagesapi.entities.History;
import javb93.personal.messagesapi.enums.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History,Long> {
    List<History> findByNotificationType(NotificationType notificationType);
}
