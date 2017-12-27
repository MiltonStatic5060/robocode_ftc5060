// package org.firstinspires.ftc.teamcode.competition2017;

// import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
// import com.qualcomm.robotcore.eventloop.opmode.Disabled;
// import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

// import org.firstinspires.ftc.robotcore.external.ClassFactory;
// import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
// import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
// import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
// import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
// import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
// import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
// import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
// import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
// import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
// import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
// import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
// import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

// /**
//  * @see ConceptVuforiaNavigation
//  * @see VuforiaLocalizer
//  * @see VuforiaTrackableDefaultListener
//  * see  ftc_app/doc/tutorial/FTC_FieldCoordinateSystemDefinition.pdf
//  */

// @Autonomous(name="VuMark Back Id", group ="Competition2017-18")
// @Disabled
// public class VuMarkTest extends LinearOpMode {

//     public static final String TAG = "Vuforia VuMark Sample";
//     OpenGLMatrix lastLocation = null;
//     VuforiaLocalizer vuforia;

//     @Override 
//     public void runOpMode() {
//         int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//         VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
//         // VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(); //no camera on yet
//         parameters.vuforiaLicenseKey = "Ac1QTT7/////AAAAGVlwGAjMbkw3ocEdzm7vGKIYn/G00umnbOY8YMgalP2T3amdOz23Zwreb6M75LNs0jr7NWFRzqtN14z/2Q+3uccAYpnKURaWXX8Knf6wq/Nc1eFK/yp1uJaOXUF3wfyiCWisARAWDnA8hp5oeSLtnldRDedMMSIZ6FUXWe+MaIdTZ6W8wGBmrlnAZcvxEAngvqwVaAtw+K9W1OqWZIpMesz3RZlzbvpdKbckkbJ/9WcZnqmuSBpQH/l8S/2/WYcuZH+moZajoKhkP+1mFxv782qMcmw0vmOAYLlNCisy0GcPWFyAgmEOFT4SYXfvBCmle1uEPDwugCaiTnJ++f16vjZgm/uZta4BIbkFlA/OWuC2";
//         //parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT; //more useful
//         parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK; //more accurate
//         this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
//         VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
//         VuforiaTrackable relicTemplate = relicTrackables.get(0);
//         relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary
//         telemetry.addData(">", "Press Play to start");
//         telemetry.update();
        
//         int stepCounter = 0;

//         waitForStart(); //waits for the play button! NECESSARY!

//         relicTrackables.activate();
        
//         //Step Counter based program
//         //while (opModeIsActive()) {
            
//             RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
//             if (stepCounter == 0){
//                 while(false){
//                     if (vuMark != RelicRecoveryVuMark.UNKNOWN ) {
//                         telemetry.addData("VuMark", "%s visible", vuMark);
                        
//                         //start pose section - not necessary
//                             OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();
//                             telemetry.addData("Pose", format(pose));
//                             if (pose != null) {
//                                 VectorF trans = pose.getTranslation();
//                                 Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

//                                 // Extract the X, Y, and Z components of the offset of the target relative to the robot
//                                 double tX = trans.get(0);
//                                 double tY = trans.get(1);
//                                 double tZ = trans.get(2);

//                                 // Extract the rotational components of the target relative to the robot
//                                 double rX = rot.firstAngle;
//                                 double rY = rot.secondAngle;
//                                 double rZ = rot.thirdAngle;
//                             }
//                         //end pose section
//                         stepCounter++;

//                     }
//                     else {
//                         telemetry.addData("VuMark", "not visible");
//                     }
//                     telemetry.update();
//                 }
//             } else if (stepCounter==1){
//                 while (false){
//                     telemetry.addData("Step 1","Moving Horizontally 6 inches");
//                     telemetry.update();
//                     //moves horizontal 6 inches (left or right)
//                 }
//                 stepCounter++;
//             } else if (stepCounter==2) {
//                 while (false){
//                     telemetry.addData("Step 2","Moving Vertically");
//                     telemetry.update();
//                     //moves vertical 24 inches or 0 inches
//                 }
//                 stepCounter++;
//             } else if (stepCounter==3){
//                 while (false){
//                     telemetry.addData("Step 3","Moving into %s shelving position",vuMark);
//                     telemetry.update();
//                     //moves to LEFT, RIGHT, or CENTER position
//                 }
//                 stepCounter++;
//             } else if (stepCounter==3){
//                 while(false){
//                     telemetry.addData("Step 4","Placing block in to shelf");
//                     telemetry.update();
//                 }
//                 stepCounter++;
//             } else if (stepCounter==4){
//                 while(false){
//                     telemetry.addData("Step 5","Parking robot (Line following/%s Compensation",vuMark);
//                     telemetry.update();
//                 }
//             }

//         }
//         //Normal Program
//         //while (opModeIsActive()) {

//             /**
//              * See if any of the instances of {@link relicTemplate} are currently visible.
//              * {@link RelicRecoveryVuMark} is an enum which can have the following values:
//              * UNKNOWN, LEFT, CENTER, and RIGHT. When a VuMark is visible, something other than
//              * UNKNOWN will be returned by {@link RelicRecoveryVuMark#from(VuforiaTrackable)}.
//              */
//             RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
//             if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

//                 /* Found an instance of the template. In the actual game, you will probably
//                  * loop until this condition occurs, then move on to act accordingly depending
//                  * on which VuMark was visible. */
//                 telemetry.addData("VuMark", "%s visible", vuMark);

//                 /* For fun, we also exhibit the navigational pose. In the Relic Recovery game,
//                  * it is perhaps unlikely that you will actually need to act on this pose information, but
//                  * we illustrate it nevertheless, for completeness. */
//                 OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();
//                 telemetry.addData("Pose", format(pose));

//                 /* We further illustrate how to decompose the pose into useful rotational and
//                  * translational components */
//                 if (pose != null) {
//                     VectorF trans = pose.getTranslation();
//                     Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

//                     // Extract the X, Y, and Z components of the offset of the target relative to the robot
//                     double tX = trans.get(0);
//                     double tY = trans.get(1);
//                     double tZ = trans.get(2);

//                     // Extract the rotational components of the target relative to the robot
//                     double rX = rot.firstAngle;
//                     double rY = rot.secondAngle;
//                     double rZ = rot.thirdAngle;
//                 }
//             }
//             else {
//                 telemetry.addData("VuMark", "not visible");
//             }

//             telemetry.update();
//         }
//     }

//     public String format(OpenGLMatrix transformationMatrix) {
//         return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
//     }
// }
