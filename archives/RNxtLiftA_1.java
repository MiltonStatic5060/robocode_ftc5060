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
public class RNxtLiftA_1 extends OpMode {
DcMotor motorLeft;
DcMotor motorRight;
DcMotor arm;
Servo hammer;

double powLeft;

double powRight;

double powArm;

double posHam;

boolean reverse1;
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
    posHam = 0;
    reverse1 = true;
  }
  /*
   * This method will be called repeatedly in a loop
   * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
   */
  @Override
  public void loop() {
    
    powLeft = 0.1;
    powRight = 0.1;


    motorLeft.setPower(Range.clip(powLeft,-1,1));
    motorRight.setPower(Range.clip(powRight,-1,1));
    arm.setPower(Range.clip(powArm,-1,1));
    hammer.setPosition(Range.clip(posHam,0,1));

    //data that is shown on the Driver station phone
    telemetry.addData("motor ( left ) power: ", powLeft);
    telemetry.addData("motor ( right ) power: ", powRight);
    telemetry.addData("Hammer ( hammer ) position: ",posHam);
    telemetry.addData("motor ( arm ) power: ", powArm);


    /**
    *
    *
    *
    */
    

  }
}
