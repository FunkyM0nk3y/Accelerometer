package com.example.funkym0nk3y.acelerometer;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

  private SensorManager manejador;
  private Sensor sensorAcelerometro;
  private TextView axisX, axisY, axisZ;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    axisX = (TextView) this.findViewById(R.id.axisX);
    axisY = (TextView) this.findViewById(R.id.axisY);
    axisZ = (TextView) this.findViewById(R.id.axisZ);

    manejador = (SensorManager) this.getSystemService(this.SENSOR_SERVICE);
    sensorAcelerometro = manejador.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    this.manejador.registerListener(this, this.sensorAcelerometro, SensorManager.SENSOR_DELAY_NORMAL);
  }

  public void  onSensorChanged(SensorEvent event) {
    if ( event.sensor.getType() == Sensor.TYPE_ACCELEROMETER ){
      actualiza(event);
    }
  }

  @Override
  public void onAccuracyChanged(Sensor sensor, int accuracy) {

  }

  private void actualiza(SensorEvent evento) {
    float[] values = evento.values;
    float x = values[0];
    float y = values[1];
    float z = values[2];

    this.axisX.setText("Axis X:   " + x);
    this.axisY.setText("Axis Y:   " + y);
    this.axisZ.setText("Axis Z:   " + z);
  }

  @Override
  protected void onResume() {
    super.onResume();
    this.manejador.registerListener(this, this.sensorAcelerometro, SensorManager.SENSOR_DELAY_NORMAL);
  }

  @Override
  protected void onPause() {
    super.onPause();
    this.manejador.unregisterListener(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if ( id == R.id.action_settings ) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
