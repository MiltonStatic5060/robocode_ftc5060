
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
//@Disabled
public class ColorSensorTest extends OpMode
{
    ColorSensor colores;
    Servo servo1;

    @Override
    public void init() {

        colores = hardwareMap.colorSensor.get("colores");
        
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {


    }

    @Override
    public void loop() {
        if(gamepad1.b){
            colores.enableLed(true);
        } else {
            colores.enableLed(false);
        }
        
        telemetry.addData("Color Value",colores.argb());
        telemetry.addData("Blue Value",colores.blue());
        telemetry.addData("Green Value",colores.green());
        telemetry.addData("Red Value",colores.red());
        telemetry.addData("Alpha Value",colores.alpha());
        telemetry.update();
    }

    @Override
    public void stop() {
    }

}
