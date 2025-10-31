package com.mycompany.chickenfarmsimulator.view;

import com.mycompany.chickenfarmsimulator.model.Chicken;
import com.mycompany.chickenfarmsimulator.model.ChickenCoop;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChickenFarmSimulator {

    private static final Path DATA_FILE =
            Paths.get(System.getProperty("user.dir"), "farm_data.json");
    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        List<ChickenCoop> coops = loadFromJson();

        if (coops.isEmpty()) {
            ChickenCoop coop1 = new ChickenCoop(1);
            ChickenCoop coop2 = new ChickenCoop(2);
            coop1.add(new Chicken(1, "Lucy", "White", 2, false));
            coop1.add(new Chicken(2, "Maruja", "Brown", 1, true));
            coop2.add(new Chicken(3, "Pio", "Black", 3, false));
            coops = new ArrayList<>(Arrays.asList(coop1, coop2));
            saveToJson(coops);
        }

        while (true) {
            System.out.println("\n==============================");
            System.out.println("        CHICKEN FARM MENU     ");
            System.out.println("==============================");
            System.out.println("1. View all coops and chickens");
            System.out.println("2. Add coop");
            System.out.println("3. Add chicken");
            System.out.println("4. Remove chicken");
            System.out.println("5. Edit chicken");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            String option = SC.nextLine().trim();
            switch (option) {
                case "1" -> showAll(coops);
                case "2" -> addCoopFlow(coops);
                case "3" -> addChickenFlow(coops);
                case "4" -> removeChickenFlow(coops);
                case "5" -> editChickenFlow(coops);
                case "6" -> { System.out.println("Exiting Chicken Farm Simulator."); return; }
                default -> System.out.println("Invalid option. Please choose 1–6.");
            }
        }
    }

    // -------- DISPLAY ----------
    private static void showAll(List<ChickenCoop> coops) {
        System.out.println("\n==============================");
        System.out.println("       FARM INFORMATION       ");
        System.out.println("==============================");

        if (coops.isEmpty()) {
            System.out.println("No chicken coops found.");
            return;
        }

        for (ChickenCoop coop : coops) {
            System.out.println("\nCOOP #" + coop.getId());
            System.out.println("------------------------------------------------------------");
            System.out.printf("%-5s %-15s %-12s %-5s %-10s%n", "ID", "NAME", "COLOR", "AGE", "MOLTING");
            System.out.println("------------------------------------------------------------");
            if (coop.getChickens().isEmpty()) {
                System.out.println("(No chickens in this coop)");
            } else {
                coop.getChickens().stream()
                        .sorted(Comparator.comparingInt(Chicken::getId))
                        .forEach(c -> System.out.printf("%-5d %-15s %-12s %-5d %-10s%n",
                                c.getId(), c.getName(), c.getColor(), c.getAge(), String.valueOf(c.isMolting())));
            }
            System.out.println("------------------------------------------------------------");
        }
    }

    // -------- MENU ACTIONS ----------
    private static void addCoopFlow(List<ChickenCoop> coops) {
        try {
            System.out.println("\n=== ADD NEW COOP ===");
            System.out.print("Enter new coop ID: ");
            int coopId = Integer.parseInt(SC.nextLine().trim());
            if (coopIdExists(coops, coopId)) {
                System.out.println("A coop with that ID already exists.");
                return;
            }
            coops.add(new ChickenCoop(coopId));
            saveToJson(coops);
            System.out.println("Coop #" + coopId + " created.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Try again.");
        }
    }

    private static void addChickenFlow(List<ChickenCoop> coops) {
        try {
            System.out.println("\n=== ADD NEW CHICKEN ===");
            System.out.print("Enter coop ID: ");
            int coopId = Integer.parseInt(SC.nextLine().trim());
            ChickenCoop coop = findCoop(coops, coopId);
            if (coop == null) {
                System.out.println("Coop not found.");
                return;
            }
            System.out.print("Enter chicken name: ");
            String name = SC.nextLine().trim();
            System.out.print("Enter color: ");
            String color = SC.nextLine().trim();
            System.out.print("Enter age: ");
            int age = Integer.parseInt(SC.nextLine().trim());
            System.out.print("Is it molting? (y/n): ");
            boolean isMolting = "y".equalsIgnoreCase(SC.nextLine().trim());

            int newId = nextChickenId(coops);
            coop.add(new Chicken(newId, name, color, age, isMolting));
            saveToJson(coops);
            System.out.println("Chicken '" + name + "' added to Coop #" + coopId + " with ID " + newId + ".");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Try again.");
        }
    }

    private static void removeChickenFlow(List<ChickenCoop> coops) {
        try {
            System.out.println("\n=== REMOVE CHICKEN ===");
            System.out.print("Enter chicken ID to remove: ");
            int chickenId = Integer.parseInt(SC.nextLine().trim());
            FindResult fr = findChicken(coops, chickenId);
            if (fr.chicken == null) {
                System.out.println("Chicken not found.");
                return;
            }
            fr.coop.remove(chickenId);
            saveToJson(coops);
            System.out.println("Chicken ID " + chickenId + " removed from Coop #" + fr.coop.getId() + ".");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Try again.");
        }
    }

    private static void editChickenFlow(List<ChickenCoop> coops) {
        try {
            System.out.println("\n=== EDIT CHICKEN ===");
            System.out.print("Enter chicken ID to edit: ");
            int chickenId = Integer.parseInt(SC.nextLine().trim());
            FindResult fr = findChicken(coops, chickenId);
            if (fr.chicken == null) {
                System.out.println("Chicken not found.");
                return;
            }
            Chicken ch = fr.chicken;

            System.out.println("\nEditing Chicken (ID=" + ch.getId() + ")");
            System.out.println("Current: Name=" + ch.getName() + ", Color=" + ch.getColor() +
                    ", Age=" + ch.getAge() + ", Molting=" + ch.isMolting());
            System.out.println("\nSelect field to edit:");
            System.out.println("1. Name");
            System.out.println("2. Color");
            System.out.println("3. Age");
            System.out.println("4. Molting status");
            System.out.println("5. Move to another coop");
            System.out.println("6. Cancel");
            System.out.print("Option: ");
            String choice = SC.nextLine().trim();

            switch (choice) {
                case "1" -> { System.out.print("New name: "); ch.setName(SC.nextLine().trim()); }
                case "2" -> { System.out.print("New color: "); ch.setColor(SC.nextLine().trim()); }
                case "3" -> { System.out.print("New age: "); ch.setAge(Integer.parseInt(SC.nextLine().trim())); }
                case "4" -> { System.out.print("Is it molting? (y/n): "); ch.setMolting("y".equalsIgnoreCase(SC.nextLine().trim())); }
                case "5" -> {
                    System.out.print("Enter target coop ID: ");
                    int newCoopId = Integer.parseInt(SC.nextLine().trim());
                    ChickenCoop target = findCoop(coops, newCoopId);
                    if (target == null) { System.out.println("Target coop not found."); return; }
                    fr.coop.remove(ch.getId());
                    target.add(ch);
                    System.out.println("Chicken moved to Coop #" + newCoopId + ".");
                }
                case "6" -> { System.out.println("Edit cancelled."); return; }
                default -> { System.out.println("Invalid option."); return; }
            }

            saveToJson(coops);
            System.out.println("Changes saved.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Try again.");
        }
    }

    // -------- HELPERS ----------
    private static boolean coopIdExists(List<ChickenCoop> coops, int coopId) {
        return findCoop(coops, coopId) != null;
    }

    private static ChickenCoop findCoop(List<ChickenCoop> coops, int coopId) {
        for (ChickenCoop cp : coops) if (cp.getId() == coopId) return cp;
        return null;
    }

    private static int nextChickenId(List<ChickenCoop> coops) {
        int max = 0;
        for (ChickenCoop cp : coops)
            for (Chicken c : cp.getChickens())
                if (c.getId() > max) max = c.getId();
        return max + 1;
    }

    private static FindResult findChicken(List<ChickenCoop> coops, int chickenId) {
        for (ChickenCoop cp : coops) {
            for (Chicken c : cp.getChickens()) {
                if (c.getId() == chickenId) return new FindResult(cp, c);
            }
        }
        return new FindResult(null, null);
    }

    private record FindResult(ChickenCoop coop, Chicken chicken) {}

    // -------- JSON I/O (sin librerías) ----------
    private static void saveToJson(List<ChickenCoop> coops) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < coops.size(); i++) {
            ChickenCoop coop = coops.get(i);
            sb.append("{\"id\":").append(coop.getId()).append(",\"chickens\":[");
            for (int j = 0; j < coop.getChickens().size(); j++) {
                Chicken c = coop.getChickens().get(j);
                sb.append("{")
                  .append("\"id\":").append(c.getId()).append(",")
                  .append("\"name\":\"").append(escape(c.getName())).append("\",")
                  .append("\"color\":\"").append(escape(c.getColor())).append("\",")
                  .append("\"age\":").append(c.getAge()).append(",")
                  .append("\"is_molting\":").append(c.isMolting())
                  .append("}");
                if (j < coop.getChickens().size() - 1) sb.append(",");
            }
            sb.append("]}");
            if (i < coops.size() - 1) sb.append(",");
        }
        sb.append("]");

        try {
            Files.writeString(DATA_FILE, sb.toString(), StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Failed to save JSON: " + e.getMessage());
        }
    }

    private static List<ChickenCoop> loadFromJson() {
        if (!Files.exists(DATA_FILE)) return new ArrayList<>();
        String content;
        try { content = Files.readString(DATA_FILE, StandardCharsets.UTF_8); }
        catch (IOException e) { return new ArrayList<>(); }
        if (content == null || content.isBlank()) return new ArrayList<>();

        List<ChickenCoop> result = new ArrayList<>();

        Pattern coopPat = Pattern.compile("\\{\\s*\"id\"\\s*:\\s*(\\d+)\\s*,\\s*\"chickens\"\\s*:\\s*\\[(.*?)\\]\\s*\\}",
                Pattern.DOTALL);
        Matcher coopMatcher = coopPat.matcher(content);
        while (coopMatcher.find()) {
            int coopId = Integer.parseInt(coopMatcher.group(1));
            String chickensRaw = coopMatcher.group(2);
            ChickenCoop coop = new ChickenCoop(coopId);

            Pattern chPat = Pattern.compile(
                "\\{\\s*\"id\"\\s*:\\s*(\\d+)\\s*,\\s*\"name\"\\s*:\\s*\"(.*?)\"\\s*,\\s*\"color\"\\s*:\\s*\"(.*?)\"\\s*,\\s*\"age\"\\s*:\\s*(\\d+)\\s*,\\s*\"is_molting\"\\s*:\\s*(true|false)\\s*\\}");
            Matcher chMatcher = chPat.matcher(chickensRaw);
            while (chMatcher.find()) {
                int id = Integer.parseInt(chMatcher.group(1));
                String name = unescape(chMatcher.group(2));
                String color = unescape(chMatcher.group(3));
                int age = Integer.parseInt(chMatcher.group(4));
                boolean molting = Boolean.parseBoolean(chMatcher.group(5));
                coop.add(new Chicken(id, name, color, age, molting));
            }
            result.add(coop);
        }
        return result;
    }

    private static String escape(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
    private static String unescape(String s) {
        return s.replace("\\\"", "\"").replace("\\\\", "\\");
    }
}
