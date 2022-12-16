package pairmatching.domain.crew;

import pairmatching.domain.Level;
import pairmatching.domain.Course;

import java.util.*;

public class Crew {
    private final Course course;
    private final String name;
    private final Map<Level, Set<Crew>> crews;

    public Crew(Course course, String name) {
        this.course = course;
        this.name = name;

        this.crews = new HashMap<>();
        for (Level level : Level.values()) {
            crews.put(level, new HashSet<>());
        }
    }

    public String getName() {
        return name;
    }

    public void addCrew(Level level, Crew crew) {
        Set<Crew> crewSet = crews.getOrDefault(level, new HashSet<>());
        crewSet.add(crew);
        crews.put(level, crewSet);
    }

    public void addCrewAll(Level level, Collection<Crew> crewList) {
        Set<Crew> crewSet = crews.getOrDefault(level, new HashSet<>());
        crewSet.addAll(crewList);
        crews.put(level, crewSet);
    }

    public boolean isMatchingBefore(Level level, Crew crew) {
        return crews.get(level).contains(crew);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crew crew = (Crew) o;
        return Objects.equals(name, crew.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Crew{" +
                "name='" + name + '\'' +
                '}';
    }
}
