package org.firstinspires.ftc.teamcode.competition2017;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.*;



@TeleOp(name = "TeleOp2", group = "Competition2017-18")
//@Autonomous(name = "Concept: NullOp", group = "Concept")
//@Disabled
public class TeleOp_2 extends OpMode {
    Hardware2017_1 robot1 = null;
    
    @Override
    public void init(){
        robot1.init(hardwareMap,gamepad1,gamepad2);    
    }
    @Override
    public void loop(){
        robot1.loop();
    }
    @Override
    public void start(){
        robot1.start();
    }
}