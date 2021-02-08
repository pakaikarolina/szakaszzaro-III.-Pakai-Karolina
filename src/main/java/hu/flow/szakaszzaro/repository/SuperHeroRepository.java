package hu.flow.szakaszzaro.repository;

import hu.flow.szakaszzaro.entity.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperHeroRepository extends JpaRepository <SuperHero, String> {
}
