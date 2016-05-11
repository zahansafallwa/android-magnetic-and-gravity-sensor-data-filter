# android-magnetic-and-gravity-sensor-data-filter
This class filters the android magnetic and gravity sensor data for usable error free data in augmented reality.

For any sensor value changed you just need to call the minPassFilter class like below

     public void onSensorChanged(SensorEvent evt) {
    	if (!computing.compareAndSet(false, true)) return;

        if (evt.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            smooth = minPassFilter.filter(0.5f, 1.0f, evt.values, grav);
            grav[0] = smooth[0];
            grav[1] = smooth[1];
            grav[2] = smooth[2];
        } else if (evt.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            smooth = minPassFilter.filter(2.0f, 4.0f, evt.values, mag);
            mag[0] = smooth[0];
            mag[1] = smooth[1];
            mag[2] = smooth[2];
        }
    }
