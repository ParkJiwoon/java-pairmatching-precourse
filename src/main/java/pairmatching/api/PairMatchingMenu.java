package pairmatching.api;

import java.util.Arrays;

public enum PairMatchingMenu {
    MATCH_PAIR("1", "페어 매칭", PairMatchingView::matchPair),
    SHOW_PAIR("2", "페어 조회", PairMatchingView::showPair),
    RESET_PAIR("3", "페어 초기화", PairMatchingView::resetPair),
    QUIT("!", "종료", PairMatchingView::quit),
    ;

    private final String index;
    private final String krName;
    private final Runnable runnable;

    PairMatchingMenu(String index, String krName, Runnable runnable) {
        this.index = index;
        this.krName = krName;
        this.runnable = runnable;
    }

    public String getIndex() {
        return index;
    }

    public static PairMatchingMenu of(String input) {
        return Arrays.stream(values())
                .filter(value -> value.getIndex().equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 선택할 수 없는 기능입니다."));
    }

    public void run() {
        runnable.run();
    }
}
