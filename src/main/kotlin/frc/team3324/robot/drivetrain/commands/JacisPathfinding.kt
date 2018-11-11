package frc.team3324.robot.drivetrain.commands

import edu.wpi.first.wpilibj.Notifier
import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.team3324.robot.Constants
import frc.team3324.robot.drivetrain.DriveTrain

import jaci.pathfinder.Pathfinder
import jaci.pathfinder.Trajectory
import jaci.pathfinder.Waypoint
import jaci.pathfinder.followers.EncoderFollower
import jaci.pathfinder.modifiers.TankModifier

/**
 *
 */
class JacisPathfinding(path: String) : Command("Jacis") {

    private var trajectory: Trajectory? = null
    private var left: EncoderFollower
    private var right: EncoderFollower

    init {
        DriveTrain.clearSensors()
        val defaultPoints = arrayOf(Waypoint(0.0, 0.0,
                0.0), // Waypoint @ x=-0, y=-0, exit angle= 0 degrees
                Waypoint(3.048, 0.0, 0.0))
        val lMiddlePoints = arrayOf(Waypoint(0.0, 0.0, 0.0), Waypoint(3.556, 1.2192, 0.0))
        val rMiddlePoints = arrayOf(Waypoint(0.0, 0.0, 0.0), Waypoint(3.556, -1.9812, 0.0))
        val lLeftPoints = arrayOf(Waypoint(0.0, 0.0,
                0.0), // Waypoint @ x=-0, y=-0, exit angle= 0 degrees
                Waypoint(4.2672, 0.4064, Pathfinder.d2r(90.0)))
        val rRightpoints = arrayOf(Waypoint(0.0, 0.0, 0.0), Waypoint(4.2672, -1.8288, 90.0))
        val config = Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_LOW, 0.01,
                Constants.Drivetrain.LOW_GEAR_METERS_PER_SECOND * 0.7, 4.5, 9.0)
        if (path == "LLLeft") {
            trajectory = Pathfinder.generate(lLeftPoints, config)
        } else if (path == "RRRight") {
            trajectory = Pathfinder.generate(rRightpoints, config)
        } else if (path == "RMiddle") {
            trajectory = Pathfinder.generate(rMiddlePoints, config)
        } else if (path == "LMiddle") {
            trajectory = Pathfinder.generate(lMiddlePoints, config)
        } else {
            trajectory = Pathfinder.generate(defaultPoints, config)
        }
        val modifier = TankModifier(trajectory).modify(Constants.Drivetrain.DISTANCE_BETWEEN_WHEELS_METERS)
        left = EncoderFollower(modifier.leftTrajectory)
        right = EncoderFollower(modifier.rightTrajectory)
        left.configureEncoder(DriveTrain.lEncoder.raw, Constants.Drivetrain.TICKS_PER_REVOLUTION, Constants.Drivetrain.WHEEL_DIAMETER_METERS)
        right.configureEncoder(DriveTrain.rEncoder.raw, Constants.Drivetrain.TICKS_PER_REVOLUTION,
                Constants.Drivetrain.WHEEL_DIAMETER_METERS)
        left.configurePIDVA(0.3, 0.0, 0.0, 1 / Constants.Drivetrain.LOW_GEAR_METERS_PER_SECOND, 0.0)
        right.configurePIDVA(0.3, 0.0, 0.0, 1 / Constants.Drivetrain.LOW_GEAR_METERS_PER_SECOND, 0.0) // TODO: Tune these
        DriveTrain.setBrakeMode()
    }
    private var notifier = Notifier {
        val lOutput = left.calculate(DriveTrain.lEncoder.raw)
        val rOutput = right.calculate(DriveTrain.rEncoder.raw)
        val gyroHeading = -DriveTrain.gyro.yaw   // Assuming the gyro is giving a value in degrees
        val desiredHeading = Pathfinder.r2d(left.heading) // Should also be in degrees
        val angleDifference = Pathfinder.boundHalfDegrees(desiredHeading - gyroHeading)
        val turn = 1.2 * (-1.0 / 80.0) * angleDifference
        SmartDashboard.putNumber("lOutput", lOutput)
        SmartDashboard.putNumber("rOutput", rOutput)
        DriveTrain.mDrive.tankDrive(-(lOutput + turn), -(rOutput - turn), false)
    }
    // Called just before this Command runs the first time
    override fun initialize() {
        notifier.startPeriodic(0.01)
    }

    // Make this return true when this Command no longer needs to run execute()
    override fun isFinished(): Boolean {
        if (left.isFinished == true && right.isFinished == true) {
            notifier.stop()
            DriveTrain.setCoastMode()
            return left.isFinished
        } else {
            return false
        }
    }

    // Called once after isFinished returns true
    override fun end() {
        DriveTrain.mDrive.tankDrive(0.0, 0.0, false)
        DriveTrain.setCoastMode()
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    override fun interrupted() {
       DriveTrain.setCoastMode()
    }
}