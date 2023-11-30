package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.List;

// BACKDROP SIDE

@Autonomous
public class NovaAutoBackdropSideBlueCopy extends LinearOpMode {

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
                novaBot.frontLeftMotor.setPower(0.3);
                novaBot.backLeftMotor.setPower(0.3);
                novaBot.frontRightMotor.setPower(0.3);
                novaBot.backRightMotor.setPower(0.3);

                sleep(675);
                novaBot.frontLeftMotor.setPower(0);
                novaBot.backLeftMotor.setPower(0);
                novaBot.frontRightMotor.setPower(0);
                novaBot.backRightMotor.setPower(0);
                sleep(100);
                novaBot.frontLeftMotor.setPower(-0.3);
                novaBot.backLeftMotor.setPower(-0.3);
                novaBot.frontRightMotor.setPower(-0.3);
                novaBot.backRightMotor.setPower(-0.3);
                sleep(450);
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
                sleep(750);

                sleep(2000);
                // check for prop on center
                if (isPropPresent()) {
                    // place prop on center
                    novaBot.frontLeftMotor.setPower(0.3);
                    novaBot.backLeftMotor.setPower(0.3);
                    novaBot.frontRightMotor.setPower(0.3);
                    novaBot.backRightMotor.setPower(0.3);
                    sleep(1200);
                    novaBot.frontLeftMotor.setPower(-0.3);
                    novaBot.backLeftMotor.setPower(-0.3);
                    novaBot.frontRightMotor.setPower(-0.3);
                    novaBot.backRightMotor.setPower(-0.3);
                    sleep(500);
                } else {
                    novaBot.frontLeftMotor.setPower(0.3);
                    novaBot.backLeftMotor.setPower(0.3);
                    novaBot.frontRightMotor.setPower(0.3);
                    novaBot.backRightMotor.setPower(0.3);
                    sleep(300);
                    // turn to left spike
                    novaBot.frontLeftMotor.setPower(0.3);
                    novaBot.backLeftMotor.setPower(0.3);
                    novaBot.frontRightMotor.setPower(-0.3);
                    novaBot.backRightMotor.setPower(-0.3);
                    sleep(750);
                    novaBot.frontLeftMotor.setPower(0);
                    novaBot.backLeftMotor.setPower(0);
                    novaBot.frontRightMotor.setPower(0);
                    novaBot.backRightMotor.setPower(0);
                    sleep(50);

                    sleep(2000);
                    if (isPropPresent()) {
                        // place prop on left
                        novaBot.frontLeftMotor.setPower(0.3);
                        novaBot.backLeftMotor.setPower(0.3);
                        novaBot.frontRightMotor.setPower(0.3);
                        novaBot.backRightMotor.setPower(0.3);
                        sleep(1400);
                        novaBot.frontLeftMotor.setPower(-0.3);
                        novaBot.backLeftMotor.setPower(-0.3);
                        novaBot.frontRightMotor.setPower(-0.3);
                        novaBot.backRightMotor.setPower(-0.3);
                        sleep(1000);
                        novaBot.frontLeftMotor.setPower(0);
                        novaBot.backLeftMotor.setPower(0);
                        novaBot.frontRightMotor.setPower(0);
                        novaBot.backRightMotor.setPower(0);
                        sleep(50);
                    }
                }
            }





