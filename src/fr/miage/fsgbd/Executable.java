package fr.miage.fsgbd;

import java.io.Serializable;

public interface Executable<T> extends Serializable {

	boolean execute(T arg1, T arg2);
	
}
