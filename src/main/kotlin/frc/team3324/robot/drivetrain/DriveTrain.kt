package frc.team3324.robot.drivetrain

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX
import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.wpilibj.CounterBase
import edu.wpi.first.wpilibj.Encoder
import edu.wpi.first.wpilibj.SPI
import edu.wpi.first.wpilibj.SpeedControllerGroup
import edu.wpi.first.wpilibj.command.Subsystem
import edu.wpi.first.wpilibj.drive.DifferentialDrive
import frc.team3324.robot.util.Constants
import frc.team3324.robot.drivetrain.commands.Drive


object DriveTrain : Subsystem() {
    val lEncoder = Encoder(Constants.Drivetrain.LEFT_ENCODER_PORT_A, Constants.Drivetrain.LEFT_ENCODER_PORT_B, false, CounterBase.EncodingType.k4X)
    val rEncoder = Encoder(Constants.Drivetrain.RIGHT_ENCODER_PORT_A, Constants.Drivetrain.RIGHT_ENCODER_PORT_B, false, CounterBase.EncodingType.k4X)
    val gyro = AHRS(SPI.Port.kMXP)

    val flMotor = WPI_VictorSPX(Constants.Drivetrain.FL_MOTOR_PORT) // Instantiate the motors as a new TalonSRX motor controller
    val blMotor = WPI_VictorSPX(Constants.Drivetrain.BL_MOTOR_PORT)
    val lMotors = SpeedControllerGroup(flMotor, blMotor) // Combine the left motors into one lMotors speed controller group

    val frMotor = WPI_VictorSPX(Constants.Drivetrain.FR_MOTOR_PORT) //repeat for right motors
    val brMotor = WPI_VictorSPX(Constants.Drivetrain.BR_MOTOR_PORT)
    val rMotors = SpeedControllerGroup(frMotor, brMotor)

    val mDrive = DifferentialDrive(lMotors, rMotors)

    init {
        rEncoder.setReverseDirection(true)
    }

    fun clearSensors() {
        gyro.reset()
        lEncoder.reset()
        rEncoder.reset()
    }

    fun setBrakeMode() {
        frMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake)
        brMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake)
        blMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake)
        flMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake)
    }

    fun setCoastMode() {
        frMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast)
        brMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast)
        blMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast)
        flMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast)
    }

    override fun initDefaultCommand() {
        defaultCommand = Drive
    }
}