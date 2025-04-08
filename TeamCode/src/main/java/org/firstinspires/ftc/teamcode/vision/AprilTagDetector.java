package org.firstinspires.ftc.teamcode.vision;

import android.util.Size;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

public class AprilTagDetector {
    private final AprilTagProcessor tagProcessor;
    private final VisionPortal visionPortal;

    public AprilTagDetector(HardwareMap hardwareMap, String webcamName) {
        tagProcessor = new AprilTagProcessor.Builder()
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .build();

        visionPortal = new VisionPortal.Builder()
                .addProcessor(tagProcessor)
                .setCamera(hardwareMap.get(WebcamName.class, webcamName))
                .setCameraResolution(new Size(640, 480))
                .build();
    }

    public Integer getLatestTagId() {
        List<AprilTagDetection> detections = tagProcessor.getDetections();
        return detections.isEmpty() ? null : detections.get(0).id;
    }

    public AprilTagDetection getLatestDetection() {
        List<AprilTagDetection> detections = tagProcessor.getDetections();
        return detections.isEmpty() ? null : detections.get(0);
    }

    public void stop() {
        if (visionPortal != null) {
            visionPortal.close();
        }
    }
}
