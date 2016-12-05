package model;

import java.io.Serializable;

/**
 * Created by Gustavo on 29/11/16.
 */

public class Authority  implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
