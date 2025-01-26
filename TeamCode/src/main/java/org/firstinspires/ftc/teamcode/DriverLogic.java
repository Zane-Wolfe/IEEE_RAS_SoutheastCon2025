package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class DriverLogic {

    private Main main;

    /**
     * FLMotor = Front Left Motor
     * FRMotor = Front Right Motor
     * BLMotor = Back Left Motor
     * BRMotor = Back Right Motor
     */

    private DcMotor FLMotor;
    private DcMotor FRMotor;
    private DcMotor BLMotor;
    private DcMotor BRMotor;

    public DriverLogic(Main main, DcMotor FLMotor, DcMotor FRMotor, DcMotor BLMotor, DcMotor BRMotor){
        this.main = main;
        this.FLMotor = FLMotor;
        this.FRMotor = FRMotor;
        this.BLMotor = BLMotor;
        this.BRMotor = BRMotor;

        FLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FLMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FRMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BLMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BRMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void moveLeft(float power){
        power = Math.abs(power);
        FLMotor.setPower(-power);
//        FRMotor.setPower(power*((double) 312 /117));
        FRMotor.setPower(power);
        BLMotor.setPower(power*((double) 312 /117));
        BRMotor.setPower(-power);
    }

    public void moveRight(float power){
        power = Math.abs(power);
        FLMotor.setPower(power);
//        FRMotor.setPower(-power*((double) 312 /117));
        FRMotor.setPower(-power);
        BLMotor.setPower(-power*((double) 312 /117));
        BRMotor.setPower(power);
    }

    public void moveForward(float power){
        power = Math.abs(power);
        FLMotor.setPower(power);
//        FRMotor.setPower(power*((double) 312 /117));
        FRMotor.setPower(power);
        BLMotor.setPower(power*((double) 312 /117));
        BRMotor.setPower(power);
    }

    public void moveBackward(float power){
        power = Math.abs(power);
        FLMotor.setPower(-power);
//        FRMotor.setPower(-power*((double) 312 /117));
        FRMotor.setPower(-power);
        BLMotor.setPower(-power*((double) 312 /117));
        BRMotor.setPower(-power);
    }

    public void stopMoving(){
        FLMotor.setPower(0);
        FRMotor.setPower(0);
        BLMotor.setPower(0);
        BRMotor.setPower(0);
    }

    public void dumpLog(){
        main.telemetry.addLine("FLMotor:"+String.valueOf(FLMotor.getCurrentPosition()));
        main.telemetry.addLine("FRMotor:"+String.valueOf(FRMotor.getCurrentPosition()));
        main.telemetry.addLine("BLMotor:"+String.valueOf(BLMotor.getCurrentPosition()));
        main.telemetry.addLine("BRMotor:"+String.valueOf(BRMotor.getCurrentPosition()));
    }
}
