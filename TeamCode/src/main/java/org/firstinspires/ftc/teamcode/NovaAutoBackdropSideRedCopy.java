package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.List;

// BACKDROP SIDE

@Autonomous
public class NovaAutoBackdropSideRedCopy extends LinearOpMode {

    NovaBot novaBot;

    @Override
    public void runOpMode() throws InterruptedException {

        novaBot = new NovaBot(this);
        novaBot.initNovaBot();

        waitForStart();
        novaBot.runtime.reset();

        // move forward (depends on model)
        novaBot.frontLeftMotor.setPower(0.3);
        novaBot.backLeftMotor.setPower(0.3);
        novaBot.frontRightMotor.setPower(0.3);
        novaBot.backRightMotor.setPower(0.3);
        telemetry.addData("Status: ", "stopping");
        telemetry.update();
        sleep(1075);
        novaBot.frontLeftMotor.setPower(0);
        novaBot.backLeftMotor.setPower(0);
        novaBot.frontRightMotor.setPower(0);
        novaBot.backRightMotor.setPower(0);
        telemetry.addData("Status: ", "entering if statement");
        telemetry.update();
        sleep(1000);

        // turn right
        novaBot.frontLeftMotor.setPower(-0.3);
        novaBot.backLeftMotor.setPower(-0.3);
        novaBot.frontRightMotor.setPower(0.3);
        novaBot.backRightMotor.setPower(0.3);
        sleep(750);
        novaBot.frontLeftMotor.setPower(0);
        novaBot.backLeftMotor.setPower(0);
        novaBot.frontRightMotor.setPower(0);
        novaBot.backRightMotor.setPower(0);
        sleep(2000);
        // check for prop on right spike
        if (isPropPresent()) {
            // place prop on right spike
            novaBot.frontLeftMotor.setPower(0.3);
            novaBot.backLeftMotor.setPower(0.3);
            novaBot.frontRightMotor.setPower(0.3);
            novaBot.backRightMotor.setPower(0.3);
            sleep(600);
            novaBot.frontLeftMotor.setPower(0);
            novaBot.backLeftMotor.setPower(0);
            novaBot.frontRightMotor.setPower(0);
            novaBot.backRightMotor.setPower(0);
            sleep(100);
            novaBot.frontLeftMotor.setPower(-0.3);
            novaBot.backLeftMotor.setPower(-0.3);
            novaBot.frontRightMotor.setPower(-0.3);
            novaBot.backRightMotor.setPower(-0.3);
            sleep(550);

            // park in backstage
            parkFromRightSpike();
        } else {
            // turn to center
            novaBot.frontLeftMotor.setPower(0.3);
            novaBot.backLeftMotor.setPower(0.3);
            novaBot.frontRightMotor.setPower(-0.3);
            novaBot.backRightMotor.setPower(-0.3);
            sleep(750);
            novaBot.frontLeftMotor.setPower(0);
            novaBot.backLeftMotor.setPower(0);
            novaBot.frontRightMotor.setPower(0);
            novaBot.backRightMotor.setPower(0);
            sleep(10);
            novaBot.frontLeftMotor.setPower(0.3);
            novaBot.backLeftMotor.setPower(0.3);
            novaBot.frontRightMotor.setPower(0.3);
            novaBot.backRightMotor.setPower(0.3);
            sleep(200);
            // strafe
            novaBot.frontLeftMotor.setPower(0.3);
            novaBot.backLeftMotor.setPower(-0.3);
            novaBot.frontRightMotor.setPower(-0.3);
            novaBot.backRightMotor.setPower(0.3);
            sleep(400);
            novaBot.frontLeftMotor.setPower(0);
            novaBot.backLeftMotor.setPower(0);
            novaBot.frontRightMotor.setPower(0);
            novaBot.backRightMotor.setPower(0);
            sleep(10);

            sleep(2000);
            // check for prop on center
            if (isPropPresent()) {
                // place prop on center
                novaBot.frontLeftMotor.setPower(0.3);
                novaBot.backLeftMotor.setPower(0.3);
                novaBot.frontRightMotor.setPower(0.3);
                novaBot.backRightMotor.setPower(0.3);
                sleep(900);
                novaBot.frontLeftMotor.setPower(-0.3);
                novaBot.backLeftMotor.setPower(-0.3);
                novaBot.frontRightMotor.setPower(-0.3);
                novaBot.backRightMotor.setPower(-0.3);
                sleep(650);

                // park in backstage
                parkFromCenterSpike();
            } else {
                novaBot.frontLeftMotor.setPower(0.3);
                novaBot.backLeftMotor.setPower(0.3);
                novaBot.frontRightMotor.setPower(0.3);
                novaBot.backRightMotor.setPower(0.3);
                sleep(375);
                // turn to left spike
                novaBot.frontLeftMotor.setPower(0.3);
                novaBot.backLeftMotor.setPower(0.3);
                novaBot.frontRightMotor.setPower(-0.3);
                novaBot.backRightMotor.setPower(-0.3);
                sleep(1400);

                    // place prop on left
                    novaBot.frontLeftMotor.setPower(0.3);
                    novaBot.backLeftMotor.setPower(0.3);
                    novaBot.frontRightMotor.setPower(0.3);
                    novaBot.backRightMotor.setPower(0.3);
                    sleep(650);
                    novaBot.frontLeftMotor.setPower(-0.3);
                    novaBot.backLeftMotor.setPower(-0.3);
                    novaBot.frontRightMotor.setPower(-0.3);
                    novaBot.backRightMotor.setPower(-0.3);
                    sleep(600);
                    novaBot.frontLeftMotor.setPower(0);
                    novaBot.backLeftMotor.setPower(0);
                    novaBot.frontRightMotor.setPower(0);
                    novaBot.backRightMotor.setPower(0);
                    sleep(10);

                    // park in backstage
                    parkFromLeftSpike();
            }
        }


        telemetry.addData("Status: ", "exited if statement");
        telemetry.update();
    }


