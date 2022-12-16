package pairmatching.domain;

import java.util.Objects;

public class Mission {
    private final String name;
    private final Course course;
    private final Level level;

    public Mission(String name, Course course, Level level) {
        this.name = name;
        this.course = course;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public Course getCourse() {
        return course;
    }

    public Level getLevel() {
        return level;
    }

    public String getKey() {
        return String.join(",",name, course.getName(), level.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mission mission = (Mission) o;
        return Objects.equals(name, mission.name) && course == mission.course && level == mission.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, course, level);
    }
}
