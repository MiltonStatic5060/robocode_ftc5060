/**
 * This function is executed when this Op Mode is selected from the Driver Station.
 */
function runOpMode() {
  right.setDirection("REVERSE");
  linearOpMode.waitForStart();
  while (linearOpMode.opModeIsActive()) {
    right.setPower(gamepad1.getRightStickY());
    left.setPower(gamepad1.getLeftStickY());
    telemetry.update();
  }
}
