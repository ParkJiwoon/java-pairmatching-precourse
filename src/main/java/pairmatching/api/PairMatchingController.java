package pairmatching.api;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.*;
import pairmatching.domain.crew.Crew;
import pairmatching.domain.crew.CrewRepository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class PairMatchingController {

    public List<PairResponse> newPairMatching(MissionRequest missionRequest) {
        PairMatching pairMatching = PairMatchingRepository.findByMissionKey(missionRequest.getMissionKey());

        if (pairMatching != null) {
            throw new IllegalArgumentException("매칭 정보가 있습니다. 다시 매칭하시겠습니까?");
        }

        return generateNewPairMatching(missionRequest);
    }

    public List<PairResponse> renewPairMatching(MissionRequest missionRequest) {
        PairMatchingRepository.deleteByMissionKey(missionRequest.getMissionKey());
        return generateNewPairMatching(missionRequest);
    }


    private List<PairResponse> generateNewPairMatching(MissionRequest missionRequest) {
        Mission mission = missionRequest.toMission();

        // 선택한 과정의 크루들 가져오기
        List<Crew> crews = CrewRepository.findByCourse(mission.getCourse());

        // 학생 셔플 후 페어 매칭
        List<Crew> shuffledCrews = Randoms.shuffle(crews);
        List<Pair> pairs = matchPair(shuffledCrews, mission.getLevel());
        PairMatching newPairMatching = new PairMatching(mission, pairs);

        for (int i = 0; i < 3; i++) {
            if (newPairMatching.isNotMatchingBefore()) {
                PairMatchingRepository.save(newPairMatching);

                return newPairMatching.getPairs().stream()
                        .map(PairResponse::of)
                        .collect(Collectors.toList());
            }
        }

        throw new IllegalArgumentException("[ERROR] 페어 매칭 3회 실패");
    }

    private List<Pair> matchPair(List<Crew> crews, Level level) {
        Queue<Crew> queue = new LinkedList<>(crews);
        List<Pair> pairs = new ArrayList<>();

        while (!queue.isEmpty()) {
            Pair pair = new Pair();

            for (int i = 0; i < 2 && !queue.isEmpty(); i++) {
                pair.addCrew(level, queue.poll());
            }

            if (queue.size() == 1) {
                pair.addCrew(level, queue.poll());
            }

            pairs.add(pair);
        }

        return pairs;
    }

    public List<PairResponse> findPairMatching(MissionRequest missionRequest) {
        PairMatching pairMatching = PairMatchingRepository.findByMissionKey(missionRequest.getMissionKey());

        if (pairMatching == null) {
            throw new IllegalArgumentException("매칭 정보가 없습니다.");
        }

        return pairMatching.getPairs().stream()
                .map(PairResponse::of)
                .collect(Collectors.toList());
    }

    public void resetPairMatching() {
        PairMatchingRepository.reset();
    }
}
