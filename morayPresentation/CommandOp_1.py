# robot.py
"""This code controls the big metal robot 1st Generation"""

#import packages for Gamepad Control and Motor Controls
from nxttools import sound as SoundBrick
from nxttools import rangetools as Range
from nxttools import hardware as hardwareMap
from nxttools.reactcore import reactctl
from nxttools.gamepad import *

#import nxt-python packages to connect to robot and bluetooth
import nxt, thread, time
import nxt.bluesock # Make sure you remember this!
from nxt.sensor.hitechnic import *

# --Connect to your NXT Brick--
def get_brick1():
    return nxt.bluesock.BlueSock('00:16:53:16:12:02').connect() #5060-S
def get_brick2():
    return nxt.bluesock.BlueSock('00:16:53:10:22:3D').connect() #5060

b = get_brick1() # Actually do the connecting
gamepad1 = gamepad1
gamepad2 = gamepad2

#  ...Making some motors...

#  motor Front Right - nxt port 1, motor port 1
motorFR = hardwareMap.DcMotor(b,nxt.PORT_1,1)

#  motor Front Left - nxt port 1, motor port 2
#  --insert code--

#  motor Back  Right - nxt port 2, motor port 1
#  --insert code--

#  motor Back  Left - nxt port 2, motor port 2
#  --insert code--

def tank_driving(left_stick=0,right_stick=0):
    """have the left_stick control the left motors, and the right_stick control the right motors"""
    powerRight = right_stick
    powerLeft  = 
    
    motorFR.setPower(  powerRight  )
    motorFL.
    motorBR.
    motorBL.

def onestick_driving(stick_x=0,stick_y=0):
    """have a single stick's y value control forward/backward
    and x value control rotation."""
    power      = 
    direction  = stick_x
    
    powerLeft  = power - direction
    powerRight = 

    motorFR.setPower(  powerRight )
    motorFL.
    motorBR.
    motorBL.


while 1:
    pass # placeholder for nothing running