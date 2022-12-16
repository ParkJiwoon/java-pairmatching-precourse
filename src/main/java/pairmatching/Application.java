package pairmatching;

import pairmatching.api.PairMatchingView;
import pairmatching.domain.Course;
import pairmatching.domain.crew.Crew;
import pairmatching.domain.crew.CrewRepository;
import pairmatching.util.FileHelper;

import java.util.List;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        init();
        PairMatchingView.run();
    }

    private static void init() {
        initBackend();
        initFrontend();
    }

    private static void initBackend() {
        List<Crew> crews = FileHelper.readFileLines("src/main/resources/backend-crew.md").stream()
                .map(name -> new Crew(Course.BACKEND, name))
                .collect(Collectors.toList());

        CrewRepository.init(Course.BACKEND, crews);
    }

    private static void initFrontend() {
        List<Crew> crews = FileHelper.readFileLines("src/main/resources/frontend-crew.md").stream()
                .map(name -> new Crew(Course.FRONTEND, name))
                .collect(Collectors.toList());

        CrewRepository.init(Course.FRONTEND, crews);
    }
}
