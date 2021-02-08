package hu.flow.szakaszzaro.controller;

import hu.flow.szakaszzaro.entity.SuperHero;
import hu.flow.szakaszzaro.entity.Team;
import hu.flow.szakaszzaro.exception.ValidationException;
import hu.flow.szakaszzaro.service.SuperHeroService;
import hu.flow.szakaszzaro.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/super-hero")
public class SuperHeroController {

    private static final Logger log = LoggerFactory.getLogger(SuperHeroController.class);

    private SuperHeroService superHeroService;

    @Autowired
    public SuperHeroController(SuperHeroService superHeroService) {
        this.superHeroService = superHeroService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public SuperHero createNewSuperHero(@RequestBody SuperHero superHero) {
        log.info("Received create new superHero request {} ... ", superHero);
        try {
            SuperHero newSuperHero = superHeroService.createNewSuperHero(superHero);
            log.debug("The new superHero is: {}", newSuperHero);
            return newSuperHero;
        } catch (ValidationException e) {
            log.error("Error when creating new superHero: " + e.getMessage());
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SuperHero updateSuperHero(@RequestBody SuperHero superHero, @PathVariable ("id")  String id) {
        log.info("Received update superHero request {} ... ", superHero);
        try {
            SuperHero updateSuperHero = superHeroService.updateSuperHero(superHero, id);
            log.debug("The new superHero is: {}", updateSuperHero);
            return updateSuperHero;
        } catch (ValidationException e) {
            log.error("Error when updating superHero: " + e.getMessage());
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SuperHero> getSuperHeroList(@RequestParam(value = "page", required = false) Optional<Integer> page,
                                  @RequestParam(value = "limit", required = false) Optional<Integer> limit) {
        log.info("Retrieving teams (page: {}, limit: {}) ...", page.isPresent() ? page.get() : "n.a.", limit.orElse(10));
        List<SuperHero> superHeroList;
        if (page.isPresent()) {
            superHeroList = superHeroService.listSuperHeros(PageRequest.of(page.get(), limit.orElse(10)));
        } else {
            superHeroList = superHeroService.listSuperHeros();
        }
        log.debug("Found SuperHeros: {}", superHeroList.size());
        return superHeroList;
    }

    @GetMapping ("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SuperHero getSuperHeroById(@PathVariable ("id") String id) {
        log.info("Received find request by id: {}", id);

        Optional <SuperHero> foundSuperHeroById = superHeroService.getSuperHeroById(id);
        if (foundSuperHeroById.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return foundSuperHeroById.get();
    }
}
