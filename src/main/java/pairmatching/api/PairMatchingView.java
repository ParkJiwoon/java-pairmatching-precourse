package pairmatching.api;

import pairmatching.util.InputView;

import java.util.List;

public class PairMatchingView {
    private static final PairMatchingController pairMatchingController = new PairMatchingController();
    private static boolean showing = true;

    public static void startShowing() {
        showing = true;
    }

    public static void quit() {
        showing = false;
    }

    public static void run() {
        startShowing();

        while (showing) {
            show();
        }
    }

    private static void show() {
        try {
            displayMenu();
            String select = inputSelect();
            runSelectMenu(select);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayMenu() {
        System.out.println("기능을 선택하세요.\n" +
                "1. 페어 매칭\n" +
                "2. 페어 조회\n" +
                "3. 페어 초기화\n" +
                "Q. 종료");
    }

    private static String inputSelect() {
        System.out.println("기능을 선택하세요");
        return InputView.string();
    }

    private static void runSelectMenu(String select) {
        PairMatchingMenu menu = PairMatchingMenu.of(select);
        menu.run();
    }

    /**
     * 1. 페어 매칭
     */
    public static void matchPair() {
        displayMission();

        while (true) {
            MissionRequest request = inputMissionRequest();

            try {
                List<PairResponse> pairs = pairMatchingController.newPairMatching(request);
                displayPairMatchingResult(pairs);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n" + "네 | 아니오");
                String rematchingAnswer = InputView.string();

                if (rematchingAnswer.equals("아니오")) {
                    continue;
                }

                if (rematchingAnswer.equals("네")) {
                    List<PairResponse> pairs = pairMatchingController.renewPairMatching(request);
                    displayPairMatchingResult(pairs);
                }

                break;
            }
        }
    }

    /**
     * 2. 페어 조회
     */
    public static void showPair() {
        displayMission();
        MissionRequest request = inputMissionRequest();
        List<PairResponse> pairs = pairMatchingController.findPairMatching(request);
        displayPairMatchingResult(pairs);
    }

    private static MissionRequest inputMissionRequest() {
        System.out.println("과정, 레벨, 미션을 선택하세요.\n" +
                "ex) 백엔드, 레벨1, 자동차경주");
        String[] selects = InputView.strings();
        String course = selects[0];
        String level = selects[1];
        String mission = selects[2];
        return new MissionRequest(course, level, mission);
    }

    private static void displayPairMatchingResult(List<PairResponse> pairs) {
        System.out.println("페어 매칭 결과입니다.");
        pairs.stream().map(PairResponse::getPairString).forEach(System.out::println);
        System.out.println();
    }

    private static void displayMission() {
        System.out.println("#############################################\n" +
                "과정: 백엔드 | 프론트엔드\n" +
                "미션:\n" +
                "  - 레벨1: 자동차경주 | 로또 | 숫자야구게임\n" +
                "  - 레벨2: 장바구니 | 결제 | 지하철노선도\n" +
                "  - 레벨3: \n" +
                "  - 레벨4: 성능개선 | 배포\n" +
                "  - 레벨5: \n" +
                "############################################");
    }

    /**
     * 3. 페어 초기화
     */
    public static void resetPair() {
        pairMatchingController.resetPairMatching();
        System.out.println("초기화 되었습니다.");
        System.out.println();
    }
}
