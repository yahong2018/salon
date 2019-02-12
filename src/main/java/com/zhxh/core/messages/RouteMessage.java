package com.zhxh.core.messages;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class RouteMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID messageId;
    private String messageClass;
    private Serializable messageData;
    private String source;
    private String destination;
    private LocalDateTime createTime;

    public UUID getMessageId() {
        return messageId;
    }

    public void setMessageId(UUID messageId) {
        this.messageId = messageId;
    }

    public String getMessageClass() {
        return messageClass;
    }

    public void setMessageClass(String messageClass) {
        this.messageClass = messageClass;
    }

    public Serializable getMessageData() {
        return messageData;
    }

    public void setMessageData(Serializable messageData) {
        this.messageData = messageData;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
