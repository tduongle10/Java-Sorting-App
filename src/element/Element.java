package element;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Element extends StackPane{
	public final static int MAX_VALUE = 100;
	public final static int WIDTH = 50;
	public final static int HEIGHT = 50;
	private int value;
	private Rectangle rec;
	private Label la;
	public Rectangle getRec() {
		return rec;
	}
	public Element(int value) {
		// TODO Auto-generated constructor stub
		this.value = value;
		this.la = new Label();
		this.rec = new Rectangle(WIDTH,HEIGHT,Color.WHITE);
		this.rec.setArcHeight(15);
		this.rec.setArcWidth(15);
		this.la.setText(Integer.toString(value));
		this.la.setFont(new Font("Helvetica",15));
		this.la.setStyle("-fx-font-weight: bold");
		this.la.setTextFill(Color.DARKSLATEGRAY);
		this.getChildren().add(rec);
		this.getChildren().add(la);
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public TranslateTransition moveElement(int x) {
		TranslateTransition transition = new TranslateTransition();
		transition.setNode(this);
		transition.setDuration(Duration.millis(500));
		transition.setByX(x);
		return transition;
	}
	public TranslateTransition moveElementVertically(int y) {
		TranslateTransition transition = new TranslateTransition();
		transition.setNode(this);
		transition.setDuration(Duration.millis(500));
		transition.setByY(y);
		return transition;
	}
	public int compare(Element element) {
		if(this.value > element.value) return 1;
		else if(this.value < element.value) return -1;
		else return 0;
	}
}
