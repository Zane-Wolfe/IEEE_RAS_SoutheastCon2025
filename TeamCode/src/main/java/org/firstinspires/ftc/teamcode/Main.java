package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "main")
public class Main extends LinearOpMode {

    //   private final DriverLogic driverLogic;
    private CameraLogic cameraLogic;

    @Override
    public void runOpMode() {
        cameraLogic = new CameraLogic(this);
//        driverLogic = setupDriverLogic();

        waitForStart(); // Wait for the game to start

        while (opModeIsActive()) {
            // Process the frame in each loop iteration
            double driveDirection = cameraLogic.getDriveDirection();

            telemetry.addData("Drive Direction Intensity", driveDirection);
            telemetry.update();


            // Add a short sleep to prevent busy looping
            sleep(50); // Adjust the delay as needed for performance
        }
    }

    private DriverLogic setupDriverLogic() {
        DcMotor FLMotor = hardwareMap.get(DcMotor.class, "FrontDriver");
        DcMotor FRMotor = hardwareMap.get(DcMotor.class, "FrontPassenger");
        DcMotor BLMotor = hardwareMap.get(DcMotor.class, "BackDriver");
        DcMotor BRMotor = hardwareMap.get(DcMotor.class, "BackPassenger");
        return new DriverLogic(this, FLMotor, FRMotor, BLMotor, BRMotor);
    }




}
