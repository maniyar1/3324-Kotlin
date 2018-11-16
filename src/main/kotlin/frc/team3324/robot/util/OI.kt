package frc.team3324.robot.util

import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj.buttons.JoystickButton
import frc.team3324.robot.intake.commands.Intake
import frc.team3324.robot.intake.commands.Outtake

object OI {
    val PRIMARY_CONTROLLER = XboxController(0)
    val SECONDARY_CONTROLLER = XboxController(1)
    // Xbox controller button mappings
    private const val BUTTON_A = 1;
    private const val BUTTON_B = 2;
    private const val BUTTON_X = 3;
    private const val BUTTON_Y = 4;
    private const val BUMPER_LEFT = 5;
    private const val BUMPER_RIGHT = 6;
    private const val BUTTON_BACK = 7;
    private const val BUTTON_START = 8;
    private const val JOYSTICK_LEFT_CLICK = 9;
    private const val JOYSTICK_RIGHT_CLICK = 10;
    // Primary driver's buttons
    val PRIMARY_BUTTON_A = JoystickButton(PRIMARY_CONTROLLER, BUTTON_A);
    val PRIMARY_BUMPER_RIGHT = JoystickButton(PRIMARY_CONTROLLER, BUMPER_RIGHT);
    //Secondary driver's buttons
    val SECONDARY_BUMPER_LEFT = JoystickButton(PRIMARY_CONTROLLER, BUTTON_A);
    val SECONDARY_BUMPER_RIGHT = JoystickButton(PRIMARY_CONTROLLER, BUTTON_A);

    init {
        SECONDARY_BUMPER_LEFT.whileHeld(Outtake)
        SECONDARY_BUMPER_RIGHT.whileHeld(Intake)
    }
}