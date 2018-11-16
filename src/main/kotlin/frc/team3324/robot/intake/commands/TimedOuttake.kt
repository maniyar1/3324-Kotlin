package frc.team3324.robot.intake.commands

import edu.wpi.first.wpilibj.command.Command
import frc.team3324.robot.intake.Intake

object TimedOuttake : Command("OuttakeAuto") {
    init {
        requires(Intake)
        setTimeout(2.0)
    }

    public override fun execute() {
        Intake.setSpeed(1.0);
    }

    public override fun isFinished(): Boolean {
        return isTimedOut
    }
}