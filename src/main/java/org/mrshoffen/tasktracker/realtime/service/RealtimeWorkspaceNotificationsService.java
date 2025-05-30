package org.mrshoffen.tasktracker.realtime.service;

import lombok.RequiredArgsConstructor;
import org.bouncycastle.jcajce.provider.symmetric.DES;
import org.mrshoffen.tasktracker.commons.kafka.event.comment.CommentCreatedEvent;
import org.mrshoffen.tasktracker.commons.kafka.event.comment.CommentDeletedEvent;
import org.mrshoffen.tasktracker.commons.kafka.event.desk.DeskCreatedEvent;
import org.mrshoffen.tasktracker.commons.kafka.event.desk.DeskDeletedEvent;
import org.mrshoffen.tasktracker.commons.kafka.event.desk.DeskUpdatedEvent;
import org.mrshoffen.tasktracker.commons.kafka.event.sticker.StickerCreatedEvent;
import org.mrshoffen.tasktracker.commons.kafka.event.sticker.StickerDeletedEvent;
import org.mrshoffen.tasktracker.commons.kafka.event.task.TaskCreatedEvent;
import org.mrshoffen.tasktracker.commons.kafka.event.task.TaskDeletedEvent;
import org.mrshoffen.tasktracker.commons.kafka.event.task.TaskUpdatedEvent;
import org.mrshoffen.tasktracker.commons.kafka.event.workspace.WorkspaceUpdatedEvent;
import org.mrshoffen.tasktracker.realtime.dto.NotificationDto;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import static org.mrshoffen.tasktracker.realtime.dto.NotificationType.*;

@Service
@RequiredArgsConstructor
public class RealtimeWorkspaceNotificationsService {

    private final SimpMessagingTemplate messagingTemplate;

    private static final String DESTINATION_PREFIX = "/topic/workspace/";

    public void sendWorkspaceUpdatedNotification(WorkspaceUpdatedEvent event) {
        NotificationDto notificationDto = new NotificationDto(WORKSPACE_UPDATED, event);
        messagingTemplate
                .convertAndSend(DESTINATION_PREFIX + event.getWorkspaceId(),
                        notificationDto);
    }

    public void sendDeskCreatedNotification(DeskCreatedEvent event) {
        NotificationDto notification = new NotificationDto(DESK_CREATED, event.getCreatedDesk());
        messagingTemplate
                .convertAndSend(DESTINATION_PREFIX + event.getCreatedDesk().getWorkspaceId(), notification);
    }

    public void sendDeskDeletedNotification(DeskDeletedEvent event) {
        NotificationDto notificationDto = new NotificationDto(DESK_DELETED, event.getDeskId());
        messagingTemplate
                .convertAndSend(DESTINATION_PREFIX + event.getWorkspaceId(),
                        notificationDto);
    }

    public void sendDeskUpdatedNotification(DeskUpdatedEvent event) {
        NotificationDto notificationDto = new NotificationDto(DESK_UPDATED, event);
        messagingTemplate
                .convertAndSend(DESTINATION_PREFIX + event.getWorkspaceId(),
                        notificationDto);
    }

    public void sendTaskCreatedNotification(TaskCreatedEvent event) {
        NotificationDto notificationDto = new NotificationDto(TASK_CREATED, event.getCreatedTask());
        messagingTemplate
                .convertAndSend(DESTINATION_PREFIX + event.getCreatedTask().getWorkspaceId(),
                        notificationDto);
    }

    public void sendTaskDeletedNotification(TaskDeletedEvent event) {
        NotificationDto notificationDto = new NotificationDto(TASK_DELETED, event);
        messagingTemplate
                .convertAndSend(DESTINATION_PREFIX + event.getWorkspaceId(), notificationDto);
    }

    public void sentTaskUpdatedNotification(TaskUpdatedEvent event) {
        NotificationDto notificationDto = new NotificationDto(TASK_UPDATED, event);
        messagingTemplate
                .convertAndSend(DESTINATION_PREFIX + event.getWorkspaceId(), notificationDto);
    }

    public void sendStickerCreatedNotification(StickerCreatedEvent event) {
        NotificationDto notificationDto = new NotificationDto(STICKER_CREATED, event.getCreatedSticker());
        messagingTemplate
                .convertAndSend(DESTINATION_PREFIX + event.getCreatedSticker().getWorkspaceId(), notificationDto);
    }

    public void sendStickerDeletedNotification(StickerDeletedEvent event) {
        NotificationDto notificationDto = new NotificationDto(STICKER_DELETED, event);
        messagingTemplate
                .convertAndSend(DESTINATION_PREFIX + event.getWorkspaceId(), notificationDto);
    }

    public void sendCommentCreatedNotification(CommentCreatedEvent event) {
        NotificationDto notificationDto = new NotificationDto(COMMENT_CREATED, event.getCreatedComment().getTaskId());
        messagingTemplate
                .convertAndSend(DESTINATION_PREFIX + event.getCreatedComment().getWorkspaceId(), notificationDto);
    }

    public void sendCommentDeletedNotification(CommentDeletedEvent event) {
        NotificationDto notificationDto = new NotificationDto(COMMENT_DELETED, event.getTaskId());
        messagingTemplate
                .convertAndSend(DESTINATION_PREFIX + event.getWorkspaceId(), notificationDto);
    }
}
