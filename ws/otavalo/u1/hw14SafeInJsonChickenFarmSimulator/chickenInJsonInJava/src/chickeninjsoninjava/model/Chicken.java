package chickeninjsoninjava.model;

/**
 *
 * @author Arelys
 */
public class Chicken {
    private static int nextId = 1;

    private String id;
    private String name;
    private String color;
    private int age;
    private boolean isMolting;

    public Chicken(String name, String color, int age, boolean isMolting) {
        this.id = String.valueOf(nextId);
        nextId++;
        this.name = name;
        this.color = color;
        this.age = age;
        this.isMolting = isMolting;
    }

    public Chicken(String id, String name, String color, int age, boolean isMolting) {
        if (id == null || id.isEmpty()) {
            this.id = String.valueOf(nextId);
        } else {
            this.id = id;
            try {
                int numericId = Integer.parseInt(id);
                if (numericId >= nextId) {
                    nextId = numericId + 1;
                }
            } catch (NumberFormatException e) {
                System.out.println(" Warning: Invalid ID '" + id + "', generating automatically.");
            }
        }

        this.name = name;
        this.color = color;
        this.age = age;
        this.isMolting = isMolting;
    }

    public static void setNextId(int value) {
        nextId = value;
    }

    public String cluck() {
        return name + " is clucking";
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getColor() { return color; }
    public int getAge() { return age; }
    public boolean isMolting() { return isMolting; }

    public void setName(String name) { this.name = name; }
    public void setColor(String color) { this.color = color; }
    public void setAge(int age) { this.age = age; }
    public void setMolting(boolean molting) { this.isMolting = molting; }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Color: %s | Age: %d | Molting: %s",
                id, name, color, age, isMolting ? "Yes" : "No");
    }

    public String toJson() {
        return String.format(
            "{\"id\":\"%s\",\"name\":\"%s\",\"color\":\"%s\",\"age\":%d,\"isMolting\":%s}",
            id, name, color, age, isMolting
        );
    }

    public static Chicken fromJson(String jsonLine) {
        if (jsonLine == null || jsonLine.isBlank()) return null;
        jsonLine = jsonLine.trim().replace("{", "").replace("}", "").replace("\"", "");
        if (jsonLine.isBlank()) return null;

        String[] parts = jsonLine.split(",");
        String id = "", name = "", color = "";
        int age = 0;
        boolean isMolting = false;

        for (String p : parts) {
            String[] kv = p.split(":");
            if (kv.length != 2) continue;
            String key = kv[0].trim();
            String value = kv[1].trim();
            switch (key) {
                case "id": id = value; break;
                case "name": name = value; break;
                case "color": color = value; break;
                case "age":
                    try {
                        age = Integer.parseInt(value);
                    } catch (NumberFormatException e) {
                        age = 0;
                    }
                    break;
                case "isMolting": isMolting = Boolean.parseBoolean(value); break;
            }
        }
        return new Chicken(id, name, color, age, isMolting);
    }
}
