import json
import os
from pathlib import Path
from typing import List, Dict, Any, Union

class ChickenSave:
    def __init__(self, filename: str = 'chickenFarm.json', data_directory: str = 'data'):
        current_working_dir = Path.cwd() 
        self.__dir_path = current_working_dir / data_directory
        self.__file_path = self.__dir_path / filename

        print(f"Save path set to: {self.__file_path}")

    async def __read_and_parse_file(self) -> List[Dict[str, Any]]:
        try:
            data = self.__file_path.read_text(encoding='utf-8')
            return json.loads(data)
        except FileNotFoundError:
            print(f"File {self.__file_path} not found. Returning empty array.")
            return []
        except json.JSONDecodeError as e:
            print(f'Error loading or parsing coop data: {e}')
            raise Exception('Failed to deserialize data.') from e
        except Exception as e:
            print(f'Error loading or parsing coop data: {e}')
            raise Exception('Failed to deserialize data.') from e

    async def __save_data_to_file(self, data: List[Dict[str, Any]]):
        try:
            self.__dir_path.mkdir(parents=True, exist_ok=True)
            
            json_data = json.dumps(data, indent=2, ensure_ascii=False)
            
            self.__file_path.write_text(json_data, encoding='utf-8')
            print(f"Data successfully saved to: {self.__file_path}")
        except Exception as e:
            print(f'Error saving data: {e}')
            raise Exception('Failed to save data to file.') from e

    async def save(self, coops: List[Any]):
        try:
            serializable_data = []
            for coop in coops:
                chickens_data = []
                for chicken in coop.getChickens():
                    chickens_data.append({
                        "id": chicken.id,  
                        "name": chicken.name,
                        "color": chicken.color,
                        "age": chicken.age,
                        "isMolting": chicken.isMolting
                    })
                
                serializable_data.append({
                    "id": coop.getId(),
                    "name": coop.getName(),
                    "chickens": chickens_data
                })

            await self.__save_data_to_file(serializable_data)

            print("Coop and Chicken data successfully serialized and saved.")
        except Exception as e:
            print(f'Error saving coop data: {e}')
            raise Exception('Failed to serialize and save data.') from e

    async def load(self) -> List[Dict[str, Any]]:
        loaded_coops = await self.__read_and_parse_file()
        print(f"Coop and Chicken data successfully loaded from: {self.__file_path}")
        return loaded_coops

    async def find_coop_or_chicken_by_id(self, id: Union[str, int], type: str) -> Union[Dict[str, Any], None]:
        data = await self.__read_and_parse_file()

        if not data:
            print('No data loaded to search.')
            return None

        if type == 'coop':
            coop = next((c for c in data if c['id'] == id), None)
            if coop:
                print(f"Coop found with ID: {id}")
                return coop
        elif type == 'chicken':
            for coop in data:
                chicken = next((c for c in coop.get('chickens', []) if c['id'] == id), None)
                if chicken:
                    print(f"Chicken found with ID: {id} in Coop: {coop['name']}")
                    return {"coop": coop, "chicken": chicken}
        else:
            raise ValueError("Invalid type for search. Must be 'coop' or 'chicken'.")

        print(f"{type} with ID {id} not found.")
        return None

    async def update_data(self, id: Union[str, int], type: str, update_data: Dict[str, Any]) -> Dict[str, Any]:
        data = await self.__read_and_parse_file()

        if not data:
            raise Exception('No data loaded. Cannot update.')

        updated_item = None
        data_modified = False

        if type == 'coop':
            for i, coop in enumerate(data):
                if coop['id'] == id:
                    data[i].update(update_data)
                    data[i]['id'] = id
                    updated_item = data[i]
                    data_modified = True
                    break
        elif type == 'chicken':
            for coop in data:
                for i, chicken in enumerate(coop.get('chickens', [])):
                    if chicken['id'] == id:
                        coop['chickens'][i].update(update_data)
                        coop['chickens'][i]['id'] = id
                        updated_item = coop['chickens'][i]
                        data_modified = True
                        break
                if data_modified:
                    break
        else:
            raise ValueError("Invalid type for update. Must be 'coop' or 'chicken'.")

        if updated_item is None:
            raise Exception(f"{type} with ID {id} not found for update.")

        if data_modified:
            await self.__save_data_to_file(data)
            print(f"{type} with ID {id} successfully updated.")

        return updated_item

    async def delete_data(self, id: Union[str, int], type: str) -> bool:
        data = await self.__read_and_parse_file()

        if not data:
            print('No data loaded. Nothing to delete.')
            return False

        data_modified = False

        if type == 'coop':
            initial_length = len(data)
            data[:] = [c for c in data if c['id'] != id]
            if len(data) < initial_length:
                data_modified = True
        elif type == 'chicken':
            for coop in data:
                initial_length = len(coop.get('chickens', []))
                new_chickens = [c for c in coop.get('chickens', []) if c['id'] != id]
                if len(new_chickens) < initial_length:
                    coop['chickens'] = new_chickens
                    data_modified = True
                    break
        else:
            raise ValueError("Invalid type for delete. Must be 'coop' or 'chicken'.")

        if data_modified:
            await self.__save_data_to_file(data)
            print(f"{type} with ID {id} successfully deleted.")
            return True
        else:
            print(f"{type} with ID {id} not found for deletion.")
            return False