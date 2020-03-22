package de.team_franky.allein_daheim.data.repository;

import de.team_franky.allein_daheim.data.entity.Match;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Long> {
}
