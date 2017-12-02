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
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * TeleOp Mode
 * <p>
 * Enables control of the robot via the gamepad
 */
//Robot Control 1 - on opmode
public class RNxtLiftB_1 extends OpMode {
DcMotor motorLeft;
DcMotor motorRight;
DcMotor arm;
DcMotor bucket;
Servo hammer;

double powLeft;

double powRight;

double powArm;

double posHam;

boolean reverse1;

double powBucket;
  /*
   * Code to run when the op mode is first enabled goes here
   * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#init()
   */
  @Override
  public void init() {
    motorLeft = hardwareMap.dcMotor.get("left");
    motorRight = hardwareMap.dcMotor.get("right");
    hammer = hardwareMap.servo.get("hammer");
    arm = hardwareMap.dcMotor.get("arm");
    bucket = hardwareMap.dcMotor.get("bucket");
  }

  /*
   * Code that runs repeatedly when the op mode is first enabled goes here
   * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#init_loop()
   */
  @Override
  public void init_loop() {
    motorRight.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    motorLeft.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    arm.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    motorRight.setDirection(DcMotor.Direction.REVERSE);
    bucket.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    posHam = 0;
    reverse1 = true;
  }
  /*
   * This method will be called repeatedly in a loop
   * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
   */
  @Override
  public void loop() {
    //makes sure that motors stay at rest when controls are inactive
    //motor power is -1 to 1 (double)
    powArm  = 0;
    powRight= 0;
    powLeft = 0;
    powBucket = 0;
    // The op mode should only use "write" methods (setPower, setMode, etc) while in
    // WRITE_ONLY mode or SWITCHING_TO_WRITE_MODE

    //restrict value to between -1 and 1
    powLeft  = Range.clip(gamepad1.left_stick_y+gamepad2.left_stick_y,-1,1);
    powRight = Range.clip(gamepad1.right_stick_y+gamepad2.right_stick_y,-1,1);
    
    if(gamepad1.back||gamepad2.back){
      reverse1 = !reverse1;
    }


    //If the guide button is pressed that gamepad's triggers cease to affect powArm and now affect powBucket
    powArm = Range.clip( ((gamepad1.guide)?0:(gamepad1.left_trigger-gamepad1.right_trigger)) + ((gamepad1.guide)?0:(gamepad2.left_trigger-gamepad2.right_trigger)),-1,1);
    
    if(gamepad2.guide){
      powBucket = Range.clip(gamepad2.left_trigger-gamepad2.right_trigger,-1,1);
    }

    if(gamepad1.guide){
      powBucket = Range.clip(gamepad1.left_trigger-gamepad1.right_trigger,-1,1);
    }

    //a is 1, b is 2, c is 3, d is 4 in rank of strength
    if (gamepad1.a || gamepad2.a) {
      powArm = (reverse1) ? 0.10 : -0.10;
    } else if (gamepad1.x || gamepad2.x) {
      powArm = (reverse1) ? 0.25 : -0.25;
    } else if (gamepad1.y || gamepad2.y) {
      powArm = (reverse1) ? 0.50 : -0.75;
    } else if (gamepad1.b || gamepad2.b) {
      powArm = (reverse1) ? 0.75 : -0.75;
    } else if (gamepad1.dpad_down || gamepad2.dpad_down) {
      powArm = (reverse1) ? 0.05 : -0.05;
    } else if (gamepad1.dpad_left || gamepad2.dpad_left) {
      powArm = (reverse1) ? 0.10 : -0.10;
    } else if (gamepad1.dpad_up || gamepad2.dpad_up) {
      powArm = (reverse1) ? 0.15 : -0.15;
    }else if (gamepad1.dpad_right || gamepad2.dpad_right) {
      powArm = (reverse1) ? 0.20 : -0.20;
    }

    if(gamepad1.left_bumper||gamepad2.left_bumper){
      posHam = 0;
    } else if(gamepad1.right_bumper||gamepad2.right_bumper){
      posHam = 1;
    }



    motorLeft.setPower(Range.clip(powLeft,-1,1));
    motorRight.setPower(Range.clip(powRight,-1,1));
    arm.setPower(Range.clip(powArm,-1,1));
    hammer.setPosition(Range.clip(posHam,0,1));
    bucket.setPower(Range.clip(powBucket,-1,1));

    //data that is shown on the Driver station phone
    telemetry.addData("motor ( left ) power: ", powLeft);
    telemetry.addData("motor ( right ) power: ", powRight);
    telemetry.addData("Hammer ( hammer ) position: ",posHam);
    telemetry.addData("motor ( arm ) power: ", powArm);
    telemetry.addData("motor ( bucket ) power: ", powBucket);


    /**
    *
    *
    *
    */
    

  }
}
