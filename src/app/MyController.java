package app;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import element.SetOfElements;
import javafx.animation.SequentialTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import typeofsort.BubbleSort;
import typeofsort.HeapSort;
import typeofsort.QuickSort;
import typeofsort.RadixSort;
import typeofsort.Sort;
 
public class MyController implements Initializable {
	
	public final static int NUMBER_OF_ELEMENTS = 12;
	
	@FXML
	private Pane masterPane;
	@FXML
	private Pane displayPane;
	@FXML
	private Button sortButton;
	@FXML
	private Button randomButton;
	@FXML
	private ChoiceBox<String> choiceBox;
	
	private SetOfElements set;
	private Sort sort;
	ArrayList<Sort> sorts = new ArrayList<>();
   @Override
   public void initialize(URL location, ResourceBundle resources) {
	   set = new SetOfElements(NUMBER_OF_ELEMENTS); 
	   displayPane.getChildren().addAll(set.getSetOfElements());
	   masterPane.setStyle("-fx-background-color:#DCDCDC");
	   choiceBox.setItems(FXCollections.observableArrayList("Bubble Sort","Quick Sort","Heap Sort","Radix Sort"));
	   choiceBox.getSelectionModel().selectFirst();
   }
   public void doSorting(ActionEvent event) {
	   sortButton.setDisable(true);
	   randomButton.setDisable(true);
	   choiceBox.setDisable(true);
	   switch(choiceBox.getSelectionModel().getSelectedItem()) {
	   case "Bubble Sort": sort = new BubbleSort();
	   break;
	   case "Quick Sort": sort = new QuickSort();
	   break;
	   case "Heap Sort": sort = new HeapSort();
	   break;
	   case "Radix Sort": sort = new RadixSort();
	   }
	   SequentialTransition sequential = new SequentialTransition();
	   sequential.getChildren().addAll(sort.sortOn(set));
	   sequential.setOnFinished(e -> {
		   randomButton.setDisable(false);
		   choiceBox.setDisable(false);
	   });
	   sequential.play();
   }
   public void randomElements(ActionEvent event) {
	   sortButton.setDisable(false);
	   displayPane.getChildren().clear();
	   set.randomize();
	   displayPane.getChildren().addAll(set.getSetOfElements());
   }
}