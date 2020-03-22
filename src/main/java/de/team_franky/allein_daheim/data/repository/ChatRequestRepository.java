package de.team_franky.allein_daheim.data.repository;

import de.team_franky.allein_daheim.data.entity.ChatRequest;
import de.team_franky.allein_daheim.data.entity.embeddable.ChatRequestKey;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ChatRequestRepository extends PagingAndSortingRepository<ChatRequest, ChatRequestKey> {
}
