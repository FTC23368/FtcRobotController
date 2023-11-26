
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class Test extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private DcMotor leftRear = null;
    private DcMotor rightRear = null;

    @Override
    public void runOpMode() {
        // Initialize the hardware variables. The strings must match the names
        // assigned during the robot configuration step (using the FTC Robot Controller app on the phone).
        leftFront  = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        rightFront = hardwareMap.get(DcMotor.class, "frontRightMotor");
        leftRear  = hardwareMap.get(DcMotor.class, "backLeftMotor");
        rightRear = hardwareMap.get(DcMotor.class, "backRightMotor");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        leftRear.setDirection(DcMotor.Direction.REVERSE);
        rightRear.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // Set power for all four wheels to move the robot forward
        leftFront.setPower(0.3);
        rightFront.setPower(0.3);
        leftRear.setPower(0.3);
        rightRear.setPower(0.3);

        // Wait for 1 second
        sleep(1000);

        // Stop all motion by setting motor power to zero
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        // Complete the operation and wait for a new command
        telemetry.addData("Path", "Complete");
        telemetry.update();
    }
}


