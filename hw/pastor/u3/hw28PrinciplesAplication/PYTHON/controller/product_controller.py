class ProductController:
    def __init__(self, service):
        self.service = service

    def add_product(self, data_dict):
        self.service.save_new_item(data_dict)

    def get_data_for_table(self):
        entities = self.service.get_processed_data()
        rows = []
        for entity in entities:
            rows.append((
                entity.get_id(),
                entity.get_data("name"),
                entity.get_data("priceBase"),
                entity.get_data("endPrice")
            ))
        return rows