package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp
public class NovaTeleOpPocket extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Servo pocket = hardwareMap.servo.get("pocket");

        waitForStart();

        if (isStopRequested()) return;

        // While we're in TeleOp...
        while (opModeIsActive()) {
            telemetry.addLine("Current position: " + pocket.getPosition());
            telemetry.update();

            if (gamepad2.y) {
                // 45 degrees - POCKET OPEN
                //pocket.setDirection(Servo.Direction.REVERSE);
                pocket.setPosition(0);
                sleep(800);
                // 0 degrees - POCKET CLOSED
                pocket.setPosition(0.25);
            }

        }
    }

}