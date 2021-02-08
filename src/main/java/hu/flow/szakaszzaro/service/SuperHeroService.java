package hu.flow.szakaszzaro.service;

import hu.flow.szakaszzaro.entity.SuperHero;
import hu.flow.szakaszzaro.repository.SuperHeroRepository;
import hu.flow.szakaszzaro.repository.TeamRepository;
import org.hibernate.cfg.NotYetImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SuperHeroService {

    private static final Logger log = LoggerFactory.getLogger(SuperHeroService.class);

    private final SuperHeroRepository superHeroRepository;

    @Autowired

    public SuperHeroService(SuperHeroRepository superHeroRepository) {
        this.superHeroRepository = superHeroRepository;
    }

    public SuperHero createNewSuperHero(SuperHero superHero) {
        throw new NotYetImplementedException("");
    }

    public SuperHero updateSuperHero(SuperHero superHero, String id) {
        throw new NotYetImplementedException("");
    }

    public List<SuperHero> listSuperHeros(PageRequest of) {
        throw new NotYetImplementedException("");
    }

    public List<SuperHero> listSuperHeros() {
        throw new NotYetImplementedException("");
    }

    public Optional<SuperHero> getSuperHeroById(String id) {
        throw new NotYetImplementedException("");
    }
}
