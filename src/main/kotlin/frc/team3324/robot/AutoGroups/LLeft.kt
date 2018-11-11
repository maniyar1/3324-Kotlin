package frc.team3324.robot.AutoGroups

import edu.wpi.first.wpilibj.command.CommandGroup
import frc.team3324.robot.drivetrain.commands.JacisPathfinding
import frc.team3324.robot.intake.commands.TimedOuttake

/**
 * Set robot in foremost left of the left driverstation.
 */
object LLeft : CommandGroup() {
    init {
        this.addSequential(JacisPathfinding("LLLeft")) //was 192
        this.addSequential(TimedOuttake)
    }
}