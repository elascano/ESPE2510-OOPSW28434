from view.FrmAlarm import FrmAlarm
from controller.AlarmController import AlarmController

if __name__ == "__main__":
    controller = AlarmController()
    view = FrmAlarm(controller)
    controller.set_view(view)
    

    controller.run()