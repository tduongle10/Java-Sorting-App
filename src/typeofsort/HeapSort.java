package typeofsort;

import java.util.ArrayList;

import element.SetOfElements;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;

public class HeapSort extends Sort{
	private static final Color ROOT = Color.CRIMSON;
	public HeapSort() {
		// TODO Auto-generated constructor stub
		super();
	}
	private void heapify(SetOfElements set,int n, int i) {
		int largest = i;
		int l = 2*i +1;
		int r = 2*i +2;
		transitions.add(set.changeSomeColor(ROOT, i));
		if(l < n && set.getSetOfElements()[l].compare(set.getSetOfElements()[largest]) == 1)
			largest = l;
		if(r < n && set.getSetOfElements()[r].compare(set.getSetOfElements()[largest]) ==1 )
			largest = r;
		if(largest != i) {
			transitions.add(set.swapTwoElements(i, largest));
			heapify(set,n,largest);
		}
		transitions.add(set.changeSomeColor(INITIAL, i));
	}
	
	private void heapSort(SetOfElements set, int n) {
		for(int i = n/2 - 1;i>=0;i--) {
			heapify(set,n,i);
		}
		for(int i = n-1;i>=0;i--) {
			transitions.add(set.changeSomeColor(SELECTED,0,i));
			transitions.add(set.swapTwoElements(0, i));
			transitions.add(set.changeSomeColor(INITIAL, 0));
			transitions.add(set.changeSomeColor(SORTED, i));
			heapify(set,i,0);
		}
		transitions.add(set.changeSomeColor(SORTED, 0));
	}
	public ArrayList<Transition> sortOn(SetOfElements set){
		heapSort(set,set.getSetOfElements().length);
		return this.transitions;
	}
}
