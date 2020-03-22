package de.team_franky.allein_daheim.business.service;

import de.team_franky.allein_daheim.data.entity.Match;
import de.team_franky.allein_daheim.data.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    public Match save(Match match) {
        return matchRepository.save(match);
    }

    public Iterable<Match> saveAll(Iterable<Match> matches) {
        return matchRepository.saveAll(matches);
    }

    public void delete(Match match) {
        matchRepository.delete(match);
    }

    public Iterable<Match> findAll() {
        return matchRepository.findAll();
    }

    public Optional<Match> findByUserId(Long userId) {
        return matchRepository.findById(userId);
    }

    public Iterable<Match> findAllByMatchId(Long matchId) {
        List<Match> matchesById = new ArrayList<>();
        Iterable<Match> matches = matchRepository.findAll();
        matches.forEach(match -> {
            if (match.getMatchInfo().getMatchId() == matchId) {
                matchesById.add(match);
            }
        });
        return matchesById;
    }

}
