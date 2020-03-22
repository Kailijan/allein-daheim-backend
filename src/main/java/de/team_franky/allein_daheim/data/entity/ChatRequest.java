package de.team_franky.allein_daheim.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import de.team_franky.allein_daheim.data.entity.embeddable.ChatRequestKey;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "CHAT_REQUEST")
public class ChatRequest {

    @EmbeddedId
    private ChatRequestKey chatRequestKey;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    public ChatRequest() {
        this.chatRequestKey = null;
        setExpires();
    }

    public ChatRequest(User user, Topic topic) {
        this.chatRequestKey = new ChatRequestKey(user.getId(), topic.getId());
        setExpires();
    }

    private void setExpires() {
        this.createdAt = new Timestamp(new Date().getTime());
    }

    public ChatRequestKey getChatRequestKey() {
        return chatRequestKey;
    }

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd.MM.yyyy HH:mm:ssZ")
    public Timestamp getCreatedAt() {
        return createdAt;
    }
}

