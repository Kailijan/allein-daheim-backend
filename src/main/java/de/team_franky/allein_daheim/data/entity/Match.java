package de.team_franky.allein_daheim.data.entity;

import de.team_franky.allein_daheim.data.entity.embeddable.MatchInfo;

import javax.persistence.*;

@Entity
@Table(name = "MATCH")
public class Match {

    @Id
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "MATCH_ID")
    private Long matchId;

    @Embedded
    private MatchInfo matchInfo;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public MatchInfo getMatchInfo() {
        return matchInfo;
    }

    public void setMatchInfo(MatchInfo matchInfo) {
        this.matchInfo = matchInfo;
    }
}
