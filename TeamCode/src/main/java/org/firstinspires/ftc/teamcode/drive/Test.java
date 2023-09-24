package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class Test extends OpMode {
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
        if(gamepad1.left_stick_y != 0.0){
            LeftForward.setPower(-gamepad1.left_stick_y);
            LeftBackward.setPower(-gamepad1.left_stick_x);

        }else{
            LeftForward.setPower(0.0);
            LeftBackward.setPower(0.0);
        }

        if(gamepad1.right_stick_y != 0.0){
            RightForward.setPower(-gamepad1.right_stick_y);
            RightBackward.setPower(-gamepad1.right_stick_y);
        }else{
            RightForward.setPower(0.0);
            RightBackward.setPower(0.0);
        }
    }
}
