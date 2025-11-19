package ec.edu.espe.soundmixer.view;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.List;
import ec.edu.espe.soundmixer.model.JsonReader;
import ec.edu.espe.soundmixer.model.JsonWriter;
import ec.edu.espe.soundmixer.model.SoundMixer;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public class Main {
    private static final String FILE = "soundmixer_data.json";

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        List<String> data = loadAndEnter();

        while (true) {
            System.out.println("\n--- Sound Mixer ---");
            System.out.println("1. Write sound mixer");
            System.out.println("2. Read sound mixers ");
            System.out.println("3. Exit");
            System.out.print("Option: ");
            String option = scann.nextLine();

            switch (option) {
                case "1":
                    System.out.print("Name: ");
                    
                    data.add(FILE);
                    System.out.println("Volume: ");
                    
                    data.add(FILE);
                    System.out.println("Blass: ");
                    
                    try {
                        JsonWriter.save(FILE, data);
                        System.out.println(".");
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    break;


                case "2":
                    System.out.println("\nSound Mixers: ");
                    data.forEach(System.out::println);
                    break;

                case "3":
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Incorrect input.");
            }
        }
    }

    private static List<String> loadAndEnter() {
        try {
            File f = new File(FILE);
            if (!f.exists()) {
                JsonWriter.save(f, FILE);
            }
            return JsonReader.read(FILE,type);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ArrayList<>();
        }
    }
}
