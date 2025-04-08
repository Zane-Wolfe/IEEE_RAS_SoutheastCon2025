package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(1579, 790);
        Image img = null;
        try { img = ImageIO.read(new File("/home/nathan/Downloads/firefox/map.png")); }
        catch(IOException e) {}

//                           .forward(18)
  //              .turn(Math.toRadians(-90))
    //            .forward(18)
      //          .turn(Math.toRadians(90))
        //        .forward(5)
          //      .turn(Math.toRadians(180))
            //    .forward(58)
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(30, 30, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(12, 12)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(8.5, 7.9, 3.14))
                        .strafeRight(5)
                        .turn(Math.toRadians(-90))
                        .forward(10)
                        .turn(Math.toRadians(-90))
                        .forward(31)
                        .forward(-30)
                        .turn(Math.toRadians(90))
                        .forward(10)
                        .turn(Math.toRadians(90))
                        .build());


        meepMeep.setBackground(img)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}