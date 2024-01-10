package org.firstinspires.ftc.teamcode.Auto;

// Import necessary FTC libraries
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

public class ForwardOdoTrial extends LinearOpMode {

    // Define your DcMotor objects

    private DcMotor BLeft;
    private DcMotor BRight;
    private DcMotor FLeft;
    private DcMotor FRight;

    // Constants for wheel diameter and encoder counts per inch
    private static final double WHEEL_DIAMETER_INCHES = 3.77953; // TODO: Adjust this based on your robot's wheel size
    private static final double COUNTS_PER_INCH = 1440 / (Math.PI * WHEEL_DIAMETER_INCHES);

    // Variables for target distance and speed
    private static final double TARGET_DISTANCE_INCHES = 10.0;
    private static final double DRIVE_SPEED = 0.25; // Adjust this based on your robot's speed capability

    // Timer for tracking elapsed time
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        // Initialize motors
        BLeft = hardwareMap.dcMotor.get("BLeft");
        BRight = hardwareMap.dcMotor.get("BRight");
        FLeft  = hardwareMap.dcMotor.get("FLeft");
        FRight = hardwareMap.dcMotor.get("FRight");

        // Set motor directions (adjust based on your robot's configuration)
        FRight.setDirection(DcMotorSimple.Direction.REVERSE);
        BLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        // Reset encoders and set mode to RUN_USING_ENCODER
        resetEncoders();
        runUsingEncoders();

        // Wait for the start button to be pressed
        waitForStart();

        // Move forward for the specified distance
        moveForward(TARGET_DISTANCE_INCHES);

        // Stop the robot
        stopRobot();
    }

    private void resetEncoders() {
        FLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    private void runUsingEncoders() {
        FLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void moveForward(double distanceInches) {
        int targetPosition = (int) (distanceInches * COUNTS_PER_INCH);

        // Set target position for both motors
        FLeft.setTargetPosition(targetPosition);
        FRight.setTargetPosition(targetPosition);
        BLeft.setTargetPosition(targetPosition);
        BRight.setTargetPosition(targetPosition);

        // Set mode to RUN_TO_POSITION
        BRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set power for both motors
        BRight.setPower(DRIVE_SPEED);
        FRight.setPower(DRIVE_SPEED);
        FLeft.setPower(DRIVE_SPEED);
        BLeft.setPower(DRIVE_SPEED);

        // Wait until both motors reach the target position
        while (opModeIsActive() && (BRight.isBusy() || BLeft.isBusy() || FRight.isBusy() || FLeft.isBusy())) {
            // You can add additional logic here if needed
            telemetry.addData("Status", "Moving to target position");
            telemetry.update();
        }

        // Stop both motors
        stopRobot();

        // Set mode back to RUN_USING_ENCODER
        runUsingEncoders();
    }

    private void stopRobot() {
        BRight.setPower(0);
        BLeft.setPower(0);
        FRight.setPower(0);
        FLeft.setPower(0);
    }
}
