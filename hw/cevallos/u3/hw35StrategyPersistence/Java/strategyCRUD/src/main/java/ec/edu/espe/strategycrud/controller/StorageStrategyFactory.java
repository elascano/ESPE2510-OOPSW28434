/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.strategycrud.controller;

import ec.edu.espe.strategycrud.model.StorageStrategy;
import ec.edu.espe.strategycrud.model.CsvStorageStrategy;
import ec.edu.espe.strategycrud.model.JsonStorageStrategy;
import ec.edu.espe.strategycrud.model.MongoStorageStrategy;

/**
 *
 * @author Mateo Cevallos
 */
public class StorageStrategyFactory {

    public enum StorageType {
        JSON, CSV, MONGODB
    }

    public static StorageStrategy createStrategy(StorageType type) {
        switch (type) {
            case JSON:
                return new JsonStorageStrategy();
            case CSV:
                return new CsvStorageStrategy();
            case MONGODB:
                return new MongoStorageStrategy();
            default:
                throw new IllegalArgumentException("Tipo de almacenamiento no válido");
        }
    }
}
