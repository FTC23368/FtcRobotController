package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp
public class NovaTeleOpDrone extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        CRServo leftDrone = hardwareMap.crservo.get("leftDrone");
        CRServo rightDrone = hardwareMap.crservo.get("rightDrone");

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
           if (gamepad2.a) {
               leftDrone.setPower(1);
               rightDrone.setPower(-1);
           }
        }

    }


}