from controller.update_strategy import UpdateStrategy

class MongoUpdateStrategy(UpdateStrategy):

    def update(self, collection, cellphone):
        collection.update_one(
            {"id": cellphone.id},
            {"$set": {
                "model": cellphone.model,
                "price": cellphone.price
            }}
        )
