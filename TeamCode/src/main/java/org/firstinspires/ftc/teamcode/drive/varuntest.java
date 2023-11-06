package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class Assignment1 extends OpMode {
    DcMotor LeftForward = null;
    DcMotor RightForward = null;
    DcMotor LeftBackward = null;
    DcMotor RightBackward = null;
    @Override
    public void init(){
        LeftForward = hardwareMap.get(DcMotor.class, "LeftBackward");
        RightForward = hardwareMap.get(DcMotor.class, "RightForward");
        LeftBackward = hardwareMap.get(DcMotor.class, "LeftBackward");
        RightBackward = hardwareMap.get(DcMotor.class, "RightBackward");

        LeftForward.setDirection(DcMotorSimple.Direction.REVERSE);
        LeftBackward.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop(){
        if(gamepad1.left_stick_y > 0){
            LeftForward.setPower(1.0);
            RightForward.setPower(1.0);
            LeftBackward.setPower(1.0);
            RightBackward.setPower(1.0);
        }
        if(gamepad1.left_stick_y < 0){
            LeftForward.setPower(-1.0);
            RightForward.setPower(-1.0);
            LeftBackward.setPower(-1.0);
            RightBackward.setPower(-1.0);
        } else {
            LeftForward.setPower(1.0);
            LeftForward.setPower(1.0);
            LeftForward.setPower(1.0);
            LeftForward.setPower(1.0);

        }
    }
}
