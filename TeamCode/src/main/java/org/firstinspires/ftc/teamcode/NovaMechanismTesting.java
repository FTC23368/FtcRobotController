package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp
public class NovaMechanismTesting extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        // All of the following is drafted code for functionality of individual mechanisms:
        /*
        Mechanisms we need to program (tele-op):
        - Linear Slides (left and right) --> DC motors
        - Pocket --> Servo
        - Intake (broom) --> DC motor
        - Linear Actuators (left and right) --> DC motors
         */

        Servo pocket = hardwareMap.servo.get("pocket");
        DcMotor leftLinearSlide = hardwareMap.dcMotor.get("leftLinearSlide");
        DcMotor rightLinearSlide = hardwareMap.dcMotor.get("rightLinearSlide");


        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            // The code below is for pocket functionality (opening and closing the pocket)
            if (gamepad2.y) {
                // 45 degrees - POCKET OPEN
                pocket.setDirection(Servo.Direction.REVERSE);
                pocket.setPosition(0.15);
                sleep(500);
                // 0 degrees - POCKET CLOSED
                pocket.setPosition(0);
            }

            // The code below is for linear slide functionality

            if (gamepad2.dpad_left) {
                //low height
                double leftCurrentPosition = leftLinearSlide.getCurrentPosition();
                double rightCurrentPosition = rightLinearSlide.getCurrentPosition();
                leftLinearSlide.setTargetPosition(20);
                while (leftCurrentPosition!= 0) {

                }

            }
            if (gamepad2.dpad_up) {
                //medium height
                leftLinearSlide.setPower(0.5);
                rightLinearSlide.setPower(-0.5);
            }
            if (gamepad2.dpad_right) {
                //max height
                leftLinearSlide.setPower(0.5);
                rightLinearSlide.setPower(-0.5);
            }
            if (gamepad2.dpad_down) {
                //min height
                leftLinearSlide.setPower(-0.5);
                rightLinearSlide.setPower(0.5);
            }

            /*
            double power = PIDControl(100, testMotor.getCurrentPosition())
            testMotor.setPower(power);
            */

        }
    }

    public double PIDControl(double reference, double state) {

        double lastError = 0;
        double integralSum = 0;
        double kp = 0;
        double ki = 0;
        double kd = 0;

        ElapsedTime timer = new ElapsedTime();

        double error = reference - state;
        integralSum += error * timer.seconds();
        double derivative = (error - lastError) / timer.seconds();
        lastError = error;

        timer.reset();

        double output = (error * kp) + (derivative * kd) + (integralSum * ki);
        return output;
    }

}

/*
        //go to fixed pos of linear slide
        boolean linear_moving = true;
        int distance_to_move = leftLinearSlideMotor.getCurrentPosition() - leftLinearSlideMotor.



            The following code can be used for other additional gamepad functionality
            that tests the pocket motion:

            if (gamepad2.b) {
                // 90 degrees
                pocket.setDirection(Servo.Direction.REVERSE);
                pocket.setPosition(0.3);
            }
            if (gamepad2.x) {
                // 0 degrees - POCKET CLOSED
                pocket.setDirection(Servo.Direction.REVERSE);
                pocket.setPosition(0);
            }

             */