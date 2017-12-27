package org.firstinspires.ftc.teamcode.competition2017;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.*;



@TeleOp(name = "ControlOp2", group = "Presentation2017-18")
//@Autonomous(name = "Concept: NullOp", group = "Concept")
//@Disabled
public class ControlOp_2 extends OpMode {
    /* Drive motors */
    public DcMotor  motorFL;
    public DcMotor  motorFR;
    public DcMotor  motorBL;
    public DcMotor  motorBR;
    
    @Override
    public void init(){
        motorFL = hardwareMap.get( DcMotor.class , "motorFL" );
        motorFR = hardwareMap.get( DcMotor.class , "motorFR" );
        motorBL = hardwareMap.get( DcMotor.class , "motorBL" );
        motorBR = hardwareMap.get( DcMotor.class , "motorBR" );
    }
    @Override
    public void loop(){
        loop_drive();
    }
    public void loop_drive(){
        double left_x  = Range.clip( gamepad1.left_stick_x  ,-1,1);
        double left_y  = Range.clip( gamepad1.left_stick_y  ,-1,1);

        double powFR =  Range.clip( left_y + left_x , -1 , 1 );
        double powFL =  Range.clip( left_y - left_x , -1 , 1 );
        double powBR =  Range.clip( left_y + left_x , -1 , 1 );
        double powBL =  Range.clip( left_y - left_x , -1 , 1 );
        
        motorFR.setPower(powFR);
        motorFL.setPower(powFL);
        motorBR.setPower(powBR);
        motorBL.setPower(powBL);
    }
}