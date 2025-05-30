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
public class RealtimeChatNotificationsService {

    private final SimpMessagingTemplate messagingTemplate;

    private static final String DESTINATION_PREFIX = "/topic/workspace/%s/chat/%s";

    public void sendCommentCreatedNotification(CommentCreatedEvent event) {
        NotificationDto notificationDto = new NotificationDto(COMMENT_CREATED, event.getCreatedComment());
        messagingTemplate
                .convertAndSend(
                        DESTINATION_PREFIX.formatted(event.getCreatedComment().getWorkspaceId(), event.getCreatedComment().getTaskId()),
                        notificationDto);
    }

    public void sendCommentDeletedNotification(CommentDeletedEvent event){
        NotificationDto notificationDto = new NotificationDto(COMMENT_DELETED, event);
        messagingTemplate
                .convertAndSend(
                        DESTINATION_PREFIX.formatted(event.getWorkspaceId(), event.getTaskId()),
                        notificationDto
                );
    }
}
