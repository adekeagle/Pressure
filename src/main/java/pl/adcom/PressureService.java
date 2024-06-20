package pl.adcom;

import java.util.List;

public interface PressureService {
    void addPressure(Pressure pressure);
    Pressure getPressureById(long id);
    List<Pressure> getAllPressuresForUser(long userId);
    void updatePressure(Pressure pressure);
    void deletePressure(long id);
}
