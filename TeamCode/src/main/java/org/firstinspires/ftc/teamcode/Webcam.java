package org.firstinspires.ftc.teamcode;

// imported for CPU efficiency
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
// imported because there was an error earlier and this was the suggested fix (in telemetryTfod())
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;
import java.util.List;

@TeleOp(name = "Concept: TensorFlow Object Detection Easy", group = "Concept")

// all using 'easy' method right now
public class Webcam {

    private static final boolean USE_WEBCAM = true;
    private TfodProcessor tfod = TfodProcessor.easyCreateWithDefaults();

    private void initTfod() {

        // Create the TensorFlow processor the easy way
        TfodProcessor tfod = TfodProcessor.easyCreateWithDefaults();

        // creating vision portal
        if (USE_WEBCAM) {
            VisionPortal visionPortal = VisionPortal.easyCreateWithDefaults(
                    HardwareMap.get(WebcamName.class, "Webcam 1"), tfod);
        } else {
            VisionPortal visionPortal = VisionPortal.easyCreateWithDefaults(
                    BuiltinCameraDirection.BACK, tfod);
        }

        // zoomed in center area of each image will be sent to tensorflow object detector
        // no zoom = 1.0
        // ensures pixel will be seen from more than 2 ft away
        tfod.setZoom(2.0);

        // object detector has to be at least 80% sure it sees a pixel
        tfod.setMinResultConfidence((float) 0.80);

    }

    /*
     * // Save CPU resources; can resume streaming when needed.
     * if (gamepad1.dpad_down) {
     *      visionPortal.stopStreaming();
     * } else if (gamepad1.dpad_up) {
     *      visionPortal.resumeStreaming();
     * }
     */

    private void telemetryTfod() {

        List<Recognition> currentRecognitions = tfod.getRecognitions();
        telemetry.addData("# Objects Detected", currentRecognitions.size());

        // Step through the list of recognitions and display info for each one
        for (Recognition recognition : currentRecognitions) {
            double x = (recognition.getLeft() + recognition.getRight()) / 2 ;
            double y = (recognition.getTop()  + recognition.getBottom()) / 2 ;

            telemetry.addData(""," ");
            telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
            telemetry.addData("- Position", "%.0f / %.0f", x, y);
            telemetry.addData("- Size", "%.0f x %.0f", recognition.getWidth(), recognition.getHeight());
        }

    }

}
