package frc.team3324.robot.AutoGroups

import frc.team3324.robot.drivetrain.commands.JacisPathfinding
import edu.wpi.first.wpilibj.command.CommandGroup
import frc.team3324.robot.intake.commands.TimedOuttake

object RMiddle : CommandGroup() {
    init {
        this.addSequential(JacisPathfinding("RMiddle"))
        this.addSequential(TimedOuttake)
    }
}