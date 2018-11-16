package frc.team3324.robot

import edu.wpi.first.wpilibj.CameraServer
import edu.wpi.first.wpilibj.DriverStation
import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.command.Scheduler
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.team3324.robot.AutoGroups.*
import frc.team3324.robot.arm.Arm
import frc.team3324.robot.drivetrain.DriveTrain
import frc.team3324.robot.intake.Intake
import frc.team3324.robot.util.LimitSwitch
import frc.team3324.robot.util.OI


class Robot : TimedRobot() {
    private var autoSelector = SendableChooser<Int>()
    private var infoString: String? = null
    private var selectedCommand: Command? = null

    private val defaultSet = 0
    private val left = 1
    private val middle = 2
    private val right = 3

    override fun robotInit() {
        Intake
        DriveTrain
        Arm
        OI

        CameraServer.getInstance().startAutomaticCapture()
        CameraServer.getInstance().putVideo("Camera Output", 1280, 720)
        /* Initialize AUTO selecter UI */
        autoSelector.addDefault("Default", defaultSet)
        autoSelector.addObject("Left position", left)
        autoSelector.addObject("Middle position", middle)
        autoSelector.addObject("Right position", right)

        SmartDashboard.putData("CHOOSE ONE", autoSelector)
    }

    override fun robotPeriodic() {
        SmartDashboard.putBoolean("Limit Switch:", LimitSwitch.limitSwitch.get())
        Scheduler.getInstance().run()
        CameraServer.getInstance().video
    }


    override fun disabledPeriodic() {
        val gameData = DriverStation.getInstance().gameSpecificMessage
        var positionString: String? = null
        if (autoSelector.selected === defaultSet) {
            positionString = "Default position"
        } else if (autoSelector.selected === left) {
            positionString = "Left position"
        } else if (autoSelector.selected === middle) {
            positionString = "Middle position"
        } else if (autoSelector.selected === right) {
            positionString = "Right position"
        }

        SmartDashboard.putString("You are in: ", positionString)

        if (gameData != null && gameData.isNotEmpty()) {

            val firstLetter = gameData[0]
            if (positionString.equals("Default position")) {
                selectedCommand = Default
                infoString = "Drive forward (default)"
            } else if (firstLetter === 'L' && positionString.equals("Left position")) {
                selectedCommand = LLeft
                infoString = "LLeft"
            }
            if (firstLetter === 'L' && positionString.equals("Middle position")) {
                selectedCommand = LMiddle
                infoString = "LMiddle"
            } else if (firstLetter === 'L' && positionString.equals("Right position")) {
                selectedCommand = Default
                infoString = "LRight"
            }
            if (firstLetter === 'L' && positionString.equals("Right position")) {
                selectedCommand = Default
                infoString = "RLeft"
            } else if (firstLetter === 'R' && positionString.equals("Middle position")) {
                selectedCommand = RMiddle
                infoString = "RMiddle"
            } else if (firstLetter === 'R') {
                selectedCommand = RRight
                infoString = "RRight"
            } else {
                DriverStation.reportError("No game data received.", false)
                infoString = "No game data received."
            }

            CameraServer.getInstance().video
        }
    }

    override fun teleopPeriodic() {

    }
}