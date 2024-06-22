package pl.adcom;

import java.time.LocalTime;

public class Pressure {

    private long id;
    private int upperPressure;
    private int lowerPressure;
    private int pulse;
    private LocalTime startedMeassure;
    private long userId;

    public Pressure(int upperPressure, int lowerPressure, int pulse, LocalTime startedMeassure, long userId) {
        this.upperPressure = upperPressure;
        this.lowerPressure = lowerPressure;
        this.pulse = pulse;
        this.startedMeassure = startedMeassure;
        this.userId = userId;
    }

    public Pressure(long id, int upperPressure, int lowerPressure, int pulse, LocalTime startedMeassure, long userId) {
        this.id = id;
        new Pressure(upperPressure, lowerPressure, pulse, startedMeassure, userId);
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalTime getStartedMeassure() {
        return startedMeassure;
    }

    public void setStartedMeassure(LocalTime startedMeassure) {
        this.startedMeassure = startedMeassure;
    }

    public int getPulse() {
        return pulse;
    }

    public void setPulse(int pulse) {
        this.pulse = pulse;
    }

    public int getLowerPressure() {
        return lowerPressure;
    }

    public void setLowerPressure(int lowerPressure) {
        this.lowerPressure = lowerPressure;
    }

    public int getUpperPressure() {
        return upperPressure;
    }

    public void setUpperPressure(int upperPressure) {
        this.upperPressure = upperPressure;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Pressure{" +
                "id=" + id +
                ", upperPressure=" + upperPressure +
                ", lowerPressure=" + lowerPressure +
                ", pulse=" + pulse +
                ", startedMeassure=" + startedMeassure +
                ", userId=" + userId +
                '}';
    }
}
