package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.List;

// BACKDROP SIDE

@Autonomous
public class NovaAutoBackdropSideBlueNew extends LinearOpMode {

    NovaBot novaBot;

    @Override
    public void runOpMode() throws InterruptedException {

        novaBot = new NovaBot(this);
        novaBot.initNovaBot();

        waitForStart();
        novaBot.runtime.reset();

        // move forward 15.25 in.
        novaBot.forwardUsingEncoders(16, 0.3);
        novaBot.gyroTurnRight(-47);
        novaBot.backwardUsingEncoders(2, 0.2);

        sleep(1250);
        // check for prop on spike
        if (isPropPresent()) {
            novaBot.gyroTurnLeft(7);
            novaBot.forwardUsingEncoders(12.5, 0.3);
            novaBot.backwardUsingEncoders(12.5, 0.3);

            // go to backdrop to place pixel
            novaBot.gyroTurnLeft(126);
            novaBot.forwardUsingEncoders(5, 0.25);
            novaBot.linearOpMode.telemetry.addData("Status", "starting to strafe");
            novaBot.linearOpMode.telemetry.update();

            // strafe
            novaBot.strafeRightUsingEncoders(18, 0.4);
            novaBot.forwardUsingEncoders(38.75, 0.275);
            sleep(400);

            // slides up and place pixel
            placePixel();

            // park in backstage
            parkFromRightSpike();

        } else {
            novaBot.forwardUsingEncoders(2, 0.2);
            novaBot.gyroTurnLeft(47);
            sleep(1250);
            if (isPropPresent()) {
                novaBot.forwardUsingEncoders(15.5, 0.3);
                novaBot.backwardUsingEncoders(17, 0.3);

                // strafe
                novaBot.strafeLeftUsingEncoders(24, 0.4);
                // rotate
                novaBot.gyroTurnLeft(87);
                // strafe right
                novaBot.strafeRightUsingEncoders(13.7, 0.4);
                // move forward to backdrop
                novaBot.forwardUsingEncoders(19, 0.3);

                // place pixel on backdrop
                placePixel();

                // park in backstage
                parkFromCenterSpike();
            } else {
                novaBot.forwardUsingEncoders(3, 0.2);
                novaBot.gyroTurnLeft(47);
                novaBot.backwardUsingEncoders(1, 0.2);
                novaBot.gyroTurnLeft(47);
                sleep(3000);
                novaBot.forwardUsingEncoders(14, 0.3);
                novaBot.backwardUsingEncoders(8.5, 0.3);

                // rotate
                novaBot.gyroTurnLeft(32);
                // strafe left
                novaBot.strafeLeftUsingEncoders(5, 0.4);
                // forward
                novaBot.forwardUsingEncoders(24, 0.3);
                // strafe right a little
                novaBot.strafeRightUsingEncoders(2, 0.4);
                // forward to align to backdrop
                novaBot.forwardUsingEncoders(17, 0.3);
                sleep(400);

                // place pixel on backdrop
                placePixel();

                // park in backstage
                parkFromLeftSpike();
            }
        }
    }

        /**
         * PLACING PIXEL ON BACKDROP
         */

        public void placePixel() {
            novaBot.linearOpMode.telemetry.addData("Status", "placing pixel");
            novaBot.linearOpMode.telemetry.update();
            novaBot.pidMoveSliderToEncoderPosBrakeMode(1500, .5, 100);
            novaBot.pocket.setPosition(0);
            sleep(700);
            novaBot.pocket.setPosition(0.25);
            novaBot.pidMoveSliderToEncoderPosBrakeMode(0, .3, 100);
            novaBot.linearOpMode.telemetry.addData("Status", "finished placing pixel");
            novaBot.linearOpMode.telemetry.update();
        }

        /**
         * METHODS FOR PARKING
         */

        public void parkFromRightSpike() {
            novaBot.backwardUsingEncoders(3, 0.3);
            novaBot.strafeRightUsingEncoders(12, 0.4);
            novaBot.forwardUsingEncoders(8, 0.4);
        }

        public void parkFromCenterSpike() {
            novaBot.backwardUsingEncoders(2, 0.3);
            novaBot.strafeLeftUsingEncoders(20, 0.4);
            novaBot.forwardUsingEncoders(4, 0.4);
        }

        public void parkFromLeftSpike() {
            novaBot.backwardUsingEncoders(2, 0.3);
            novaBot.strafeLeftUsingEncoders(18, 0.4);
            novaBot.forwardUsingEncoders(8, 0.4);
        }

        /**
         * Add telemetry about TensorFlow Object Detection (TFOD) recognitions.
         */

        private boolean isPropPresent() {
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

    }