    /**
     * METHODS FOR MOVING TO BACKSTAGE AND PARKING
     */

    public void parkFromRightSpike() {
        novaBot.frontLeftMotor.setPower(0.3);
        novaBot.backLeftMotor.setPower(0.3);
        novaBot.frontRightMotor.setPower(-0.3);
        novaBot.backRightMotor.setPower(-0.3);
        sleep(1750);
        novaBot.frontLeftMotor.setPower(-0.4);
        novaBot.backLeftMotor.setPower(-0.4);
        novaBot.frontRightMotor.setPower(-0.4);
        novaBot.backRightMotor.setPower(-0.4);
        sleep(1300);
        novaBot.frontLeftMotor.setPower(0.3);
        novaBot.backLeftMotor.setPower(0.3);
        novaBot.frontRightMotor.setPower(-0.3);
        novaBot.backRightMotor.setPower(-0.3);
        sleep(450);
        novaBot.frontLeftMotor.setPower(-0.4);
        novaBot.backLeftMotor.setPower(-0.4);
        novaBot.frontRightMotor.setPower(-0.4);
        novaBot.backRightMotor.setPower(-0.4);
        sleep(650);
        novaBot.frontLeftMotor.setPower(0);
        novaBot.backLeftMotor.setPower(0);
        novaBot.frontRightMotor.setPower(0);
        novaBot.backRightMotor.setPower(0);
        sleep(10);
        novaBot.intakeMotor.setPower(-1);
        sleep(1000);
    }

    public void parkFromCenterSpike() {
        novaBot.frontLeftMotor.setPower(0.3);
        novaBot.backLeftMotor.setPower(0.3);
        novaBot.frontRightMotor.setPower(-0.3);
        novaBot.backRightMotor.setPower(-0.3);
        sleep(1000);
        novaBot.frontLeftMotor.setPower(-0.4);
        novaBot.backLeftMotor.setPower(-0.4);
        novaBot.frontRightMotor.setPower(-0.4);
        novaBot.backRightMotor.setPower(-0.4);
        sleep(1650);
        novaBot.frontLeftMotor.setPower(0.3);
        novaBot.backLeftMotor.setPower(0.3);
        novaBot.frontRightMotor.setPower(-0.3);
        novaBot.backRightMotor.setPower(-0.3);
        sleep(600);
        novaBot.frontLeftMotor.setPower(-0.4);
        novaBot.backLeftMotor.setPower(-0.4);
        novaBot.frontRightMotor.setPower(-0.4);
        novaBot.backRightMotor.setPower(-0.4);
        sleep(500);
        novaBot.frontLeftMotor.setPower(0);
        novaBot.backLeftMotor.setPower(0);
        novaBot.frontRightMotor.setPower(0);
        novaBot.backRightMotor.setPower(0);
        sleep(10);
        novaBot.intakeMotor.setPower(-1);
        sleep(1000);
    }

