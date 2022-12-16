package pairmatching.domain.crew;

import pairmatching.domain.Course;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CrewRepository {
    private static final Map<Course, List<Crew>> crewDictionary = new LinkedHashMap<>();
    private static final Map<String, Crew> CREW_DICTIONARY = new LinkedHashMap<>();

    public static void init(Course course, List<Crew> crews) {
        crewDictionary.put(course, crews);
    }

    public static List<Crew> findByCourse(Course course) {
        return Collections.unmodifiableList(crewDictionary.get(course));
    }

    public static void save(Crew crew) {
        CREW_DICTIONARY.put(crew.getName(), crew);
    }

    public static Crew findByName(String name) {
        return CREW_DICTIONARY.get(name);
    }
}
