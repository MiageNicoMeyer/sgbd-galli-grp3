package fr.miage.fsgbd;

import java.io.Serializable;

public class TestInteger implements Executable<Integer>, Serializable {

    public boolean execute(Integer int1, Integer int2) {
        return (int1 < int2);
    }
    
}