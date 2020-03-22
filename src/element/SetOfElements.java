package element;

import java.util.Random;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class SetOfElements {
	Element[] setOfElements;
	public static final int GAP = 5;
	public Element[] getSetOfElements() {
		return setOfElements;
	}
	public SetOfElements(int n) {
		// TODO Auto-generated constructor stub
		this.setOfElements = new Element[n];
		this.randomize();
	}
	public void randomize() {
		Random rand = new Random();
		for(int i = 0;i < this.setOfElements.length;i++) {
			this.setOfElements[i] = new Element(rand.nextInt(Element.MAX_VALUE)+1);
			this.setOfElements[i].setLayoutX(i*(GAP+Element.WIDTH));
		}
	}
	public ParallelTransition changeSomeColor(Color color,int... a) {
		ParallelTransition parallel = new ParallelTransition();
		
		for(int i = 0; i < a.length;i++) {
			FillTransition fill = new FillTransition();
			fill.setShape(this.setOfElements[a[i]].getRec());
			fill.setToValue(color);
			fill.setDuration(Duration.millis(500));
			parallel.getChildren().add(fill);
		}
		return parallel;
	}
	
	public ParallelTransition swapTwoElements(int i, int j) {
		ParallelTransition transition = new ParallelTransition();
		transition.getChildren().addAll(this.setOfElements[i].moveElement((j-i)*(Element.WIDTH+GAP)),
				this.setOfElements[j].moveElement(-(j-i)*(Element.WIDTH+GAP)));
		Element temp;
		temp = this.setOfElements[i];
		this.setOfElements[i] = this.setOfElements[j];
		this.setOfElements[j] = temp;
		return transition;
	}
	
	public ParallelTransition changeAllColors(Color color) {
		ParallelTransition parallel = new ParallelTransition();
		
		for(int i = 0; i < this.setOfElements.length;i++) {
			FillTransition fill = new FillTransition();
			fill.setShape(this.setOfElements[i].getRec());
			fill.setToValue(color);
			fill.setDuration(Duration.millis(500));
			parallel.getChildren().add(fill);
		}
		return parallel;
	}
	
}
