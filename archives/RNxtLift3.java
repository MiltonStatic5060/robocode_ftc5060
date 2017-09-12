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
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * TeleOp Mode
 * <p>
 *Enables control of the robot via the gamepad
 */
public class RNxtLift3 extends OpMode {
  DcMotor motorLeftF;
  DcMotor motorLeftB;
  DcMotor motorRightF;
  DcMotor motorRightB;

  DcMotor motorArm;
  DcMotor motorBalls;
  //DcMotor motorCatapult; //Only line is here
  //DcMotor motorExpand;

  Servo servoWrist;
  Servo servoClaw;
  Servo servoButton;
  Servo servoGate;
  

  @Override
  public void init() {
    /*
      Hardware Map get commands

    */
    motorLeftF = hardwareMap.dcMotor.get("wheelFL");
    motorLeftB = hardwareMap.dcMotor.get("wheelBL");
    motorRightF = hardwareMap.dcMotor.get("wheelFR");
    motorRightB = hardwareMap.dcMotor.get("wheelBR");

    motorArm = hardwareMap.dcMotor.get("arm");
    motorBalls = hardwareMap.dcMotor.get("balls");

    servoGate = hardwareMap.servo.get("gate");
    servoClaw = hardwareMap.servo.get("claw");
    servoButton = hardwareMap.servo.get("button"); 
    servoWrist = hardwareMap.servo.get("wrist");
  }

  /*
     * Code to run when the op mode is first enabled goes here
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
     */
  @Override
  public void init_loop() {
    motorRightF.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    motorRightB.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    motorLeftB.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    motorLeftF.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);

    motorArm.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    motorBalls.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);

    motorRightF.setDirection(DcMotor.Direction.REVERSE);
    motorRightB.setDirection(DcMotor.Direction.REVERSE);

    /*
      Give variables values and set modes and directions of devices
    */
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
    _wheelControl();
    _servoControl();
    _armControl();
    
    //telemetry.addData("2 Status", "running for " + runtime.toString());
  }

  public void _wheelControl(){
    //motorLeftF
    //motorLeftB
    //motorRightF
    //motorRightB

    // throttle:  left_stick_y ranges from -1 to 1, where -1 is full up,  and 1 is full down
    // direction: left_stick_x  ranges from -1 to 1, where -1 is full left and 1 is full right
    double rightFront, rightBack, leftFront, leftBack;

    double leftY = gamepad1.left_stick_y+gamepad2.left_stick_y;
    double leftX = gamepad1.left_stick_x+gamepad2.left_stick_x;
    double rightY = gamepad1.right_stick_y+gamepad2.right_stick_y;
    double rightX = gamepad1.right_stick_x+gamepad2.right_stick_x;


    //left stick controls
    double throttle = -leftY;
    double direction = leftX;
    double right = throttle - direction;
    double left = throttle + direction;

    right = Range.clip(right, -1, 1);
    left = Range.clip(left, -1, 1);

    //right stick controls
    throttle = -rightY;
    direction = rightX;
    double right1 = throttle - direction;
    double left1 = throttle + direction;

    rightFront = Range.clip(right + right1, -1, 1);
    leftFront  = Range.clip(left  + left1,  -1, 1);
    rightBack  = Range.clip(right + left1,  -1, 1);
    leftBack   = Range.clip(left  + right1, -1, 1);

    motorRightF.setPower(rightFront);
    motorLeftF.setPower(leftFront);
    motorRightB.setPower(rightBack);
    motorLeftB.setPower(leftBack);

    
    telemetry.addData("leftFront,rightFront",leftFront+" , "+rightFront);
    
    telemetry.addData("leftBack,rightBack",leftBack+" , "+rightBack);

  }

  public void _servoControl(){
    //servoGate
    //servoWrist
    //servoClaw
    //servoButton
    double wristPos = 0;
    double gatePos = 0;
    double clawPos = 0;
    double buttonPos = 0;

    //wrist controlled by right bumper
    if(gamepad1.right_bumper||gamepad2.right_bumper){
      wristPos = 1;
    }

    //claw controlled by right trigger
    clawPos = (gamepad1.right_trigger+gamepad2.right_trigger)/2;
    clawPos = Range.clip(clawPos,0,0.5);

    //button pusher controlled by guide button
    if(gamepad1.guide||gamepad2.guide)
      buttonPos = 0.5;

    //gate controlled by right dpad to sync up with catapult arm later on
    if(gamepad1.dpad_right||gamepad2.dpad_right)
      gatePos = 0.5;

    servoWrist.setPosition(wristPos);
    servoGate.setPosition(gatePos);
    servoClaw.setPosition(clawPos);
    servoButton.setPosition(buttonPos);

    telemetry.addData("wristPos",wristPos);
    telemetry.addData("gatePos",gatePos);
    telemetry.addData("clawPos",clawPos);
    telemetry.addData("buttonPos",buttonPos);
  }
  public void _armControl(){
    //motorArm
    //motorBalls

    //arm control
    double negPowArm = (gamepad1.left_bumper||gamepad2.left_bumper) ? 0.5 : 0 ;
    double powArm = (gamepad1.left_trigger+gamepad2.left_trigger)-negPowArm;

    if(gamepad1.a||gamepad2.a){
      powArm = 0.10;
    } else if(gamepad1.x||gamepad2.x){
      powArm = 0.25;
    } else if(gamepad1.y||gamepad2.y){
      powArm = 0.75;
    } else if(gamepad1.b||gamepad2.b){
      powArm = 1;
    } 

    Range.clip(powArm,-1,1);

    //ball elevator control
    double powBalls = 0;
    if(gamepad1.dpad_up||gamepad2.dpad_up)
      powBalls = 1;
    if(gamepad1.dpad_down||gamepad2.dpad_down)
      powBalls = -1;

    motorArm.setPower(powArm);
    motorBalls.setPower(powBalls);

    telemetry.addData("powArm",powArm);
    telemetry.addData("powBallsLift",powBalls);
  }
}
