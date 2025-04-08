package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class DriveTrain {

    public DcMotor frontLeft, frontRight, backLeft, backRight;
    private LinearOpMode opMode;

    private static final double WHEEL_DIAMETER_INCHES = 3.78;
    private static final double TICKS_PER_REV = 537.6;
    private static final double TICKS_PER_INCH = TICKS_PER_REV / (Math.PI * WHEEL_DIAMETER_INCHES);

    // Tuned for accurate rotation (calibrated from working 180)
    private static final double TURN_CIRCUMFERENCE_INCHES = 53.0;
    private static final double HALF_TURN_INCHES = TURN_CIRCUMFERENCE_INCHES / 2;
    private static final double QUARTER_TURN_INCHES = HALF_TURN_INCHES / 2;

    public DriveTrain(HardwareMap hardwareMap, LinearOpMode opMode) {
        this.opMode = opMode;

        frontLeft = hardwareMap.get(DcMotor.class, "leftFront");
        frontRight = hardwareMap.get(DcMotor.class, "rightFront");
        backLeft = hardwareMap.get(DcMotor.class, "leftBack");
        backRight = hardwareMap.get(DcMotor.class, "rightBack");

        DcMotor[] motors = {frontLeft, frontRight, backLeft, backRight};
        for (DcMotor motor : motors) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
    }

    public void stop() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    private void moveMotors(int fl, int fr, int bl, int br, double power) {
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + fl);
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + fr);
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + bl);
        backRight.setTargetPosition(backRight.getCurrentPosition() + br);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);

        while ((frontLeft.isBusy() || frontRight.isBusy() ||
                backLeft.isBusy() || backRight.isBusy()) && opMode.opModeIsActive()) {
            // Optional: Add telemetry if needed
        }

        stop();

        for (DcMotor motor : new DcMotor[]{frontLeft, frontRight, backLeft, backRight}) {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    // --------------------------------
    // Drive Functions (Forward/Back)
    // --------------------------------
    public void driveForwardInches(double inches, double power) {
        int ticks = (int) (inches * TICKS_PER_INCH);
        moveMotors(-ticks, -ticks, -ticks, -ticks, power);
    }

    public void driveRightInches(double inches, double power) {
        int ticks = (int) (inches * TICKS_PER_INCH);
    }

    public void driveBackwardInches(double inches, double power) {
        int ticks = (int) (inches * TICKS_PER_INCH);
        moveMotors(ticks, ticks, ticks, ticks, power);
    }

    // --------------------------------
    // Strafe Functions (Left/Right)
    // --------------------------------
    public void turnLeftInches(double inches, double power) {
        int ticks = (int) (inches * TICKS_PER_INCH);
        moveMotors(ticks, -ticks, -ticks, (int)(ticks * 1.33), power); // Boost backRight
    }

    public void turnRightInches(double inches, double power) {
        int ticks = (int) (inches * TICKS_PER_INCH);
        moveMotors(-ticks, ticks, ticks, (int)(-ticks * 1.33), power); // Boost backRight
    }

    // --------------------------------
    // Rotation Functions
    // --------------------------------
    public void rotateLeftInPlace(double inches, double power) {
        int ticks = (int) (inches * TICKS_PER_INCH);
        moveMotors(-ticks, ticks, -ticks, ticks, power);
    }

    public void rotateRightInPlace(double inches, double power) {
        int ticks = (int) (inches * TICKS_PER_INCH);
        moveMotors(ticks, -ticks, ticks, -ticks, power);
    }

    public void rotateLeft90(double power) {
        rotateLeftInPlace(QUARTER_TURN_INCHES, power);
    }

    public void rotateRight90(double power) {
        rotateRightInPlace(QUARTER_TURN_INCHES, power);
    }

    public void rotate180(double power) {
        rotateRightInPlace(HALF_TURN_INCHES, power);
    }
}
