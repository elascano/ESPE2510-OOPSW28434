class CompanyAccount:
    def __init__(self, name=None, address=None, ruc=None, phone=None, email=None, username=None, password=None):
        self.__name = name
        self.__address = address
        self.__ruc = ruc
        self.__phone = phone
        self.__email = email
        self.__username = username
        self.__password = password

    def check_password(self, attempt):
        return self.__password is not None and self.__password == attempt

    def get_name(self):
        return self.__name

    def get_address(self):
        return self.__address

    def get_ruc(self):
        return self.__ruc

    def get_phone(self):
        return self.__phone

    def get_email(self):
        return self.__email

    def get_username(self):
        return self.__username