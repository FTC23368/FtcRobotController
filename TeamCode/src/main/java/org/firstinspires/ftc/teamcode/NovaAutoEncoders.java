package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.List;

// BACKDROP SIDE

@Autonomous
public class NovaAutoEncoders extends LinearOpMode {

    NovaBot novaBot;
    static final double MOTOR_TICK_COUNTS = 537.7;
    static final double WHEEL_DIAMETER_MM = 96;
    static final double WHEEL_CIRCUMFERENCE_MM = 301.44;

    @Override
    public void runOpMode() throws InterruptedException {

        novaBot = new NovaBot(this);
        novaBot.initNovaBot();

        waitForStart();
        novaBot.runtime.reset();

        // reset encoders
        novaBot.frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        novaBot.backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        novaBot.frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        novaBot.backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // calculating # of turns to make robot move 18 in.
        // to calculate, divide number of inches you want to travel by the circumference
        double rotationsNeeded = 18/WHEEL_CIRCUMFERENCE_MM;
        int encoderDrivingTarget = (int)(rotationsNeeded*MOTOR_TICK_COUNTS);

        novaBot.frontLeftMotor.setTargetPosition(encoderDrivingTarget);
        novaBot.frontRightMotor.setTargetPosition(encoderDrivingTarget);
        novaBot.backLeftMotor.setTargetPosition(encoderDrivingTarget);
        novaBot.backRightMotor.setTargetPosition(encoderDrivingTarget);

        novaBot.frontLeftMotor.setPower(0.5);
        novaBot.frontRightMotor.setPower(0.5);
        novaBot.backLeftMotor.setPower(0.5);
        novaBot.backLeftMotor.setPower(0.5);

        novaBot.frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        novaBot.frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        novaBot.backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        novaBot.backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(novaBot.frontLeftMotor.isBusy()){
            telemetry.addData("Path", "Driving");
            telemetry.update();
        }
        novaBot.frontLeftMotor.setPower(0);
        novaBot.frontRightMotor.setPower(0);
        novaBot.backLeftMotor.setPower(0);
        novaBot.backLeftMotor.setPower(0);

    }

}
