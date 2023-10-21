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


        Servo testServo = hardwareMap.servo.get("testServo");
        //DcMotor testMotor = hardwareMap.dcMotor.get("testMotor");

        double SERVO_HOME = 0.0;
        double SERVO_MIN_POS = 0.0;
        double SERVO_MAX_POS = 1.0;
        double SERVO_SPEED = 0.3;

        //double currServoPos = testServo.getPosition();

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            if (gamepad2.a) {
                testServo.setDirection(Servo.Direction.REVERSE);
                testServo.setPosition(0.2);

                System.out.println("servo moved");
            }
            if (gamepad2.x) {
                testServo.setDirection(Servo.Direction.FORWARD);
                testServo.setPosition(0.1);
            }
            //testServo.setPosition(currServoPos);
            //currServoPos = Range.clip(currServoPos, SERVO_MIN_POS, SERVO_MAX_POS);


            /*
            double power = PIDControl(100, testMotor.getCurrentPosition())
            testMotor.setPower(power);
            */


        }

/*
        //go to fixed pos of linear slide
        boolean linear_moving = true;
        int distance_to_move = leftLinearSlideMotor.getCurrentPosition() - leftLinearSlideMotor.
        */
    }

    /*
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

     */
}