    public void parkFromLeftSpike() {
        novaBot.frontLeftMotor.setPower(-0.3);
        novaBot.backLeftMotor.setPower(-0.3);
        novaBot.frontRightMotor.setPower(0.3);
        novaBot.backRightMotor.setPower(0.3);
        sleep(1100);
        novaBot.frontLeftMotor.setPower(-0.4);
        novaBot.backLeftMotor.setPower(-0.4);
        novaBot.frontRightMotor.setPower(-0.4);
        novaBot.backRightMotor.setPower(-0.4);
        sleep(1050);
        novaBot.frontLeftMotor.setPower(0.3);
        novaBot.backLeftMotor.setPower(0.3);
        novaBot.frontRightMotor.setPower(-0.3);
        novaBot.backRightMotor.setPower(-0.3);
        sleep(1250);
        novaBot.frontLeftMotor.setPower(-0.4);
        novaBot.backLeftMotor.setPower(-0.4);
        novaBot.frontRightMotor.setPower(-0.4);
        novaBot.backRightMotor.setPower(-0.4);
        sleep(1200);
        novaBot.frontLeftMotor.setPower(0);
        novaBot.backLeftMotor.setPower(0);
        novaBot.frontRightMotor.setPower(0);
        novaBot.backRightMotor.setPower(0);
        sleep(10);
        novaBot.intakeMotor.setPower(-1);
        sleep(1000);
    }

    /**
     * Add telemetry about TensorFlow Object Detection (TFOD) recognitions.
     */

    private boolean isPropPresent () {
        List<Recognition> currentRecognitions = novaBot.tfod.getRecognitions();
        int size = currentRecognitions.size();
        telemetry.addData("# objects detected: ", size);
        telemetry.update();

        if (size > 0) {
            return true;
        } else {
            return false;
        }
    }

