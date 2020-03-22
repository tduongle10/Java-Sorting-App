package typeofsort;

import java.util.ArrayList;

import element.Element;
import element.SetOfElements;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;

public class QuickSort extends Sort{
	private static final Color PIVOT = Color.CRIMSON;
	public QuickSort() {
		// TODO Auto-generated constructor stub
		super();
	}
	private int partition(SetOfElements set, int begin, int end) {
		transitions.add(set.changeSomeColor(PIVOT,end));
		Element pivot = set.getSetOfElements()[end];
		int i = begin-1;
		for(int j = begin; j < end ;j++) {
			transitions.add(set.changeSomeColor(SELECTED,j));
			if(set.getSetOfElements()[j].compare(pivot) !=1) {
				i++;
				transitions.add(set.swapTwoElements(i, j));
			} else {
				transitions.add(set.changeSomeColor(INITIAL,j));
			}
		}
		transitions.add(set.swapTwoElements(i+1, end));
		transitions.add(set.changeSomeColor(SORTED, i+1));
		ParallelTransition parallel = new ParallelTransition();
		for(int k = begin;k<=i;k++) {
			parallel.getChildren().add(set.changeSomeColor(INITIAL,k));
		}
		transitions.add(parallel);
		return i+1;
	}
	private void quickSort(SetOfElements set, int begin, int end) {
		if(begin<end) {
			int partition = partition(set,begin,end);
			quickSort(set,begin,partition-1);
			quickSort(set,partition+1,end);
		}
	}
	public ArrayList<Transition> sortOn(SetOfElements set){
		quickSort(set,0,set.getSetOfElements().length-1);
		transitions.add(set.changeAllColors(SORTED));
		return this.transitions;
	}

}
