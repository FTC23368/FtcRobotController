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

        sleep(1250);
        // check for prop on spike
        if (isPropPresent()) {
            novaBot.gyroTurnLeft(7);
            novaBot.forwardUsingEncoders(15, 0.3);
            novaBot.backwardUsingEncoders(10, 0.3);

            novaBot.placePixel();

            parkFromRightSpike();

        } else {

            sleep(1250);
            if (isPropPresent()) {


                novaBot.placePixel();
                parkFromCenterSpike();
            } else {


                novaBot.placePixel();
                parkFromLeftSpike();
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