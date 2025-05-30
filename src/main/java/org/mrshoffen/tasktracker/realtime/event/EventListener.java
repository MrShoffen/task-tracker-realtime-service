package org.mrshoffen.tasktracker.realtime.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import org.mrshoffen.tasktracker.realtime.service.RealtimeChatNotificationsService;
import org.mrshoffen.tasktracker.realtime.service.RealtimeWorkspaceNotificationsService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class EventListener {

    private final RealtimeWorkspaceNotificationsService workspaceNotificationSender;

    private final RealtimeChatNotificationsService chatNotificationSender;

    @KafkaListener(topics = WorkspaceUpdatedEvent.TOPIC)
    public void handleWorkspaceUpdatedEvent(WorkspaceUpdatedEvent event) {
        log.info("Received event in topic {} - {}", WorkspaceUpdatedEvent.TOPIC, event);
        workspaceNotificationSender.sendWorkspaceUpdatedNotification(event);
    }

    @KafkaListener(topics = DeskCreatedEvent.TOPIC)
    public void handleDeskCreatedEvent(DeskCreatedEvent event) {
        log.info("Received event in topic {} - {}", DeskCreatedEvent.TOPIC, event);
        workspaceNotificationSender.sendDeskCreatedNotification(event);
    }

    @KafkaListener(topics = DeskDeletedEvent.TOPIC)
    public void handleDeskDeleted(DeskDeletedEvent event) {
        log.info("Received event in topic {} - {}", DeskDeletedEvent.TOPIC, event);
        workspaceNotificationSender.sendDeskDeletedNotification(event);
    }

    @KafkaListener(topics = DeskUpdatedEvent.TOPIC)
    public void handleDeskUpdated(DeskUpdatedEvent event) {
        log.info("Received event in topic {} - {}", DeskUpdatedEvent.TOPIC, event);
        workspaceNotificationSender.sendDeskUpdatedNotification(event);
    }

    @KafkaListener(topics = TaskCreatedEvent.TOPIC)
    public void handleTaskCreated(TaskCreatedEvent event) {
        log.info("Received event in topic {} - {}", TaskCreatedEvent.TOPIC, event);
        workspaceNotificationSender.sendTaskCreatedNotification(event);
    }

    @KafkaListener(topics = TaskDeletedEvent.TOPIC)
    public void handleTaskDeleted(TaskDeletedEvent event) {
        log.info("Received event in topic {} - {}", TaskDeletedEvent.TOPIC, event);
        workspaceNotificationSender.sendTaskDeletedNotification(event);
    }

    @KafkaListener(topics = TaskUpdatedEvent.TOPIC)
    public void handleTaskUpdated(TaskUpdatedEvent event) {
        log.info("Received event in topic {} - {}", TaskUpdatedEvent.TOPIC, event);
        workspaceNotificationSender.sentTaskUpdatedNotification(event);
    }

    @KafkaListener(topics = StickerCreatedEvent.TOPIC)
    public void handleStickerCreated(StickerCreatedEvent event) {
        log.info("Received event in topic {} - {}", StickerCreatedEvent.TOPIC, event);
        workspaceNotificationSender.sendStickerCreatedNotification(event);
    }

    @KafkaListener(topics = StickerDeletedEvent.TOPIC)
    public void handleStickerDeleted(StickerDeletedEvent event) {
        log.info("Received event in topic {} - {}", StickerDeletedEvent.TOPIC, event);
        workspaceNotificationSender.sendStickerDeletedNotification(event);
    }

    @KafkaListener(topics = CommentCreatedEvent.TOPIC)
    public void handleCommentCreated(CommentCreatedEvent event) {
        log.info("Received event in topic {} - {}", CommentCreatedEvent.TOPIC, event);
        chatNotificationSender.sendCommentCreatedNotification(event);
        workspaceNotificationSender.sendCommentCreatedNotification(event);
    }

    @KafkaListener(topics = CommentDeletedEvent.TOPIC)
    public void handleCommentDeleted(CommentDeletedEvent event) {
        log.info("Received event in topic {} - {}", CommentDeletedEvent.TOPIC, event);
        chatNotificationSender.sendCommentDeletedNotification(event);
        workspaceNotificationSender.sendCommentDeletedNotification(event);
    }

}
