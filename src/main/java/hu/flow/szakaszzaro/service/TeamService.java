package hu.flow.szakaszzaro.service;

import hu.flow.szakaszzaro.entity.Team;
import hu.flow.szakaszzaro.entity.Universe;
import hu.flow.szakaszzaro.exception.ValidationException;
import hu.flow.szakaszzaro.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TeamService {

    private static final Logger log = LoggerFactory.getLogger(TeamService.class);

    private final TeamRepository teamRepository;

    @Autowired

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    public Team createNewTeam(Team team) throws ValidationException {
        log.info("Creating new team based on {} ... ", team);

        if (team.getName() == null || team.getName().equals("")) {
            log.error("Name or Universe must have a value ({name})");
            throw new ValidationException("Name or Universe must have a value");
        }
        Team newTeam = teamRepository.save(team);
        log.debug("Created team: {}", team);
        return newTeam;
    }


    public Team updateTeam(Team team, String id) throws ValidationException {
        log.info("Updating food based on {} ... ", team);

        if (team == null) {
            log.error("Food doesn't exist yet");
            throw new ValidationException("Please create team first");
        }
        Team updatedTeam = teamRepository.save(team);
        log.debug("Updated food: {}", team);
        return updatedTeam;
    }

    public List<Team> listTeams(Pageable pageable) {
        log.info("Listing teams (page information: {}) ...", pageable);
        Page<Team> teamPage = teamRepository.findAll(pageable);
        List<Team> teamList = teamPage.getContent();
        log.debug("Total count: {}, total pages: {}", teamPage.getTotalElements(), teamPage.getTotalPages());
        return teamList;
    }

    public List<Team> listTeams() {
        log.info("Listing all teams ...");
        List<Team> teamList = teamRepository.findAll();
        log.debug("Total count: {}, ", teamList.size());
        return teamList;
    }

    public Optional<Team> getTeamById(String id) {
        log.info("Listing team by id ...");
        return teamRepository.findById(id);
    }
}
