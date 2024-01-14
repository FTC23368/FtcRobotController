package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.List;

// BACKDROP SIDE

@Autonomous
public class NovaAutoBackdropSideRedNew extends LinearOpMode {

    NovaBot novaBot;

    @Override
    public void runOpMode() throws InterruptedException {

        novaBot = new NovaBot(this);
        novaBot.initNovaBot();

        waitForStart();
        novaBot.runtime.reset();

        // move forward 15.25 in.
        novaBot.forwardUsingEncoders(16, 0.3);
        novaBot.gyroTurnLeft(26);
        novaBot.gyroTurnLeft(26);
        novaBot.backwardUsingEncoders(2, 0.2);

        sleep(1250);
        // check for prop on spike
        if (isPropPresent()) {
            novaBot.forwardUsingEncoders(5, 0.2);
            novaBot.gyroTurnLeft(14);
            novaBot.gyroTurnLeft(14);
            novaBot.forwardUsingEncoders(10, 0.3);
            novaBot.backwardUsingEncoders(14, 0.3);

            // strafe
            novaBot.strafeRightUsingEncoders(15, 0.4);
            // move and align to backdrop
            novaBot.gyroTurnRight(-138);
            novaBot.strafeLeftUsingEncoders(6, 0.4);
            novaBot.forwardUsingEncoders(27, 0.3);
            // place pixel
            placePixel();
            // park in backstage
            parkFromLeftSpike();

        } else {
            // move to look at center spike
            novaBot.forwardUsingEncoders(2, 0.2);
            novaBot.gyroTurnRight(-26);

            sleep(1250);
            // check if prop is present
            if (isPropPresent()) {
                // place pixel on spike
                novaBot.forwardUsingEncoders(15, 0.3);
                novaBot.backwardUsingEncoders(9, 0.3);
                // move and align to backdrop
                novaBot.gyroTurnRight(-90);
                novaBot.forwardUsingEncoders(35, 0.3);
                novaBot.strafeLeftUsingEncoders(8, 0.4);
                novaBot.forwardUsingEncoders(5, 0.3);
                // place pixel on backdrop
                placePixel();
                // park in backstage
                parkFromCenterSpike();

            } else {
                // move to right spike
                novaBot.gyroTurnRight(-36);
                // place on spike
                novaBot.forwardUsingEncoders(10.5, 0.3);
                novaBot.backwardUsingEncoders(7.5, 0.3);
                // move and align to backdrop
                novaBot.gyroTurnRight(-45);
                novaBot.forwardUsingEncoders(30, 0.3);
                novaBot.strafeLeftUsingEncoders(2, 0.4);
                novaBot.forwardUsingEncoders(10, 0.3);
                // place pixel on backdrop
                placePixel();
                // park in backstage
                parkFromRightSpike();
            }
        }
    }

    /**
     * METHOD TO PLACE PIXEL ON BACKDROP
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
        novaBot.backwardUsingEncoders(2, 0.3);
        novaBot.strafeRightUsingEncoders(17, 0.4);
        novaBot.forwardUsingEncoders(4, 0.3);
    }

    public void parkFromCenterSpike() {
        novaBot.backwardUsingEncoders(2, 0.3);
        novaBot.strafeRightUsingEncoders(20, 0.4);
        novaBot.forwardUsingEncoders(3, 0.3);
    }

    public void parkFromLeftSpike() {
        novaBot.backwardUsingEncoders(2, 0.3);
        novaBot.strafeLeftUsingEncoders(19, 0.4);
        novaBot.forwardUsingEncoders(5, 0.2);
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