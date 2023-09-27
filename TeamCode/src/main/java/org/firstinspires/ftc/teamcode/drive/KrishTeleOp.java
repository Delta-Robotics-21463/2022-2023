package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class KrishTeleOp extends OpMode {

    DcMotor frontLeft = null;
    DcMotor frontRight = null;
    DcMotor backLeft = null;
    DcMotor backRight = null;

    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "leftDrive");
        frontRight = hardwareMap.get(DcMotor.class, "rightDrive");
        backLeft = hardwareMap.get(DcMotor.class, "backLeftDrive" );
        backRight =  hardwareMap.get(DcMotor.class, "backRightDrive" );

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
    }

    public void loop() {
        if (gamepad1.left_stick_y != 0.0) {
            frontLeft.setPower(-gamepad1.left_stick_y);
            backLeft.setPower(-gamepad1.left_stick_y);
        }
        else
        {
            frontLeft.setPower(0.0);
            backLeft.setPower(0.0);
        }
        if (gamepad1.right_stick_y != 0.0) {
            frontRight.setPower(-gamepad1.right_stick_y);
            backRight.setPower(-gamepad1.right_stick_y);
        }
        else
        {
            frontRight.setPower(0.0);
            backRight.setPower(0.0);
        }
    }

}