    /** PID METHODS */
/*
    public void pidMoveSliderToEncoderPosBrakeMode (int targetEncoderPos, double power, int slowDownEncoderPos) {
        novaBot.isSliderMoving = true;

        getCurrentSliderEncoderPos();

        telemetry.addLine(targetEncoderPos + "," + novaBot.leftSliderMotor.getCurrentPosition());
        telemetry.update();

        if (targetEncoderPos > novaBot.leftSliderMotor.getCurrentPosition()) {
            pidSliderMoveUpBrakeMode(targetEncoderPos, power, slowDownEncoderPos);
        } else if (targetEncoderPos < novaBot.leftSliderMotor.getCurrentPosition()) {
            pidSliderMoveDownBrakeMode(targetEncoderPos, power, slowDownEncoderPos);
            telemetry.addLine("Current Position: " + novaBot.leftSliderMotor.getCurrentPosition());
            telemetry.update();
        }

        novaBot.isSliderMoving = false;
    }

    private void pidSliderMoveUpBrakeMode (int targetEncoderPos, double power, int slowDownEncoderPos) {
        this.novaBot.leftSliderMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        novaBot.rightSliderMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        double encoderDiff;
        double kP = 0.01;

        getCurrentSliderEncoderPos();

        while ((getCurrentSliderEncoderPos() <= targetEncoderPos - slowDownEncoderPos) && opModeIsActive()) {
            encoderDiff = novaBot.leftSliderMotor.getCurrentPosition() - novaBot.rightSliderMotor.getCurrentPosition();

            if (encoderDiff >= 0){
                novaBot.leftSliderMotor.setPower((power - kP * encoderDiff));
                novaBot.rightSliderMotor.setPower(power + kP * encoderDiff);
            } else {
                novaBot.rightSliderMotor.setPower((power + kP * encoderDiff));
                novaBot.leftSliderMotor.setPower(power - kP * encoderDiff);
            }
        }


        while (getCurrentSliderEncoderPos() <= targetEncoderPos && opModeIsActive()) {
            encoderDiff = novaBot.leftSliderMotor.getCurrentPosition() - novaBot.rightSliderMotor.getCurrentPosition();
            power = 0.3;

            if (encoderDiff >= 0) {
                novaBot.leftSliderMotor.setPower(power - kP *encoderDiff);
                novaBot.rightSliderMotor.setPower(power + kP * encoderDiff);
            } else {
                novaBot.rightSliderMotor.setPower(power + kP *encoderDiff);
                novaBot.leftSliderMotor.setPower(power-kP * encoderDiff);
            }
        }


        holdSlider();
    }

    public int getCurrentSliderEncoderPos() {
        return (novaBot.leftSliderMotor.getCurrentPosition() + novaBot.rightSliderMotor.getCurrentPosition()) / 2;
    }

    public void holdSlider() {
        novaBot.rightSliderMotor.setPower(0.05);
        novaBot.leftSliderMotor.setPower(0.05);
    }

    private void pidSliderMoveDownBrakeMode (int targetEncoderPos, double power, int slowDownEncoderPos) {
        novaBot.leftSliderMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        novaBot.rightSliderMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        double encoderDiff;
        double kP = 0.01;
        power = -power;

        while (getCurrentSliderEncoderPos() >= targetEncoderPos + slowDownEncoderPos && opModeIsActive()) {
            encoderDiff = novaBot.leftSliderMotor.getCurrentPosition() - novaBot.rightSliderMotor.getCurrentPosition();

            if (encoderDiff >= 0) {
                novaBot.leftSliderMotor.setPower(power - kP * encoderDiff);
                novaBot.rightSliderMotor.setPower(power + kP * encoderDiff);
            } else {
                novaBot.rightSliderMotor.setPower(power + kP * encoderDiff);
                novaBot.leftSliderMotor.setPower(power - kP * encoderDiff);
            }
        }

        while (getCurrentSliderEncoderPos() >= targetEncoderPos && opModeIsActive()) {
            encoderDiff = novaBot.leftSliderMotor.getCurrentPosition() - novaBot.rightSliderMotor.getCurrentPosition();

            power = -0.1;

            if (encoderDiff >= 0) {
                novaBot.leftSliderMotor.setPower(power - kP * encoderDiff);
                novaBot.rightSliderMotor.setPower(power + kP * encoderDiff);
            } else {
                novaBot.rightSliderMotor.setPower(power + kP * encoderDiff);
                novaBot.leftSliderMotor.setPower(power - kP * encoderDiff);
            }
        }

        holdSlider();
    }

    public void resetEncoders() {
        novaBot.frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        novaBot.frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        novaBot.backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        novaBot.backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    */
    /*// turn left to check for left spike
                novaBot.frontLeftMotor.setPower(0.3);
                novaBot.backLeftMotor.setPower(0.3);
                novaBot.frontRightMotor.setPower(-0.3);
                novaBot.backRightMotor.setPower(-0.3);
                sleep(750);
                novaBot.frontLeftMotor.setPower(0);
                novaBot.backLeftMotor.setPower(0);
                novaBot.frontRightMotor.setPower(0);
                novaBot.backRightMotor.setPower(0);
                sleep(2000);
                telemetry.addData("Status: ", "entering if statement");
                telemetry.update();
                // check for prop on left spike
                if (isPropPresent()) {
                    sleep(2000);
                    telemetry.addData("Status: ", "prop detected; ENTERED if statement");
                    telemetry.update();
                    // place pixel on left
                    novaBot.frontLeftMotor.setPower(0.3);
                    novaBot.backLeftMotor.setPower(0.3);
                    novaBot.frontRightMotor.setPower(0.3);
                    novaBot.backRightMotor.setPower(0.3);
                    sleep(1350);
                    novaBot.frontLeftMotor.setPower(0.3);
                    novaBot.backLeftMotor.setPower(0.3);
                    novaBot.frontRightMotor.setPower(-0.3);
                    novaBot.backRightMotor.setPower(-0.3);
                    sleep(500);
                    // back up
                    novaBot.frontLeftMotor.setPower(-0.3);
                    novaBot.backLeftMotor.setPower(-0.3);
                    novaBot.frontRightMotor.setPower(-0.3);
                    novaBot.backRightMotor.setPower(-0.3);
                    sleep(700);*/

}
