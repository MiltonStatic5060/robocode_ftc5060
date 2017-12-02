package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

@TeleOp(name = "Concept: AutoSetup", group = "Concept")

public class NxtR_3 extends OpMode{
    private DcMotor rightF;
    private DcMotor sweeper;
    private DcMotor conveyor;
    private DcMotor leftB;
    private DcMotor leftF;
    private DcMotor catapult;
    private DcMotor rightB;
    private LightSensor light1;
    private LightSensor light2;
    private Servo gate1;
    private Servo gate2;
    private UltrasonicSensor ultra2but;
    private UltrasonicSensor ultra2;
    private UltrasonicSensor ultra2arm;
    private UltrasonicSensor ultra1;

    // todo: write your code here
    @Override
    public void init(){}
    
    @Override
    public void loop(){}
}