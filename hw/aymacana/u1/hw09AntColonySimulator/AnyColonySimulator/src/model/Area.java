package model;

/**
 *
 * @author Mateo Aymacaña @ESPE T.A.P(The Art of Programming)DELL
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Area {

    private final int width;
    private final int height;
    private final int initNumberOfFoodPiles;
    private final int tickDuration; // ms, for controller usage
    private final Cell[][] grid;
    private final List<Colony> colonies;
    private final List<AntEater> antEaters;
    private final List<FoodPile> foodPiles;
    private final List<PheromoneDrop> pheromones;
    private int globalTick = 0;
    private final Random R = new Random();

    public Area(int width, int height, int initNumberOfFoodPiles, int tickDuration) {
        this.width = width;
        this.height = height;
        this.initNumberOfFoodPiles = initNumberOfFoodPiles;
        this.tickDuration = tickDuration;
        this.grid = new Cell[height][width];
        this.colonies = new ArrayList<>();
        this.antEaters = new ArrayList<>();
        this.foodPiles = new ArrayList<>();
        this.pheromones = new ArrayList<>();
        // initialize cells
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                grid[r][c] = new Cell(r, c);
            }
        }
    }

    public void setupRandomPiles() {
        for (int i = 0; i < initNumberOfFoodPiles; i++) {
            int r = R.nextInt(height);
            int c = R.nextInt(width);
            Cell cell = getCell(r, c);
            if (cell.getFoodPile() == null) {
                FoodPile fp = new FoodPile(10 + R.nextInt(90), cell);
                cell.setFoodPile(fp);
                foodPiles.add(fp);
            }
        }
    }

    public void replenishFoodPiles() {
        if (globalTick % 30 == 0) {
            System.out.println("=== Replenishing Food (Tick: " + globalTick + ") ===");
            int replenished = 0;
            int created = 0;

            for (FoodPile fp : new ArrayList<>(foodPiles)) {
                if (fp.getPileAmount() < 50) {
                    int addAmount = 40 + R.nextInt(60);
                    fp.setPileAmount(fp.getPileAmount() + addAmount);
                    replenished++;
                    System.out.println("Spare pile: " + fp.getPosition() + " now has " + fp.getPileAmount() + "mg");
                }
            }

            if (foodPiles.size() < 20 || R.nextDouble() < 0.6) {
                int attempts = 0;
                while (attempts < 8 && created < 3) {
                    int r = R.nextInt(height);
                    int c = R.nextInt(width);
                    Cell cell = getCell(r, c);
                    if (cell != null && cell.getFoodPile() == null) {
                        FoodPile newPile = new FoodPile(50 + R.nextInt(100), cell);
                        cell.setFoodPile(newPile);
                        foodPiles.add(newPile);
                        created++;
                        System.out.println("New amount created: " + cell + " with " + newPile.getPileAmount() + "mg");
                    }
                    attempts++;
                }
            }

            System.out.println("Summary: " + replenished + " lots of spare parts, " + created + " lots of spare parts");
            System.out.println("Total active heaps: " + foodPiles.size());
        }
    }

    public Cell getCell(int row, int col) {
        if (row < 0 || row >= height || col < 0 || col >= width) {
            return null;
        }
        return grid[row][col];
    }

    public void addColony(Colony c) {
        if (c != null) {
            colonies.add(c);
        }
    }

    public void addAntEater(AntEater ae) {
        if (ae != null) {
            antEaters.add(ae);
        }
    }

    public void update() {
        globalTick++;

        replenishFoodPiles();

        for (Colony col : new ArrayList<>(colonies)) {
            col.tickSpawn();
        }

        for (Colony col : colonies) {
            List<Ant> copy = new ArrayList<>(col.getAnts());
            for (Ant a : copy) {
                a.tick(this);
            }
        }

        for (AntEater ae : new ArrayList<>(antEaters)) {
            ae.tick(this);
        }

        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                Cell cell = grid[r][c];
                PheromoneDrop p = cell.getPheromone();
                if (p != null) {
                    p.tick();
                    if (p.getLevel() <= 0) {
                        cell.setPheromone(null);
                    }
                }
            }
        }

        List<FoodPile> toRemove = new ArrayList<>();
        for (FoodPile fp : foodPiles) {
            if (fp.getPileAmount() <= 0) {
                toRemove.add(fp);
            }
        }
        for (FoodPile fp : toRemove) {
            if (fp.getPosition() != null) {
                fp.getPosition().setFoodPile(null);
            }
            foodPiles.remove(fp);
        }
    }

    public Cell getRandomNeighbor(Cell pos, int preferRow, int preferCol) {
        if (pos == null) {
            return null;
        }
        int r = pos.getRow();
        int c = pos.getCol();
        List<Cell> neighbors = new ArrayList<>();
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) {
                    continue;
                }
                Cell nb = getCell(r + dr, c + dc);
                if (nb != null) {
                    neighbors.add(nb);
                }
            }
        }
        if (neighbors.isEmpty()) {
            return null;
        }
        for (Cell nb : neighbors) {
            if (nb.getRow() - r == preferRow && nb.getCol() - c == preferCol) {
                return nb;
            }
        }
        return neighbors.get(R.nextInt(neighbors.size()));
    }

    public Cell findNeighborWithLowestNonZeroPheromone(Cell pos) {
        if (pos == null) {
            return null;
        }
        Cell best = null;
        int bestLevel = Integer.MAX_VALUE;
        int r = pos.getRow(), c = pos.getCol();
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) {
                    continue;
                }
                Cell nb = getCell(r + dr, c + dc);
                if (nb != null && nb.getPheromone() != null && nb.getPheromone().getLevel() > 0) {
                    int lvl = nb.getPheromone().getLevel();
                    if (lvl < bestLevel) {
                        bestLevel = lvl;
                        best = nb;
                    }
                }
            }
        }
        return best;
    }

    public Cell findNeighborWithAnyPheromone(Cell pos) {
        if (pos == null) {
            return null;
        }
        int r = pos.getRow(), c = pos.getCol();
        List<Cell> candidates = new ArrayList<>();
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) {
                    continue;
                }
                Cell nb = getCell(r + dr, c + dc);
                if (nb != null && nb.getPheromone() != null && nb.getPheromone().getLevel() > 0) {
                    candidates.add(nb);
                }
            }
        }
        if (candidates.isEmpty()) {
            return null;
        }
        return candidates.get(R.nextInt(candidates.size()));
    }

    // neighbor toward destination (simple greedy)
    public Cell getNeighborTowards(Cell from, Cell to) {
        if (from == null || to == null) {
            return null;
        }
        int dr = Integer.compare(to.getRow(), from.getRow());
        int dc = Integer.compare(to.getCol(), from.getCol());
        Cell candidate = getCell(from.getRow() + dr, from.getCol() + dc);
        if (candidate != null) {
            return candidate;
        }
        return getRandomNeighbor(from, dr, dc);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Colony> getColonies() {
        return colonies;
    }

    public List<AntEater> getAntEaters() {
        return antEaters;
    }

    public int getGlobalTick() {
        return globalTick;
    }

    public List<FoodPile> getFoodPiles() {
        return foodPiles;
    }

    @Override
    public String toString() {
        return "Area{" + width + "x" + height + ", tick=" + globalTick + "}";
    }
}
