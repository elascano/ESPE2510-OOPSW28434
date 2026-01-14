from typing import List, Optional, Dict, Any
from pymongo.errors import PyMongoError

from models.contact_model import Contact, ContactType
from config.database import DatabaseConnection
from exceptions.database_exceptions import OperationException, NotFoundException

class ContactRepository:
    
    def __init__(self, database_connection: Optional[DatabaseConnection] = None):
        self.db_connection = database_connection or DatabaseConnection()
        self.collection = self.db_connection.get_collection()
    
    def find_all(self, sort_by: Optional[str] = None, sort_order: int = 1) -> List[Contact]:
        try:
            if sort_by:
                contacts_data = list(self.collection.find().sort(sort_by, sort_order))
            else:
                contacts_data = list(self.collection.find())
            
            return [Contact.from_dict(contact) for contact in contacts_data]
        except PyMongoError as e:
            raise OperationException('find_all', str(e))
    
    def find_by_id(self, contact_id: int) -> Contact:
        try:
            contact_data = self.collection.find_one({'id': contact_id})
            
            if not contact_data:
                raise NotFoundException('Contact', str(contact_id))
            
            return Contact.from_dict(contact_data)
        except PyMongoError as e:
            raise OperationException('find_by_id', str(e))
    
    def find_by_type(self, contact_type: ContactType) -> List[Contact]:
        try:
            contacts_data = list(self.collection.find({'type': contact_type.value}))
            return [Contact.from_dict(contact) for contact in contacts_data]
        except PyMongoError as e:
            raise OperationException('find_by_type', str(e))
    
    def get_sales_summary(self) -> Dict[str, Any]:
        try:
            pipeline_total = [{'$group': {'_id': None, 'total': {'$sum': '$totalSale'}}}]
            result_total = list(self.collection.aggregate(pipeline_total))
            total_sales = result_total[0]['total'] if result_total else 0
            
            count = self.collection.count_documents({})
            average_sales = total_sales / count if count > 0 else 0
            
            pipeline_by_type = [
                {'$group': {
                    '_id': '$type',
                    'total_sales': {'$sum': '$totalSale'},
                    'contact_count': {'$sum': 1},
                    'avg_discount': {'$avg': '$discount'}
                }}
            ]
            sales_by_type = list(self.collection.aggregate(pipeline_by_type))
            
            return {
                'total_sales': total_sales,
                'average_sales': average_sales,
                'sales_by_type': sales_by_type,
                'total_contacts': count
            }
        except PyMongoError as e:
            raise OperationException('get_sales_summary', str(e))
    
    def get_top_spenders(self, limit: int = 3) -> List[Contact]:
        try:
            contacts_data = list(
                self.collection.find()
                .sort('totalSale', -1)
                .limit(limit)
            )
            return [Contact.from_dict(contact) for contact in contacts_data]
        except PyMongoError as e:
            raise OperationException('get_top_spenders', str(e))