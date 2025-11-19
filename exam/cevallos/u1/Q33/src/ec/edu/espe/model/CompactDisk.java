/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ec.edu.espe.model;

/**
 *
 * @author Mateo Cevallos, Object Masters, @ESPE
 */
public class CompactDisk {
    private int id;
    private String title;
    private String year;
    private String artist;

    public CompactDisk(int id, String title, String year, String artist) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.artist = artist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "CompactDisk{" + "id=" + id + ", title=" + title + ", year=" + year + ", artist=" + artist + '}';
    }
      
}

