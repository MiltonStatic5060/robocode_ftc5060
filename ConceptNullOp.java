

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Concept: NullOp", group = "Concept")
//@Disabled
public class ConceptNullOp extends OpMode {

  private ElapsedTime runtime = new ElapsedTime();
  /**
  AccelerationSensor variableName;
  DcMotor variableName;
  Servo variableName;
  ServoController variableName;
  DcMotorController variableName;
  */
  @Override
  public void init() {
    telemetry.addData("Status", "Initialized");
  }
  @Override
  public void loop() {
    telemetry.addData("Status", "Run Time: " + runtime.toString());
  }
}
