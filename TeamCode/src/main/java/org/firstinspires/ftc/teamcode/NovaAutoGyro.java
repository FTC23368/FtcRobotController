package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.List;

// BACKDROP SIDE

@Autonomous
public class NovaAutoGyro extends LinearOpMode {

    NovaBot novaBot;

    @Override
    public void runOpMode() throws InterruptedException {

        novaBot = new NovaBot(this);
        novaBot.initNovaBot();

        waitForStart();
        novaBot.runtime.reset();

        novaBot.gyroTurnLeft(45);
        sleep(1500);
        novaBot.gyroTurnLeft(45);
        sleep(1500);
        novaBot.gyroTurnLeft(45);

        novaBot.linearOpMode.telemetry.addData("count", novaBot.count);
        novaBot.linearOpMode.telemetry.update();
        sleep(3000);
    }

}
