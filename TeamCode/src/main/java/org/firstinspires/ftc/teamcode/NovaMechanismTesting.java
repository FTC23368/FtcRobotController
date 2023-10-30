package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.linearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.robotcore.external.Telemetry ;

@TeleOp
public class NovaMechanismTesting extends LinearOpMode {

    public DcMotor leftSliderMotor;
    public DcMotor rightSliderMotor;

    public TouchSensor limitSwitch;

    public boolean isSliderMoving = false;
    public boolean slidersResetByLimitSwitch = false;


    //PIDMethods pid = new PIDMethods();

    //Servo pocket = hardwareMap.servo.get("pocket");

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addLine("test string");
        telemetry.update();
        leftSliderMotor = hardwareMap.dcMotor.get("leftSliderMotor");
        rightSliderMotor = hardwareMap.dcMotor.get("rightSliderMotor");

        leftSliderMotor.setPower(0);
        rightSliderMotor.setPower(0);

        //leftSliderMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //rightSliderMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // All of the following is drafted code for functionality of individual mechanisms

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            // The code below is for pocket functionality (opening and closing the pocket)
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

            // The code below is for linear slide functionality
            if (gamepad2.x) {
                // telemetry
                telemetry.addLine("left button pressed");
                //telemetry.update();

                // linear slides up
                pidMoveSliderToEncoderPosBrakeMode(50, .3, 10);
            }
        }
    }


    public void pidMoveSliderToEncoderPosBrakeMode(int targetEncoderPos, double power, int slowDownEncoderPos) {
        isSliderMoving = true;

        getCurrentSliderEncoderPos();

        telemetry.addLine("Inside Move Slider to Encoder Brake");
        //telemetry.update();

        if (targetEncoderPos > leftSliderMotor.getCurrentPosition()) {
            telemetry.addLine("targetEncoder (" + targetEncoderPos +") > getCurrent(" +
                    leftSliderMotor.getCurrentPosition() + ")");
            telemetry.update();

            pidSliderMoveUpBrakeMode(targetEncoderPos, power, slowDownEncoderPos);
        }
        else if (targetEncoderPos < leftSliderMotor.getCurrentPosition()) {
            telemetry.addLine("targetEncoder < getCurrent\"");
            telemetry.update();

            //pidSliderMoveDownBrakeMode(targetEncoderPos, power, slowDownEncoderPos);
        }

        isSliderMoving = false;
    }

    private void pidSliderMoveUpBrakeMode(int targetEncoderPos, double power, int slowDownEncoderPos){
        this.leftSliderMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSliderMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        double encoderDiff;
        double kP = 0.01;

        getCurrentSliderEncoderPos();
        telemetry.addLine("Inside the Move Up Brake Method");
        telemetry.addLine("About to move to " + targetEncoderPos + "; with Power: " + power
                + "; Will Slow at: " + slowDownEncoderPos);
        //telemetry.update();

        int loopCounter = 0;
        while((getCurrentSliderEncoderPos() <= targetEncoderPos - slowDownEncoderPos) && opModeIsActive() && loopCounter < 2) {
            encoderDiff = leftSliderMotor.getCurrentPosition() - rightSliderMotor.getCurrentPosition();
            telemetry.addLine("Difference in Motors is: " + encoderDiff);
            //telemetry.update();

            if (encoderDiff >= 0){
                telemetry.addLine("EncoderDiff = " + encoderDiff);
                telemetry.addLine("Left Power: " + (power-kP*encoderDiff) +
                        "; Right Power: " + (power + kP*encoderDiff));
                telemetry.update();

                leftSliderMotor.setPower((power - kP * encoderDiff));
                rightSliderMotor.setPower(power + kP * encoderDiff);
            }
            else{
                telemetry.addLine("EncoderDiff = " + encoderDiff);
                telemetry.addLine("Left Power: " + (power + kP*encoderDiff) +
                        "; Right Power: " + (power - kP*encoderDiff));
                telemetry.update();

                rightSliderMotor.setPower((power + kP * encoderDiff));
                leftSliderMotor.setPower(power - kP * encoderDiff);
            }
            loopCounter++;
        }

        /*
        while (getCurrentSliderEncoderPos() <= targetEncoderPos && opModeIsActive()){
            encoderDiff = leftSliderMotor.getCurrentPosition() - rightSliderMotor.getCurrentPosition();
            power = 0.3;

            if (encoderDiff >= 0){
                leftSliderMotor.setPower(power - kP *encoderDiff);
                rightSliderMotor.setPower(power + kP * encoderDiff);
            }else{
                rightSliderMotor.setPower(power + kP *encoderDiff);
                leftSliderMotor.setPower(power-kP * encoderDiff);
            }
        }
         */

        holdSlider();
    }

    public int getCurrentSliderEncoderPos() {
        telemetry.addLine("Inside the getCurrentSliderEncoderPos");
        telemetry.addLine("Current Position: " +
                leftSliderMotor.getCurrentPosition() + rightSliderMotor.getCurrentPosition() / 2);
        telemetry.update();

        leftSliderMotor.get
        return (leftSliderMotor.getCurrentPosition() + rightSliderMotor.getCurrentPosition()) / 2;
    }

    public void holdSlider(){
        telemetry.addLine("Inside the hold Slider");
        //telemetry.update();


        rightSliderMotor.setPower(0.05);
        leftSliderMotor.setPower(0.05);
    }

    private void pidSliderMoveDownBrakeMode(int targetEncoderPos, double power, int slowDownEncoderPos) {
        leftSliderMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSliderMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        double encoderDiff;
        double kP = 0.01;
        power = -power;

        telemetry.addLine("Inside the Move Down Brake Method");
        telemetry.update();

        while (getCurrentSliderEncoderPos() >= targetEncoderPos + slowDownEncoderPos && linearOpMode.opModeIsActive()) {
            encoderDiff = leftSliderMotor.getCurrentPosition() - rightSliderMotor.getCurrentPosition();

            if(encoderDiff >= 0) {
                leftSliderMotor.setPower(power - kP * encoderDiff);
                rightSliderMotor.setPower(power + kP * encoderDiff);
            } else {
                rightSliderMotor.setPower(power + kP * encoderDiff);
                leftSliderMotor.setPower(power - kP * encoderDiff);
            }
        }

        while (getCurrentSliderEncoderPos() >= targetEncoderPos && linearOpMode.opModeIsActive()) {
            encoderDiff = leftSliderMotor.getCurrentPosition() - rightSliderMotor.getCurrentPosition();

            power = -0.01;

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

    // THE FOLLOWING IS PID CODE

    public void resetSliderEncoderWithLimitSwitch() {
        while (!limitSwitch.isPressed()) {
            leftSliderMotor.setPower(-0.1);
            rightSliderMotor.setPower(-0.1);
        }

        if(limitSwitch.isPressed()) {
            slidersResetByLimitSwitch = true;
        }

        leftSliderMotor.setPower(0);
        rightSliderMotor.setPower(0);

        leftSliderMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSliderMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
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

            if (gamepad2.x) {
                // 0 degrees - POCKET CLOSED
                pocket.setDirection(Servo.Direction.REVERSE);
                pocket.setPosition(0);
            }

 */