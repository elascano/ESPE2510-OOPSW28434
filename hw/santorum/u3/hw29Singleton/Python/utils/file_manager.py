class FileManager:
    FILE_NAME = "appointments.txt"

    @staticmethod
    def save_appointment(appointment):
        with open(FileManager.FILE_NAME, "a") as file:
            file.write(appointment.to_file_string())
