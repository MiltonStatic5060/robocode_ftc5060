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
    //Block Lift Motor
    DcMotor blockLift;
    
    //Front Motors
    DcMotor motorRight;
    DcMotor motorLeft;

    //Back Motors
    DcMotor motorRight1;
    DcMotor motorLeft1;

    //Servo Block Grabbers
    Servo blockGrabL;
    Servo blockGrabR;

    @Override
    public void init(){
        blockLift = hardwareMap.dcMotor.get("blockLift");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight1 = hardwareMap.dcMotor.get("motorRight1");
        motorLeft1 = hardwareMap.dcMotor.get("motorLeft1");
        blockGrabL = hardwareMap.servo.get("blockGrabL");
        blockGrabR = hardwareMap.servo.get("blockGrabR");

        motorRight.setDirection(DcMotor.REVERSE);
        motorRight1.setDirection(DcMotor.REVERSE);   

        blockLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        blockLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);     
    }
    @Override
    public void loop(){
        r_motorCtl();
        r_grabber();
        telemetry.addData("Lift Motor Position",blockLift.getPosition());
        
    }
    public void r_motorCtl(){
        double left_stick_x = 1 ? gamepad1.dpad_right : 0;
        double left_stick_y = 1 ? gamepad1.dpad_down : 0;
        left_stick_x -= 1 ? gamepad1.dpad_left : 0;
        left_stick_y -= 1 ? gamepad1.dpad_up : 0;

        double right_stick_x = gamepad2.left_stick_x + gamepad1.left_stick_x;
        double right_stick_y = gamepad2.left_stick_y + gamepad1.left_stick_y;

        double left_x = Range.clip(left_stick_x,-1,1);
        double left_y = Range.clip(left_stick_y,-1,1);
        double right_x  = Range.clip(right_stick_x,-1,1);
        double right_y = Range.clip(right_stick_y,-1,1);

        double powRight =  Range.clip( left_y + right_y + left_x + right_x , -1 , 1 );
        double powLeft =   Range.clip( left_y + right_y - left_x - right_x , -1 , 1 );
        double powRight1 = Range.clip( left_y + right_y - left_x + right_x , -1 , 1 );
        double powLeft1 =  Range.clip( left_y + right_y + left_x - right_x , -1 , 1 );

        motorRight.setPower(powRight);
        motorLeft.setPower(powLeft);
        motorRight1.setPower(powRight1);
        motorLeft1.setPower(powLeft1);
    }
    public void r_grabber(){
        //blockGrabL blockGrabR blockLift
        double right_trigger = gamepad1.right_trigger;
        blockGrabL.setPosition(Range.clip(right_trigger*255,0,255-100));
        blockGrabR.setPosition(Range.clip((1.0-right_trigger)*255,100,255));
        
        //TEMPORARY
        double right_stick_y = gamepad1.right_stick_y;
        
        blockLift.setTargetPosition(gamepad1.left_trigger*-2880);

        // print blockLift.get_enc_current(),
        // print blockLift.get_enc_target()

        if(gamepad1.back){
            blockLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            blockLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);     
        }
    }
}