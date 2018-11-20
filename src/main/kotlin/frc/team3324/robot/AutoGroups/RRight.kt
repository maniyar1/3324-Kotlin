package frc.team3324.robot.AutoGroups

import edu.wpi.first.wpilibj.command.CommandGroup
import frc.team3324.robot.drivetrain.commands.JacisPathfinding
import frc.team3324.robot.intake.commands.TimedOuttake

object RRight : CommandGroup() {
    init {
        this.addSequential(JacisPathfinding("RRight"))
        this.addSequential(TimedOuttake)
    }
}
