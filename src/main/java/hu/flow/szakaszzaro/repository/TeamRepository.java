package hu.flow.szakaszzaro.repository;

import hu.flow.szakaszzaro.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, String> {
}
