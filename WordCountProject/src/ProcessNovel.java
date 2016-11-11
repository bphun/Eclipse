import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.StackPane;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.util.Duration;


@SuppressWarnings("restriction")

public class ProcessNovel extends Application {
	WordStorage ws = new WordStorage();

	static String fileName = "";
//	final static WordStorage wordStorage = new WordStorage();
	static WordStorage wordStorage = new WordStorage();

	public boolean running = false;  

	public static void main(String[] args) {
		//	Prompt the user for the text file to be processed
		promptForFile();	

		//	Read in the contents of the text file, adding to the WordStorage as they are input
		readInNovel();
		//	Print out the results    
		displayResults();

//		launch(args);
	}
	public static void promptForFile() {
		System.out.println("Prompting for novel file name");
		fileName = JOptionPane.showInputDialog("What is the name of the novel you want to process? \n Don't include file extension");
		if (fileName.length() <= 0) {
			promptForFile();
		}
//		JFrame.setDefaultLookAndFeelDecorated(true);
//		JDialog.setDefaultLookAndFeelDecorated(true);
//		JFrame frame = new JFrame("JComboBox Test");
//		frame.setLayout(new FlowLayout());
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		JButton button = new JButton("Select File");     
//
//		button.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				JFileChooser fileChooser = new JFileChooser();
//				fileChooser.setCurrentDirectory(new File("~/Desktop/Developer/Java/WordCountProject/"));
//				int returnValue = fileChooser.showOpenDialog(null);
//				if (returnValue == JFileChooser.APPROVE_OPTION) {
//					File selectedFile = fileChooser.getSelectedFile();
//					fileName = selectedFile.getName();
//				}
//			}
//		});
//		frame.add(button);
//		frame.pack();
//		frame.setVisible(true);
	}

	public static void readInNovel() {

		System.out.println("Opening file");
		Scanner file = null;
		try {
			file = new Scanner(new File(fileName + ".txt"));
			System.out.println("Opened file, " + fileName + ".txt");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Could not open " + fileName + ".txt");
		}	

		System.out.println("Reading file contents... this may take a while");
		String[] novel = null;
		String novelStr = "";
		while (file.hasNext()) {
			novelStr += file.nextLine() + "\n";
		}
		
		System.out.print("Finished reading file contents\n");

		System.out.println("Converting all characters in novel to lowercase");
		novelStr = novelStr.toLowerCase();
		System.out.println("Completed converting all characters in novel to lowercase");

		novelStr = novelStr.trim();
		
		System.out.println("Removing all special characters");
		novel = novelStr.split("[^a-zA-Z0-9]");
		System.out.println("Removed all special characters");
		
		novelStr = "";
		System.out.println("Writing file to word storage... this may take a while");
		for (final String s  : novel) {
			WordCount wordCount = new WordCount(s);
			wordStorage.add(wordCount);
			
//			if (s != "\n" || s != null || s != "") {
//				wordStorage.add(s);
//			}
		}
		System.out.println("Finished processing novel, printing out words");
		novel = null;
		file = null;
	}	

	public static void displayResults() {
		System.out.println(wordStorage);
	}

	final static String itemA = "A";
    final static String itemB = "B";
    final static String itemC = "F";
    
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public void start(Stage stage) {
        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();
        final BarChart<Number, String> bc = new BarChart<Number, String>(xAxis, yAxis);
        bc.setTitle("Word Frequency");
//        bc.setUserData(getChartData());
        xAxis.setLabel("Word");
        yAxis.setLabel("Frequency");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");
        series1.getData().add(new XYChart.Data(2, itemA));
        series1.getData().add(new XYChart.Data(20, itemB));
        series1.getData().add(new XYChart.Data(10, itemC));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("2004");
        series2.getData().add(new XYChart.Data(50, itemA));
        series2.getData().add(new XYChart.Data(41, itemB));
        series2.getData().add(new XYChart.Data(45, itemC));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("2005");
        series3.getData().add(new XYChart.Data(45, itemA));
        series3.getData().add(new XYChart.Data(44, itemB));
        series3.getData().add(new XYChart.Data(18, itemC));

        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), 
            new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent actionEvent) {
                for (XYChart.Series<Number, String> series : bc.getData()) {
                    for (XYChart.Data<Number, String> data : series.getData()) {
                        data.setXValue(Math.random() * 100);
                    }
                }
            }
        }));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();

        Scene scene = new Scene(bc, 800, 600);
        bc.getData().addAll(series1, series2, series3);
        stage.setScene(scene);
        stage.show();
    }


//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	private ObservableList<XYChart.Series<String, Integer>> getChartData() {
//		ObservableList<XYChart.Series<String, Integer>> chartData = FXCollections.observableArrayList();
//
//		XYChart.Series series = new XYChart.Series();
//		series.setName("Bar Data");      
//
//		for (int i = 0; i < wordStorage.getItems().length; i++) {
//			System.out.print("X: " + wordStorage.get(i).getWord() + " , Y: " + wordStorage.get(i).getFrequency() + "\n");
//
//			String word = wordStorage.get(i).getWord().toString();
//			int frequency = wordStorage.get(i).getFrequency();
//			if ((word != "") && (frequency != 0)) {
//				series.getData().add(new XYChart.Data(wordStorage.get(i).getWord(), wordStorage.get(i).getFrequency()));
//				System.out.print("X: " + wordStorage.get(i).getWord() + ", Y: " + wordStorage.get(i).getFrequency());
//				System.out.println(series);	
//			}
//		}
//
//		chartData.add(series);
//		System.out.println("Total bars: " + chartData.size());
//		return chartData;
//	}

}




