package pairmatching.api;

import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.domain.Course;

public class MissionRequest {
    private final String courseName;
    private final String levelName;
    private final String missionName;

    public MissionRequest(String courseName, String levelName, String missionName) {
        this.courseName = courseName.trim();
        this.levelName = levelName.trim();
        this.missionName = missionName.trim();
    }

    public String getCourseName() {
        return courseName;
    }

    public String getLevelName() {
        return levelName;
    }

    public String getMissionName() {
        return missionName;
    }

    public String getMissionKey() {
        return String.join(",", missionName, courseName, levelName);
    }

    public Mission toMission() {
        Course course = Course.byName(courseName);
        Level level = Level.byName(levelName);
        return new Mission(missionName, course, level);
    }
}
