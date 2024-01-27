package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp
public class Test_Intake extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare and initialize intake motor
        DcMotor intakeMotor;
        intakeMotor = hardwareMap.dcMotor.get("intakeMotor");

        boolean previousButtonState = false;
        boolean motorToggle = false;

        // Wait until play button on Driver Station is pressed
        waitForStart();

        if (isStopRequested()) return;

        // While we're in TeleOp...
        while (opModeIsActive()) {

            boolean currentButtonState = gamepad2.b;

            if (currentButtonState && !previousButtonState) {
                motorToggle = !motorToggle;

                intakeMotor.setPower(motorToggle ? 1 : 0);
            }

            previousButtonState = currentButtonState;

        }

    }


}