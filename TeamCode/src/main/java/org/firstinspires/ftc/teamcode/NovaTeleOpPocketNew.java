package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="NovaTeleOpPocketNew", group="Linear Opmode")
public class NovaTeleOpPocketNew extends LinearOpMode {

    private boolean pocketOpen = false;
    private boolean lastButtonState = false;

    @Override
    public void runOpMode() {
        Servo pocket = hardwareMap.servo.get("pocket");

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addLine("Current position: " + pocket.getPosition());
            telemetry.update();

            boolean currentButtonState = gamepad2.y;
            // Check if button state has changed from not being pressed to being pressed
            if (currentButtonState && !lastButtonState) {
                pocketOpen = !pocketOpen; // Toggle the state
                pocket.setPosition(pocketOpen ? 0 : 0.25); // Set position based on the state
            }
            lastButtonState = currentButtonState; // Update the last button state
        }
    }
}