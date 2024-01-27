// This is where we test individual mechanisms (intake, linear slides, pocket)

package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp
public class Test_TeleOpMechanisms extends LinearOpMode {

    public DcMotor leftSliderMotor;
    public DcMotor rightSliderMotor;

    public DcMotor intakeMotor;

    public TouchSensor limitSwitch;

    public boolean isSliderMoving = false;
    public boolean slidersResetByLimitSwitch = false;

    //Servo pocket = hardwareMap.servo.get("pocket");

    @Override
    public void runOpMode() throws InterruptedException {

        // Telemetry
            telemetry.addLine("OpMode is running");
            telemetry.update();


        // Initializing motors
            leftSliderMotor = hardwareMap.dcMotor.get("leftSliderMotor");
            rightSliderMotor = hardwareMap.dcMotor.get("rightSliderMotor");
            rightSliderMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            intakeMotor = hardwareMap.dcMotor.get("intakeMotor");


        // TEMPORARY
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

            // The code below is for pocket functionality (opening and closing the pocket)
            // ONLY UNCOMMENT POCKET CODE WHEN IT'S CONNECTED TO CONTROL HUB OTHERWISE CODE WON'T RUN
            /*
            if (gamepad2.y) {
                // 45 degrees - POCKET OPEN
                pocket.setDirection(Servo.Direction.REVERSE);
                pocket.setPosition(0.15);
                sleep(500);
                // 0 degrees - POCKET CLOSED
                pocket.setPosition(0);
            }
            */


            // If X is pressed, sliders up to medium height
                if (gamepad2.x) {
                    pidMoveSliderToEncoderPosBrakeMode(1500, .4, 100);
                }


            // If Y is pressed, sliders up to high height
                if (gamepad2.y) {
                    pidMoveSliderToEncoderPosBrakeMode(1800, .4, 100);
                }


            // If A is pressed, sliders retract fully
                if (gamepad2.a) {
                    pidMoveSliderToEncoderPosBrakeMode(0, .3, 100);
                }

            // If B is pressed, run intake
                if (gamepad2.b) {
                    intakeMotor.setPower(1);
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


/*
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

            if (gamepad2.x) {
                // 0 degrees - POCKET CLOSED
                pocket.setDirection(Servo.Direction.REVERSE);
                pocket.setPosition(0);
            }

 */