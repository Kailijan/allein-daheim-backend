package de.team_franky.allein_daheim.business.task;

import de.team_franky.allein_daheim.business.service.ChatRequestService;
import de.team_franky.allein_daheim.business.service.MatchService;
import de.team_franky.allein_daheim.business.service.UserService;
import de.team_franky.allein_daheim.data.entity.ChatRequest;
import de.team_franky.allein_daheim.data.entity.Match;
import de.team_franky.allein_daheim.data.entity.embeddable.MatchInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Component
public class MatchFinderTask {

    @Autowired
    private ChatRequestService chatRequestService;

    @Autowired
    private UserService userService;

    @Autowired
    private MatchService matchService;

    @Scheduled(fixedRate = 5000)
    public void updateMatches() {
        Iterable<ChatRequest> chatRequests = chatRequestService.findAll();

        HashMap<Long, List<ChatRequest>> chatRequestsByTopic = new HashMap<Long, List<ChatRequest>>();
        chatRequests.forEach(chatRequest -> {
            Long topicId = chatRequest.getChatRequestKey().getTopicId();
            if (!chatRequestsByTopic.containsKey(topicId)) {
                chatRequestsByTopic.put(topicId, new ArrayList<>());
            }
            chatRequestsByTopic.get(topicId).add(chatRequest);
        });

        List<Match> matches = new ArrayList<>();
        List<ChatRequest> chatRequestsToRemove = new ArrayList<>();

        chatRequestsByTopic.forEach((topicId, chatRequestsForTopic) -> {
            for (int i = 1; i < chatRequestsForTopic.size(); i++) {
                ChatRequest request1 = chatRequestsForTopic.get(i-1);
                ChatRequest request2 = chatRequestsForTopic.get(i);
                MatchInfo matchInfo = new MatchInfo();
                matchInfo.setTopicId(topicId);

                Match match1 = new Match();
                match1.setMatchInfo(matchInfo);
                match1.setUserId(request1.getChatRequestKey().getUserId());
                matches.add(match1);

                Match match2 = new Match();
                match2.setMatchInfo(matchInfo);
                match2.setUserId(request2.getChatRequestKey().getUserId());
                matches.add(match2);

                chatRequestsToRemove.add(request1);
                chatRequestsToRemove.add(request2);
            }
        });

        chatRequestService.deleteAll(chatRequestsToRemove);
        matchService.saveAll(matches);
    }

}
