import wpilib
import wpilib.drive


class MyRobot(wpilib.TimedRobot):
    def robotInit(self):
        self.left_motor = wpilib.Spark(0)
        self.right_motor = wpilib.Spark(1)
        self.drive = wpilib.drive.DifferentialDrive(self.left_motor,self.right_motor)
        self.stick = wpilib.Joystick(1)
        self.timer = wpilib.Timer()

    def autonomousInit(self):
        self.timer.reset()
        self.timer.start()

    def autonomousPeriodic(self):
        #
        if self.timer.get() < 2.0:
            self.drive.arcadeDrive(-0.5, 0) # Drive fowards at half speed
        else: 
            self.drive.arcadeDrive(0,0) # Stop Robot
        
    def teleopPeriod(self):
        self.drive.arcadeDrive(self.stick.getY(), self.stick.getX())


if __name__ == "__main__":
    wpilib.run(MyRobot)
