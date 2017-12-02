package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.robot.Robot;

import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;

//Ryan Waz Here, with ED

/**
 * Example OpMode
 */
@TeleOp(name="Relic Recovery Teleop Main", group="Relic")
//@Disabled //This disables the opmode on the phone. 
//Comment out to make it visible on phone

public class MotorTest extends OpMode {

    DcMotor motorBlockLift;
    DcMotor motor
    DcMotor leftF;


    public void init(){
        motor1 = hardwareMap.dcMotor.get("first_motor");

leftF = hardwareMap.get(DcMotor.class,"leftF");
    } //Closes the init()

    public void loop(){
        if(gamepad1.a){
            motor1.setPower(-0.5);
        } else {
            motor1.setPower(0);
        }
    } //Closes the loop()

} //Closes the class





 public class MotorTest extends OpMode {
            DcMotor motorLeft;
            DcMotor motorRight;
     DcMotor leftF;
     DcMotor rightF;
     DcMotor leftB;
     DcMotor rightB;

            public void init(){
                motorLeft = hardwareMap.dcMotor.get("motor_left");
                motorRight = hardwareMap.dcMotor.get("motor_right");

                motorRight.setDirection(DcMotor.Direction.REVERSE);
            }

            public void loop(){
                //TANK MODE
                //motorLeft.setPower(gamepad1.left_stick_y);
                //motorRight.setPower(gamepad1.right_stick_y);
//leftF.setPower(gamepad1.left_stick_y);
//rightF.setPower(gamepad1.right_stick_y);
//Good job matt
               // :)
                //ONE STICK DRIVING
                double drivePower = -(gamepad2.left_stick_y);
                double driveDirection = gamepad2.left_stick_x;
double drivePowerR = -(gamepad2.left_stick_y);
double driveDirectionR = gamepad2.left_stick_x;

                double leftF =   Range.clip( drivePower + driveDirection       + drivePowerR  + driveDirectionR , -1 , 1 );
                double rightF = Range.clip( drivePower  - driveDirection       + drivePowerR - driveDirectionR , -1 , 1 );
double leftB =   Range.clip( drivePower  - driveDirection       + drivePowerR + driveDirectionR , -1 , 1 );
double rightB = Range.clip( drivePower  + driveDirection       + drivePowerR - driveDirectionR , -1 , 1 );



                leftF.setPower(leftF);
                rightF.setPower(rightF);
 leftB.setPower(leftB);
rightB.setPower(rightB);
            }
        }
    