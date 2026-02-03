package utils;

import com.google.gson.Gson;
import java.io.*;

/**
 *
 * @author LABS-ESPE
 */
public class JsonUtil {
    private static final Gson gson = new Gson();
    
    public static void saveJSON(String document, Object object){
        try(Writer writer = new FileWriter(document)){
            gson.toJson(object, writer);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static <T> T readJSON(String document, Class<T> clazz){
        try (Reader reader = new FileReader(document)){
            return gson.fromJson(reader, clazz);
        } catch (IOException e) {
            return null;
        }
    }
}


