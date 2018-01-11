package nl.rene;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String... args) throws IOException {
        /*Player player = new Player();
        player.play("V0 I[Piano] Eq Ch. | Eq Ch. | Dq Eq Dq Cq   V1 I[Flute] Rw | Rw | GmajQQQ CmajQ");
        Pattern pattern = new ChordProgression("I IV V")
                .distribute("7%6")
                .allChordsAs("$0 $0 $0 $0 $1 $1 $0 $0 $2 $1 $0 $0")
                .eachChordAs("$0ia100 $1ia80 $2ia80 $3ia80 $4ia100 $3ia80 $2ia80 $1ia80")
                .getPattern()
                .setInstrument("Acoustic_Bass")
                .setTempo(100);
        new Player().play(pattern);


        Rhythm rhythm = new Rhythm()
                .addLayer("O..oO...O..oOO..")
                .addLayer("..S...S...S...S.")
                .addLayer("````````````````")
                .addLayer("...............+");
        new Player().play(rhythm.getPattern().repeat(2));
        */

        //new Player().play(new ChordProgression("I IV vi V").eachChordAs("$!i $!i Ri $!i"), new Rhythm().addLayer("..X...X...X...XO"));
        final Map<String, String> instruments = new HashMap<String, String>();
        instruments.put("I1", "piano");
        instruments.put("I56", "horn");
        instruments.put("I24", "guitar");
        instruments.put("I32", "bass");
        instruments.put("I40", "string");
        instruments.put("I48", "ensemble");
        String[] notes = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
        String[] octaves = {"2", "3", "4", "5", "6", "7"};
        String[] durations = {"w", "h", "q", "i", "s", "t", "x", "o"};
        for (String instrument : instruments.keySet()) {
            for (String note : notes) {
                for (String octave : octaves) {
                    for (String duration : durations) {
                        final String snd = String.format("%s %s%s%s", instrument, note, octave, duration);
                        new File("out/" + instruments.get(instrument)).mkdir();
                        final String fullPath = "out/" + instruments.get(instrument) + "/" + String.format("%s%s%s", note, octave, duration);
                        final File file = new File(fullPath + ".mid");
                        MidiFileManager.savePatternToMidi(new Pattern(snd), file);
                        Runtime.getRuntime().exec("./conv.sh " + fullPath);
                    }
                }
            }
        }
    }
}
