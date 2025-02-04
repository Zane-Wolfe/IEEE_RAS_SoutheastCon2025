package org.firstinspires.ftc.teamcode;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@Autonomous(name = "main")
public class Main extends LinearOpMode {

    private DriverLogic driverLogic;
//    private CameraLogic cameraLogic;

    @Override
    public void runOpMode() {
//        cameraLogic = new CameraLogic(this);
        driverLogic = setupDriverLogic();

        AprilTagProcessor tagProcessor = new AprilTagProcessor.Builder()
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .build();

        VisionPortal visionPortal = new VisionPortal.Builder()
                .addProcessor(tagProcessor)
                .setCamera(hardwareMap.get(WebcamName.class, "Webcamm"))
                .setCameraResolution(new Size(640, 480))
                .build();

        waitForStart(); // Wait for the game to start

        while (opModeIsActive()) {
            if(!tagProcessor.getDetections().isEmpty()){
                AprilTagDetection tag = tagProcessor.getDetections().get(0);
                telemetry.addLine("April Tag ID: "+ tag.id);
            }

            // Process the frame in each loop iteration
//            double driveDirection = cameraLogic.getDriveDirection();
            double driveDirection = 0;

            // 312
            // 117

            // below 0, go left
            // above 0, go right
            float middleThreshold = 0.4f;
            float power = 0.3f;
            if(driveDirection > middleThreshold){
                driverLogic.moveRight(power);
            }else if(driveDirection < -middleThreshold){
                driverLogic.moveLeft(power);
            }else{
//                driverLogic.moveForward(power);
            }

//            telemetry.addData("Drive Direction Intensity", driveDirection);
//            driverLogic.dumpLog();
            telemetry.update();


            // Add a short sleep to prevent busy looping
            sleep(50); // Adjust the delay as needed for performance
        }
    }

    private DriverLogic setupDriverLogic() {
        DcMotor FLMotor = hardwareMap.get(DcMotor.class, "frontdriver");
        FLMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        DcMotor FRMotor = hardwareMap.get(DcMotor.class, "frontpassenger");
        FRMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        DcMotor BLMotor = hardwareMap.get(DcMotor.class, "reardriver");
        BLMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        DcMotor BRMotor = hardwareMap.get(DcMotor.class, "rearpassenger");
        BRMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        return new DriverLogic(this, FLMotor, FRMotor, BLMotor, BRMotor);
    }

}
