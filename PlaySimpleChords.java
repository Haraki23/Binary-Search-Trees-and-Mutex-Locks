package assignment1;
import algs31.*;
import stdlib.*;
public class PlaySimpleChords {

public static BinarySearchST<String, Double> createTable() {
	BinarySearchST<String, Double> ST = new BinarySearchST<String, Double>();
	StdIn.fromFile("data/notes_frequencies.txt");
	String note = null;
	double freq = 0;
	while (StdIn.hasNextLine()) {
		String line = StdIn.readLine();
		String[] fields = line.split("\\s+");
		note = fields[0];
		freq = Double.parseDouble(fields[1]);
		ST.put(note, freq);
	}
	return ST;
}
public static void playChord(double duration, double... frequencies) {
	final int sliceCount = (int) (StdAudio.SAMPLE_RATE * duration);
	final double[] slices = new double[sliceCount+1];
	for (int i = 0; i <= sliceCount; i++) {
		for (double frequency: frequencies) {
			slices[i] += Math.sin(2 * Math.PI * i * frequency / StdAudio.SAMPLE_RATE);
		}
		slices[i] /= frequencies.length;
	}
	StdAudio.play(slices);
}

public static void playfile(BinarySearchST<String, Double> ST) {
	StdIn.fromFile("data/sample_simple_chords.txt");
	Double duration;
	String chord_1 = null;
	String chord_2 = null;
	while (StdIn.hasNextLine()) {
		String line = StdIn.readLine();
		String[] fields = line.split("\\s+");
		chord_1 = fields[0];
		chord_2 = fields[1];
		duration = Double.parseDouble(fields[2]);
		Double freq_1 = ST.get(chord_1);
		Double freq_2 = ST.get(chord_2);
		System.out.println("Playing Chord " + chord_1 + " and " + chord_2);
		playChord(duration, freq_1, freq_2);
		//StdOut.println(chord_1 + "   " + chord_2+" " + duration);
		
	}
}
public static void main(String[] args) {
	playfile(createTable());
	
}
}
