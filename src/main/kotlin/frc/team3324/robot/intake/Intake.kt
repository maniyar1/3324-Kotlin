package frc.team3324.robot.intake

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX
import edu.wpi.first.wpilibj.SpeedControllerGroup
import edu.wpi.first.wpilibj.command.Subsystem
import frc.team3324.robot.util.Constants

object Intake : Subsystem() {
    val leftIntakeMotor = WPI_VictorSPX(Constants.Intake.LEFT_INTAKE_MOTOR_PORT)
    val rightIntakeMotor = WPI_VictorSPX(Constants.Intake.RIGHT_INTAKE_MOTOR_PORT)
    val intakeMotors = SpeedControllerGroup(leftIntakeMotor, rightIntakeMotor);

    init {
        leftIntakeMotor.inverted = true;
    }

    fun setSpeed(speed: Double) = intakeMotors.set(speed)

    override fun initDefaultCommand() {}
}