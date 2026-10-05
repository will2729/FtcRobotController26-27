package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Mecanum No Encoders")
public class motors extends LinearOpMode {

    DcMotor frontLeft;
    //yellow
    DcMotor frontRight;
    //blue
    DcMotor backLeft;
    //red
    DcMotor backRight;
    //green

    @Override
    public void runOpMode() {

// Motor names from the Robot Configuration
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

// Reverse right side motors
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);

// Turn encoders off
    //    frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      //  frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while (opModeIsActive()) {

// Joystick inputs
            double y = -gamepad1.left_stick_y; // Forward/back
            double x = gamepad1.left_stick_x; // Strafe
            double rx = gamepad1.right_stick_x; // Turn

// Mecanum calculations
            double fl = y + x + rx;
            double fr = y - x - rx;
            double bl = y - x + rx;
            double br = y + x - rx;

// Normalize powers
            double max = Math.max(Math.abs(fl),
                    Math.max(Math.abs(fr),
                            Math.max(Math.abs(bl), Math.abs(br))));

            if (max > 1.0) {
                fl /= max;
                fr /= max;
                bl /= max;
                br /= max;
            }

// Set motor powers
            frontLeft.setPower(-fl);
            frontRight.setPower(-fr);
            backLeft.setPower(bl);
            backRight.setPower(br);
        }
    }
}
