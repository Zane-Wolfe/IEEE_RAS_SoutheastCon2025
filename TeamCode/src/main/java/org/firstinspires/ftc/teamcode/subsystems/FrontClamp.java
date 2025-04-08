package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class FrontClamp {
    private final Servo leftClaw;
    private final Servo rightClaw;

    private static final double ONE_POS = 0.65;
    private static final double TWO_POS = 0.4;
    private static final double THREE_POS = 0.2;
    private static final double CLOSED_POS = 0.0;

    public FrontClamp(HardwareMap hardwareMap) {
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");
    }

    public void intital() {
        setPosition(THREE_POS);
    }

    public void cycle(LinearOpMode opMode) {
        setPosition(ONE_POS);
        opMode.sleep(500);

        setPosition(TWO_POS);
        opMode.sleep(500);

        setPosition(THREE_POS);
        opMode.sleep(500);

        close();
        opMode.sleep(500);
    }

    public void close() {
        setPosition(CLOSED_POS);
    }

    public void setPosition(double pos) {
        pos = Math.max(0.0, Math.min(pos, 1.0));
        leftClaw.setPosition(pos);
        rightClaw.setPosition(1.0 - pos); // Mirror movement
    }
}
