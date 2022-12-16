package pairmatching.domain;

import pairmatching.domain.crew.Crew;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Pair {
    private final List<Crew> crews;

    public Pair() {
        this.crews = new ArrayList<>();
    }

    public List<String> getCrewNames() {
        List<String> crewNames = crews.stream().map(Crew::getName).collect(Collectors.toList());
        return Collections.unmodifiableList(crewNames);
    }

    public void addCrew(Level level, Crew crew) {
        crews.forEach(c -> c.addCrew(level, crew));
        crew.addCrewAll(level, crews);

        this.crews.add(crew);
    }

    public boolean isMatchingBefore(Level level) {
        for (Crew crew1 : crews) {
            for (Crew crew2 : crews) {
                if (crew1.isMatchingBefore(level, crew2)) {
                    return true;
                }
            }
        }

        return false;
    }
}
