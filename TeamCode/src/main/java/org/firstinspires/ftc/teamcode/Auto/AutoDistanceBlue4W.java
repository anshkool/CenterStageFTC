package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous (name="AutoDistanceBlue4W")
public class AutoDistanceBlue4W extends LinearOpMode {

    private DistanceSensor distanceSensor;
    private DcMotor FLeft;
    private DcMotor FRight;
    private DcMotor BRight;
    private DcMotor BLeft;


    @Override
    public void runOpMode() {
        float Prop;
        Prop = 0;

        BLeft = hardwareMap.get(DcMotor.class, "BLeft");
        BRight = hardwareMap.get(DcMotor.class, "BRight");
        FLeft = hardwareMap.get(DcMotor.class, "FLeft");
        FRight = hardwareMap.get(DcMotor.class, "FRight");
        distanceSensor = hardwareMap.get(DistanceSensor.class, "distanceSensor");


        FRight.setDirection(DcMotorSimple.Direction.REVERSE);
        FLeft.setDirection(DcMotorSimple.Direction.REVERSE);



        waitForStart();


        if (opModeIsActive()) {
            BLeft.setPower(0.25);
            BRight.setPower(0.25);
            FRight.setPower(0.25);
            FLeft.setPower(0.25);

            telemetry.addData("Distance (cm)", distanceSensor.getDistance(DistanceUnit.CM));
            telemetry.addData("Status", "RunningMotorsForwards");
            telemetry.update();

            sleep(2000);

            BLeft.setPower(0);
            BRight.setPower(0);
            FRight.setPower(0);
            FLeft.setPower(0);
            //TODO: Change around these values
            //TODO: FIGURE OUT BETTER WAY TO SOLVE THIS ISSUE WITH INACCURATE SENSOR

            if (distanceSensor.getDistance(DistanceUnit.CM) < 25) {
                sleep(1000);
                Prop = 1;

                telemetry.addData("Prop1", "Prop = 1");
                telemetry.update();

            } else {
                turnRight(0.25, 1500);
                if (distanceSensor.getDistance(DistanceUnit.CM) < 25) {
                    sleep(1000);
                    Prop = 2;

                    telemetry.addData("Prop2", "Prop = 2");
                    telemetry.update();


                } else {
                    turnLeft(0.25, 3000);
                    sleep(1000);
                    Prop = 3;

                }

            }

        }

        if (Prop == 1) {
            PropEqualsOne();
        } else if (Prop == 2) {
            PropEqualsTwo();
        } else if (Prop == 3) {
            PropEqualsThree();
        }
    }

    public void PropEqualsOne () {
        runMotorsTime(0.25, 350);
        runMotorsTime(-0.25, 3000);
        strafeMotorsLeft(0.25,5500);

        telemetry.addData("Distance (cm)", distanceSensor.getDistance(DistanceUnit.CM));
        telemetry.addData("Status", "Running");
        telemetry.update();

    }
    public void PropEqualsTwo () {
        turnRight(0.25,200);
        runMotorsTime(0.25, 600);
        runMotorsTime(-0.25, 2000);
        turnLeft(0.25,2000);
        runMotorsTime(-0.25,1800);
        strafeMotorsLeft(0.25, 8000);
    }
    public void PropEqualsThree () {
        runMotorsTime(0.25, 200);
        runMotorsTime(-0.25, 600);
        turnRight(0.25,2000);
        runMotorsTime(-0.25, 2500);
        strafeMotorsLeft(0.25, 7000);
    }



    public void brakeMotors () {
        BLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void runMotorsTime (double power, long motorTime){
        BLeft.setPower(power);
        BRight.setPower(power);
        FRight.setPower(power);
        FLeft.setPower(power);

        telemetry.addData("Distance (cm)", distanceSensor.getDistance(DistanceUnit.CM));
        telemetry.addData("Status", "Running");
        telemetry.update();

        sleep(motorTime);

        BLeft.setPower(0);
        BRight.setPower(0);
        FRight.setPower(0);
        FLeft.setPower(0);
    }

    public void strafeMotorsRight (double power, long motorTime){
        BLeft.setPower(-power);
        BRight.setPower(-power);
        FRight.setPower(power);
        FLeft.setPower(power);

        sleep(motorTime);

        BLeft.setPower(0);
        BRight.setPower(0);
        FRight.setPower(0);
        FLeft.setPower(0);
    }

    public void strafeMotorsLeft (double power, long motorTime){
        BLeft.setPower(power);
        BRight.setPower(power);
        FRight.setPower(-power);
        FLeft.setPower(-power);

        sleep(motorTime);

        BLeft.setPower(0);
        BRight.setPower(0);
        FRight.setPower(0);
        FLeft.setPower(0);
    }

    public void turnRight (double power, long motorTime){
        BLeft.setPower(power);
        BRight.setPower(-power);
        FRight.setPower(-power);
        FLeft.setPower(power);

        sleep(motorTime);

        BLeft.setPower(0);
        BRight.setPower(0);
        FRight.setPower(0);
        FLeft.setPower(0);

    }


    public void turnLeft (double power, long motorTime){
        BLeft.setPower(-power);
        BRight.setPower(power);
        FRight.setPower(power);
        FLeft.setPower(-power);

        sleep(motorTime);

        BLeft.setPower(0);
        BRight.setPower(0);
        FRight.setPower(0);
        FLeft.setPower(0);
    }

}
