from model.photographer import Photographer
from utils.json_file_util import JsonFileUtil
from utils.mongo_db_util import MongoDBUtil

class PhotographerController:

    def register(self, name, specialty, experience_text, rate_text):
        try:
            experience = int(experience_text)
            hourly_rate = float(rate_text)
        except ValueError:
            raise ValueError("Experience and rate must be numeric")

        photographer = Photographer(
            name,
            specialty,
            experience,
            hourly_rate
        )

        JsonFileUtil().save(photographer)
        MongoDBUtil().save(photographer)
