package frc.team3324.robot.AutoGroups

import edu.wpi.first.wpilibj.command.CommandGroup
import frc.team3324.robot.drivetrain.commands.JacisPathfinding

object Default : CommandGroup() {
    init {
        this.addSequential(JacisPathfinding("Default"))
    }
}