package mytexteditor.rpa.editor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

import java.awt.event.*;


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
		setTitle("✩My Editor Text✩");

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
		 * --Create a label-- JLabel label = new JLabel("hola mundo"); --Add to panel--
		 * add(label);
		 */
		// Here we have created our container where our text boxes will go.
		// ---------------------MENU-------------------------------------------------
		// we declare the menu object
		JPanel panelMenu = new JPanel();
		// here we are going to declare the tools that the menu interface will have.
		menu = new JMenuBar();
		fileMenu = new JMenu("Archivo");
		editMenu = new JMenu("Editar");
		selectMenu = new JMenu("Seleccionar");
		lookMenu = new JMenu("Ver");
		skinMenu = new JMenu("Apariencia");

		//add the tools to the menu. 
		//skinMenu is not added to the menu as it is inside the lookMenu.
		menu.add(fileMenu);
		menu.add(editMenu);
		menu.add(selectMenu);
		menu.add(lookMenu);
		
		//File items/options
		createItem("Nuevo Archivo", "fileMenu", "newFile");
		createItem("Abrir Archivo", "fileMenu", "openfile");
		fileMenu.addSeparator();
		createItem("Guardar", "fileMenu", "savefile");
		createItem("Guardar como", "fileMenu", "saveAsFile");
		//Edit items/options
		createItem("Deshacer", "editMenu", "");
		createItem("Rehacer", "editMenu", "");
		editMenu.addSeparator();
		createItem("Cortar", "editMenu", "");
		createItem("Copiar", "editMenu", "");
		createItem("Pegar", "editMenu", "");
		//Select items/options
		createItem("Seleccionar Todo", "selectMenu", "");
		//Look items/options
		createItem("Numeración", "lookMenu", "");
		lookMenu.add(skinMenu);
		createItem("Normal", "skinMenu","");
		createItem("Negrita", "skinMenu", "");
		// now let's add this menu to the JPanel
		panelMenu.add(menu);
		// --------------------------------------------------------------------------
		// ---------------------ÁREA DE TEXTO----------------------------------------
		// add window pane
		tPane = new JTabbedPane();
		// --------------------------------------------------------------------------
		/*
		 * Now, let's add the pane to our window. But we place it before the tPane,
		 * because the tPane contains the text area, so it would go after the options.
		 */
		add(panelMenu);
		add(tPane);
	}

	// let's add the tool options
	public void createItem(String label, String menu, String action) {
		itemValue = new JMenuItem(label);
		if (menu.equals("fileMenu")) {
			fileMenu.add(itemValue);
			if(action.equals("newFile")) {		
				//add event/item to new file option under Archivo
				//The addActionListener method takes the ActionListener interface as a parameter.
				itemValue.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						createPanel();	
					}
				});
			}
		} 
		
		else if (menu.equals("editMenu")) {
			editMenu.add(itemValue);
		} else if (menu.equals("selectMenu")) {
			selectMenu.add(itemValue);
		} else if (menu.equals("lookMenu")) {
			lookMenu.add(itemValue);
		} else if (menu.equals("skinMenu")) {
			skinMenu.add(itemValue);
		}
	}

	// to create the accesses to these text windows, we will create a method
	public void createPanel() {
		// container=panel
		// we instantiate the window container and the text area
		window = new JPanel();
		textArea = new JTextPane();

		// we put the text area inside the window container
		window.add(textArea);
		// now, we place this in tPane, which will take care of placing it in the form
		// of tabs.
		tPane.addTab("title", window);
	}

	// ----------------CREATE
	// OBJECTS------------------------------------------------
	private JTabbedPane tPane;
	// panel where our text window will go
	private JPanel window;
	// now, the tools to create a text area
	private JTextPane textArea;
	// here we put the special container, the tools and its options
	private JMenuBar menu;
	private JMenu fileMenu, editMenu, selectMenu, lookMenu, skinMenu;
	private JMenuItem itemValue;
}
