package frc.team3324.robot.drivetrain.commands

import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.command.Command
import frc.team3324.robot.util.OI
import frc.team3324.robot.drivetrain.DriveTrain

object Drive : Command("Drive") {
    init {
        requires(DriveTrain)
    }

    override fun execute() {
        var leftY = OI.PRIMARY_CONTROLLER.getY(GenericHID.Hand.kLeft)
        var rightX = OI.PRIMARY_CONTROLLER.getX(GenericHID.Hand.kRight)
        if (OI.PRIMARY_BUMPER_RIGHT.get()) {
            DriveTrain.mDrive.curvatureDrive(leftY, rightX, true)
        } else if (leftY < 0.2) {
            DriveTrain.mDrive.curvatureDrive(leftY, rightX, true)
        } else {
            DriveTrain.mDrive.curvatureDrive(leftY, rightX, false)
        }
    }

    override fun isFinished(): Boolean {
        return false
    }
}