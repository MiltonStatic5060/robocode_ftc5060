package org.firstinspires.ftc.teamcode.competition2017;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.HardwareMap;
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
 * motorFL     : Assumes there is a DcMotor named "motorFL"
 * motorFR     : Assumes there is a DcMotor named "motorFR"
 * motorBL     : Assumes there is a DcMotor named "motorBL"
 * motorBR     : Assumes there is a DcMotor named "motorBR"
 * blockLift   : Assumes there is a DcMotor named "blockLift"
 * relicPusher : Assumes there is a DcMotor named "relicPusher"
 * relicLifter : Assumes there is a DcMotor named "relicLifter"
 * 
 * blockGrabL  : Assumes there is a  Servo  named "blockGrabL" 
 * blockGrabR  : Assumes there is a  Servo  named "blockGrabR" 
 * relicClaw   : Assumes there is a  Servo  named "relicClaw"
 * 
 * colorSensor : Assumes there is a ColorSensor named "colorSensor"
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
    public Servo    relicClaw    = null;

    public ColorSensor colorSensor      = null;

    /* Robot constants */
    public static final double BLOCK_LIFT_LIM = -2880; //Number of encoder degrees
    public static final double RELIC_LIFT_LIM = 1440.0/3.0; //Number of encoder degrees
    public static final double BLOCK_GRAB_LIM = 0.55 ; //0.0 to 1.0 servo degrees
    public static final double RELIC_CLAW_LIM = 0.5; //0.0 to 1.0 servo degrees
    public static final double BLUE_THRESHOLD  = 0;  //Should be int 0 to around 15 
    public static final double RED_THRESHOLD   = 0;  //Should be int 0 to around 15
    public static final double GREEN_THRESHOLD = 0;  //Should be int 0 to around 15

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    Gamepad gamepad1            =  null;
    Gamepad gamepad2            =  null;

    /* Constructor */
    public Hardware2017_1(HardwareMap ahwMap, Gamepad gamepad1, Gamepad gamepad2){
        this.hwMap = ahwMap;
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
    }

    /* Initialize standard Hardware interfaces */
    public void init() {
        // Save reference to Hardware map
        

        // Define and Initialize Motors
        this.motorFL     = this.hwMap.get( DcMotor.class , "motorFL"     );
        this.motorFR     = this.hwMap.get( DcMotor.class , "motorFR"     );
        this.motorBL     = this.hwMap.get( DcMotor.class , "motorBL"     );
        this.motorBR     = this.hwMap.get( DcMotor.class , "motorBR"     );
        this.blockLift   = this.hwMap.get( DcMotor.class , "blockLift"   );
        this.blockGrabL  = this.hwMap.get( Servo.class   , "blockGrabL"  );
        this.blockGrabR  = this.hwMap.get( Servo.class   , "blockGrabR"  );
        this.relicPusher = this.hwMap.get( DcMotor.class , "relicPusher" );
        this.relicLifter = this.hwMap.get( DcMotor.class , "relicLifter" );
        this.relicClaw   = this.hwMap.get( Servo.class   , "relicClaw"   );
        this.colorSensor = this.hwMap.get( ColorSensor.class, "colorSensor");

        this.motorFL.setDirection(     DcMotor.Direction.FORWARD );                
        this.motorFR.setDirection(     DcMotor.Direction.REVERSE );
        this.motorBL.setDirection(     DcMotor.Direction.FORWARD );
        this.motorBR.setDirection(     DcMotor.Direction.REVERSE );

        this.blockLift.setDirection(   DcMotor.Direction.FORWARD );
        this.relicPusher.setDirection( DcMotor.Direction.FORWARD );
        this.relicLifter.setDirection( DcMotor.Direction.FORWARD );
        
        this.blockGrabL.setDirection(  Servo.Direction.FORWARD   );
        this.blockGrabR.setDirection(  Servo.Direction.FORWARD   );
        this.relicClaw.setDirection(   Servo.Direction.FORWARD   );        
        
        // Set all motors to zero power
        this.motorFL.setPower(0);
        this.motorFR.setPower(0);
        this.motorBL.setPower(0);
        this.motorBR.setPower(0);
        this.blockLift.setPower(0);
        this.relicPusher.setPower(0);
        this.relicLifter.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        this.motorFL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.motorFR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.motorBL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.motorBR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.blockLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.relicPusher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.relicLifter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);        

    }
    public void start(){
        this.blockLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.blockLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // this.blockLift.setPower(0.4);
        this.relicLifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.relicLifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // this.relicLifter.setPower(0.4);
    }

    public void loop(){
        loop_drive();
        loop_grabber();
    }

    // public <T extends Number> boolean isNear( T num, T target, T thresh ){
    //     return isNear( (double)num, (double)target, (double)thresh  );
    // }
    public boolean isNear( double num, double target, double thresh ){
        return Math.abs( target - num )<=thresh;
    }
    public boolean isNear( int num, int target, int thresh ){
        return Math.abs( target - num )<=thresh;
    }

    /**
     * returns the String BLUE RED GREEN NONE to indicate the highest color value
     *
     *
     *
     */
    public String whichColor(){
        this.colorSensor.enableLed(true);
        int blue = this.colorSensor.blue();
        int red = this.colorSensor.red();
        int green = this.colorSensor.green();
        int alpha = this.colorSensor.alpha();
        int thresh = 0;
        if( isNear(blue,red,thresh) || isNear(blue,green,thresh) || isNear(green,red,thresh) ){
            return "NEITHER";
        } else if (isNear(Math.max(blue,Math.max(red,green)),blue,this.BLUE_THRESHOLD)){
            return "BLUE";
        } else if (isNear(Math.max(blue,Math.max(red,green)),red,this.RED_THRESHOLD)){
            return "RED";
        } else if (isNear(Math.max(blue,Math.max(red,green)),green,this.GREEN_THRESHOLD)){
            return "GREEN";
        } else {
            return "NEITHER";
        }
    }
    public void loop_drive(){
        double left_stick_x = this.gamepad1.dpad_right ? 0.8 : 0;
        double left_stick_y = this.gamepad1.dpad_down  ? 0.8 : 0;
        left_stick_x       -= this.gamepad1.dpad_left  ? 0.8 : 0;
        left_stick_y       -= this.gamepad1.dpad_up    ? 0.8 : 0;

        double right_stick_x = this.gamepad2.left_stick_x + this.gamepad1.left_stick_x;
        double right_stick_y = this.gamepad2.left_stick_y + this.gamepad1.left_stick_y;

        double left_x  = Range.clip( left_stick_x  ,-1,1);
        double left_y  = Range.clip( left_stick_y  ,-1,1);
        double right_x = Range.clip( right_stick_x ,-1,1);
        double right_y = Range.clip( right_stick_y ,-1,1);

        double powFR =  Range.clip( left_y + right_y + left_x + right_x , -1 , 1 );
        double powFL =  Range.clip( left_y + right_y - left_x - right_x , -1 , 1 );
        double powBR =  Range.clip( left_y + right_y - left_x + right_x , -1 , 1 );
        double powBL =  Range.clip( left_y + right_y + left_x - right_x , -1 , 1 );

        this.motorFR.setPower(powFR);
        this.motorFL.setPower(powFL);
        this.motorBR.setPower(powBR);
        this.motorBL.setPower(powBL);
    }
    public void loop_grabber(){
        double leftAng  = Range.clip(this.gamepad1.right_trigger*this.BLOCK_GRAB_LIM,0,1-this.BLOCK_GRAB_LIM);
        double rightAng = Range.clip(((1.0-this.gamepad1.right_trigger)*this.BLOCK_GRAB_LIM),this.BLOCK_GRAB_LIM,1);
        this.blockGrabL.setPosition(leftAng);
        this.blockGrabR.setPosition(rightAng);
        this.blockLift.setPower(0.5);
        this.blockLift.setTargetPosition( (int)Math.floor((1-this.gamepad1.left_trigger)*-1800) );
        // blockLift.setMode(DcMotor.RunMode);
        //STOP_AND_RESET_ENCODER - current encoder position zero
        //RUN_TO_POSITION - go to target encoder
        //RUN_USING_ENCODER - constant velocity
        //RUN_WITHOUt_ENCODER - just normal power based
    }
    public void loop_relic(){

    }
    public void loop_colortest(){

    }
 }

