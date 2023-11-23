package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;

public class NovaTeleOpDroneNew extends LinearOpMode {

    private CRServo leftWheel;
    private CRServo rightWheel;

    @Override
    public void runOpMode() {
        // Initialize the continuous rotation servos
        leftWheel = hardwareMap.get(CRServo.class, "leftWheel");
        rightWheel = hardwareMap.get(CRServo.class, "rightWheel");

        // Reverse one of the servos if they are physically oriented in the same direction
        rightWheel.setDirection(CRServo.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive()) {
            // Check if gamepad button is pressed to start the wheels
            if (gamepad2.a) {
                // Spin the wheels to launch the paper plane
                // Assuming leftWheel is set to FORWARD by default
                leftWheel.setPower(1.0);   // Wheel spins forward
                // Assuming rightWheel needs to spin in the opposite direction
                rightWheel.setPower(1.0);  // Wheel spins backward because it's reversed

                telemetry.addData("Status", "Launching Paper Plane");
            } else {
                // Stop the wheels when button is not pressed
                leftWheel.setPower(0);
                rightWheel.setPower(0);

                telemetry.addData("Status", "Stopped");
            }

            // Telemetry to display the status
            telemetry.update();
        }

        // Stop the wheels when OpMode is no longer active
        leftWheel.setPower(0);
        rightWheel.setPower(0);
    }
}
