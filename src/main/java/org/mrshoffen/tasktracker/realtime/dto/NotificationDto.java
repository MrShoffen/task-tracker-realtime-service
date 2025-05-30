package org.mrshoffen.tasktracker.realtime.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {

    NotificationType type;
    Object payload;
}
