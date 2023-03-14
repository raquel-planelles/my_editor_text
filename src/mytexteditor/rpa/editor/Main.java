package mytexteditor.rpa.editor;

import javax.swing.*;
import java.awt.event.*;

import java.io.File;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.util.ArrayList;

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

		// add the tools to the menu.
		// skinMenu is not added to the menu as it is inside the lookMenu.
		menu.add(fileMenu);
		menu.add(editMenu);
		menu.add(selectMenu);
		menu.add(lookMenu);

		// File items/options
		createItem("Nuevo Archivo", "fileMenu", "newFile");
		createItem("Abrir Archivo", "fileMenu", "openfile");
		fileMenu.addSeparator();
		createItem("Guardar", "fileMenu", "savefile");
		createItem("Guardar como", "fileMenu", "saveAsFile");
		// Edit items/options
		createItem("Deshacer", "editMenu", "");
		createItem("Rehacer", "editMenu", "");
		editMenu.addSeparator();
		createItem("Cortar", "editMenu", "");
		createItem("Copiar", "editMenu", "");
		createItem("Pegar", "editMenu", "");
		// Select items/options
		createItem("Seleccionar Todo", "selectMenu", "");
		// Look items/options
		createItem("Numeración", "lookMenu", "");
		lookMenu.add(skinMenu);
		createItem("Normal", "skinMenu", "");
		createItem("Negrita", "skinMenu", "");
		// now let's add this menu to the JPanel
		panelMenu.add(menu);
		// --------------------------------------------------------------------------
		// ---------------------ÁREA DE TEXTO----------------------------------------
		// add window pane
		tPane = new JTabbedPane();

		// we still need to call listFile and the rest of the lists.
		listTextArea = new ArrayList<JTextPane>();
		listScroll = new ArrayList<JScrollPane>();
		listFile = new ArrayList<File>();

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
			if (action.equals("newFile")) {
				// add event/item to new file option under Archivo
				// The addActionListener method takes the ActionListener interface as a
				// parameter.
				itemValue.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						createPanel();
					}
				});
			} else if (action.equals("openfile")) {
				itemValue.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						/*
						 * OPEN FILE: Here we use a java method to open a new window and the
						 * fileSelector constructor to take the option to open files and directories.
						 * This can be found in the docs.oracle.com documentation.
						 */
						createPanel();
						JFileChooser fileSelector = new JFileChooser();
						fileSelector.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
						/*
						 * the latter has to be stored in an int because that's what its info says in
						 * the oracle documents
						 * fileSelector.showOpenDialog(listTextArea.get(tPane.getSelectedIndex()));
						 */
						int result = fileSelector.showOpenDialog(listTextArea.get(tPane.getSelectedIndex()));

						/*
						 * instead of figuring out what the open or cancel button equals (1,0) Java
						 * already gives us variables with those results so we use them. This is
						 * professional, the ==0 or 1 is not.
						 */
						if (result == JFileChooser.APPROVE_OPTION) {
							System.out.println("Cancel");

							try {
								boolean pathOn = false;
								// get file
								// File is equal to its empty constructor
								File f = new File("");
								// this method will return an object of type file and we will store it in f, an
								// object of type File.
								f = fileSelector.getSelectedFile();
								// to check if it works, we print f with getPat() which gets the path where the
								// file is located.
								System.out.println(f.getPath());
							} catch (Exception e1) {
								// TODO: handle exception
							}
						}
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

		/*
		 * This constructor, in its interior, receives a text string that the user gives
		 * us, but as we don't know what it is, we leave it empty.
		 */
		listFile.add(new File(""));
		// here we have added our text area
		// here we have added our text area.
		listTextArea.add(new JTextPane());
		/*
		 * here we tell it to add a new scroll to the scroll list, and that this scroll
		 * will be linked to this text area that is at position 0.
		 */
		listScroll.add(new JScrollPane(listTextArea.get(panelCounter)));

		// we put the text area inside the window container
		window.add(listScroll.get(panelCounter));

		// now, we place this in tPane, which will take care of placing it in the form
		// of tabs.
		tPane.addTab("title", window);
		// this is so that when we click on new file, it runs in a different tab and not
		// in the same panel.
		tPane.setSelectedIndex(panelCounter);
		// here we tell the counter to keep adding up so that it is not always at 0.
		panelCounter++;
		panelExists = true;
	}

	// ----------------CREATE
	// OBJECTS------------------------------------------------
	// container of all Pane
	private JTabbedPane tPane;
	// panel where our text window will go
	private JPanel window;
	/*
	 * we need an object for the text area, but if we put only one, the program will
	 * interact with the last one created. To solve this, we'll do an arrayList()
	 * private JTextPane testArea;
	 */
	// This variable will count how many panels have been created.
	public int panelCounter = 0;
	private boolean panelExists = false;
	private ArrayList<JTextPane> listTextArea;
	private ArrayList<JScrollPane> listScroll;
	private ArrayList<File> listFile;
	// here we put the special container, the tools and its options
	private JMenuBar menu;
	private JMenu fileMenu, editMenu, selectMenu, lookMenu, skinMenu;
	private JMenuItem itemValue;
}
