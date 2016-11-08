package code;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RandomSentence extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5872688777389046656L;
	
	/**
	 * Sentence Structure: [determiner] [adjective] [noun] [adverb] [verb] [determiner] [adjective] [noun]
	 */

	/***************/
	/** VARIABLES **/
	/***************/
	private ArrayList<CLabel> _labels;
	private JPanel _top;
	private JPanel _bottom;
	private Color _c1 = Color.YELLOW;
	private Color _c2 = Color.BLUE;
	private boolean _alt = true;
	private enum Grammar {DETERMINER, ADJECTIVE, NOUN, ADVERB, VERB}
	private Grammar[] _struct = new Grammar[]{Grammar.DETERMINER, Grammar.ADJECTIVE, Grammar.NOUN, Grammar.ADVERB, Grammar.VERB, Grammar.DETERMINER, Grammar.ADJECTIVE, Grammar.NOUN};
	
	/**
	 * constructor
	 */
	public RandomSentence(){
		super("William Pritchard's Lab 9");
		
		// we have to initiate the _labels ArrayList
		_labels = new ArrayList<CLabel>();
		
		// Configures components and adds them to the seperate JPanels
		configureComponents();
		
		// start with random sentence
		newSentence();
		
		// adds the JPanels to the frame and sets all the properties of the window
		configureFrame();
	}
	
	/**
	 * Configures all the small components for the panels
	 */
	private void configureComponents(){
		// Create new instances for the JPanels
		_top = new JPanel();
		_bottom = new JPanel();
		
		// configure Jpanel with flow layouts
		_top.setLayout(new FlowLayout());
		_bottom.setLayout(new BorderLayout());
		
		// this for loop will run 8 times so this is when we create the special CLabels
		// also perfect becuase it will use _struct to determine FileReader as well
		for(int x = 0; x < 8; x++){
			// adds new CLabel instance to labels with an alternating color and next grammar part
			_labels.add(new CLabel(getAltColor(), getOppositeFore(), getFileReader(_struct[x])));
		}
		
		// Now we iterate through the labels array to add each item to the top JPanel with enhanced for loop
		for(CLabel c : _labels){
			_top.add(c);
		}
		
		// Because the JButton isn't really necessary to be stored as an instance variable, we can just make it an local variable
		JButton button = new JButton("Random sentence");
		
		// Theres no need to create a whole new ActionListener class for 1 method to be called
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				newSentence();
			}
			
		});
		
		// add button to _bottom
		this._bottom.add(button, BorderLayout.WEST);
	}

	/**
	 * adds the JPanels to the frame and sets all the properties of the window
	 */
	private void configureFrame(){
		// Organize the panels in JFrame
		this.setLayout(new BorderLayout());
		this.add(this._top, BorderLayout.NORTH);
		this.add(this._bottom, BorderLayout.SOUTH);
		
		
		
		// frame defaults
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	/**
	 * @return Alternating Colors
	 */
	private Color getAltColor(){
		// since color is irrelevant, we can switch the alt boolean from the start
		// the if statement returns 1 of two colors: blue or yellow
		_alt = !_alt;
		if(_alt){
			return _c1;
		} else {
			return _c2;
		}
	}
	
	/**
	 * return opposite color to the above method
	 */
	private Color getOppositeFore(){
		if(_alt){
			return _c2;
		} else {
			return _c1;
		}
	}
	
	/**
	 * @param Grammar part
	 * @return new FileReader 
	 */
	private FileReader getFileReader(Grammar g){
		// Grammar g is an enum so we can switch through the values and return the correct FileReader and file
		switch(g){
		case DETERMINER:
			return new FileReader("determiners.txt");
		case VERB:
			return new FileReader("verbs.txt");
		case ADJECTIVE:
			return new FileReader("adjectives.txt");
		case NOUN:
			return new FileReader("nouns.txt");
		case ADVERB:
			return new FileReader("adverbs.txt");
		default:
			// the default is run in case the method is called with an incorrect parameter.  A NullPointerException will be thrown
			return null;
		}
	}
	
	/**
	 * makes each CLabel randomize its value according to the FileReader file
	 */
	public void newSentence(){
		for(CLabel c : _labels){
			c.doNext();
		}
		this.pack();
	}

}
