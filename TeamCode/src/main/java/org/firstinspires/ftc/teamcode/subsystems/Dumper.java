package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Dumper {
    private final Servo leftDump;
    private final Servo rightDump;

    // Logical positions (0.0 to 1.0 scale)
    private static final double LEFT_REST = 0.0;
    private static final double LEFT_PRIME = 0.33;
    private static final double LEFT_DUMP = 1.0;

    private static final double RIGHT_REST = 0.0;
    private static final double RIGHT_PRIME = 0.33;
    private static final double RIGHT_DUMP = 1.0;

    // Physical hardware range (tuned for your linear servo setup)
    private static final double PHYSICAL_MIN = 0.0;
    private static final double PHYSICAL_MAX = 0.3;

    public Dumper(HardwareMap hardwareMap) {
        leftDump = hardwareMap.get(Servo.class, "leftDump");
        rightDump = hardwareMap.get(Servo.class, "rightDump");

        reset(); // Initialize to rest position
    }

    // Manual scale function (logical 0.0–1.0 → physical 0.0–0.3)
    private double scale(double logical) {
        logical = Math.max(0.0, Math.min(logical, 1.0)); // clamp
        return PHYSICAL_MIN + (PHYSICAL_MAX - PHYSICAL_MIN) * logical;
    }

    // Left servo controls
    public void leftUp() {
        leftDump.setPosition(scale(LEFT_DUMP));
    }

    public void leftDown() {
        leftDump.setPosition(scale(LEFT_REST));
    }

    public void setLeft(double logicalPos) {
        leftDump.setPosition(scale(logicalPos));
    }

    // Right servo controls
    public void rightUp() {
        rightDump.setPosition(scale(RIGHT_DUMP));
    }

    public void rightDown() {
        rightDump.setPosition(scale(RIGHT_REST));
    }

    public void setRight(double logicalPos) {
        rightDump.setPosition(scale(logicalPos));
    }

    // Combined actions
    public void dump() {
        leftUp();
        rightUp();
    }

    public void reset() {
        leftDown();
        rightDown();
    }
}
