package pairmatching.domain;

import java.util.Collections;
import java.util.List;

public class PairMatching {
    private final Mission mission;
    private final List<Pair> pairs;

    public PairMatching(Mission mission, List<Pair> pairs) {
        this.mission = mission;
        this.pairs = pairs;
    }

    public List<Pair> getPairs() {
        return Collections.unmodifiableList(pairs);
    }

    public String getMissionKey() {
        return mission.getKey();
    }

    public boolean isNotMatchingBefore() {
        return !isMatchingBefore();
    }

    private boolean isMatchingBefore() {
        for (Pair pair : pairs) {
            if (pair.isMatchingBefore(mission.getLevel())) {
                return true;
            }
        }

        return false;
    }
}
