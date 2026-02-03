import os
import sys

class FileManager:
    @staticmethod
    def get_base_dir():
        if getattr(sys, 'frozen', False):
            return os.path.dirname(sys.executable)
        else:
            return os.path.dirname(os.path.abspath(__file__))
    
    @staticmethod
    def get_full_path(filename):
        base_dir = FileManager.get_base_dir()
        data_dir = os.path.join(base_dir, "data")
        os.makedirs(data_dir, exist_ok=True)
        return os.path.join(data_dir, filename)
    
    @staticmethod
    def save_to_file(filename, content):
        try:
            full_path = FileManager.get_full_path(filename)
            print(f"Saving to: {full_path}")  
            
            with open(full_path, 'w', encoding='utf-8') as file:
                file.write(content)
            return True
        except Exception as e:
            print(f"Error saving to file {filename}: {e}")
            return False
    
    @staticmethod
    def append_to_file(filename, content):
        try:
            full_path = FileManager.get_full_path(filename)
            print(f"Appending to: {full_path}")  
            
            with open(full_path, 'a', encoding='utf-8') as file:
                file.write(content + '\n')
            return True
        except Exception as e:
            print(f"Error appending to file {filename}: {e}")
            return False
    
    @staticmethod
    def read_file(filename):
        try:
            full_path = FileManager.get_full_path(filename)
            print(f"Reading from: {full_path}")  
            
            if not os.path.exists(full_path):
                print(f"File {filename} does not exist")
                return ""
            
            with open(full_path, 'r', encoding='utf-8') as file:
                return file.read()
        except Exception as e:
            print(f"Error reading file {filename}: {e}")
            return ""
    
    @staticmethod
    def file_exists(filename):
        full_path = FileManager.get_full_path(filename)
        exists = os.path.exists(full_path)
        print(f"Checking if {full_path} exists: {exists}")  
        return exists