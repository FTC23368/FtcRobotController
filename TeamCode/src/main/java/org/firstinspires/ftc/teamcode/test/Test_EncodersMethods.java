package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.NovaBot;

// BACKDROP SIDE

@Autonomous
public class Test_EncodersMethods extends LinearOpMode {

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

        novaBot.frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        novaBot.backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        novaBot.frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        novaBot.backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        novaBot.frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        novaBot.backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        novaBot.frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        novaBot.backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        double fLTargetPos = novaBot.frontLeftMotor.getCurrentPosition() - 3000;
        // -3009 = 69 in
        // 1 inch = 43.6 ticks

        // strafing
        // -3011 = 62 in
        // 1 inch = 48.6 ticks
        while (novaBot.linearOpMode.opModeIsActive() && novaBot.frontLeftMotor.getCurrentPosition() > fLTargetPos) {
            novaBot.frontLeftMotor.setPower(0.5);
            novaBot.backLeftMotor.setPower(-0.5);
            novaBot.frontRightMotor.setPower(-0.5);
            novaBot.backRightMotor.setPower(0.5);
        }

        novaBot.frontLeftMotor.setPower(0);
        novaBot.backLeftMotor.setPower(0);
        novaBot.frontRightMotor.setPower(0);
        novaBot.backRightMotor.setPower(0);

        novaBot.linearOpMode.telemetry.addData("Encoder pos front left", novaBot.frontLeftMotor.getCurrentPosition());
        novaBot.linearOpMode.telemetry.addData("Encoder pos back left", novaBot.backLeftMotor.getCurrentPosition());
        novaBot.linearOpMode.telemetry.addData("Encoder pos front right", novaBot.frontRightMotor.getCurrentPosition());
        novaBot.linearOpMode.telemetry.addData("Encoder pos back right", novaBot.backRightMotor.getCurrentPosition());

        novaBot.linearOpMode.telemetry.update();
        sleep(3000);

        //stop();

    }

}