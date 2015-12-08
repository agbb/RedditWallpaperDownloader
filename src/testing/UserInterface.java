package testing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UserInterface extends JFrame {

    String filePath;
    String addressSelection;
    JTextField width = new JTextField("1920");
    JTextField height = new JTextField("1080");
    int widthInt;
    int heightInt;

    public UserInterface() {

	setLayout(new FlowLayout());
	JOptionPane
	.showMessageDialog(
		null,
		"Contact: alexander.gubbay@kcl.ac.uk \nCredit to JSOUP for HTML scraping: http://jsoup.org",
		"Built By Alex Gubbay", JOptionPane.INFORMATION_MESSAGE);
	JPanel mainPanel = new JPanel(new BorderLayout());
	mainPanel.setSize(500, 500);

	JPanel top = new JPanel();
	top.setLayout(new GridLayout(2, 1));
	JLabel title = new JLabel(
		"<html><b>Reddit Wallpaper Downloader</b></html>");
	title.setBorder(new EmptyBorder(5, 5, 5, 5));
	top.add(title);

	JPanel fileSelect = new JPanel(new BorderLayout());
	final JTextField currentSelection = new JTextField("No Path Selection");
	currentSelection.setEditable(false);
	JButton Select = new JButton("Select Path");

	final JButton confirm = new JButton("Download");
	confirm.setEnabled(false);

	Select.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// Create a file chooser
		final JFileChooser chooser = new JFileChooser();

		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Select Save Directory");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		    currentSelection.setText(chooser.getSelectedFile()
			    .getPath());
		    confirm.setEnabled(true);
		    filePath = chooser.getSelectedFile().getPath();
		} else {
		    currentSelection.setText("No Path Selection");
		}
	    }

	});

	fileSelect.add(currentSelection, BorderLayout.CENTER);
	fileSelect.add(Select, BorderLayout.EAST);
	top.add(fileSelect);
	mainPanel.add(top, BorderLayout.NORTH);

	JPanel subreddit = new JPanel(new GridLayout(5, 1));

	final String[] subredditAddresses = {
		"http://www.reddit.com/r/EarthPorn/top/?sort=top&t=week",
		"http://www.reddit.com/r/Beachporn/top/?sort=top&t=week",
		"http://www.reddit.com/r/CityPorn/top/?sort=top&t=week",
	"http://www.reddit.com/r/ruralporn/top/?sort=top&t=week" };
	String[] subredditNames = { "Earth", "Beach", "City", "Rural" };
	final ButtonGroup selector = new ButtonGroup();
	for (int i = 0; i < 4; i++) {
	    JRadioButton redditSelection = new JRadioButton(subredditNames[i]);
	    selector.add(redditSelection);
	    subreddit.add(redditSelection);
	    selector.setSelected(redditSelection.getModel(), true);
	}

	width = new JTextField("1920");
	height = new JTextField("1080");
	JPanel bottom = new JPanel(new BorderLayout());
	final JCheckBox check = new JCheckBox("Remove Existing Backgrounds");

	confirm.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {

		try {

		    int widthInt = Integer.parseInt(width.getText());
		    int heightInt = Integer.parseInt(height.getText());
		} catch (NumberFormatException a) {
		    JOptionPane.showMessageDialog(null,
			    "Enter valid digits for dimensions.");
		}
		boolean overwrite = check.isSelected();
		// TODO add overwrite fucntion

		String address = null;

		for (Enumeration<AbstractButton> buttons = selector
			.getElements(); buttons.hasMoreElements();) {
		    AbstractButton button = buttons.nextElement();

		    if (button.isSelected()) {
			address = button.getText();
		    }
		}
		System.out.println(address);
		switch (address) {
		case "Earth":
		    addressSelection = subredditAddresses[0];
		    break;
		case "Beach":
		    addressSelection = subredditAddresses[1];
		    break;
		case "City":
		    addressSelection = subredditAddresses[2];
		    break;
		case "Rural":
		    addressSelection = subredditAddresses[3];
		    break;

		default:
		    addressSelection = null;
		    break;
		}
		System.out.println(addressSelection);
		int[] pass = { widthInt, heightInt };

		ImageDownload down = new ImageDownload(addressSelection,
			filePath, pass);
		down.run();

	    }
	});

	JPanel dimensionBox = new JPanel(new FlowLayout());
	JLabel SetMinimumSize = new JLabel("Set Minimum Size (Width x Height)");

	width.setColumns(4);
	height.setColumns(4);
	JLabel middle = new JLabel("x");

	dimensionBox.add(SetMinimumSize);
	dimensionBox.add(width);
	dimensionBox.add(middle);
	dimensionBox.add(height);
	bottom.add(dimensionBox, BorderLayout.NORTH);
	bottom.add(check, BorderLayout.WEST);
	bottom.add(confirm, BorderLayout.CENTER);
	mainPanel.add(bottom, BorderLayout.SOUTH);
	mainPanel.add(subreddit);
	add(mainPanel);

	pack();
	setVisible(true);
	setResizable(false);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	UserInterface a = new UserInterface();
    }

}
