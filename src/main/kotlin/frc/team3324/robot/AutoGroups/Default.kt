package frc.team3324.robot.AutoGroups

import frc.team3324.robot.drivetrain.commands.JacisPathfinding
import edu.wpi.first.wpilibj.command.CommandGroup

object Default : CommandGroup() {
    init {
        this.addSequential(JacisPathfinding("Default"))
    }
}