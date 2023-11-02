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

// apriltag and tfod processors & library
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagLibrary;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;
import org.opencv.core.Point;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


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
    private ExposureControl exposureControl;
    private AprilTagLibrary library;


    public void initTfod() {

        // create tensorflow and apriltag processor the easy way
        tfod = TfodProcessor.easyCreateWithDefaults();
        aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();

        // creating vision portal - using both tagprocessor and tfod in same opMode,
        // can be changed to seperate ones if wanted
        VisionPortal visionPortal = VisionPortal.easyCreateWithDefaults(
                hardwareMap.get(WebcamName.class, "Webcam 1"), aprilTagProcessor, tfod);

        // create exposure control object
        exposureControl = visionPortal.getCameraControl(ExposureControl.class);
        // myExposureControl = vuforia.getCamera().getControl(ExposureControl.class);
        // it does not work because tfod does not have a getCamera option. (?)

        // https://ftc-docs.firstinspires.org/en/latest/programming_resources/vision/
        // webcam_controls/gain/ex1/ex1.html?highlight=gain#example-1-exposures-effect-on-tfod
        exposureControl.setExposure(35, TimeUnit.MILLISECONDS);

        // zoomed in center area of each image will be sent to tensorflow object detector
        // no zoom = 1.0; ensures pixel will be seen from more than 2 ft away
        tfod.setZoom(2.0);

        // object detector has to be at least 75% sure it sees a pixel
        tfod.setMinResultConfidence((float) 0.75);
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

    public List<Recognition> telemetryTfod() {

        List<Recognition> tfodCurrentRecognitions = tfod.getRecognitions();

        telemetry.addData("# Objects Detected for TFOD", tfodCurrentRecognitions.size());
        // Step through the list of recognitions and display info for each one
        for (Recognition recognition : tfodCurrentRecognitions) {
            double x = (recognition.getLeft() + recognition.getRight()) / 2;
            double y = (recognition.getTop() + recognition.getBottom()) / 2;

            telemetry.addData("", " ");
            telemetry.addData("Image", "%s (%.0f %% Conf.)",
                    recognition.getLabel(), recognition.getConfidence() * 100);
            telemetry.addData("- Position", "%.0f / %.0f", x, y);
            telemetry.addData("- Size", "%.0f x %.0f",
                    recognition.getWidth(), recognition.getHeight());
            telemetry.update();
        }

        return tfodCurrentRecognitions;
    }

    public ArrayList<AprilTagDetection> telemetryAprilTag(){
        ArrayList<AprilTagDetection> apriltagCurrentRecognitions = aprilTagProcessor.getDetections();
        telemetry.addData("# Tags Detected for AprilTag", apriltagCurrentRecognitions.size());
        // Step through the list of recognitions and display info for each one
        for (AprilTagDetection detection : apriltagCurrentRecognitions) {
            double xCenter = detection.center.x;
            double yCenter = detection.center.y;
            Point[] corners = detection.corners; // i don't know which corner is which
            // assuming its counter clockwise, and that corners[0] is top left

            telemetry.addData("", " ");
            telemetry.addData("Detection",
                    detection.toString());
            telemetry.addData("- Center", "%.0f / %.0f", xCenter, yCenter);
            telemetry.addData("- Size", "%.0f x %.0f",
                    (Math.sqrt(Math.pow((corners[0].x + corners[1].x), 2)
                            - Math.pow((corners[0].y + corners[1].y), 2))));
        }

        return apriltagCurrentRecognitions;
    }



    public void runOpMode(){
        telemetryTfod();
        telemetryAprilTag();
    }

}


// // creating vision portal in the case that we don't use a webcam
//        if (USE_WEBCAM) {
//            VisionPortal visionPortal = VisionPortal.easyCreateWithDefaults(
//                    hardwareMap.get(WebcamName.class, "Webcam 1"), tfod);
//        } else {
//            VisionPortal visionPortal = VisionPortal.easyCreateWithDefaults(
//                    BuiltinCameraDirection.BACK, tfod);
//        }