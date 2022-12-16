package pairmatching.domain;

import java.util.Arrays;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private final String name;

    Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Course byName(String name) {
        return Arrays.stream(values())
                .filter(value -> value.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 과정 이름입니다."));
    }
}
