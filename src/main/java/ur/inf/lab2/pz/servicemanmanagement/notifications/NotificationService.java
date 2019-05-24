package ur.inf.lab2.pz.servicemanmanagement.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ur.inf.lab2.pz.servicemanmanagement.domain.Notification;
import ur.inf.lab2.pz.servicemanmanagement.domain.User;
import ur.inf.lab2.pz.servicemanmanagement.repository.AllUsersRepository;

import java.io.IOException;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private AllUsersRepository allUsersRepository;

    public List<Notification> getUserNotifications(long id, int page) {

        List<Notification> notifications = notificationRepository.findAllByUserIdOrderByCreationDateDesc(id, PageRequest.of(page, 10));

        return notifications;
    }

    public void addNotification(Notification notification, long userId) throws IOException {

        User targetUser = allUsersRepository.findById(userId).orElseThrow(() -> new IOException());

        notification.setUser(targetUser);
        targetUser.getNotifications().add(notification);

        notificationRepository.save(notification);
    }

    public void setNotificationAsViewed(long notificationId) throws IOException {

        Notification targetNotification = notificationRepository.findById(notificationId).orElseThrow(() -> new IOException());

        targetNotification.setViewed(true);
        notificationRepository.save(targetNotification);
    }

    public void deleteNotifications(List<Long> ids) {

        for(Notification notification : notificationRepository.findAllById(ids))
        {
            notification.getUser().getNotifications().remove(notification);
        }

        notificationRepository.deleteByIdIn(ids);
    }

    public void deleteNotification(long id) throws IOException {

        Notification targetNotification = notificationRepository.findById(id).orElseThrow(() -> new IOException());

        targetNotification.getUser().getNotifications().remove(targetNotification);

        notificationRepository.delete(targetNotification);
    }
}