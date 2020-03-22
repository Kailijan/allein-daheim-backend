package de.team_franky.allein_daheim.business.service;

import de.team_franky.allein_daheim.data.entity.ChatRequest;
import de.team_franky.allein_daheim.data.entity.Topic;
import de.team_franky.allein_daheim.data.entity.User;
import de.team_franky.allein_daheim.data.repository.ChatRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ChatRequestService {

    @Autowired
    private ChatRequestRepository chatRequestRepository;

    public Iterable<ChatRequest> findAll() {
        return chatRequestRepository.findAll();
    }

    public ChatRequest save(ChatRequest chatRequest) {
        return chatRequestRepository.save(chatRequest);
    }

    public void delete(ChatRequest chatRequest) {
        if (chatRequest == null) {
            return;
        }
        chatRequestRepository.delete(chatRequest);
    }

    public void deleteAll(Iterable<ChatRequest> chatRequests) {
        if (chatRequests == null) {
            return;
        }
        chatRequestRepository.deleteAll(chatRequests);
    }

    public Iterable<ChatRequest> findByTopics(Collection<Topic> topics) {
        return findByTopics(topics, null);
    }

    public Iterable<ChatRequest> findByTopics(Collection<Topic> topics, User excludeUser) {
        List<Long> topicIds = new ArrayList<>();
        topics.forEach(topic -> topicIds.add(topic.getId()));

        List<ChatRequest> filteredChatRequests = new ArrayList<>();
        chatRequestRepository.findAll().forEach(chatRequest -> {
            if (excludeUser == null || excludeUser != null && chatRequest.getChatRequestKey().getUserId() != excludeUser.getId()) {
                if (topicIds.contains(chatRequest.getChatRequestKey().getTopicId())) {
                    filteredChatRequests.add(chatRequest);
                }
            }
        });

        return filteredChatRequests;
    }

    public Iterable<ChatRequest> findByUser(User user) {
        List<ChatRequest> filteredChatRequests = new ArrayList<>();
        chatRequestRepository.findAll().forEach(chatRequest -> {
            if (chatRequest.getChatRequestKey().getUserId() == user.getId()) {
                filteredChatRequests.add(chatRequest);
            }
        });

        return filteredChatRequests;
    }

}
