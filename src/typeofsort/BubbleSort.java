package typeofsort;

import java.util.ArrayList;

import element.SetOfElements;
import javafx.animation.Transition;

public class BubbleSort extends Sort{
	public BubbleSort() {
		// TODO Auto-generated constructor stub
		super();
	}
	@Override
	public ArrayList<Transition> sortOn(SetOfElements set){
		for(int i = 0;i < set.getSetOfElements().length;i++) {
			for(int j=1;j < set.getSetOfElements().length-i;j++) {
				transitions.add(set.changeSomeColor(SELECTED,j-1,j));
				if(set.getSetOfElements()[j-1].compare(set.getSetOfElements()[j])==1) {
					this.transitions.add(set.swapTwoElements(j-1,j));					
				}
				transitions.add(set.changeSomeColor(INITIAL,j-1,j));
			}
			transitions.add(set.changeSomeColor(SORTED,set.getSetOfElements().length-i-1));
		}
		return this.transitions;
	}

}
