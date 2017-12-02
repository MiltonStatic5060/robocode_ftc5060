/* Copyright (c) 2014, 2015 Qualcomm Technologies Inc

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * TeleOp Mode
 * <p>
 *Enables control of the robot via the gamepad
 */
public class RNxt1_1A extends OpMode {

  /*
    Initialize Device Variables here and any other variables to use throughout the program

  */

  DcMotor motorLeft;
  DcMotor motorRight;
  DcMotor motorArm;
  DcMotor motorConv;

  Servo servoClaw;
  Servo servoWrist;

  double wristPosition;
  double clawPosition;
  double powConv;

  @Override
  public void init() {
    /*
      Hardware Map get commands

    */
    motorLeft = hardwareMap.dcMotor.get("left");
    motorRight = hardwareMap.dcMotor.get("right");
    motorArm = hardwareMap.dcMotor.get("arm");
    motorConv = hardwareMap.dcMotor.get("conveyor");

    servoClaw = hardwareMap.servo.get("claw");
    servoWrist = hardwareMap.servo.get("wrist");

  }

  /*
     * Code to run when the op mode is first enabled goes here
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
     */
  @Override
  public void init_loop() {
    //  telemetry.addData("Null Op Init Loop", runtime.toString());
    
    motorRight.setDirection(DcMotor.Direction.REVERSE);
    motorLeft.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    motorRight.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    motorConv.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    motorArm.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    wristPosition = 0.1;
    /*
      Give variables values and set modes and directions of devices
    */

      powConv = 0;
  }

  /*
   * This method will be called repeatedly in a loop
   * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
   */
  @Override
  public void loop() {
    
    
    /*
      Put in here the conditionals and loops that control the robot
      Program Autonomous mode here using "encoders"
      Program Manual mode using gamepad1 and gamepad2 values
      Also add telemetry, just be careful using legacy motor controllers
        and assigning their correct Device Modes
    */
    
    //telemetry.addData("1 Start", "NullOp started at " + startDate);
    //telemetry.addData("2 Status", "running for " + runtime.toString());
  }

  private void r_motorPower(){
    double powLeft  = Range.clip(gamepad1.left_stick_y+gamepad2.left_stick_y,-1,1);
    double powRight = Range.clip(gamepad1.right_stick_y+gamepad2.right_stick_y,-1,1);

    motorRight.setPower(powRight);
    motorLeft.setPower(powLeft);

    
    if(gamepad1.start||gamepad2.start||gamepad2.dpad_up||gamepad1.dpad_up){
      powConv = 0.3;
    }
    if(gamepad1.back||gamepad2.back||gamepad1.dpad_down||gamepad2.dpad_down){
      powConv = -0.3;
    }
    if(gamepad1.guide||gamepad2.guide||gamepad1.right_bumper||gamepad2.right_bumper){
      powConv = 0;
    }
    motorConv.setPower(powConv);
  }
  private void r_motorArm(){
    double powArm = 0;

    powArm = gamepad1.left_trigger+gamepad2.left_trigger;
    if(gamepad1.left_bumper||gamepad2.left_bumper){
      powArm-=0.5;
    }
    
    if(gamepad1.a||gamepad2.a){
      powArm = 0.10;
    } else if(gamepad1.x||gamepad2.x){
      powArm = 0.20;
    } else if(gamepad1.y||gamepad2.y){
      powArm = 0.40;
    } else if(gamepad1.b||gamepad2.b){
      powArm = 0.60;
    }     

    motorArm.setPower(Range.clip(powArm,-1,1));
  }
  private void r_servoArm(){
    
    clawPosition = 0.95;
    double x = gamepad1.right_trigger+gamepad2.right_trigger;
    x = Range.clip(x,0,1);
    x = 0.95-(x/10);
    clawPosition = x;

    if(gamepad1.dpad_right||gamepad2.dpad_right){
      wristPosition = 0.9;
    }
    if(gamepad1.dpad_left||gamepad2.dpad_left){
      wristPosition = 0.1;
    }

    servoWrist.setPosition(wristPosition);
    servoClaw.setPosition(clawPosition);
  }

}
