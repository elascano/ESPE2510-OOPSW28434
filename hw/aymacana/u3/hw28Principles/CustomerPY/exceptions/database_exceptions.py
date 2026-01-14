class DatabaseException(Exception):
    pass

class ConnectionException(DatabaseException):
    def __init__(self, message: str = "Error de conexión a la base de datos"):
        super().__init__(message)


class OperationException(DatabaseException):
    def __init__(self, operation: str, details: str = ""):
        message = f"Error en operación '{operation}': {details}"
        super().__init__(message)


class NotFoundException(DatabaseException):
    def __init__(self, resource: str, identifier: str):
        message = f"{resource} con identificador '{identifier}' no encontrado"
        super().__init__(message)


class ValidationException(DatabaseException):
    def __init__(self, field: str, message: str):
        super().__init__(f"Error de validación en campo '{field}': {message}")