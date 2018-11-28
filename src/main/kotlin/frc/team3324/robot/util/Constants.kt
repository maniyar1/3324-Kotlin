package frc.team3324.robot.util

object Constants {
    object Drivetrain {
        const val FL_MOTOR_PORT = 6
        const val BL_MOTOR_PORT = 4
        const val FR_MOTOR_PORT = 0
        const val BR_MOTOR_PORT = 2
        const val LEFT_ENCODER_PORT_A = 0 // Checked Buckeye
        const val LEFT_ENCODER_PORT_B = 1 // Checked Buckeye
        const val RIGHT_ENCODER_PORT_A = 2 // Checked Buckeye
        const val RIGHT_ENCODER_PORT_B = 3 // Checked Buckeye
        const val CIRCUMFERENCE = 18.85 // (Inches) Need to measure in inches
        const val TICKS_PER_REVOLUTION = 7680 // 256 (pulses) * 4 (quadature, 4 ticks/pulse) * 3 * 2.5 (gear ratios)
        const val DISTANCE_BETWEEN_WHEELS = 22.5
        const val DISTANCE_BETWEEN_WHEELS_METERS = 0.5715
        const val LOW_GEAR_METERS_PER_SECOND = 2.4384 // lowgearSpeedMeters
        const val WHEEL_DIAMETER_METERS = 0.15240359
    }

    object Intake {
        const val LEFT_INTAKE_MOTOR_PORT = 7
        const val RIGHT_INTAKE_MOTOR_PORT = 3
    }

    object Arm {
        const val MOTOR_PORT_ARM_LEFT = 9
        const val MOTOR_PORT_ARM_RIGHT = 8
    }
    object Vision {
        const val RESOLUTION_WIDTH = 360
        const val RESOLUTION_HEIGHT = 240
        const val kP = 1.0/(RESOLUTION_WIDTH/2.0)  
        const val kD = 1  // Change this (shouldn't be 1)
        const val kI = 0.00005  // Change this
    }
}
