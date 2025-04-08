package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class BeaconArm {
    private final Servo beaconServo;

    private static final double REST_POS = 1.0;
    private static final double PRIME_POS = 0.8;
    private static final double PLACE_POS = 0.5;

    public BeaconArm(HardwareMap hardwareMap) {
        beaconServo = hardwareMap.get(Servo.class, "beaconArm");
    }

    public void rest() { beaconServo.setPosition(REST_POS); }
    public void prime() { beaconServo.setPosition(PRIME_POS); }
    public void place() { beaconServo.setPosition(PLACE_POS); }

    public void setPosition(double pos) { beaconServo.setPosition(pos); }

}