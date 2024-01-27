package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.NovaBot;

// BACKDROP SIDE

@Autonomous
public class Test_Encoders extends LinearOpMode {

    NovaBot novaBot;
    static final double MOTOR_TICK_COUNTS = 537.6;
    static final double WHEEL_DIAMETER_MM = 96;
    static final double WHEEL_CIRCUMFERENCE_MM = 301.44;

    static final double COUNTS_PER_INCH = MOTOR_TICK_COUNTS / (WHEEL_DIAMETER_MM * Math.PI);

    @Override
    public void runOpMode() throws InterruptedException {

        novaBot = new NovaBot(this);
        novaBot.initNovaBot();

        waitForStart();
        novaBot.runtime.reset();

        //novaBot.forwardUsingEncoders(24, 0.3);
        //novaBot.gyroTurnRight(-90);
        novaBot.strafeLeftUsingEncoders(18, 0.4);
        //novaBot.strafeRightUsingEncoders(10, 0.4);
    }

}