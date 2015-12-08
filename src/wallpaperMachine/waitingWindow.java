package wallpaperMachine;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class waitingWindow extends JFrame implements Observer {

    waitingWindow() {
	super("Hello");
	JLabel label = new JLabel("Hello World");
	getContentPane().add(label);

	// Display the window.
	pack();
	setVisible(true);
    }

    private void close(boolean data) {
	if (data == false) {
	    dispose();
	}
    }

    @Override
    // Observer interface's implemented method
    public void update(Observable o, Object data) {

	close((boolean) data);

    }

}