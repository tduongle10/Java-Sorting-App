package typeofsort;

import java.util.ArrayList;

import element.Element;
import element.SetOfElements;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;

public class RadixSort extends Sort{
	public RadixSort() {
		// TODO Auto-generated constructor stub
		super();
	}
	private int getMax(SetOfElements set,int n) {
		int max = set.getSetOfElements()[0].getValue();
		for(int i = 1;i < n; i++) {
			if(set.getSetOfElements()[i].getValue() > max)
				max = set.getSetOfElements()[i].getValue();
		}
		return max;
	}
	private void countSort(SetOfElements set, int n, int exp) {
		Element[] output = new Element[n];
		int[] count = new int[10];
		for(int i =0 ;i<10;i++) {
			count[i] = 0;
		}
		for(int i=0;i<n;i++)
			count[(set.getSetOfElements()[i].getValue()/exp)%10]++;
		for(int i = 1;i<10;i++)
			count[i] += count[i-1];
		for(int i = n-1 ;i>=0;i--) {
			this.transitions.add(set.getSetOfElements()[i].moveElementVertically(100));
			this.transitions.add(set.getSetOfElements()[i].moveElement((count[set.getSetOfElements()[i].getValue()/exp%10]-1-i)*(Element.WIDTH+SetOfElements.GAP)));
			output[count[(set.getSetOfElements()[i].getValue()/exp)%10]-1] = set.getSetOfElements()[i];
			count[(set.getSetOfElements()[i].getValue()/exp)%10]--;
		}
		for(int i = 0;i<n;i++)
			set.getSetOfElements()[i] = output[i];
	}
	public ArrayList<Transition> sortOn(SetOfElements set){
		int max = getMax(set,set.getSetOfElements().length);
		int mark=0;
		for(int exp = 1;max/exp >0;exp *=10) {
			countSort(set, set.getSetOfElements().length, exp);
			mark++;
		}
		ParallelTransition parallel = new ParallelTransition();
		for(int i =0;i< set.getSetOfElements().length;i++)
			parallel.getChildren().add(set.getSetOfElements()[i].moveElementVertically(-100*mark));
		this.transitions.add(parallel);
		this.transitions.add(set.changeAllColors(SORTED));
		return this.transitions;
	}
}
