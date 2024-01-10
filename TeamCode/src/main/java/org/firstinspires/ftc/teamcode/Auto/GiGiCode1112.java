package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "GiGiCode1112")
public class GiGiCode1112 extends LinearOpMode {

    private DcMotor BLeft;
    private DcMotor FRight;
    private DcMotor Linear;
    private DcMotor Arm;
    private Servo LeftClaw;
    private Servo RightClaw;
    private Servo shoot;
    private DcMotor BRight;
    private DcMotor FLeft;

    /**
     * This function is executed when this OpMode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        double DrivePower;
        float armPower;
        float LinearPower;

        BLeft = hardwareMap.get(DcMotor.class, "BLeft");
        FRight = hardwareMap.get(DcMotor.class, "FRight");
        Linear = hardwareMap.get(DcMotor.class, "Linear");
        Arm = hardwareMap.get(DcMotor.class, "Arm");
        LeftClaw = hardwareMap.get(Servo.class, "LeftClaw");
        RightClaw = hardwareMap.get(Servo.class, "RightClaw");
        shoot = hardwareMap.get(Servo.class, "shoot");
        BRight = hardwareMap.get(DcMotor.class, "BRight");
        FLeft = hardwareMap.get(DcMotor.class, "FLeft");

        // Put initialization blocks here.
        waitForStart();
        BLeft.setDirection(DcMotor.Direction.REVERSE);
        FRight.setDirection(DcMotor.Direction.REVERSE);
        Linear.setDirection(DcMotor.Direction.REVERSE);
        DrivePower = 0.75;

        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                // Put loop blocks here.
                armPower = gamepad2.left_stick_y;
                Arm.setPower(armPower);
                LinearPower = gamepad2.right_stick_y;
                Linear.setPower(LinearPower);
                if (gamepad2.dpad_right) {
                    // open
                    LeftClaw.setPosition(0.4);
                    RightClaw.setPosition(0.9);
                } else if (gamepad2.dpad_left) {
                    // closed
                    LeftClaw.setPosition(0.6);
                    RightClaw.setPosition(0.55);
                }
                if (gamepad1.a) {
                    shoot.setPosition(0);
                } else if (gamepad1.b) {
                    shoot.setPosition(1);
                } else if (gamepad1.x) {
                    shoot.setPosition(0.5);
                }

                if (gamepad1.left_stick_y != 0) {
                    BRight.setPower(gamepad1.left_stick_y * DrivePower);
                    BLeft.setPower(gamepad1.left_stick_y * DrivePower);
                    FRight.setPower(gamepad1.left_stick_y * DrivePower);
                    FLeft.setPower(gamepad1.left_stick_y * DrivePower);
                } else if (gamepad1.left_stick_x != 0) {
                    BRight.setPower(-(gamepad1.left_stick_x * DrivePower));
                    BLeft.setPower(gamepad1.left_stick_x * DrivePower);
                    FRight.setPower(gamepad1.left_stick_x * DrivePower);
                    FLeft.setPower(-(gamepad1.left_stick_x * DrivePower));
                } else if (gamepad1.right_stick_x != 0) {
                    BRight.setPower(gamepad1.right_stick_x * DrivePower);
                    BLeft.setPower(-(gamepad1.right_stick_x * DrivePower));
                    FRight.setPower(gamepad1.right_stick_x * DrivePower);
                    FLeft.setPower(-(gamepad1.right_stick_x * DrivePower));
                } else {
                    BRight.setPower(0);
                    BLeft.setPower(0);
                    FRight.setPower(0);
                    FLeft.setPower(0);
                }
                telemetry.update();
            }
        }
    }
}
