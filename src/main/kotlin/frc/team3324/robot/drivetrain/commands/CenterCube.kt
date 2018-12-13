package frc.team3324.robot.drivetrain.commands

import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.team3324.robot.drivetrain.DriveTrain
import frc.team3324.robot.util.Constants.Vision

object CenterCube : Command("CenterCube") { // This program centers a cube using SmartDash/NT information
    init {
        requires(DriveTrain)
    }
    var integral = 0
    val goal = 0
    var finished = false
    override fun initialize() {
        DriveTrain.setBrakeMode()
    }

    override fun execute() {
        val position = SmartDashboard.getNumber("visionX", 180.0) // Yoink vision info
        val mappedPosition = position - (Vision.RESOLUTION_WIDTH/2) // This maps value from -180 to 180 (Error)
        val error = goal.toDouble() - mappedPosition.toDouble()
	SmartDashboard.putNumber("Vision Error", error.toDouble())
        integral = integral + error.toInt()
        val speed = (error.toDouble() * Vision.kP) + (integral.toDouble() * Vision.kI)
	SmartDashboard.putNumber("Vision Speed", speed.toDouble())
        DriveTrain.mDrive.arcadeDrive(0.0, speed, false)
        val Finished = (error < 10)
    }

    override fun isFinished(): Boolean {
        return finished
    }

    override fun end() {
        DriveTrain.setCoastMode()
    }
}
