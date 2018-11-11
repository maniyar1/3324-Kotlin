package frc.team3324.robot.arm.commands

import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.command.Command
import frc.team3324.robot.OI
import frc.team3324.robot.Robot
import frc.team3324.robot.arm.Arm

/**
 * Moves the set arm from forward to backward with the left joystick.
 */
object ControlArm : Command() {
    init {
        requires(Arm)
    }

    override fun execute() {
        var leftY = OI.SECONDARY_CONTROLLER.getY(GenericHID.Hand.kLeft)
        Arm.armMotors.set(leftY)
    }

    // Make this return true when this Command no longer needs to run execute()
    override fun isFinished(): Boolean {
        return false
    }
}