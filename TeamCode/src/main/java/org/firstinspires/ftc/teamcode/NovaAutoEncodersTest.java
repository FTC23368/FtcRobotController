package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.List;

// BACKDROP SIDE

@Autonomous
public class NovaAutoEncodersTest extends LinearOpMode {

    NovaBot novaBot;
    static final double MOTOR_TICK_COUNTS = 537.6;
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
        novaBot.frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        novaBot.backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        novaBot.frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        novaBot.backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        novaBot.frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        novaBot.backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        novaBot.frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        novaBot.frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        novaBot.backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        novaBot.backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Wait for the start button to be pressed
        waitForStart();

        novaBot.frontLeftMotor.setTargetPosition(1000);
        novaBot.backLeftMotor.setTargetPosition(1000);
        novaBot.frontRightMotor.setTargetPosition(1000);
        novaBot.backRightMotor.setTargetPosition(1000);

        novaBot.frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        novaBot.frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        novaBot.backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        novaBot.backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        novaBot.frontLeftMotor.setPower(0.3);
        novaBot.frontRightMotor.setPower(0.3);
        novaBot.backLeftMotor.setPower(0.3);
        novaBot.backRightMotor.setPower(0.3);

        if(novaBot.frontLeftMotor.getCurrentPosition() == novaBot.frontLeftMotor.getTargetPosition()) {
            novaBot.frontLeftMotor.setPower(0);
            novaBot.frontRightMotor.setPower(0);
            novaBot.backLeftMotor.setPower(0);
            novaBot.backRightMotor.setPower(0);
        }

        // Stop the op mode
        stop();

    }

}
