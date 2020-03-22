package de.team_franky.allein_daheim.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import de.team_franky.allein_daheim.data.entity.embeddable.ChatRequestKey;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "CHAT_REQUEST")
public class ChatRequest {

    @EmbeddedId
    private ChatRequestKey chatRequestKey;

    @Column(name = "EXPIRES")
    private Date expires;

    public ChatRequest() {
        this.chatRequestKey = null;
        setExpires();
    }

    public ChatRequest(User user, Topic topic) {
        this.chatRequestKey = new ChatRequestKey(user.getId(), topic.getId());
        setExpires();
    }

    private void setExpires() {
        long timeoutSpan = 1000 * 20;
        java.util.Date now = new java.util.Date();
        this.expires = new Date(now.getTime() + timeoutSpan);
    }

    public ChatRequestKey getChatRequestKey() {
        return chatRequestKey;
    }

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd.MM.yyyy H:m:s")
    public Date getExpires() {
        return expires;
    }
}