/*
        sleep(1000);
            if (isPropPresent()) {
                sleep(2000);
                telemetry.addData("Status: ", "prop detected; ENTERED if statement");
                telemetry.update();
                novaBot.frontLeftMotor.setPower(0.3);
                novaBot.backLeftMotor.setPower(0.3);
                novaBot.frontRightMotor.setPower(0.3);
                novaBot.backRightMotor.setPower(0.3);
                sleep(1200);
                novaBot.frontLeftMotor.setPower(-0.3);
                novaBot.backLeftMotor.setPower(-0.3);
                novaBot.frontRightMotor.setPower(-0.3);
                novaBot.backRightMotor.setPower(-0.3);
                sleep(500);
            } else {
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
                        novaBot.frontLeftMotor.setPower(0.3);
                        novaBot.backLeftMotor.setPower(0.3);
                        novaBot.frontRightMotor.setPower(0.3);
                        novaBot.backRightMotor.setPower(0.3);

                        sleep(675);
                        novaBot.frontLeftMotor.setPower(0);
                        novaBot.backLeftMotor.setPower(0);
                        novaBot.frontRightMotor.setPower(0);
                        novaBot.backRightMotor.setPower(0);
                        sleep(100);
                        novaBot.frontLeftMotor.setPower(-0.3);
                        novaBot.backLeftMotor.setPower(-0.3);
                        novaBot.frontRightMotor.setPower(-0.3);
                        novaBot.backRightMotor.setPower(-0.3);
                        sleep(450);
                    } else {
                        novaBot.frontLeftMotor.setPower(-0.3);
                        novaBot.backLeftMotor.setPower(-0.3);
                        novaBot.frontRightMotor.setPower(-0.3);
                        novaBot.backRightMotor.setPower(-0.3);
                        sleep(300);
                        novaBot.frontLeftMotor.setPower(0.3);
                        novaBot.backLeftMotor.setPower(0.3);
                        novaBot.frontRightMotor.setPower(-0.3);
                        novaBot.backRightMotor.setPower(-0.3);
                        sleep(2000);

                        if (isPropPresent()) {
                            novaBot.frontLeftMotor.setPower(0.3);
                            novaBot.backLeftMotor.setPower(0.3);
                            novaBot.frontRightMotor.setPower(-0.3);
                            novaBot.backRightMotor.setPower(-0.3);
                            sleep(300);
                            novaBot.frontLeftMotor.setPower(0.3);
                            novaBot.backLeftMotor.setPower(0.3);
                            novaBot.frontRightMotor.setPower(0.3);
                            novaBot.backRightMotor.setPower(0.3);
                            sleep(1250);
                            novaBot.frontLeftMotor.setPower(0);
                            novaBot.backLeftMotor.setPower(0);
                            novaBot.frontRightMotor.setPower(0);
                            novaBot.backRightMotor.setPower(0);
                            sleep(50);
                            novaBot.frontLeftMotor.setPower(-0.3);
                            novaBot.backLeftMotor.setPower(-0.3);
                            novaBot.frontRightMotor.setPower(-0.3);
                            novaBot.backRightMotor.setPower(-0.3);
                            sleep(750);
                            novaBot.frontLeftMotor.setPower(0);
                            novaBot.backLeftMotor.setPower(0);
                            novaBot.frontRightMotor.setPower(0);
                            novaBot.backRightMotor.setPower(0);
                            sleep(50);
                        }

                    }
                    // forward and place

                    // back up
                }
            telemetry.addData("Status: ", "exited if statement");
            telemetry.update();*/



            // go forward x inches, place pixel and then back

            // turn counterclockwise 90 deg in place
            /*frontRightMotor.setPower(0.5);
            backRightMotor.setPower(0.5);
            frontLeftMotor.setPower(-0.5);
            backLeftMotor.setPower(-0.5);
            sleep(200);
            frontLeftMotor.setPower(0);
            backLeftMotor.setPower(0);
            frontRightMotor.setPower(0);
            backRightMotor.setPower(0);

            // move forward till backdrop
            frontLeftMotor.setPower(0.5);
            backLeftMotor.setPower(0.5);
            frontRightMotor.setPower(0.5);
            backRightMotor.setPower(0.5);
            sleep(1000);
            frontLeftMotor.setPower(0);
            backLeftMotor.setPower(0);
            frontRightMotor.setPower(0);
            backRightMotor.setPower(0);

            // extend linear slides and place pixel
            pidMoveSliderToEncoderPosBrakeMode(1200, .5, 100);
            pocket.setPosition(0);
            sleep(700);
            // 0 degrees - POCKET CLOSED
            pocket.setPosition(0.25);

            // retract sliders and move back
            pidMoveSliderToEncoderPosBrakeMode(5, .3, 100);
            frontLeftMotor.setPower(-0.5);
            backLeftMotor.setPower(-0.5);
            frontRightMotor.setPower(-0.5);
            backRightMotor.setPower(-0.5);
            sleep(200);
            frontLeftMotor.setPower(0);
            backLeftMotor.setPower(0);
            frontRightMotor.setPower(0);
            backRightMotor.setPower(0);

            // strafe right and then move forward to park
            frontLeftMotor.setPower(-0.5);
            backLeftMotor.setPower(0.5);
            frontRightMotor.setPower(0.5);
            backRightMotor.setPower(-0.5);
            sleep(200);
            frontLeftMotor.setPower(0);
            backLeftMotor.setPower(0);
            frontRightMotor.setPower(0);
            backRightMotor.setPower(0);
            // stop*/

            //novaBot.visionPortal.close();

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
