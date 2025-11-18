package ec.edu.espe.flashdrive.model;

/**
 *
 * @author César Vargas, Paradigm, @ESPE
 */

import java.util.ArrayList;
import java.util.List;

  public class Storage {
    
    public static List<FlashDrive> allStorageDevices = new ArrayList<>();

    public static void addDevice(FlashDrive device) {
        allStorageDevices.add(device);
    }

    public static void setAllDevices(List<FlashDrive> newList) {
        allStorageDevices = newList;
    }
}
