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
            novaBot.forwardUsingEncoders(14, 0.3);
            novaBot.backwardUsingEncoders(14, 0.3);

            // go to backdrop to place pixel
            novaBot.gyroTurnLeft(126);
            novaBot.forwardUsingEncoders(5, 0.25);

            // strafe
            novaBot.strafeRightUsingEncoders(18, 0.4);
            novaBot.forwardUsingEncoders(34.75, 0.25);


            parkFromRightSpike();
        } else {
            novaBot.forwardUsingEncoders(2, 0.2);
            novaBot.gyroTurnLeft(47);
            sleep(1250);
            if (isPropPresent()) {
                novaBot.forwardUsingEncoders(15.5, 0.3);
                novaBot.backwardUsingEncoders(15.5, 0.3);

                parkFromCenterSpike();
            } else {
                novaBot.forwardUsingEncoders(3, 0.2);
                novaBot.gyroTurnLeft(30);
                novaBot.backwardUsingEncoders(2, 0.2);
                sleep(1250);
                if (isPropPresent()) {
                    novaBot.gyroTurnLeft(10);
                    novaBot.forwardUsingEncoders(15, 0.3);
                    novaBot.backwardUsingEncoders(11, 0.3);

                    parkFromLeftSpike();
                }
            }
        }

/*
        // turn right

        // check for prop on right spike
        if (isPropPresent()) {
            // place prop on spike
            // move forward
            // move back

            // park in backstage
            parkFromRightSpike();
        } else {
            // turn to center (turn left)

            // check for prop on center
            if (isPropPresent()) {
                // place prop on center
                // move forward

                // move back

                // park in backstage
                parkFromCenterSpike();
            } else {
                // move forward to position

                // turn to left spike

                if (isPropPresent()) {
                    // place prop on left
                    // move forward
                    // move backward


                    // park in backstage
                    parkFromLeftSpike();
                }
            }
        }


        telemetry.addData("Status: ", "exited if statement");
        telemetry.update();
    }

 */
    }


        /**
         * METHODS FOR MOVING TO BACKSTAGE AND PARKING
         */

        public void parkFromRightSpike () {

        }

        public void parkFromCenterSpike () {

        }

        public void parkFromLeftSpike () {

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