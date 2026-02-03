import re

def valid_model(text):
    return bool(re.fullmatch(r"[A-Za-z ]+", text))

def valid_price(text):
    return bool(re.fullmatch(r"\d+(\.\d{1,2})?", text))
