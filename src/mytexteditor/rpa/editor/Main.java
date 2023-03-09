package mytexteditor.rpa.editor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

public class Main {

	public static void main(String[] args) {
		WindowFrame windowFrame = new WindowFrame();
		// To close the program when you click it and it doesn't stay running:
		/*
		 * This method receives a static constant as a parameter. A class constant. This
		 * means that it needs us to tell it the name of the class in which it is
		 * created. For example: static, void... This class is in the JFrame class,
		 * which is static. Therefore, we must put JFrame. and the constant we need,
		 * EXIT_ON_CLOSE Therefore, static means that when we have a method or a
		 * constant, we have to put the name of the class to which it belongs.
		 */
		windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// to make the frame visible we use the method .setVisible()
		windowFrame.setVisible(true);
	}
}

class WindowFrame extends JFrame {

	// this constructor will provide us with the entire container of the window
	public WindowFrame() {
		// to give it a dimension and a position we use the setBounds method
		// the first parameter receives the view coordinates and the second is the size.
		setBounds(300, 300, 300, 300);
		setTitle("MY EDITOR TEXT");

		/*
		 * Here, we're going to add the panel method to the window frame so it's
		 * displayed in there. Otherwise, when executed, nothing would be seen.
		 */
		add(new Panel());

	}
}

/*
 * the window class will no longer be touched. The Panel is the container of our
 * program, where most things will go most of the objects.
 */
class Panel extends JPanel {
	public Panel() {
		/*
		 * --Create a label-- 
		 * JLabel label = new JLabel("hola mundo"); 
		 * --Add to panel--
		 * add(label);
		 */
		// Here we have created our container where our text boxes will go.
		//---------------------ÁREA DE TEXTO----------------------------------------
		tPane = new JTabbedPane();
		createPanel();
		//--------------------------------------------------------------------------
		add(tPane);
	}
	
	//to create the accesses to these text windows, we will create a method
	public void createPanel( ) {
		//container=panel
		//we instantiate the window container and the text area
		window = new JPanel();
		textArea = new JTextPane();
		
		//we put the text area inside the window container
		window.add(textArea);
		//now, we place this in tPane, which will take care of placing it in the form of tabs.
		tPane.addTab("title",window);
	}
	private JTabbedPane tPane;
	//panel where our text window will go
	private JPanel window;
	//now, the tools to create a text area
	private JTextPane textArea;
}
