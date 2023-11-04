package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

// Imports for Computer Vision and TFOD recognition
import org.firstinspires.ftc.vision.tfod.TfodProcessor;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.ExposureControl;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.GainControl;
import org.firstinspires.ftc.vision.VisionPortal;

import java.util.List;

@Autonomous
public class NovaAutoMecanum extends LinearOpMode {

    // Declare Devices
    DcMotor leftFrontMotor = null, rightFrontMotor = null, leftRearMotor = null, rightRearMotor = null;

    // drive motor position variables
    private int lfPos, rfPos, lrPos, rrPos;

    // operational constants
    private double fast = 0.5;
    private double medium = 0.3;
    private double slow = 0.1;
    private double clicksPerInch = 87.5;
    private double clicksPerDeg = 21.94;

    private VisionPortal visionPortal;
    private TfodProcessor tfod;

    public void initForAutonomousMode() {
        // Initialize the Motors
        leftFrontMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        rightFrontMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        leftRearMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        rightRearMotor = hardwareMap.get(DcMotor.class, "backRightMotor");

        // todo: Check which motors need to be Reversed and Correct below
        //rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        //leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        //rightRearMotor.setDirection(DcMotor.Direction.FORWARD);
        //leftRearMotor.setDirection(DcMotor.Direction.REVERSE);

        // Set the drive motor run modes:
        leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        // Initialize the TFOD and set to accept Recognitions with 75% confidence
        tfod = TfodProcessor.easyCreateWithDefaults();
        //tfod.setMinResultConfidence(75);
        visionPortal = VisionPortal.easyCreateWithDefaults(
                hardwareMap.get(WebcamName.class, "Webcam 1"), tfod);
    }


    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.setAutoClear(true);
        initForAutonomousMode();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive()){

            telemetryTfod();
            // Main Game play for our Autonomous Mode
//            moveForward(-10, slow);
//            turnClockwise(180, slow);
//            // Look for Pixels and get Direction
//            int direction = getDirectionOfPixel();
//            if (direction == -1) {
//                turnClockwise(-150, slow);
//                moveForward(-5, slow);
//            } else if (direction == 1) {
//                turnClockwise(-210, slow);
//                moveForward(-5, slow);
//            } else {
//                turnClockwise(-180, slow);
//                moveForward(-8, slow);
//            }
//            moveForward(10, slow);
        }

    }



    private void moveForward(int howMuch, double speed) {
        // howMuch is in inches. A negative howMuch moves backward.

        telemetry.addLine("Trying to Move Foward");
        telemetry.update();

        // fetch motor positions
        lfPos = leftFrontMotor.getCurrentPosition();
        rfPos = rightFrontMotor.getCurrentPosition();
        lrPos = leftRearMotor.getCurrentPosition();
        rrPos = rightRearMotor.getCurrentPosition();
        telemetry.addLine("Current Positions: LF:" + lfPos + "; RF: " + rfPos);
        telemetry.update();

        // calculate new targets
        lfPos += howMuch * clicksPerInch;
        rfPos += howMuch * clicksPerInch;
        lrPos += howMuch * clicksPerInch;
        rrPos += howMuch * clicksPerInch;
        telemetry.addLine("Target Positions: LF:" + lfPos + "; RF: " + rfPos);
        telemetry.update();

        // move robot to new position
        leftFrontMotor.setTargetPosition(lfPos);
        rightFrontMotor.setTargetPosition(rfPos);
        leftRearMotor.setTargetPosition(lrPos);
        rightRearMotor.setTargetPosition(rrPos);
        leftFrontMotor.setPower(medium);
        rightFrontMotor.setPower(speed);
        leftRearMotor.setPower(medium);
        rightRearMotor.setPower(speed);

        leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // wait for move to complete
        while (leftFrontMotor.isBusy() && rightFrontMotor.isBusy() &&
                leftRearMotor.isBusy() && rightRearMotor.isBusy() && opModeIsActive()) {

            // Display it for the driver.
            telemetry.addLine("Move Foward");
            telemetry.addData("Target", "%7d :%7d", lfPos, rfPos, lrPos, rrPos);
            telemetry.addData("Actual", "%7d :%7d %7d :%7d", leftFrontMotor.getCurrentPosition(),
                    rightFrontMotor.getCurrentPosition(), leftRearMotor.getCurrentPosition(),
                    rightRearMotor.getCurrentPosition());
            telemetry.update();
        }

        // Stop all motion;
        leftFrontMotor.setPower(0);
        rightFrontMotor.setPower(0);
        leftRearMotor.setPower(0);
        rightRearMotor.setPower(0);
    }

    private void moveRight(int howMuch, double speed) {
        // howMuch is in inches. A negative howMuch moves backward.

        // fetch motor positions
        lfPos = leftFrontMotor.getCurrentPosition();
        rfPos = rightFrontMotor.getCurrentPosition();
        lrPos = leftRearMotor.getCurrentPosition();
        rrPos = rightRearMotor.getCurrentPosition();

        // calculate new targets
        lfPos += howMuch * clicksPerInch;
        rfPos -= howMuch * clicksPerInch;
        lrPos -= howMuch * clicksPerInch;
        rrPos += howMuch * clicksPerInch;

        // move robot to new position
        leftFrontMotor.setTargetPosition(lfPos);
        rightFrontMotor.setTargetPosition(rfPos);
        leftRearMotor.setTargetPosition(lrPos);
        rightRearMotor.setTargetPosition(rrPos);
        leftFrontMotor.setPower(speed);
        rightFrontMotor.setPower(speed);
        leftRearMotor.setPower(speed);
        rightRearMotor.setPower(speed);

        // wait for move to complete
        while (leftFrontMotor.isBusy() && rightFrontMotor.isBusy() &&
                leftRearMotor.isBusy() && rightRearMotor.isBusy() && opModeIsActive()) {

            // Display it for the driver.
            telemetry.addLine("Strafe Right");
            telemetry.addData("Target", "%7d :%7d", lfPos, rfPos, lrPos, rrPos);
            telemetry.addData("Actual", "%7d :%7d", leftFrontMotor.getCurrentPosition(),
                    rightFrontMotor.getCurrentPosition(), leftRearMotor.getCurrentPosition(),
                    rightRearMotor.getCurrentPosition());
            telemetry.update();
        }

        // Stop all motion;
        leftFrontMotor.setPower(0);
        rightFrontMotor.setPower(0);
        leftRearMotor.setPower(0);
        rightRearMotor.setPower(0);

    }

    private void turnClockwise(int whatAngle, double speed) {
        // whatAngle is in degrees. A negative whatAngle turns counterclockwise.

        telemetry.addLine("Inside turnClockwise");
        telemetry.update();

        // fetch motor positions
        lfPos = leftFrontMotor.getCurrentPosition();
        rfPos = rightFrontMotor.getCurrentPosition();
        lrPos = leftRearMotor.getCurrentPosition();
        rrPos = rightRearMotor.getCurrentPosition();
        telemetry.addLine("Current Positions: LF:" + lfPos + "; RF: " + rfPos);
        telemetry.update();

        // calculate new targets
        lfPos += whatAngle * clicksPerDeg;
        rfPos -= whatAngle * clicksPerDeg;
        lrPos += whatAngle * clicksPerDeg;
        rrPos -= whatAngle * clicksPerDeg;
        telemetry.addLine("Target Angles: LF:" + lfPos + "; RF: " + rfPos);
        telemetry.update();

        // move robot to new position
        leftFrontMotor.setTargetPosition(lfPos);
        rightFrontMotor.setTargetPosition(rfPos);
        leftRearMotor.setTargetPosition(lrPos);
        rightRearMotor.setTargetPosition(rrPos);
        leftFrontMotor.setPower(speed);
        rightFrontMotor.setPower(speed);
        leftRearMotor.setPower(speed);
        rightRearMotor.setPower(speed);

        // wait for move to complete
        while (leftFrontMotor.isBusy() && rightFrontMotor.isBusy() &&
                leftRearMotor.isBusy() && rightRearMotor.isBusy() && opModeIsActive()) {

            // Display it for the driver.
            telemetry.addLine("Turn Clockwise");
            telemetry.addData("Target", "%7d :%7d", lfPos, rfPos, lrPos, rrPos);
            telemetry.addData("Actual", "%7d :%7d", leftFrontMotor.getCurrentPosition(),
                    rightFrontMotor.getCurrentPosition(), leftRearMotor.getCurrentPosition(),
                    rightRearMotor.getCurrentPosition());
            telemetry.update();
        }

        // Stop all motion;
        leftFrontMotor.setPower(0);
        rightFrontMotor.setPower(0);
        leftRearMotor.setPower(0);
        rightRearMotor.setPower(0);
    }


    private void telemetryTfod() {
        List<Recognition> currentRecognitions = tfod.getRecognitions();
        telemetry.addData("# Objects Detected", currentRecognitions.size());

        // Step through the list of recognitions and display info for each one.
        for (Recognition recognition : currentRecognitions) {
            double x = (recognition.getLeft() + recognition.getRight()) / 2 ;
            double y = (recognition.getTop()  + recognition.getBottom()) / 2 ;

            telemetry.addData(""," ");
            telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
            telemetry.addData("- Position", "%.0f / %.0f", x, y);
            telemetry.addData("- Size", "%.0f x %.0f", recognition.getWidth(), recognition.getHeight());
            telemetry.update();
        }   // end for() loop

    }   // end method telemetryTfod()

    int getDirectionOfPixel() {
        List<Recognition> recognitions = tfod.getRecognitions();
        while (recognitions.size() == 0 && opModeIsActive()) {
            telemetry.addLine("Found 0 Recognitions, Moving by pre-determined amount");
            telemetry.update();
            moveForward(-5, slow);
            recognitions = tfod.getRecognitions();
        }

        // Process the Recognition
        Recognition pixelOnSpikeMark = recognitions.get(0);
        double xPositionOfPixel = (pixelOnSpikeMark.getLeft() + pixelOnSpikeMark.getRight())/2;
        int direction = 0; // -1 = left, 0 = center, 1 = right
        if ((xPositionOfPixel < 0 && xPositionOfPixel > -3) || xPositionOfPixel > 0 && xPositionOfPixel < 3){
            direction = 0;
        } else if (xPositionOfPixel < -3){ // Placed towards the Left of the Robot
            direction = -1;
        } else if (xPositionOfPixel > 3){ // Placed towards the Right of the Robot
            direction = 1;
        }
        return direction;
    }
}