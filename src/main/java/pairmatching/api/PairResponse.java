package pairmatching.api;

import pairmatching.domain.Pair;

import java.util.ArrayList;
import java.util.List;

public class PairResponse {
    public final List<String> crewNames;

    public PairResponse(List<String> crewNames) {
        this.crewNames = new ArrayList<>(crewNames);
    }

    public String getPairString() {
        return String.join(" : ", crewNames);
    }

    public static PairResponse of(Pair pair) {
        return new PairResponse(pair.getCrewNames());
    }
}
