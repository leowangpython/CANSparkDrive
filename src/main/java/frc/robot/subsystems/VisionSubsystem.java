package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.networktables.BooleanPublisher;
import edu.wpi.first.networktables.DoublePublisher;

public class VisionSubsystem extends SubsystemBase {

    private final NetworkTableInstance inst;
    private final NetworkTable table;

    BooleanPublisher hasTargets;
    DoublePublisher yaw;
    DoublePublisher pitch;
    DoublePublisher area;
    DoublePublisher skew;

    public VisionSubsystem() {
        inst = NetworkTableInstance.getDefault();
        table = inst.getTable("datatable");

        hasTargets = table.getBooleanTopic("hasTargets").publish();
        yaw = table.getDoubleTopic("yaw").publish();
        pitch = table.getDoubleTopic("pitch").publish();
        area = table.getDoubleTopic("area").publish();
    }

    public void see(PhotonCamera camera) {
        var vision = camera.getLatestResult();

        String field = AprilTagFields.k2023ChargedUp.m_resourceFile;
        AprilTagFieldLayout aprilTagFieldLayout = AprilTagFieldLayout.loadFromResource(field);

        if (vision.hasTargets()) {
            hasTargets.set(true);
            PhotonTrackedTarget target = vision.getBestTarget();
            
            double distance = 0;
            Rotation3d rotation = new Rotation3d();
            Transform3d cameraToRobot = new Transform3d(new Translation3d(distance, rotation), rotation);
            
            Pose3d robotPose = PhotonUtils.estimateFieldToRobotAprilTag(target.getBestCameraToTarget(), aprilTagFieldLayout.getTagPose(target.getFiducialId()).get(), cameraToRobot);


        } 
        else {
            hasTargets.set(false);
        }

        
    }
        
}