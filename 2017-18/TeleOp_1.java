package org.firstinspires.ftc.teamcode.competition2017;

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



@TeleOp(name = "TeleOp1", group = "Competition2017-18")
//@Autonomous(name = "Concept: NullOp", group = "Concept")
//@Disabled
public class TeleOp_1 extends OpMode {
    VuMarkTool vuTool = null;

    @Override
    public void init(){
        vuTool = new VuMarkTool(hardwareMap,telemetry);
        vuTool.runOpMode();
    }
    @Override
    public void loop(){
        vuTool.runLoop();
    }
    public void stop(){
        vuTool.endRun();
    }
}