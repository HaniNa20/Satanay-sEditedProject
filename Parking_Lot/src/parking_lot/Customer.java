package parking_lot;

import java.io.Serializable;

public abstract class Customer implements Serializable {

    String name;
    String number;

    abstract void showMenu();

}
