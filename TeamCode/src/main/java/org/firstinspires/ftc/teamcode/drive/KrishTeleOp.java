package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class KrishTeleOp extends OpMode {

    DcMotor frontLeft = null;
    DcMotor frontRight = null;
    DcMotor backLeft = null;
    DcMotor backRight = null;

    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "leftDrive");
        frontRight = hardwareMap.get(DcMotor.class, "rightDrive");
        backLeft = hardwareMap.get(DcMotor.class, "backLeftDrive");
        backRight = hardwareMap.get(DcMotor.class, "backRightDrive");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);

    }
    public void loop() {
        double drive=gamepad1.left_stick_y;
        double turn = gamepad1.left_stick_x;
        double strafe=gamepad1.right_stick_x;
        double leftPower = Range.clip((drive + strafe + turn), -1.0, 1.0);
        double leftBackPower = Range.clip((drive - strafe + turn), -1.0, 1.0);
        double rightPower = Range.clip((drive - strafe - turn), -1.0, 1.0);
        double rightBackPower = Range.clip((drive + strafe - turn), -1.0, 1.0);
        frontLeft.setPower(leftPower);
        frontRight.setPower(rightPower);
        backLeft.setPower(leftBackPower);
        backRight.setPower(rightBackPower);
    }

}
