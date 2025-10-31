import json
import os
from datetime import datetime

class JSONFileManager:
    FILE_NAME = "chicken_farm_data.json"
    BACKUP_FILE_NAME = "chicken_farm_backup.json"
    
    @staticmethod
    def save_to_file(coops):
        try:
            data = JSONFileManager._convert_to_json(coops)
            
            # Create backup if file exists
            if os.path.exists(JSONFileManager.FILE_NAME):
                with open(JSONFileManager.FILE_NAME, 'r', encoding='utf-8') as f:
                    backup_data = f.read()
                with open(JSONFileManager.BACKUP_FILE_NAME, 'w', encoding='utf-8') as f:
                    f.write(backup_data)
            
            with open(JSONFileManager.FILE_NAME, 'w', encoding='utf-8') as f:
                json.dump(data, f, indent=2, ensure_ascii=False)
            
            print(f"✅ Data saved successfully to {JSONFileManager.FILE_NAME}")
            return True
            
        except Exception as e:
            print(f"❌ Error saving data: {str(e)}")
            return False
    
    @staticmethod
    def load_from_file():
        try:
            if not os.path.exists(JSONFileManager.FILE_NAME):
                print("ℹ️ No existing data file found. Starting with empty farm.")
                return []
            
            with open(JSONFileManager.FILE_NAME, 'r', encoding='utf-8') as f:
                data = json.load(f)
            
            print(f"✅ Data loaded successfully from {JSONFileManager.FILE_NAME}")
            return JSONFileManager._convert_from_json(data)
            
        except Exception as e:
            print(f"❌ Error loading data: {str(e)}")
            
            # Try to load from backup if main file is corrupted
            if os.path.exists(JSONFileManager.BACKUP_FILE_NAME):
                print("🔄 Attempting to load from backup file...")
                try:
                    with open(JSONFileManager.BACKUP_FILE_NAME, 'r', encoding='utf-8') as f:
                        backup_data = json.load(f)
                    print("✅ Backup data loaded successfully!")
                    return JSONFileManager._convert_from_json(backup_data)
                except Exception as backup_error:
                    print(f"❌ Backup file is also corrupted: {str(backup_error)}")
            
            return []
    
    @staticmethod
    def _convert_to_json(coops):
        from chicken_coop import ChickenCoop
        from chicken import Chicken
        
        total_chickens = 0
        for coop in coops:
            total_chickens += coop.get_chicken_count()
        
        return {
            "metadata": {
                "version": "1.0",
                "created": datetime.now().isoformat(),
                "totalCoops": len(coops),
                "totalChickens": total_chickens
            },
            "coops": [
                {
                    "id": coop.get_id(),
                    "description": coop.get_description(),
                    "chickens": [
                        {
                            "id": chicken.get_id(),
                            "name": chicken.get_name(),
                            "color": chicken.get_color(),
                            "age": chicken.get_age(),
                            "isMolting": chicken.is_molting(),  # Método, no propiedad
                            "coopId": coop.get_id()
                        }
                        for chicken in coop.get_chickens()
                    ]
                }
                for coop in coops
            ]
        }
    
    @staticmethod
    def _convert_from_json(data):
        from chicken_coop import ChickenCoop
        from chicken import Chicken
        
        coops = []
        
        if "coops" not in data or not isinstance(data["coops"], list):
            return coops
        
        for coop_data in data["coops"]:
            coop = ChickenCoop(coop_data["id"], coop_data["description"])
            
            if "chickens" in coop_data and isinstance(coop_data["chickens"], list):
                for chicken_data in coop_data["chickens"]:
                    try:
                        chicken = Chicken(
                            chicken_data["id"],
                            chicken_data["name"],
                            chicken_data["color"],
                            chicken_data["age"],
                            chicken_data["isMolting"]
                        )
                        coop.get_chickens().append(chicken)
                    except Exception as e:
                        print(f"⚠️ Error creating chicken from data: {str(e)}")
                        continue
            
            coops.append(coop)
        
        return coops
    
    @staticmethod
    def display_file_data():
        try:
            if not os.path.exists(JSONFileManager.FILE_NAME):
                print("ℹ️ No data file found. Please save data first.")
                return
            
            with open(JSONFileManager.FILE_NAME, 'r', encoding='utf-8') as f:
                data = json.load(f)
            
            print("\n" + "="*50)
            print("📊 JSON FILE DATA SUMMARY")
            print("="*50)
            
            if "metadata" in data:
                metadata = data["metadata"]
                created_date = datetime.fromisoformat(metadata["created"])
                print(f"📅 Created: {created_date.strftime('%Y-%m-%d %H:%M:%S')}")
                print(f"🏠 Total Coops: {metadata['totalCoops']}")
                print(f"🐔 Total Chickens: {metadata['totalChickens']}")
            
            print("\n📋 Detailed Data:")
            print(json.dumps(data, indent=2, ensure_ascii=False))
            
        except Exception as e:
            print(f"❌ Error displaying file data: {str(e)}")
    
    @staticmethod
    def delete_file():
        try:
            if os.path.exists(JSONFileManager.FILE_NAME):
                os.remove(JSONFileManager.FILE_NAME)
                print(f"🗑️ File {JSONFileManager.FILE_NAME} deleted successfully.")
            else:
                print(f"ℹ️ File {JSONFileManager.FILE_NAME} does not exist.")
        except Exception as e:
            print(f"❌ Error deleting file: {str(e)}")
    
    @staticmethod
    def update_chicken_data(coops, chicken_id, new_name, new_color, new_age, new_molting_status):
        try:
            chicken_found = False
            found_coop = None
            
            # Search for chicken in all coops
            for coop in coops:
                chickens = coop.get_chickens()
                for chicken in chickens:
                    if chicken.get_id() == chicken_id:
                        chicken.set_name(new_name)
                        chicken.set_color(new_color)
                        chicken.set_age(new_age)
                        chicken.set_molting(new_molting_status)
                        chicken_found = True
                        found_coop = coop
                        break
                if chicken_found:
                    break
            
            if chicken_found:
                JSONFileManager.save_to_file(coops)
                print(f"✅ Chicken with ID {chicken_id} in Coop {found_coop.get_id()} updated successfully.")
            else:
                print(f"❌ Chicken with ID {chicken_id} not found in any coop.")
                
        except Exception as e:
            print(f"❌ Error updating chicken data: {str(e)}")
    
    @staticmethod
    def delete_chicken_from_file(coops, coop_id, chicken_id):
        try:
            chicken_found = False
            found_coop = None
            
            for coop in coops:
                if coop.get_id() == coop_id:
                    found_coop = coop
                    chickens = coop.get_chickens()
                    for i, chicken in enumerate(chickens):
                        if chicken.get_id() == chicken_id:
                            chickens.pop(i)
                            chicken_found = True
                            break
                    break
            
            if chicken_found:
                JSONFileManager.save_to_file(coops)
                print(f"✅ Chicken with ID {chicken_id} deleted from Coop {coop_id} and JSON file updated successfully.")
            else:
                if found_coop is None:
                    print(f"❌ Coop with ID {coop_id} not found.")
                else:
                    print(f"❌ Chicken with ID {chicken_id} not found in Coop {coop_id}.")
                    
        except Exception as e:
            print(f"❌ Error deleting chicken from JSON: {str(e)}")
    
    @staticmethod
    def file_exists():
        return os.path.exists(JSONFileManager.FILE_NAME)
    
    @staticmethod
    def get_file_info():
        try:
            if not os.path.exists(JSONFileManager.FILE_NAME):
                return {"exists": False}
            
            file_stats = os.stat(JSONFileManager.FILE_NAME)
            return {
                "exists": True,
                "size_bytes": file_stats.st_size,
                "modified": datetime.fromtimestamp(file_stats.st_mtime).isoformat()
            }
        except Exception as e:
            return {"exists": False, "error": str(e)}
    
    @staticmethod
    def export_to_readable_format(coops, filename="chicken_farm_export.txt"):
        """Export data to a human-readable text format"""
        try:
            with open(filename, 'w', encoding='utf-8') as f:
                f.write("CHICKEN FARM EXPORT\n")
                f.write("=" * 50 + "\n")
                f.write(f"Export Date: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}\n")
                f.write(f"Total Coops: {len(coops)}\n")
                
                total_chickens = sum(coop.get_chicken_count() for coop in coops)
                f.write(f"Total Chickens: {total_chickens}\n\n")
                
                for i, coop in enumerate(coops, 1):
                    f.write(f"COOP {i}: ID {coop.get_id()} - {coop.get_description()}\n")
                    f.write(f"Chickens: {coop.get_chicken_count()}\n")
                    
                    for j, chicken in enumerate(coop.get_chickens(), 1):
                        f.write(f"  {j}. {chicken.get_name()} (ID: {chicken.get_id()})\n")
                        f.write(f"     Color: {chicken.get_color()}, Age: {chicken.get_age()}\n")
                        f.write(f"     Molting: {'Yes' if chicken.is_molting() else 'No'}\n")
                    
                    f.write("\n")
            
            print(f"✅ Data exported to {filename}")
            return True
            
        except Exception as e:
            print(f"❌ Error exporting data: {str(e)}")
            return False