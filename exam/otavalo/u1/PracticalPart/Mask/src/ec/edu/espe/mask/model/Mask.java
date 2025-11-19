package ec.edu.espe.mask.model;
import java.util.List;

/**
 *
 * @author Arelys Otavalo, The POOwer Rangers of Programming, @ESPE
 */

public class Mask {
    private int maskId;
    private String maskName;
    private List<Double> pricesList;
    private double averageGrade;

    public Mask(int maskId, String maskName, List<Double> gradesList) {
        this.maskId = maskId;
        this.maskName = maskName;
        this.pricesList = gradesList;
        calculateAverage();
    }

    public void calculateAverage() {
        if (pricesList == null || pricesList.isEmpty()) {
            this.averageGrade = 0;
        } else {
            double sum = 0;
            for (double g : pricesList) sum += g;
            this.averageGrade = sum / pricesList.size();
        }
    }

    public int getMaskId() {
        return maskId;
    }

    public void setMaskId(int maskId) {
        this.maskId = maskId;
    }

    public String getMaskName() {
        return maskName;
    }

    public void setMaskName(String maskName) {
        this.maskName = maskName;
    }

    public List<Double> getPricesList() {
        return pricesList;
    }

    public void setPricesList(List<Double> pricesList) {
        this.pricesList = pricesList;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

   
}
