package org.firstinspires.ftc.teamcode.hardware;

public class HardwareConstants {

    // Drive Motor Constants
    public static final double DRIVE_SPEED = 0.6;
    public static final double TURN_SPEED = 0.5;

    // Intake
    public static final double INTAKE_POWER = 1.0;

    // Servo Positions
    public static final double BEACON_PRIME_POS = 0.3;
    public static final double BEACON_PLACE_POS = 0.7;

    public static final double BOX_GRABBER_CLOSED = 0.1;
    public static final double BOX_GRABBER_OPEN = 0.6;

    public static final double BOX_DUMPER_DOWN = 0.0;
    public static final double BOX_DUMPER_UP = 1.0;

    public static final double CLAW_CLOSED = 0.2;
    public static final double CLAW_OPEN = 0.8;

    // Encoder Constants
    public static final double TICKS_PER_REV = 537.7; // for GoBilda Yellow Jacket
    public static final double WHEEL_DIAMETER_INCHES = 4.0;
    public static final double GEAR_RATIO = 1.0;
    public static final double TICKS_PER_INCH = (TICKS_PER_REV * GEAR_RATIO) / (Math.PI * WHEEL_DIAMETER_INCHES);

    // Timing
    public static final int TIMEOUT_MS = 3000;

    // Vision / Camera
    public static final String WEBCAM_NAME = "WEBCAM";
}
