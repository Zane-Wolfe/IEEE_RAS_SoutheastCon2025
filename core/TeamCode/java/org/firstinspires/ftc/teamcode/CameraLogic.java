package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.*;

import java.util.ArrayList;
import java.util.List;

public class CameraLogic {

    private final Main main;
    private Mat currentFrame = new Mat(); // Pre-allocated Mat for reuse
    private final Object frameLock = new Object(); // Lock for thread safety

    public CameraLogic(Main main) {
        this.main = main;
        cameraStart();
    }

    private OpenCvCamera usbCamera;

    public void cameraStart() {
        int cameraMonitorViewId = main.hardwareMap.appContext.getResources().getIdentifier(
                "cameraMonitorViewId", "id", main.hardwareMap.appContext.getPackageName());
        usbCamera = OpenCvCameraFactory.getInstance().createWebcam(
                main.hardwareMap.get(WebcamName.class, "Webcamm"), cameraMonitorViewId);

        usbCamera.setPipeline(new ExamplePipeline());

        usbCamera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                usbCamera.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {
                main.telemetry.addData("Camera Error", errorCode);
            }
        });
    }

    public double getDriveDirection() {
        synchronized (frameLock) {
            if (currentFrame.empty()) {
                main.telemetry.addLine("No frame available yet.");
                return 0; // Indicate no movement is needed
            }

            // Convert frame to HSV
            Mat hsvImg = new Mat();
            Imgproc.cvtColor(currentFrame, hsvImg, Imgproc.COLOR_RGB2HSV);

            // Define HSV range for purple
            Scalar lowerPurple = new Scalar(130, 50, 50); // Adjust as needed
            Scalar upperPurple = new Scalar(160, 255, 255); // Adjust as needed

            // Threshold the image to isolate purple
            Mat mask = new Mat();
            Core.inRange(hsvImg, lowerPurple, upperPurple, mask);

            // Find contours
            List<MatOfPoint> contours = new ArrayList<>();
            Mat hierarchy = new Mat();
            Imgproc.findContours(mask, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

            // Release intermediate resources
            hsvImg.release();
            mask.release();
            hierarchy.release();

            if (contours.isEmpty()) {
                main.telemetry.addLine("No purple object detected.");
                return 0; // No movement needed
            }

            // Find the largest contour
            double maxArea = 0;
            Rect boundingRect = null;
            for (MatOfPoint contour : contours) {
                double area = Imgproc.contourArea(contour);
                if (area > maxArea) {
                    maxArea = area;
                    boundingRect = Imgproc.boundingRect(contour);
                }
                contour.release();
            }

            if (boundingRect == null) {
                main.telemetry.addLine("Failed to locate purple object.");
                return 0;
            }

            // Calculate the center of the screen and object
            int frameCenter = currentFrame.cols() / 2;
            int objectCenter = boundingRect.x + (boundingRect.width / 2);

            // Calculate normalized direction intensity
            int frameWidth = currentFrame.cols();
            double normalizedDirection = (double) (objectCenter - frameCenter) / (frameWidth / 2);

            // Clamp the value between -1 and 1
            normalizedDirection = Math.max(-1.0, Math.min(1.0, normalizedDirection));

//            main.telemetry.addData("Drive Direction Intensity", normalizedDirection);
//            main.telemetry.addData("Object Center", objectCenter);
//            main.telemetry.addData("Frame Center", frameCenter);
//            main.telemetry.update();

            return normalizedDirection;
        }
    }


    // Example pipeline that updates the current frame
    private class ExamplePipeline extends OpenCvPipeline {
        @Override
        public Mat processFrame(Mat input) {
            synchronized (frameLock) {
                input.copyTo(currentFrame); // Safely copy input to currentFrame
            }
            return input; // Return the frame unmodified for display
        }
    }
}
