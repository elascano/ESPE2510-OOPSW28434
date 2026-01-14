from dataclasses import dataclass
from enum import Enum


class ContactType(Enum):
    NORMAL = "Normal"
    FRQUENT = "Frquent"

@dataclass
class Contact:
    id: int
    fullName: str
    email: str
    type: ContactType
    discount: int  
    totalSale: int 
    
    @classmethod
    def from_dict(cls, data: dict) -> 'Contact':
        contact_type = data.get('type', 'Normal')
        if isinstance(contact_type, str):
            try:
                contact_type = ContactType(contact_type.lower())
            except ValueError:
                contact_type = ContactType.NORMAL
        
        return cls(
            id=str(data.get('id', 0)),
            fullName=data.get('fullName', ''),
            email=data.get('email', ''),
            type=contact_type,
            discount=int(data.get('discount', 0)),
            totalSale=int(data.get('totalSale', 0))
        )
    
    def to_dict(self) -> dict:
        return {
            'id': self.id,
            'fullName': self.fullName,
            'email': self.email,
            'type': self.type.value,
            'discount': self.discount,
            'totalSale': self.totalSale
        }
    
    def calculate_final_price(self) -> float:
        discount_amount = self.totalSale * (self.discount / 100)
        return self.totalSale - discount_amount