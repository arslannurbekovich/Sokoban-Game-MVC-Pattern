import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import java.io.File;
class Sound  {
    private String  file;
    private Sequencer midi;
    public Sound() {
        file = "music/sokoban.mid";
    }
    public void loadSound() {
        try {
            Sequence seq = MidiSystem.getSequence(new File(file));
            midi = MidiSystem.getSequencer();
            midi.open();
            midi.setSequence(seq);
            midi.start();
            midi.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
        }
        catch (Exception exc) {
            System.out.println("Not Found Sound File " + exc);
        }
    }
    void myStop(){
        midi.stop();
        midi.close();
    }
}
