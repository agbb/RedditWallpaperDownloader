package testing;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BackgroundChange {

    public int[] pickFiles(String address, String filePath, int[] dimensions) {
	String nextPage = address;
	String file = filePath;
	int page = 0;
	int maxPage = 4;
	int imagesConsiddered = 0;
	int imagesMisshaped = 0;
	int imagesWritten = 0;
	int xSize = dimensions[0];
	int ySize = dimensions[1];

	while (page <= maxPage) {
	    try {
		Document doc = Jsoup.connect(nextPage).get();
		Elements links = doc.select("a[href]");
		ArrayList<Image> PictureList = new ArrayList<Image>();
		ArrayList<String> LinkList = new ArrayList<String>();

		for (Element element : links) {

		    if (!LinkList.contains(element.attr("abs:href"))) {
			LinkList.add(element.attr("abs:href"));
		    }
		    if (element.toString().contains("next")) {

			nextPage = element.attr("abs:href");

		    }

		}

		for (String string : LinkList) {
		    BufferedImage image = null;

		    if ((string.contains("flickr") || (string.contains("imgur")))
			    && (!(string.contains("reddit")) && (string
				    .endsWith(".jpg")))) {
			imagesConsiddered++;
			URL url = new URL(string);
			image = ImageIO.read(url);
			PictureList.add(image);
			if (((image.getHeight() >= ySize) && (image.getWidth() > xSize))
				&& (image.getWidth() > image.getHeight())) {

			    String time = Long.toString(new Date().getTime());
			    File outputfile = new File(file + "/" + "img"
				    + time + ".jpg");
			    ImageIO.write(image, "jpg", outputfile);
			    imagesWritten++;
			} else {
			    imagesMisshaped++;
			}
		    }

		}
		page++;
		System.out.println(page);
	    }

	    catch (org.jsoup.HttpStatusException a) {
		System.out
			.println("Reddit Regected Request Due To Server Overload, Retrying...");
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
	    }

	}
	int[] toReturn = { imagesConsiddered, imagesMisshaped, imagesWritten, };
	return toReturn;

    }

}
