package de.team_franky.allein_daheim.api;

import de.team_franky.allein_daheim.business.service.ChatRequestService;
import de.team_franky.allein_daheim.business.service.TopicService;
import de.team_franky.allein_daheim.business.service.UserService;
import de.team_franky.allein_daheim.data.entity.ChatRequest;
import de.team_franky.allein_daheim.data.entity.Topic;
import de.team_franky.allein_daheim.data.entity.User;
import de.team_franky.allein_daheim.data.entity.embeddable.ChatRequestKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value = "/api/chat-request")
public class ChatRequestController {

    @Autowired
    private ChatRequestService chatRequestService;

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    @PostMapping(value = "")
    public ChatRequest save(@RequestBody ChatRequestKey chatRequestKey) {
        User user = userService.findById(chatRequestKey.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));

        Topic topic = topicService.findById(chatRequestKey.getTopicId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic not found."));

        return chatRequestService.save(new ChatRequest(user, topic));
    }

    @DeleteMapping(value = "")
    public void deleteChatRequestsByUser(@RequestParam(value = "user") Long userId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));

        chatRequestService.deleteAll(chatRequestService.findByUser(user));
    }

    @GetMapping(value = "/match")
    public ChatRequest findMatch(@RequestParam(value = "user") Long userId) {
        List<Topic> userTopics = new ArrayList<>();

        User user = userService.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));

        Iterable<ChatRequest> userChatRequests = chatRequestService.findByUser(user);
        userChatRequests.forEach(chatRequest -> {
            Topic topic = topicService.findById(chatRequest.getChatRequestKey().getTopicId())
                    .orElse(null);
            if (topic != null && !userTopics.contains(topic)) {
                userTopics.add(topic);
            }
        });

        Iterable<ChatRequest> matchCandidates = chatRequestService.findByTopics(userTopics, user);
        return pickCandidate(matchCandidates);
    }

    private ChatRequest pickCandidate(Iterable<ChatRequest> matchCandidates) {
        if (matchCandidates == null) {
             return null;
        }
        Date now = new Date();
        List<ChatRequest> matchCandidatesList = new ArrayList<>();
        matchCandidates.forEach(chatRequest -> {
            if (chatRequest.getExpires().after(now)) {
                matchCandidatesList.add(chatRequest);
            } else {
                chatRequestService.delete(chatRequest);
            }
        });

        if (matchCandidatesList.size() == 0) {
            return null;
        }

        return matchCandidatesList.get(new Random().nextInt(matchCandidatesList.size()));
    }

}
