from typing import List, Dict, Any, Tuple
from models.contact_model import Contact, ContactType
from repositories.contact_repository import ContactRepository

class ContactService:
    def __init__(self, repository: ContactRepository):
        self.repository = repository
    
    def get_all_contacts(self, sort_by: str = "id") -> List[Contact]:
        return self.repository.find_all(sort_by=sort_by)
    
    def get_contact_by_id(self, contact_id: int) -> Contact:
        return self.repository.find_by_id(contact_id)
    
    def get_sales_analysis(self) -> Dict[str, Any]:
        summary = self.repository.get_sales_summary()
        top_spenders = self.repository.get_top_spenders(limit=3)
        
        contacts = self.get_all_contacts()
        total_final_price = sum(contact.calculate_final_price() for contact in contacts)
        total_discount = summary['total_sales'] - total_final_price
        
        return {
            'sales_summary': {
                'total_sales': self._format_currency(summary['total_sales']),
                'average_sales': self._format_currency(summary['average_sales']),
                'total_final_price': self._format_currency(total_final_price),
                'total_discount': self._format_currency(total_discount),
                'total_contacts': summary['total_contacts']
            },
            'top_spenders': [
                {
                    'id': contact.id,
                    'name': contact.fullName,
                    'type': contact.type.value,
                    'total_sale': self._format_currency(contact.totalSale),
                    'discount': f"{contact.discount}%",
                    'final_price': self._format_currency(contact.calculate_final_price())
                }
                for contact in top_spenders
            ]
        }
    
    def get_type_distribution(self) -> List[Tuple[str, int, str]]:
        contacts = self.get_all_contacts()
        total = len(contacts)
        
        if total == 0:
            return []
        
        type_count = {}
        for contact in contacts:
            type_str = contact.type.value
            type_count[type_str] = type_count.get(type_str, 0) + 1
        
        distribution = []
        for type_str, count in type_count.items():
            percentage = (count / total) * 100
            distribution.append((type_str, count, f"{percentage:.1f}%"))
        
        return sorted(distribution, key=lambda x: x[1], reverse=True)
    
    def prepare_contacts_for_display(self, contacts: List[Contact]) -> List[List[str]]:
        table_data = []
        
        for contact in contacts:
            final_price = contact.calculate_final_price()
            row = [
                str(contact.id),
                contact.fullName,
                contact.email,
                contact.type.value,
                f"{contact.discount}%",
                self._format_currency(contact.totalSale),
                self._format_currency(final_price),
                self._format_currency(contact.totalSale - final_price)
            ]
            table_data.append(row)
        
        return table_data
    
    def _format_currency(self, amount: float) -> str:
        return f"${amount:,.2f}"