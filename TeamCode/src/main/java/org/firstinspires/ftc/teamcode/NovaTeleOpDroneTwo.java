package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class NovaTeleOpDroneTwo extends LinearOpMode{

    public void runOpMode(){

        // declaring the servo
        Servo drone = hardwareMap.servo.get("drone");

        waitForStart();

        // if 'a' pressed set power to full
        while(opModeIsActive()){
            if (gamepad1.a){
                drone.setPosition(1);
            }
        }
    }

}
