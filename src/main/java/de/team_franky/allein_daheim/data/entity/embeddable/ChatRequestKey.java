package de.team_franky.allein_daheim.data.entity.embeddable;

import de.team_franky.allein_daheim.data.entity.Topic;
import de.team_franky.allein_daheim.data.entity.User;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.io.Serializable;

@Embeddable
@Table(name = "CHAT_REQUEST")
public class ChatRequestKey implements Serializable {

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "TOPIC_ID", nullable = false)
    private Long topicId;

    public ChatRequestKey() {
        this.userId = null;
        this.topicId = null;
    }

    public ChatRequestKey(Long userId, Long topicId) {
        this.userId = userId;
        this.topicId = topicId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }
}
