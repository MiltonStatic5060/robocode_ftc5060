package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;\
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words. 
 * 
 * motorFL     : Assumes there is a motor named "motorFL"
 * motorFR     : Assumes there is a motor named "motorFR"
 * motorBL     : Assumes there is a motor named "motorBL"
 * motorBR     : Assumes there is a motor named "motorBR"
 * blockLift   : Assumes there is a motor named "blockLift"
 * relicPusher : Assumes there is a motor named "relicPusher"
 * relicLifter : Assumes there is a motor named "relicLifter"
 * 
 * 
 *
 */
public class Hardware2017_1
{
    /* Drive motors */
    public DcMotor  motorFL   = null;
    public DcMotor  motorFR   = null;
    public DcMotor  motorBL   = null;
    public DcMotor  motorBR   = null;
    
    /* Block Lift motors */
    public DcMotor  blockLift     = null;
    public Servo    blockGrabL    = null;
    public Servo    blockGrabR    = null;

    /* Relic Manipulation motors */
    public DcMotor  relicPusher  = null;
    public DcMotor  relicLifter  = null;
    public Servo  relicClaw    = null;

    /* Robot constants */
    public static final double BLOCK_LIFT_LIM = -2880; //Number of encoder degrees
    public static final double RELIC_LIFT_LIM = 1440.0/3.0; //Number of encoder degrees
    public static final double BLOCK_GRAB_LIM = 0.55 ; //0.0 to 1.0 servo degrees
    public static final double RELIC_CLAW_LIM = 0.5; //0.0 to 1.0 servo degrees

    /* local OpMode members. */
    HardwareMap hwMap           =  null;

    /* Constructor */
    public HardwarePushbot(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        motorFL     = hwMap.get( DcMotor.class , "motorFL"     );
        motorFR     = hwMap.get( DcMotor.class , "motorFR"     );
        motorBL     = hwMap.get( DcMotor.class , "motorBL"     );
        motorBR     = hwMap.get( DcMotor.class , "motorBR"     );
        blockLift   = hwMap.get( DcMotor.class , "blockLift"   );
        blockGrabL  = hwMap.get( Servo.class   , "blockGrabL"  );
        blockGrabR  = hwMap.get( Servo.class   , "blockGrabR"  );
        relicPusher = hwMap.get( DcMotor.class , "relicPusher" );
        relicLifter = hwMap.get( DcMotor.class , "relicLifter" );
        relicClaw   = hwMap.get( Servo.class   , "relicClaw"   );

        motorFL.setDirection(     DcMotor.Direction.FORWARD );                
        motorFR.setDirection(     DcMotor.Direction.REVERSE );
        motorBL.setDirection(     DcMotor.Direction.FORWARD );
        motorBR.setDirection(     DcMotor.Direction.REVERSE );

        blockLift.setDirection(   DcMotor.Direction.FORWARD );
        relicPusher.setDirection( DcMotor.Direction.FORWARD );
        relicLifter.setDirection( DcMotor.Direction.FORWARD );
        
        blockGrabL.setDirection(  Servo.Direction.FORWARD   );
        blockGrabR.setDirection(  Servo.Direction.FORWARD   );
        relicClaw.setDirection(   Servo.Direction.FORWARD   );        
        
        // Set all motors to zero power
        motorFL.setPower(0);
        motorFR.setPower(0);
        motorBL.setPower(0);
        motorBR.setPower(0);
        blockLift.setPower(0);
        relicPusher.setPower(0);
        relicLifter.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        motorFL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorFR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorBL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorBR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        blockLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        relicPusher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        relicLifter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);        

    }
    public void start(){
        blockLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        blockLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        blockLift.setPower(0.4);
        relicLifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        relicLifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        relicLifter.setPower(0.4);
    }

    public void loop(){
        r_grabber();
    }

    public void r_grabber(){

    }
 }

