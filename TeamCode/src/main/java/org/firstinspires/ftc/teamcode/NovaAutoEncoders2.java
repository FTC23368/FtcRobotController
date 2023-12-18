package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.List;

// BACKDROP SIDE

@Autonomous
public class NovaAutoEncoders2 extends LinearOpMode {

    NovaBot novaBot;
    static final double MOTOR_TICK_COUNTS = 537.7;
    static final double WHEEL_DIAMETER_MM = 96;
    static final double WHEEL_CIRCUMFERENCE_MM = 301.44;

    static final double COUNTS_PER_INCH = MOTOR_TICK_COUNTS / (WHEEL_DIAMETER_MM * Math.PI);

    @Override
    public void runOpMode() throws InterruptedException {

        novaBot = new NovaBot(this);
        novaBot.initNovaBot();

        waitForStart();
        novaBot.runtime.reset();

        novaBot.frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        novaBot.backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        novaBot.frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        novaBot.backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        // Reset encoders and set mode to RUN_USING_ENCODER
        resetEncoders();

        // Wait for the start button to be pressed
        waitForStart();

        // Move forward for 12 inches using encoders
        encoderDrive(0.3, 12);

        // Add more autonomous movements as needed

        // Stop the op mode
        stop();

    }

    private void resetEncoders() {
        novaBot.frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        novaBot.frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        novaBot.backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        novaBot.backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        novaBot.frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        novaBot.frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        novaBot.backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        novaBot.backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void encoderDrive(double speed, double inches) {
        int targetPosition = (int) (inches * COUNTS_PER_INCH);

        // Set target positions for all motors
        novaBot.frontLeftMotor.setTargetPosition(targetPosition);
        novaBot.frontRightMotor.setTargetPosition(targetPosition);
        novaBot.backLeftMotor.setTargetPosition(targetPosition);
        novaBot.backRightMotor.setTargetPosition(targetPosition);

        // Set mode to RUN_TO_POSITION
        novaBot.frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        novaBot.frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        novaBot.backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        novaBot.backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set motor power
        novaBot.frontLeftMotor.setPower(speed);
        novaBot.frontRightMotor.setPower(speed);
        novaBot.backLeftMotor.setPower(speed);
        novaBot.backRightMotor.setPower(speed);

        // Wait until the motors reach the target position
        while (opModeIsActive() &&
                (novaBot.frontLeftMotor.isBusy() && novaBot.frontRightMotor.isBusy() && novaBot.backLeftMotor.isBusy() && novaBot.backRightMotor.isBusy())) {
            // You can add additional checks or actions here if needed
            idle();
        }

        // Stop the motors
        novaBot.frontLeftMotor.setPower(0);
        novaBot.frontRightMotor.setPower(0);
        novaBot.backLeftMotor.setPower(0);
        novaBot.backRightMotor.setPower(0);

        // Set mode back to RUN_USING_ENCODER
        resetEncoders();
    }

}
