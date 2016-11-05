package nt.proximity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.widget.TextView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    TextView counterText;
    private SensorManager mSensorManager;
    private Sensor mProximity;
    int counter = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Get an instance of the sensor service, and use that to get an instance of
        // a particular sensor.
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        counterText = (TextView) findViewById(R.id.counterView);

        mSensorManager.registerListener(this,mProximity,SensorManager.SENSOR_DELAY_NORMAL);

    }
    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event){
        //float distance = event.values[0];
        //Do something with this sensor data.
        if (event.values[0] == 0) {}
        else {
            counter++;
            counterText.setText(""+counter);
        }
    }

    @Override
    protected void onResume() {
        //Register a listener for the sensor.
        super.onResume();
        counter--;
        mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
