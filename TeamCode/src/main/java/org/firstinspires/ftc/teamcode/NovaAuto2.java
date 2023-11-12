package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous
public class NovaAuto2 extends LinearOpMode {

    public DcMotor leftSliderMotor;
    public DcMotor rightSliderMotor;
    public DcMotor frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor;

    public TouchSensor limitSwitch;
    public DistanceSensor distanceSensor;
    public boolean isSliderMoving = false;
    public boolean slidersResetByLimitSwitch = false;
    public int propPosition = 0;
    //1 = left, 2 = middle, 3 = right

    double currentPos = 0;
    @Override
    public void runOpMode() throws InterruptedException {

         frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
         backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
         frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
         backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        DcMotor intakeMotor;
        intakeMotor = hardwareMap.dcMotor.get("intakeMotor");

        Servo pocket = hardwareMap.servo.get("pocket");

        distanceSensor = hardwareMap.get(DistanceSensor.class, "distanceSensor");

        leftSliderMotor = hardwareMap.dcMotor.get("leftSliderMotor");
        rightSliderMotor = hardwareMap.dcMotor.get("rightSliderMotor");
        rightSliderMotor.setDirection(DcMotorSimple.Direction.REVERSE);


        telemetry.addData("Status: ", "Robot Initialized");
        telemetry.update();

        waitForStart();

        if (distanceSensor.getDistance(DistanceUnit.INCH) < 32) {
            propPosition = 2;
            frontRightMotor.setPower(0.6);
            frontLeftMotor.setPower(0.6);
            backLeftMotor.setPower(0.6);
            backRightMotor.setPower(0.6);
            sleep(600);
            frontRightMotor.setPower(0);
            frontLeftMotor.setPower(0);
            backLeftMotor.setPower(0);
            backRightMotor.setPower(0);


            intakeMotor.setPower(-0.7);
            sleep(1000);

        } else {
            frontRightMotor.setPower(0.5);
            frontLeftMotor.setPower(0.5);
            backLeftMotor.setPower(0.5);
            backRightMotor.setPower(0.5);
            sleep(500);
            frontRightMotor.setPower(0);
            frontLeftMotor.setPower(0);
            backLeftMotor.setPower(0);
            backRightMotor.setPower(0);
            frontRightMotor.setPower(0.5);
            frontLeftMotor.setPower(-0.5);
            backLeftMotor.setPower(-0.5);
            backRightMotor.setPower(0.5);
            sleep(600);
            frontRightMotor.setPower(0);
            frontLeftMotor.setPower(0);
            backLeftMotor.setPower(0);
            backRightMotor.setPower(0);

            if (distanceSensor.getDistance(DistanceUnit.INCH) < 20) {
                propPosition = 1;

                frontRightMotor.setPower(0.5);
                frontLeftMotor.setPower(0.5);
                backLeftMotor.setPower(0.5);
                backRightMotor.setPower(0.5);
                sleep(500);
                frontRightMotor.setPower(0);
                frontLeftMotor.setPower(0);
                backLeftMotor.setPower(0);
                backRightMotor.setPower(0);
                intakeMotor.setPower(-1);
                sleep(1000);
            }
            else {
                propPosition = 3;
                frontRightMotor.setPower(-0.6);
                frontLeftMotor.setPower(0.5);
                backLeftMotor.setPower(0.5);
                backRightMotor.setPower(-0.6);
                sleep(1200);
                frontRightMotor.setPower(0);
                frontLeftMotor.setPower(0);
                backLeftMotor.setPower(0);
                backRightMotor.setPower(0);
                frontRightMotor.setPower(0.5);
                frontLeftMotor.setPower(0.5);
                backLeftMotor.setPower(0.5);
                backRightMotor.setPower(0.5);
                sleep(300);
                frontRightMotor.setPower(0);
                frontLeftMotor.setPower(0);
                backLeftMotor.setPower(0);
                backRightMotor.setPower(0);

                intakeMotor.setPower(-1);
                sleep(1000);
            }
        }
        telemetry.addData("Prop position: ", propPosition);
        telemetry.update();

        /*frontRightMotor.setPower(0.5);
        frontLeftMotor.setPower(0.5);
        backLeftMotor.setPower(0.5);
        backRightMotor.setPower(0.5);
        sleep(1000);
        frontRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);

        frontRightMotor.setPower(0.5);
        frontLeftMotor.setPower(-0.5);
        backLeftMotor.setPower(-0.5);
        backRightMotor.setPower(0.5);
        sleep(500);
        frontRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);*/


    }

    public void mecanumDriveForward(double targetPos, double power) {
        telemetry.addLine("Entered Method");
        telemetry.update();

        while (opModeIsActive() && (currentPos < targetPos)) {
            frontRightMotor.setPower(power);
            frontLeftMotor.setPower(power);
            backLeftMotor.setPower(power);
            backRightMotor.setPower(power);
            currentPos = (frontLeftMotor.getCurrentPosition() + frontRightMotor.getCurrentPosition() +
                    backLeftMotor.getCurrentPosition() + backRightMotor.getCurrentPosition())/4;
            telemetry.addData("CurrentPos: ", currentPos);
            telemetry.update();
        }
        frontRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);

        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        currentPos = 0;
        telemetry.addLine("method ended");
        telemetry.update();
    }

    public void mecanumTurnLeft(double targetPos, double power) {
        telemetry.addLine("Entered Method");
        telemetry.update();

        while (opModeIsActive() && (currentPos < targetPos)) {
            frontRightMotor.setPower(power);
            frontLeftMotor.setPower(-power);
            backLeftMotor.setPower(-power);
            backRightMotor.setPower(power);
            currentPos = (frontLeftMotor.getCurrentPosition() + frontRightMotor.getCurrentPosition() +
                    backLeftMotor.getCurrentPosition() + backRightMotor.getCurrentPosition())/4;
            telemetry.addData("CurrentPos: ", currentPos);
            telemetry.update();
        }
        frontRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);

        resetEncoders();
        currentPos = 0;
        telemetry.addLine("method ended");
    }

    public void resetEncoders() {
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

}
