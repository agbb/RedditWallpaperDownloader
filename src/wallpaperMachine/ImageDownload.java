package wallpaperMachine;

import javax.swing.JOptionPane;

public class ImageDownload implements Runnable {

    String address;
    String filePath;
    int[] dimensions;

    public ImageDownload(String address, String filePath, int[] dimensions) {

	this.address = address;
	this.filePath = filePath;
	this.dimensions = dimensions;

    }

    @Override
    public void run() {

	BackgroundChange change = new BackgroundChange();

	int[] success = change.pickFiles(this.address, this.filePath,
		this.dimensions);
	JOptionPane
		.showMessageDialog(null, "A total of " + success[0]
			+ " images were considdered \nOf those " + success[1]
			+ " were too small or the wrong shape. \nA total of "
			+ success[2] + " Images were written to disk.",
			"Download Opperation Complete",
			JOptionPane.INFORMATION_MESSAGE);
    }

}
