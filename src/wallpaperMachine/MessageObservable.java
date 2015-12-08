package wallpaperMachine;

import java.util.Observable;

public class MessageObservable extends Observable {

    MessageObservable() {

	super();
    }

    void changeData(Object data) {

	setChanged(); // the two methods of Observable class
	notifyObservers(data);
    }
}