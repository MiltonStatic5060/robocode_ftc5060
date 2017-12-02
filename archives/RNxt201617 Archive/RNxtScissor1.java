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
public class RNxtScissor1 extends OpMode {
DcMotor motorLeft;
DcMotor motorRight;
DcMotor motorLift;
Servo   claw;
double   clawPos;
double   clawDelta;
  /*
   * Code to run when the op mode is first enabled goes here
   * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#init()
   */
  @Override
  public void init() {
    motorLeft = hardwareMap.dcMotor.get("left");
    motorRight = hardwareMap.dcMotor.get("right");
    motorLift = hardwareMap.dcMotor.get("lift");
    claw = hardwareMap.servo.get("claw");
    
  }

  /*
   * Code that runs repeatedly when the op mode is first enabled goes here
   * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#init_loop()
   */
  @Override
  public void init_loop() {
    motorRight.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    motorLeft.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    clawDelta = 0.01;
    clawPos = 0.1;
  }
  /*
   * This method will be called repeatedly in a loop
   * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
   */
  @Override
  public void loop() {
    
    // The op mode should only use "write" methods (setPower, setMode, etc) while in
    // WRITE_ONLY mode or SWITCHING_TO_WRITE_MODE

    //left stick controls left motor
    motorLeft.setPower(gamepad1.left_stick_y);
    
    //right stick controls right motor
    //negative value for right so it's reversed
    motorRight.setPower(-gamepad1.right_stick_y);

    //the two lower shoulder (trigger) buttons in the back control the lift
    motorLift.setPower( Range.clip((gamepad1.left_trigger) - (gamepad1.right_trigger),-1,1) );

    //the Directional Pad up and down will increase or decrease the speed of the claw's servo
    if(gamepad1.dpad_up)
      clawDelta+=0.01;

    if(gamepad1.dpad_down)
      clawDelta-=0.01;

    //keep range of claw speed (Delta) within .01 (base speed) to .1 (ten times faster)
    clawDelta = Range.clip(clawDelta,0.01,0.1);

    //two upper shoulder (bumper) buttons in the back control the claw
    if(gamepad1.left_bumper)
      clawPos+=clawDelta;

    if(gamepad1.right_bumper)
      clawPos-=clawDelta;

    //keep claw's position within limits of 0 to 1
    clawPos = Range.clip(clawPos,0,1);
    //set the claw's position to the variable
    claw.setPosition(clawPos);

    //data that is shown on the Driver station phone
    telemetry.addData("motor ( left ) power: ", gamepad1.left_stick_y);
    telemetry.addData("motor ( right ) power: ", gamepad1.right_stick_y);
    telemetry.addData("motor ( lift ) power: ", Range.clip( (gamepad1.left_trigger)-(gamepad1.right_trigger) ,-1 , 1 ));
    telemetry.addData("servo ( claw ) position: ", clawPos );
    telemetry.addData("servo ( claw ) speed: ", clawDelta );

  }
}
