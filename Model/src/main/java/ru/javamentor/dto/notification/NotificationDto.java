package ru.javamentor.dto.notification;

import ru.javamentor.model.user.Manager;
import ru.javamentor.model.user.User;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Date;

public class NotificationDto {

    private Long id;
    private String notificationTitle;
    private String notificationText;
    private LocalDateTime createTime;

    public NotificationDto() {
    }

    public NotificationDto(Long id, String notificationTitle, String notificationText, LocalDateTime createTime) {
        this.id = id;
        this.notificationTitle = notificationTitle;
        this.notificationText = notificationText;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
