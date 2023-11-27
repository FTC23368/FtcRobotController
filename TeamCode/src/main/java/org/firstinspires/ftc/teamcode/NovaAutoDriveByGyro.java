/*package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.IMU;
        import com.qualcomm.robotcore.util.ElapsedTime;
        import com.qualcomm.robotcore.util.Range;
        import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
        import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@Autonomous(name="NovaAutoDriveByGyro", group="Robot")
public class NovaAutoDriveByGyro extends LinearOpMode {
*/
    /* Declare OpMode members. */
/*
    private DcMotor leftFrontDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor leftRearDrive = null;
    private DcMotor rightRearDrive = null;
    private IMU imu = null; // IMU sensor

    private ElapsedTime runtime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 537.6; // eg: GoBILDA 312 RPM Yellow Jacket
    static final double DRIVE_GEAR_REDUCTION = 1.0; // No External Gearing
    static final double WHEEL_DIAMETER_INCHES = 4.0;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double P_TURN_GAIN = 0.01; // Proportional turn gain
    static final double HEADING_THRESHOLD = 1; // Heading threshold

    @Override
    public void runOpMode() {
        // Initialize the drive system variables.
        leftFrontDrive = hardwareMap.get(DcMotor.class, "left_front_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        leftRearDrive = hardwareMap.get(DcMotor.class, "left_rear_drive");
        rightRearDrive = hardwareMap.get(DcMotor.class, "right_rear_drive");

        // Initialize IMU
        imu = hardwareMap.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters());

        // Set the direction of the motors
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftRearDrive.setDirection(DcMotor.Direction.REVERSE);
        rightRearDrive.setDirection(DcMotor.Direction.FORWARD);

        // Set all motors to zero power
        leftFrontDrive.setPower(0);
        rightFrontDrive.setPower(0);
        leftRearDrive.setPower(0);
        rightRearDrive.setPower(0);

        // Set all motors to run using encoders.
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRearDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRearDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Reset encoders
        leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRearDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRearDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d",
                leftFrontDrive.getCurrentPosition(),
                rightFrontDrive.getCurrentPosition());
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Drive forward for 24 inches at half speed
        driveStraight(0.5, 24.0);

        // Turn the robot left to 45 degrees at quarter speed
        turnToHeading(0.25, 45.0);

        // Drive forward for another 12 inches at half speed
        driveStraight(0.5, 12.0);

        // Turn the robot right to -90 degrees (90 degrees to the right) at quarter speed
        turnToHeading(0.25, -90.0);

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }

    // Method to drive straight a given distance (inches) with Mecanum wheels
    public void driveStraight(double speed, double distance) {
        int moveCounts;
        int targetLeftFront;
        int targetRightFront;
        int targetLeftRear;
        int targetRightRear;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {
            // Determine new target position
            moveCounts = (int)(distance * COUNTS_PER_INCH);
            targetLeftFront = leftFrontDrive.getCurrentPosition() + moveCounts;
            targetRightFront = rightFrontDrive.getCurrentPosition() + moveCounts;
            targetLeftRear = leftRearDrive.getCurrentPosition() + moveCounts;
            targetRightRear = rightRearDrive.getCurrentPosition() + moveCounts;

            // Set Target and Turn On RUN_TO_POSITION
            leftFrontDrive.setTargetPosition(targetLeftFront);
            rightFrontDrive.setTargetPosition(targetRightFront);
            leftRearDrive.setTargetPosition(targetLeftRear);
            rightRearDrive.setTargetPosition(targetRightRear);

            leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftRearDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightRearDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Start motion
            speed = Range.clip(Math.abs(speed), 0.0, 1.0);
            leftFrontDrive.setPower(speed);
            rightFrontDrive.setPower(speed);
            leftRearDrive.setPower(speed);
            rightRearDrive.setPower(speed);

            // Keep looping while we are still active, and ALL motors are running
            while (opModeIsActive() &&
                    (leftFrontDrive.isBusy() && rightFrontDrive.isBusy() &&
                            leftRearDrive.isBusy() && rightRearDrive.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", targetLeftFront, targetRightFront);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        leftFrontDrive.getCurrentPosition(),
                        rightFrontDrive.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion
            leftFrontDrive.setPower(0);
            rightFrontDrive.setPower(0);
            leftRearDrive.setPower(0);
            rightRearDrive.setPower(0);

            // Turn off RUN_TO_POSITION
            leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            leftRearDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightRearDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    // Method to turn to a specific heading (in degrees)
    public void turnToHeading(double speed, double targetAngle) {
        // reset the IMU's heading
        imu.resetYaw();

        // Turn on RUN_USING_ENCODER
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRearDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRearDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // start turning
        double currentAngle = getHeading();
        double turnError = targetAngle - currentAngle;
        double turnSpeed;

        // Continue looping until the turn is completed
        while (opModeIsActive() && Math.abs(turnError) >= HEADING_THRESHOLD) {
            currentAngle = getHeading();
            turnError = targetAngle - currentAngle;

            // Normalize the turn error to be within -180 to 180 degrees
            while (turnError > 180) turnError -= 360;
            while (turnError <= -180) turnError += 360;

            // Apply proportional control to the turn error to determine turn speed
            turnSpeed = Range.clip(turnError * P_TURN_GAIN, -1, 1) * speed;

            // Apply the calculated turn speed to the wheels
            leftFrontDrive.setPower(-turnSpeed);
            rightFrontDrive.setPower(turnSpeed);
            leftRearDrive.setPower(-turnSpeed);
            rightRearDrive.setPower(turnSpeed);

            // Update telemetry
            telemetry.addData("Target Angle", targetAngle);
            telemetry.addData("Current Angle", currentAngle);
            telemetry.addData("Error", turnError);
            telemetry.addData("Turn Speed", turnSpeed);
            telemetry.update();
        }

        // Stop all motion
        leftFrontDrive.setPower(0);
        rightFrontDrive.setPower(0);
        leftRearDrive.setPower(0);
        rightRearDrive.setPower(0);
    }

    // Method to obtain the current heading from the IMU
    public double getHeading() {
        // This method returns therobot's heading in degrees, from 0 to 360
        YawPitchRollAngles angles = imu.getRobotYawPitchRollAngles();
        double heading = angles.getYaw(AngleUnit.DEGREES);
        heading = Range.clip(heading, 0, 360);
        return heading;
    }
}*/