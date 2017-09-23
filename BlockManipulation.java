package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.*;
/**
 * DcMotor
 * Servo
 * DcMotorController
 * ServoController
 * AccelerationSensor
 * LightSensor
 * ColorSensor
 * CRServo is Continuous Rotation Servo
 * CompassSensor
 * DeviceInterfaceModule
 * DistanceSensor
 * Gyroscope
 * GyroSensor
 * I2cController
 * I2cDevice
 * IrSeekerSensor
 * LegacyModule
 * TouchSensor
 * UltrasonicSensor
 */
/**
 * gamepad1 or gamepad2
 * true/false:
 * a,b,x,y,left_bumper,right_bumper
 * dpad_up,dpad_down,dpad_right,dpad_left
 * left_stick_button,right_stick_button
 * guide,start,back
 * 
 * -1/1 (up is -1, and left is -1)
 * left_stick_y,left_stick_x 
 * right_stick_y,right_stick_x
 * 
 * 0/1
 * left_trigger,right_trigger
 * 
 */

@TeleOp(name = "Concept: NullOp", group = "Concept")
//@Autonomous(name = "Concept: NullOp", group = "Concept")
//@Disabled
public class TeleOpTemplate extends OpMode {
    DcMotor blockLift;
    Servo blockGrabL;
    Servo blockGrabR;
    TouchSensor blockTouch;

    @Override
    public void init(){

        blockLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public void start(){
        //Calibrate BlockLift encoder
        while(!blockTouch.isPressed()){
            blockLift.setPower(-0.3);
            if(blockTouch.isPressed()){
                break;
            }
        }
        blockLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    @Override
    public void loop(){

    }
    public void r_grabber(){
        blockGrabL.setPosition(Range.clip(right_trigger,0,1));
        blockGrabR.setPosition(Range.clip((1.0-right_trigger),0,1));
        blockLift.setPower(0.5);
        blockLift.setTargetPosition((1-gamepad1.left_trigger)*-1800);
        // blockLift.setMode(DcMotor.RunMode);
        //STOP_AND_RESET_ENCODER - current encoder position zero
        //RUN_TO_POSITION - go to target encoder
        //RUN_USING_ENCODER - constant velocity
        //RUN_WITHOUt_ENCODER - just normal power based
        
        if(gamepad1.back){    
            while(!blockTouch.isPressed()){  //move to top position
                blockLift.setPower(-0.3);
                if(blockTouch.isPressed()){
                    break;
                }
            }
            blockLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            blockLift.setPower(0.5);
            blockLift.setTargetPosition(-1800);
            blockLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        /**
         * set power to -0.3 until touchsensor==true
         * setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER)
         * blockLift.setPower(0.5);
         * blockLift.setTargetPosition(-1800);
         * setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER)
         * 
         */
    }
}