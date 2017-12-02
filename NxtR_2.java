

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Concept: NXTR2", group = "Concept")
//@Disabled
public class NxtR_2 extends OpMode {
  DcMotor motor1;
  DcMotorController motCont1;
  
  @Override
  public void init() {
    motor1 = hardwareMap.dcMotor.get("motor1");
    motCont1 = hardwareMap.dcMotorController.get("motCont1");
    telemetry.addData("Status", "Initialized");
  }
  @Override
  public void loop() {
    motor1.setPower(gamepad1.left_stick_y);
    if(gamepad1.right_bumper){
      motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    if(gamepad1.left_bumper){
      motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    if(gamepad1.y){
      motor1.setTargetPosition(1440);
      
      
    }
    if(gamepad1.x){
      motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    telemetry.addData("Target: ",motor1.getTargetPosition());
    telemetry.addData("Motor 1",motCont1.getMotorCurrentPosition(1));
  }
}
