package ru.javamentor.repository.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javamentor.model.notification.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
