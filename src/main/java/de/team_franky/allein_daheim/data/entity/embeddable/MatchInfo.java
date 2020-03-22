package de.team_franky.allein_daheim.data.entity.embeddable;

import javax.persistence.*;
import java.sql.Timestamp;

@Embeddable
@Table(name = "MATCH_INFO")
public class MatchInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MATCH_ID")
    private Long matchId;

    @Column(name = "TOPIC_ID")
    private Long topicId;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    public MatchInfo() {
        createdAt = new Timestamp(new java.util.Date().getTime());
    }

    public Long getMatchId() {
        return matchId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
