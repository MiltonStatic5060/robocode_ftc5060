package org.firstinspires.ftc.teamcode;
import android.content.Context;
import android.hardware.*; //Sensor, SensorEventListener, SensorManager, SensorEvent
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name = "Concept: SensorOp", group = "Concept")
//@Disabled
public class PhoneGyroTest extends OpMode {
  SensorManager sensorManager;// = (SensorManager) hardwareMap.appContext.getSystemService(Context.SENSOR_SERVICE);
  SensorEventListener sensorListener = new SensorEventListener() {
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
      for(int i = 0; i<sensorEvent.values.length; i++){
        telemetry.addData("Sensor value "+i,sensorEvent.values[i]);
      }

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
  };
  Sensor gyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
  @Override
  public void init() {
    telemetry.addData("Status", "Initialized");
    if (sensorManager != null) {
      if (gyro != null) {
        telemetry.addData("Status", "Sensor Data");
        sensorManager.registerListener(sensorListener, gyro, 2 * 1000 * 1000);
      } else {
        telemetry.addData("Status", "Gyro Sensor Failure");
      }
      telemetry.addData("Status", "Sensor Service Failure");
    }
  }

  @Override
  public void start() {

  }
  @Override
  public void loop() {

    telemetry.update();

  }

  @Override
  public void stop(){
    //sensorManager.unregisterListener(sensorListener);
  }
}
