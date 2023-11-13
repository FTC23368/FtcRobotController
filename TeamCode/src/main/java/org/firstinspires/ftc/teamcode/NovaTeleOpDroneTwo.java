package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp
public class NovaTeleOpDroneTwo extends LinearOpMode{

    public void runOpMode(){

        // declaring the servo
        CRServo drone = hardwareMap.crservo.get("drone");

        waitForStart();

        // if 'a' pressed set power to full
        while(opModeIsActive()){
            if (gamepad1.a){
                drone.setPower(1);
            }
        }
    }

}
