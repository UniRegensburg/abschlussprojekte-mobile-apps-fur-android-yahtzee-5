package com.example.kniffel.RollTheDice.ShakeSensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class ShakeSensor implements SensorEventListener {

    /**zeitliche Abstände zwischen den Sensor-Updates*/
    private static final int SLOW_SENSOR_DELAY = 1000000;
    /** Minimale benötigte Beschleunigung auf der y-Achse um Bewegung als Schütteln zu erkennen*/
    private static final float SENSOR_SPEED_THRESHOLD = 2f;
    /** Minimaler zeitlicher Abstand zwischen zwei Aufrufen der Listener-Methoden*/
    private static final long LISTENER_UPDATE_THRESHOLD_IN_MS = 5000;

    private ShakeSensorListener listener;
    private Context context;
    private long lastListenerUpdate;
    private float lastSensorValue;
    private boolean initialSensorChangeReceived = false;

    public ShakeSensor(Context context, ShakeSensorListener listener) {
        this.context = context;
        this.listener = listener;
    }

    /**
     * Listener für den Beschleunigungssensor beim SensorManager registrieren.
     * Zeitliche Abstände zwischen zwei Updates ist eine Sekunde, kann aber verkürzt werden, wenn Zeit zu lang ist.
     */
    public void start() {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager == null) {
            return;
        }
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SLOW_SENSOR_DELAY);
    }

    /**
     * Listeners für den Beschleunigungssensor beim SensorManger deregistrieren.
     */
    public void stop() {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (initialSensorChangeReceived == false) {

            /**
             * Für uns sind nur die y-Achsen-Werte des Beschleunigungssensors wichtig, da die meisten Anwender das Gerät entlang dieser Achse schütteln.
             * Dieser Wert befindt sich im Array an zweiter Stelle.
             * Die Differenz von lastSensorValue und LastListenerUpdate ist die absolute Geschwindigkeitsveränderung.
             */
            lastSensorValue = event.values[1];
            lastListenerUpdate = 0;
            initialSensorChangeReceived = true;
            return;
        }
        float currentSensorValue = event.values[1];
        float speedDelta = Math.abs(lastSensorValue - currentSensorValue);
        lastSensorValue = currentSensorValue;
        /**
         * Überschreitet die absolute Geschwindigkeitsveränderung den angegebenen Schwellwert, wird geprüft, ob eine Benachrichtigung an den ShakeSensorListener notwendig ist
         */
        if (speedDelta > SENSOR_SPEED_THRESHOLD) {
            /** Zeit seit dem letzten Update des ShakeSensorListeners .*/
            long now = System.currentTimeMillis();
            long listenerUpdateDelta = System.currentTimeMillis() - lastListenerUpdate;
            /** Überprüfen, ob der berechnete Wert den Schwellwert übersteig .*/
            if (listenerUpdateDelta > LISTENER_UPDATE_THRESHOLD_IN_MS) {
                listener.onShakingDetected();
                lastListenerUpdate = now;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

