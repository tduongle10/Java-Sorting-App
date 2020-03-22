package typeofsort;

import java.util.ArrayList;

import element.SetOfElements;
import javafx.animation.Transition;
import javafx.scene.paint.Color;

public abstract class Sort {
	public final Color INITIAL = Color.WHITE;
	public final Color SELECTED = Color.SKYBLUE;
	public final Color SORTED = Color.AQUAMARINE;
	
	public ArrayList<Transition> transitions;
	
	public Sort() {
		this.transitions = new ArrayList<>();
	}

	public abstract ArrayList<Transition> sortOn(SetOfElements set);
}
