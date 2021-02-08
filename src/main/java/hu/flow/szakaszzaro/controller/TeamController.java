package hu.flow.szakaszzaro.controller;

import hu.flow.szakaszzaro.entity.Team;
import hu.flow.szakaszzaro.exception.ValidationException;
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
@RequestMapping("/api/teams")
public class TeamController {

    private static final Logger log = LoggerFactory.getLogger(TeamController.class);

    private TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Team createNewTeam(@RequestBody Team team) {
        log.info("Received create new team request {} ... ", team);
        try {
            Team newTeam = teamService.createNewTeam(team);
            log.debug("The new team is: {}", newTeam);
            return newTeam;
        } catch (ValidationException e) {
            log.error("Error when creating new team: " + e.getMessage());
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Team updateTeam(@RequestBody Team team, @PathVariable ("id")  String id) {
        log.info("Received update team request {} ... ", team);
        try {
            Team updateTeam = teamService.updateTeam(team, id);
            log.debug("The new team is: {}", updateTeam);
            return updateTeam;
        } catch (ValidationException e) {
            log.error("Error when updating team: " + e.getMessage());
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Team> getTeamList(@RequestParam(value = "page", required = false) Optional<Integer> page,
                              @RequestParam(value = "limit", required = false) Optional<Integer> limit) {
        log.info("Retrieving teams (page: {}, limit: {}) ...", page.isPresent() ? page.get() : "n.a.", limit.orElse(10));
        List<Team> teamList;
        if (page.isPresent()) {
            teamList = teamService.listTeams(PageRequest.of(page.get(), limit.orElse(10)));
        } else {
            teamList = teamService.listTeams();
        }
        log.debug("Found teams: {}", teamList.size());
        return teamList;
    }

    @GetMapping ("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Team getTeamById(@PathVariable ("id") String id) {
        log.info("Received find request by id: {}", id);

        Optional <Team> foundTeamById = teamService.getTeamById(id);
        if (foundTeamById.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return foundTeamById.get();
    }

}
