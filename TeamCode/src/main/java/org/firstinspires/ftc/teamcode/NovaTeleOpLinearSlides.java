// This is where we test individual mechanisms (intake, linear slides, pocket)

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp
public class NovaTeleOpLinearSlides extends LinearOpMode {

    public DcMotor leftSliderMotor;
    public DcMotor rightSliderMotor;

    public TouchSensor limitSwitch;

    public boolean isSliderMoving = false;
    public boolean slidersResetByLimitSwitch = false;

    @Override
    public void runOpMode() throws InterruptedException {

        // Telemetry
        telemetry.addLine("OpMode is running");
        telemetry.update();


        // Initializing motors
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

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            // If dpad left is pressed, sliders up to medium height
            if (gamepad2.dpad_left) {
                pidMoveSliderToEncoderPosBrakeMode(1500, .4, 100);
            }

            // If dpad up is pressed, sliders up to high height
            if (gamepad2.dpad_up) {
                pidMoveSliderToEncoderPosBrakeMode(1800, .4, 100);
            }

            // If dpad down is pressed, sliders fully retract
            if (gamepad2.dpad_down) {
                pidMoveSliderToEncoderPosBrakeMode(0, .3, 100);
            }
        }
    }


    // PID METHODS
    public void pidMoveSliderToEncoderPosBrakeMode (int targetEncoderPos, double power, int slowDownEncoderPos) {
        isSliderMoving = true;

        getCurrentSliderEncoderPos();

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
        }

        while (getCurrentSliderEncoderPos() >= targetEncoderPos && opModeIsActive()) {
            encoderDiff = leftSliderMotor.getCurrentPosition() - rightSliderMotor.getCurrentPosition();

            power = -0.1;

            if (encoderDiff >= 0) {
                leftSliderMotor.setPower(power - kP * encoderDiff);
                rightSliderMotor.setPower(power + kP * encoderDiff);
            } else {
                rightSliderMotor.setPower(power + kP * encoderDiff);
                leftSliderMotor.setPower(power - kP * encoderDiff);
            }
        }

        holdSlider();
    }

    public void resetSliderEncoderWithLimitSwitch() {
        while (!limitSwitch.isPressed()) {
            leftSliderMotor.setPower(-0.1);
            rightSliderMotor.setPower(-0.1);
        }

        if (limitSwitch.isPressed()) {
            slidersResetByLimitSwitch = true;
        }

        leftSliderMotor.setPower(0);
        rightSliderMotor.setPower(0);

        leftSliderMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSliderMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}