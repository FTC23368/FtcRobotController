package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class NovaTeleOpDrone extends LinearOpMode{

    public void runOpMode(){

        Servo drone = hardwareMap.servo.get("drone");
        // declaring the servo

        waitForStart();

        // if 'a' pressed set power to full
        while(opModeIsActive()){
            if (gamepad1.a){
                // Drone launched
                drone.setPosition(0.08);
                sleep(3000);
                drone.setPosition(0.02);
            }
        }
    }

}
