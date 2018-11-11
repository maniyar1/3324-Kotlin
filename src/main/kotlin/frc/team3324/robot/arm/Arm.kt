package frc.team3324.robot.arm

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX
import edu.wpi.first.wpilibj.SpeedControllerGroup
import edu.wpi.first.wpilibj.command.Subsystem
import frc.team3324.robot.Constants
import frc.team3324.robot.arm.commands.ControlArm

object Arm: Subsystem() {
    private val armMotorLeft = WPI_VictorSPX(Constants.Arm.MOTOR_PORT_ARM_LEFT)
    private val armMotorRight = WPI_VictorSPX(Constants.Arm.MOTOR_PORT_ARM_RIGHT)
    val armMotors = SpeedControllerGroup(armMotorLeft, armMotorRight)
    init {
        if (armMotorLeft.inverted == true) {
            armMotorLeft.inverted = true
        }
    }

    override fun initDefaultCommand() {
        defaultCommand = ControlArm
    }
}