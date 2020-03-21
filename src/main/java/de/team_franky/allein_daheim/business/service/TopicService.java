package de.team_franky.allein_daheim.business.service;

import de.team_franky.allein_daheim.data.entity.Topic;
import de.team_franky.allein_daheim.data.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public Optional<Topic> findById(Long id) {
        return topicRepository.findById(id);
    }

    public Iterable<Topic> findAll() {
        return topicRepository.findAll();
    }

}
