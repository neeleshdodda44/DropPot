package com.example.ndodda.droppot;

import android.app.Activity;

/**
 * Created by ndodda on 3/7/17.
 */


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

    private TextView xText, yText, zText, vText, mText;
    private Sensor mySensor;
    private SensorManager SM;
    private int tick;
    private int ticked=18;
    private double g = 9.8;
    private double fallThreshold=g/3;
    private double spikeThreshold=3*9.8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Create our Sensor Manager
        SM = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Accelerometer Sensor
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Register sensor Listener
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_GAME);

        // Assign TextView
        xText = (TextView) findViewById(R.id.xText);
        yText = (TextView) findViewById(R.id.yText);
        zText = (TextView) findViewById(R.id.zText);
        vText = (TextView) findViewById(R.id.vText);
        mText = (TextView) findViewById(R.id.mText);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not in use
    }

    @Override
//    public void onSensorChanged(SensorEvent event) {
//        xText.setText("X: " + event.values[0]);
//        yText.setText("Y: " + event.values[1]);
//        zText.setText("Z: " + event.values[2]);
//    }

    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        xText.setText("X: " + x);
        yText.setText("Y: " + y);
        zText.setText("Z: " + z);
        double acceleration = Math.sqrt(Math.pow(x,2)+Math.pow(y,2)+Math.pow(z,2));
        vText.setText("V: " + acceleration);
        if(acceleration < fallThreshold) {
            tick ++;
        }
        if(tick > ticked) {
            if(acceleration > spikeThreshold) {
                mText.setText("M" + acceleration);
            }
        }
        if(acceleration > fallThreshold) {
            if(tick > 0) {
                tick --;
            }
        }
    }

    private void triggerFall() {

    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
    public static void main(String[] args) {
        System.out.println("kappa");
    }
}

