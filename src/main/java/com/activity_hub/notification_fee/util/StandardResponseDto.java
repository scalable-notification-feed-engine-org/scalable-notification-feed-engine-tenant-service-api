package com.activity_hub.notification_fee.util;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StandardResponseDto {
    private int code;
    private String message;
    private Object data;
}