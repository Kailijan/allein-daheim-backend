package de.team_franky.allein_daheim.data.repository;

import de.team_franky.allein_daheim.data.entity.Topic;
import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic, Long> {
}
