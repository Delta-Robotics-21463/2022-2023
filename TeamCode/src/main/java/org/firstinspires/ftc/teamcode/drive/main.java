package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name="MAIN", group="Linear Opmode")

public class main extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor backRightDrive = null;
    private DcMotorSimple test=null;
    CRServo rightArm;
    CRServo leftArm;

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
        test=hardwareMap.get(DcMotorSimple.class,"leftLiftDown");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRightDrive" );
        backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //use speed estimated by encoder
        leftArm = hardwareMap.get(CRServo.class, "leftArm");
        rightArm = hardwareMap.get(CRServo.class, "rightArm" );

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
        while(opModeIsActive()){
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
            double drive = -gamepad1.left_stick_y;
            double strafe  = -gamepad1.left_stick_x;
            double turn = -gamepad1.right_stick_x;
            leftPower    = Range.clip(1*(drive + strafe + turn), -1.0, 1.0);
            leftBackPower    = Range.clip(1*(drive -strafe + turn), -1.0, 1.0);
            rightPower   = Range.clip(1*(drive - strafe - turn), -1.0, 1.0);
            rightBackPower   = Range.clip(1*(drive +strafe - turn), -1.0, 1.0);

            // Send calculated power to wheels
            if (gamepad1.left_bumper) {
                rightArm.setPower(1);
                leftArm.setPower(-1);
            } else if (gamepad1.right_bumper) {
                rightArm.setPower(-1);
                leftArm.setPower(1);
            } else {
                rightArm.setPower(0);
                leftArm.setPower(0);


            }
            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);
            backLeftDrive.setPower(leftBackPower);
            backRightDrive.setPower(rightBackPower);
            // test.setPower(1);


            // Show the elapsed game time and wheel power.

            telemetry.addData("Status", "Run Time: " + runtime.toString());
        }
    }
}