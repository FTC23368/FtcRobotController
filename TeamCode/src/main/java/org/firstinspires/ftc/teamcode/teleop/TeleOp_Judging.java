package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;


@TeleOp
public class TeleOp_Judging extends LinearOpMode {

    public DcMotor leftSliderMotor;
    public DcMotor rightSliderMotor;
    public Servo drone;

    public TouchSensor limitSwitch;
    public boolean isSliderMoving = false;
    public boolean slidersResetByLimitSwitch = false;

    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration

        DcMotor intakeMotor;
        intakeMotor = hardwareMap.dcMotor.get("intakeMotor");

        Servo pocket = hardwareMap.servo.get("pocket");

        limitSwitch = hardwareMap.touchSensor.get("limitSwitch");

        drone = hardwareMap.servo.get("drone");

        boolean previousButtonState = false;
        boolean motorToggle = false;

        boolean outtakePreviousButtonState = false;
        boolean outtakeMotorToggle = false;

        leftSliderMotor = hardwareMap.dcMotor.get("leftSliderMotor");
        rightSliderMotor = hardwareMap.dcMotor.get("rightSliderMotor");
        rightSliderMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        // These lines reset the encoder and do the job of the limit switch method
        leftSliderMotor.setPower(0);
        rightSliderMotor.setPower(0);

        leftSliderMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSliderMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftSliderMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightSliderMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);



        // Reverse the right side motors. THIS IS WRONG FOR OUR SETUP!!!!!
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.

        //frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        //backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        //frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        //backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        //resetSliderEncoderWithLimitSwitch();

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            // INTAKE MOVEMENT --------------------------------------------------------------------|
            boolean currentButtonState = gamepad2.x;

            if (currentButtonState && !previousButtonState) {
                motorToggle = !motorToggle;

                intakeMotor.setPower(motorToggle ? 1 : 0);
            }

            previousButtonState = currentButtonState;

            // OUTTAKE MOVEMENT -------------------------------------------------------------------|
            boolean outtakeButtonState = gamepad2.a;

            if (outtakeButtonState && !outtakePreviousButtonState) {
                outtakeMotorToggle = !outtakeMotorToggle;

                intakeMotor.setPower(outtakeMotorToggle ? -1 : 0);
            }

            outtakePreviousButtonState = outtakeButtonState;

            // LINEAR SLIDES MOVEMENT -------------------------------------------------------------|
            // If dpad left is pressed, sliders up to medium height
            if (gamepad2.dpad_left) {
                telemetry.addLine("dpad_left has been pressed");
                telemetry.update();
                pidMoveSliderToEncoderPosBrakeMode(1500, .5, 100);
            }
            telemetry.addLine("Current Position: " + leftSliderMotor.getCurrentPosition());
            telemetry.update();

            // If dpad up is pressed, sliders up to high height
                /*if (gamepad2.dpad_up) {
                    pidMoveSliderToEncoderPosBrakeMode(1800, .4, 100);
                }*/

            // If dpad down is pressed, sliders fully retract
            if (gamepad2.dpad_down) {
                pidMoveSliderToEncoderPosBrakeMode(0, .3, 100);

            }

            // POCKET MOVEMENT --------------------------------------------------------------------|
            if (gamepad2.y) {
                // 45 degrees - POCKET OPEN
                //pocket.setDirection(Servo.Direction.REVERSE);
                pocket.setPosition(0);
                sleep(700);
                // 0 degrees - POCKET CLOSED
                pocket.setPosition(0.25);
            }

            // DRONE MOVEMENT ---------------------------------------------------------------------|
            if (gamepad2.b) {
                // Drone launched
                drone.setPosition(0.08);
                sleep(3000);
                drone.setPosition(0.02);
            }
        }
    }


    // PID METHODS --------------------------------------------------------------------------------|

    public void pidMoveSliderToEncoderPosBrakeMode (int targetEncoderPos, double power, int slowDownEncoderPos) {
        isSliderMoving = true;

        getCurrentSliderEncoderPos();

        telemetry.addLine(targetEncoderPos + "," + leftSliderMotor.getCurrentPosition());
        telemetry.update();

        if (targetEncoderPos > leftSliderMotor.getCurrentPosition()) {
            pidSliderMoveUpBrakeMode(targetEncoderPos, power, slowDownEncoderPos);
        } else if (targetEncoderPos < leftSliderMotor.getCurrentPosition()) {
            pidSliderMoveDownBrakeMode(targetEncoderPos, power, slowDownEncoderPos);

        }

        isSliderMoving = false;
    }

    private void pidSliderMoveUpBrakeMode (int targetEncoderPos, double power, int slowDownEncoderPos) {
        this.leftSliderMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSliderMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        double encoderDiff;
        double kP = 0.01;

        getCurrentSliderEncoderPos();

        while ((getCurrentSliderEncoderPos() <= targetEncoderPos - slowDownEncoderPos) && opModeIsActive()) {
            encoderDiff = leftSliderMotor.getCurrentPosition() - rightSliderMotor.getCurrentPosition();

            if (encoderDiff >= 0){
                leftSliderMotor.setPower((power - kP * encoderDiff));
                rightSliderMotor.setPower(power + kP * encoderDiff);
            } else {
                rightSliderMotor.setPower((power + kP * encoderDiff));
                leftSliderMotor.setPower(power - kP * encoderDiff);
            }
            telemetry.addLine("Current Position: " + leftSliderMotor.getCurrentPosition());
            telemetry.update();
        }


        while (getCurrentSliderEncoderPos() <= targetEncoderPos && opModeIsActive()) {
            encoderDiff = leftSliderMotor.getCurrentPosition() - rightSliderMotor.getCurrentPosition();
            power = 0.3;

            if (encoderDiff >= 0) {
                leftSliderMotor.setPower(power - kP *encoderDiff);
                rightSliderMotor.setPower(power + kP * encoderDiff);
            } else {
                rightSliderMotor.setPower(power + kP *encoderDiff);
                leftSliderMotor.setPower(power-kP * encoderDiff);
            }
            telemetry.addLine("Current Position: " + leftSliderMotor.getCurrentPosition());
            telemetry.update();
        }


        holdSlider();
    }

    public int getCurrentSliderEncoderPos() {
        return (leftSliderMotor.getCurrentPosition() + rightSliderMotor.getCurrentPosition()) / 2;
    }

    public void holdSlider() {
        rightSliderMotor.setPower(0.05);
        leftSliderMotor.setPower(0.05);
    }

    private void pidSliderMoveDownBrakeMode (int targetEncoderPos, double power, int slowDownEncoderPos) {
        leftSliderMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSliderMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        double encoderDiff;
        double kP = 0.01;
        power = -power;

        while (getCurrentSliderEncoderPos() >= targetEncoderPos + slowDownEncoderPos && opModeIsActive()) {
            encoderDiff = leftSliderMotor.getCurrentPosition() - rightSliderMotor.getCurrentPosition();

            if (encoderDiff >= 0) {
                leftSliderMotor.setPower(power - kP * encoderDiff);
                rightSliderMotor.setPower(power + kP * encoderDiff);
            } else {
                rightSliderMotor.setPower(power + kP * encoderDiff);
                leftSliderMotor.setPower(power - kP * encoderDiff);
            }
            telemetry.addLine("Current Position: " + leftSliderMotor.getCurrentPosition());
            telemetry.update();
        }

        while (getCurrentSliderEncoderPos() >= targetEncoderPos && opModeIsActive() && !limitSwitch.isPressed()) {
            encoderDiff = leftSliderMotor.getCurrentPosition() - rightSliderMotor.getCurrentPosition();

            power = -0.2;

            if (encoderDiff >= 0) {
                leftSliderMotor.setPower(power - kP * encoderDiff);
                rightSliderMotor.setPower(power + kP * encoderDiff);
            } else {
                rightSliderMotor.setPower(power + kP * encoderDiff);
                leftSliderMotor.setPower(power - kP * encoderDiff);
            }
            telemetry.addLine("Current Position: " + leftSliderMotor.getCurrentPosition());
            telemetry.addData("", limitSwitch.isPressed());
            telemetry.update();
        }

        telemetry.addData("Status", "finished moving down");
        telemetry.update();
        holdSlider();
    }

    public void resetSliderEncoderWithLimitSwitch() {
        while (!limitSwitch.isPressed()) {
            leftSliderMotor.setPower(-0.1);
            rightSliderMotor.setPower(-0.1);
        }

        if (limitSwitch.isPressed()) {
            telemetry.addData("Status", "limit switch pressed");
            telemetry.update();
            slidersResetByLimitSwitch = true;
        }

        leftSliderMotor.setPower(0);
        rightSliderMotor.setPower(0);

        leftSliderMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSliderMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

}