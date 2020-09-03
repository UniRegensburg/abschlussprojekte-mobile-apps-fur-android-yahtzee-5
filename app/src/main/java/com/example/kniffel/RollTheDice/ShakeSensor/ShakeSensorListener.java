package com.example.kniffel.RollTheDice.ShakeSensor;
/**
 * Der ShakeSensor informiert Komponenten, die dieses Interface implementieren, über relevante Bewegungen
 * des Geräts.
 */
public interface ShakeSensorListener {
    void onShakingDetected();
}
