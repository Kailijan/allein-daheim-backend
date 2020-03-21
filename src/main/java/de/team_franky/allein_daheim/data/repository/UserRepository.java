package de.team_franky.allein_daheim.data.repository;


import de.team_franky.allein_daheim.data.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
}
