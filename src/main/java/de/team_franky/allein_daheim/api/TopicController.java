package de.team_franky.allein_daheim.api;

import de.team_franky.allein_daheim.business.service.TopicService;
import de.team_franky.allein_daheim.data.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/api/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Topic findOne(@PathVariable Long id) {
        return topicService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public Collection<Topic> findAll() {
        Iterable<Topic> topics = topicService.findAll();
        List<Topic> topicList = new ArrayList<>();
        topics.forEach(topic -> topicList.add(topic));
        return topicList;
    }
}
