package test;

import java.util.Comparator;

public class Comp1 implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		return o1.compareTo(o2);
	}


}
