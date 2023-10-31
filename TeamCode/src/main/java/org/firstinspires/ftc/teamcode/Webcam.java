package org.firstinspires.ftc.teamcode;

// imported for CPU efficiency
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

// imported for exposure control - how much light the webcam takes in changes the way the tfod
// and april tag processor see the pixel/apriltag a lot
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.ExposureControl;

// extends linearOpMode & uses TeleOp(?)
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

// OpenCV - image processing unit - might not be needed
//import org.opencv.

// cam direction was imported in the event we weren't using a webcam, but we are
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;

// self-explanatory; first is just for naming, second is what the tfod uses to recognize pixels
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

// vision portal -
import org.firstinspires.ftc.vision.VisionPortal;

// apriltags and pixel processors
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;


/** where is it recognizing a pixel - solved, easyCreateWithDefaults() sets the library to be used
 ** VisionPortal includes AprilTag Detection - we do not need the other two classes
 **
 */


// is it staying teleop though
@TeleOp(name = "Concept: TensorFlow Object Detection Easy", group = "Concept")


// all using default 'easy' methods right now
public class Webcam extends LinearOpMode{

    // variable below was made in the event we weren't using a webcam but we are
    // private static final boolean USE_WEBCAM = true;
    private TfodProcessor tfod;
    private AprilTagProcessor aprilTagProcessor;


    private void initTfod() {

        // Create the TensorFlow processor the easy way
        tfod = TfodProcessor.easyCreateWithDefaults();
        // create the aprilTag processor the easy way
        aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();

        // creating vision portal - using both tagprocessor and tfod in same opMode,
        // can be changed to seperate ones if wanted
        VisionPortal visionPortal = VisionPortal.easyCreateWithDefaults(
                hardwareMap.get(WebcamName.class, "Webcam 1"), aprilTagProcessor, tfod);

        // zoomed in center area of each image will be sent to tensorflow object detector
        // no zoom = 1.0
        // ensures pixel will be seen from more than 2 ft away
        tfod.setZoom(2.0);

        // object detector has to be at least 80% sure it sees a pixel
        tfod.setMinResultConfidence((float) 0.80);

    }

    /*
     * // save CPU resources; can resume streaming when needed.
     * // implement in runOpMode if decided to
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
            telemetry.addData("Image", "%s (%.0f %% Conf.)",
                    recognition.getLabel(), recognition.getConfidence() * 100);
            telemetry.addData("- Position", "%.0f / %.0f", x, y);
            telemetry.addData("- Size", "%.0f x %.0f",
                    recognition.getWidth(), recognition.getHeight());
        }

    }

    public void runOpMode(){
        return;
    }

}
