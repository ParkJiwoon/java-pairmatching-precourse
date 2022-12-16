package pairmatching.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class PairMatchingRepository {
    private static final Map<String, PairMatching> PAIR_MATCHING_DICTIONARY = new LinkedHashMap<>();

    public static void save(PairMatching pairMatching) {
        PAIR_MATCHING_DICTIONARY.put(pairMatching.getMissionKey(), pairMatching);
    }

    public static PairMatching findByMissionKey(String missionKey) {
        return PAIR_MATCHING_DICTIONARY.get(missionKey);
    }

    public static void deleteByMissionKey(String missionKey) {
        PAIR_MATCHING_DICTIONARY.remove(missionKey);
    }

    public static void reset() {
        PAIR_MATCHING_DICTIONARY.clear();
    }
}
