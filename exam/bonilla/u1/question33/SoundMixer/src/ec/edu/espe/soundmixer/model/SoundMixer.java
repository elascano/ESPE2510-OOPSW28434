package ec.edu.espe.soundmixer.model;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public class SoundMixer {
    private int id;
    private String name;
    private float volume;
    private float blass;

    public SoundMixer(int id, String name, float volume, float blass) {
        this.id = id;
        this.name = name;
        this.volume = volume;
        this.blass = blass;
    }

}
