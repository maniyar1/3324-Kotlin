package frc.team3324.robot.intake.commands

import edu.wpi.first.wpilibj.command.Command
import frc.team3324.robot.intake.Intake

object Outtake : Command("Outtake") {
    init {
        requires(Intake);
    }

    override fun execute() = Intake.setSpeed(-1.0)

    override fun isFinished(): Boolean {
        return false
    }
}