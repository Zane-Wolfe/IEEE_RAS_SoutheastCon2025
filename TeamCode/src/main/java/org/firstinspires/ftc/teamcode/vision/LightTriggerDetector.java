package org.firstinspires.ftc.teamcode.vision;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class LightTriggerDetector extends OpenCvPipeline {

    private static final Rect REGION = new Rect(280, 0, 80, 80);
    private static final double THRESHOLD = 180.0;
    private volatile boolean lightDetected = false;

    private final Mat gray = new Mat();
    private final Mat roi = new Mat();

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, gray, Imgproc.COLOR_RGB2GRAY);

        Mat region = gray.submat(REGION);
        Scalar avg = Core.mean(region);

        lightDetected = avg.val[0] >= THRESHOLD;

        Imgproc.rectangle(input, REGION, lightDetected ? new Scalar(0, 255, 0) : new Scalar(0, 0, 255), 2);

        return input;
    }

    public boolean isLightOn() {
        return lightDetected;
    }
}
