# setup.py
from setuptools import setup, find_packages

setup(
    name="taxoperations",
    version="0.1.0",  # Aquí pones la versión
    packages=find_packages(),
    description="Libreria para calcular impuesto a la renta",
    author="Daniel",
    python_requires='>=3.8',
)