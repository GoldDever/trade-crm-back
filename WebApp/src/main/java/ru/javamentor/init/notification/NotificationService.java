package ru.javamentor.init.notification;

import org.springframework.stereotype.Component;
import ru.javamentor.model.notification.Notification;
import ru.javamentor.model.user.User;
import ru.javamentor.repository.UserRepository;
import ru.javamentor.repository.notification.NotificationRepository;

import java.time.LocalDateTime;

@Component
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public void initNotification() {
        User user1 = userRepository.findByUsername("alexey@mail.ru");
        String notificationTitle1 = "Заказ согласован";
        String notificationText1 = "Заказ согласован!Нажмите чтобы посмотреть заказ";
        createNotification(user1, notificationTitle1, notificationText1, "2021-04-30T11:03:12");

        User user2 = userRepository.findByUsername("alexey@mail.ru");
        String notificationTitle2 = "Товар снят с резерва";
        String notificationText2 = "Товар снят с резерва!Нажмите чтобы посмотреть заказ";
        createNotification(user2, notificationTitle2, notificationText2, "2021-04-30T11:12:32");

    }

    public void createNotification(User user, String notificationTitle,
                                   String notificationText, String dateTime) {
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setNotificationTitle(notificationTitle);
        notification.setNotificationText(notificationText);
        notification.setCreatedAt(LocalDateTime.parse(dateTime));

        notificationRepository.save(notification);

    }
}
