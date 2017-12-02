/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.firstinspires.ftc.teamcode.competition2017;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.*;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * This OpMode illustrates the basics of using the Vuforia engine to determine
 * the identity of Vuforia VuMarks encountered on the field. The code is structured as
 * a LinearOpMode. It shares much structure with {@link ConceptVuforiaNavigation}; we do not here
 * duplicate the core Vuforia documentation found there, but rather instead focus on the
 * differences between the use of Vuforia for navigation vs VuMark identification.
 *
 * @see ConceptVuforiaNavigation
 * @see VuforiaLocalizer
 * @see VuforiaTrackableDefaultListener
 * see  ftc_app/doc/tutorial/FTC_FieldCoordinateSystemDefinition.pdf
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained in {@link ConceptVuforiaNavigation}.
 */

//@Autonomous(name="VuMark Front Id", group ="Competition2017-18")
//@Disabled
public class VuMarkTool extends LinearOpMode {

    public static final String TAG = "Vuforia VuMark Sample";
    OpenGLMatrix lastLocation = null;
    VuforiaLocalizer vuforia;
    // int cameraMonitorViewId; //Not needed here
    VuforiaTrackables relicTrackables;
    VuforiaTrackable relicTemplate;
    RelicRecoveryVuMark vuMark;
    OpenGLMatrix pose;

    /* Local Variables */
    HardwareMap hardwareMap = null;
    Telemetry telemetry = null;

    public VuMarkTool(HardwareMap hwMap, Telemetry telemetry){
        this.haredwareMap = hwMap;
        this.telemetry = telemetry;
    }

    public void runOpMode() {
        // Get Camera Id
        int cameraMonitorViewId = this.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", this.hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        // else
        //     parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = "Ac1QTT7/////AAAAGVlwGAjMbkw3ocEdzm7vGKIYn/G00umnbOY8YMgalP2T3amdOz23Zwreb6M75LNs0jr7NWFRzqtN14z/2Q+3uccAYpnKURaWXX8Knf6wq/Nc1eFK/yp1uJaOXUF3wfyiCWisARAWDnA8hp5oeSLtnldRDedMMSIZ6FUXWe+MaIdTZ6W8wGBmrlnAZcvxEAngvqwVaAtw+K9W1OqWZIpMesz3RZlzbvpdKbckkbJ/9WcZnqmuSBpQH/l8S/2/WYcuZH+moZajoKhkP+1mFxv782qMcmw0vmOAYLlNCisy0GcPWFyAgmEOFT4SYXfvBCmle1uEPDwugCaiTnJ++f16vjZgm/uZta4BIbkFlA/OWuC2";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        this.relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        this.relicTemplate = this.relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        this.telemetry.addData(">", "Press Play to start");
        this.telemetry.update();
        //waitForStart();

        this.relicTrackables.activate();

        
    }
    public void runLoop(){
        this.vuMark = RelicRecoveryVuMark.from(this.relicTemplate);
        if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

            this.telemetry.addData("VuMark", "%s visible", vuMark);

            this.pose = ((VuforiaTrackableDefaultListener)this.relicTemplate.getListener()).getPose();
            this.telemetry.addData("Pose", format(pose));

            if (pose != null) {
                VectorF trans = pose.getTranslation();
                Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

                // Extract the X, Y, and Z components of the offset of the target relative to the robot
                double tX = trans.get(0);
                double tY = trans.get(1);
                double tZ = trans.get(2);

                // Extract the rotational components of the target relative to the robot
                double rX = rot.firstAngle;
                double rY = rot.secondAngle;
                double rZ = rot.thirdAngle;
            }
        }
        else {
            this.telemetry.addData("VuMark", "not visible");
        }

        this.telemetry.update();
    }
    public void endRun(){



        this.relicTrackables.deactivate();
    }

    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}
