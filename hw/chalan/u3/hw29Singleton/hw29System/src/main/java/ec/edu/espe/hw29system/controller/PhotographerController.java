package ec.edu.espe.hw29system.controller;

import ec.edu.espe.hw29system.model.Photographer;

import utils.*;

public class PhotographerController {

    public void register(String name,
                         String specialty,
                         String experienceText,
                         String rateText) {

        int experience;
        double hourlyRate;

        try {
            experience = Integer.parseInt(experienceText);
            hourlyRate = Double.parseDouble(rateText);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Experience and rate must be numeric");
        }

        Photographer photographer = new Photographer(
                name,
                specialty,
                experience,
                hourlyRate
        );

        JsonFileUtil.getInstance().save(photographer);
        MongoDBUtil.getInstance().save(photographer);
    }
}
