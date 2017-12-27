# robot.py
"""This code controls the big metal robot 1st Generation"""

#import packages for Gamepad Control and Motor Controls
from nxttools import sound as SoundBrick
from nxttools import rangetools as Range
from nxttools import hardware as hardwareMap
from nxttools.reactcore import reactctl
# from nxttools.gamepad import *

#import nxt-python packages to connect to robot and bluetooth
import nxt, thread, time
import nxt.bluesock # Make sure you remember this!
from nxt.sensor.hitechnic import *

def hold_wait(text):
    try:
        a = input(text)
    except:
        pass