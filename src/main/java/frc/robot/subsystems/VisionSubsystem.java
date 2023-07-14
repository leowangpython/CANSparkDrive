package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

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
        skew = table.getDoubleTopic("skew").publish();
    }

    public void see(PhotonCamera camera) {
        var vision = camera.getLatestResult();

        if (vision.hasTargets()) {
            hasTargets.set(true);
            PhotonTrackedTarget target = vision.getBestTarget();
            yaw.set(target.getYaw());
            pitch.set(target.getPitch());
            area.set(target.getArea());
            skew.set(target.getSkew());
        } 
        else {
            hasTargets.set(false);
        }
    }
}