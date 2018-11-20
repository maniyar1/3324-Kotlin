package frc.team3324.robot.drivetrain.commands

import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.team3324.robot.drivetrain.DriveTrain
import frc.team3324.robot.util.Constants.Vision

object CenterCube : Command("CenterCube") { // This program centers a cube using SmartDash/NT information
    val integral = 0
    val goal = 0
    var finished = false
    override fun initialize() {
        requires(DriveTrain)
        DriveTrain.setBrakeMode()
    }

    override fun execute() {
        val position = SmartDashboard.getNumber("VisionX", 160.0) // Yoink vision info
        val mappedPosition = position - (Vision.RESOLUTION_WIDTH/2) // This maps value from -160 to 160 (Error)
        val error = goal - mappedPosition
        val integral = integral + error
        val speed = (error * Vision.kP) + (integral * Vision.kI)
        DriveTrain.mDrive.arcadeDrive(0.0, speed)
        finished = (error < 5)
    }

    override fun isFinished(): Boolean {
        return finished
    }

    override fun end() {
        DriveTrain.setCoastMode()
    }
}