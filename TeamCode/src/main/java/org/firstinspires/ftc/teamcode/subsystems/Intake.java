package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    private final DcMotor intakeMotor;


    private static final double FORWARD_POWER = -0.45;
    private static final double REVERSE_POWER = 1.0;
    private static final double STOP_POWER = 0.0;

    public Intake(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        intakeMotor.setPower(STOP_POWER);
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void start() {
        intakeMotor.setPower(FORWARD_POWER);
    }
    public void reverse() {intakeMotor.setPower(REVERSE_POWER); }
    public void stop() {
        intakeMotor.setPower(STOP_POWER);
    }
    public void setPower(double power) { intakeMotor.setPower(power); }
}