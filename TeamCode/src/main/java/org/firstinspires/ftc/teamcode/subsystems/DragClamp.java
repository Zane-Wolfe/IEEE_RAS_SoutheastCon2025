package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class DragClamp {
    private final Servo leftClamp;
    private final Servo rightClamp;

    private static final double REST_POS = 0.0;
    private static final double CLAMPED_POS = 0.63;
    private static final double OPEN_POS = 0.8;

    public DragClamp(HardwareMap hardwareMap) {
        leftClamp = hardwareMap.get(Servo.class, "leftBox");
        rightClamp = hardwareMap.get(Servo.class, "rightBox");
    }

    public void clamp() {
        leftClamp.setPosition(CLAMPED_POS);
        rightClamp.setPosition(1.0 - CLAMPED_POS);
        sleepSafely(300);
    }

    public void release() {
        leftClamp.setPosition(OPEN_POS);
        sleepSafely(300);
        rightClamp.setPosition(1.0 - OPEN_POS);
        sleepSafely(300);
    }

    public void rest() {
        leftClamp.setPosition(REST_POS);
        sleepSafely(300);
        rightClamp.setPosition(1.0 - REST_POS);
        sleepSafely(300);
    }

    public void setPosition(double pos) {
        pos = Math.max(0.0, Math.min(pos, 1.0));
        leftClamp.setPosition(pos);
        sleepSafely(300);
        rightClamp.setPosition(1.0 - pos);
        sleepSafely(300);
    }

    private void sleepSafely(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
