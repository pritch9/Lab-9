package code;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JLabel;

public class CLabel extends JLabel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FileReader _f;
	private ArrayList<String> _words;
	
	public CLabel(Color b, Color t, FileReader f){
		this.setBackground(b);
		this.setForeground(t);
		this.setOpaque(true);
		this.setFont(new Font("Trebuchet MS", Font.BOLD, 36));
		this._f = f;
		this._words = new ArrayList<String>();
		setup();
	}
	
	/**
	 * adds all the words in the text file to the _words ArrayList
	 */
	public void setup(){
		// while loop only runs as long as there is another word in the FileReader file
		while(this._f.hasNext()){
			// adds all the words to the ArrayList _words
			_words.add(this._f.next());
		}
	}
	
	/**
	 * finds and sets the next value for the JLabel
	 */
	public void doNext(){
		Collections.shuffle(this._words);
		this.setText(this._words.get(0));
	}

}
