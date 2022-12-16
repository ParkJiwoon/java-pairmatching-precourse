package pairmatching.domain;

import java.util.Arrays;

public enum Level {
    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    private final String name;

    Level(String name) {
        this.name = name;
    }

    // 추가 기능 구현

    public String getName() {
        return name;
    }

    public static Level byName(String name) {
        return Arrays.stream(values())
                .filter(value -> value.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 레벨 이름입니다."));
    }
}
