package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.math.BigDecimal;


@TeleOp(name="MAIN", group="Linear Opmode")

public class main extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor backRightDrive = null;
    private DcMotorSimple lift=null;
    private BNO055IMU imu;
    private boolean toggle = false;
    Servo arm;
    // Define class members

    @Override
    public void runOpMode() {
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftDrive  = hardwareMap.get(DcMotor.class, "leftDrive");
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //use speed estimated by encoder
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //use speed estimated by encoder
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeftDrive" );
        backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //use speed estimated by encoder
        lift=hardwareMap.get(DcMotorSimple.class,"lift");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRightDrive" );
        backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //use speed estimated by encoder
        arm=hardwareMap.get(Servo.class, "arm");
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);

        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // Setup a variable for each drive wheel to save power level for telemetry
        while(opModeIsActive()) {
            double leftPower;
            double rightPower;

            double leftBackPower;
            double rightBackPower;
            // }
            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.

            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            // leftPower  = -gamepad1.left_stick_y ;
            // rightPower = -gamepad1.right_stick_y ;
            Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            imu.getPosition();
            float heading = angles.firstAngle;
            heading= BigDecimal.valueOf(heading)
                    .setScale(0, BigDecimal.ROUND_HALF_DOWN)
                    .floatValue();
            double drive=-gamepad1.left_stick_y;
            double strafe=gamepad1.left_stick_x;
            if (heading>60.0&&heading<150&&toggle) {
                strafe =-gamepad1.left_stick_y;
                drive=-gamepad1.left_stick_x;
            } else if (heading<-60&&heading>-150&&toggle) {
                strafe=gamepad1.left_stick_y;
                drive=gamepad1.left_stick_x;
            } else if ((heading>150||heading<-150)&&toggle) {
                drive=gamepad1.left_stick_y;
                strafe=-gamepad1.left_stick_x;
            }
            double turn = gamepad1.right_stick_x;
            leftPower = Range.clip(1 * (drive + strafe + turn), -1.0, 1.0);
            leftBackPower = Range.clip(1 * (drive - strafe + turn), -1.0, 1.0);
            rightPower = Range.clip(1 * (drive - strafe - turn), -1.0, 1.0);
            rightBackPower = Range.clip(1 * (drive + strafe - turn), -1.0, 1.0);
            // Send calculated power to wheels
            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);
            backLeftDrive.setPower(leftBackPower);
            backRightDrive.setPower(rightBackPower);
            lift.setPower(gamepad1.right_trigger-gamepad1.left_trigger);
            if (gamepad1.b||gamepad2.b) {
                toggle=false;
            } else if (gamepad1.a|| gamepad2.a) {
                toggle=true;
            }
            if (gamepad1.right_bumper) {
                arm.setPosition(0.5);
            } else if (gamepad1.left_bumper) {
                arm.setPosition(0);
            }


            // Show the elapsed game time and wheel power.

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.addData(Float.toString(heading), "imu");
            telemetry.addData("toggle",String.valueOf(toggle));
            telemetry.update();             // Update telemetry with any new data
        }
    }
}