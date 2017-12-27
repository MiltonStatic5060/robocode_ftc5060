

#  --Import your tools--
from tools import *
# from nxttools.gamepad import *

#  --Connect to your NXT Brick--
def get_brick1():
    return nxt.bluesock.BlueSock('00:16:53:16:12:02').connect() #5060-S
def get_brick2():
    return nxt.bluesock.BlueSock('00:16:53:10:22:3D').connect() #5060

b = get_brick2() # Actually do the connecting


#  --Robot Initialization--

motorA = nxt.Motor(b,nxt.PORT_A)
motorB = nxt.Motor(b,nxt.PORT_B)
motorC = nxt.Motor(b,nxt.PORT_C)

soundPlayer = SoundBrick.Player(b)
soundPlayer.success()

def runMotor(motor=motorB,secs=1):
    motor.run()
    time.sleep(secs)    
    motor.idle()

#  --Robot Command Section--
hold_wait("\n\nPress Enter to run commands")

runMotor()
hold_wait("\n\nPress Enter to run next step")
motorB.run(100)
motorC.run(-100)
time.sleep(1)


#  --Robot End Commands--
motorA.idle()
motorB.idle()
motorC.idle()
hold_wait("\n\nPress Enter to close")