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
        novaBot.backwardUsingEncoders(2, 0.2);

        //sleep(1250);
        // check for prop on spike
        if (isPropPresent()) {
            novaBot.gyroTurnLeft(17);
            novaBot.forwardUsingEncoders(15, 0.3);
            novaBot.backwardUsingEncoders(14, 0.3);

            // rotate
            novaBot.gyroTurnRight(135);
            // strafe
            novaBot.strafeLeftUsingEncoders(24, 0.4);
            // move forward to backdrop
            novaBot.forwardUsingEncoders(36, 0.4);
            // correct (if needed)


            //novaBot.placePixel();

            //parkFromRightSpike();

        } else {
            // move to look at center spike
            novaBot.forwardUsingEncoders(2, 0.2);
            novaBot.gyroTurnRight(-26);

            sleep(1250);
            // check if prop is present
            if (isPropPresent()) {
                novaBot.forwardUsingEncoders(15, 0.3);
                novaBot.backwardUsingEncoders(13, 0.3);

                //novaBot.placePixel();
                //parkFromCenterSpike();
            } else {
                // move to right spike
                novaBot.gyroTurnRight(-36);
                // place on spike
                novaBot.forwardUsingEncoders(12, 0.3);
                // move back
                novaBot.backwardUsingEncoders(12, 0.3);

                //novaBot.placePixel();
                //parkFromLeftSpike();
            }
        }
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