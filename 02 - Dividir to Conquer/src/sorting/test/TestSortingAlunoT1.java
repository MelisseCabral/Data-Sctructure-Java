package sorting.test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;


import sorting.SortingImpl;
import sorting.divideAndConquer.Mergesort;
import sorting.divideAndConquer.Quicksort;
import sorting.divideAndConquer.quicksort3.QuicksortMedianOfThree;



public class TestSortingAlunoT1 {

	protected final Integer[] ascendingArray = {-1, -1, 3, 4, 5, 6, 7, 8, 9, 10};
	protected final Integer[] descendingArray = {10, 9, 8, 7, 6, 5, 4, 3, -1, -1};
	protected final Integer[] shuffledArray = {10, -1, -1, 3, 9, 5, 8, 4, 7, 6};
	protected final Integer[] emptyArray = {};
	
	protected Integer[] ascending = new Integer[10];
	protected Integer[] descending = new Integer[10];
	protected Integer[] shuffled = new Integer[10];
	protected Integer[] empty = {};
	
	protected final Object[] expectedArray = {-1, -1, 3, 4, 5, 6, 7, 8, 9, 10};
	
	SortingImpl<Integer> mSorting;
	
	
	@Before
	public void setUp() {
		ascending = java.util.Arrays.copyOfRange(ascendingArray, 0, 10);
		descending = java.util.Arrays.copyOfRange(descendingArray, 0, 10);
		shuffled = java.util.Arrays.copyOfRange(shuffledArray, 0, 10);
		mSorting = null;
	}
	
	@Test
	public void testMergesort(){
		mSorting = new Mergesort<Integer>();
		mSorting.sort(ascending);
		mSorting.sort(descending);
		mSorting.sort(shuffled);
		mSorting.sort(empty);
		assertArrayEquals(expectedArray, ascending);
		assertArrayEquals(expectedArray, descending);
		assertArrayEquals(expectedArray, shuffled);
		assertArrayEquals(emptyArray, empty);
		
	}
	
	@Test
	public void testQuickSort(){
		mSorting = new Quicksort<Integer>();
		mSorting.sort(ascending);
		mSorting.sort(descending);
		mSorting.sort(shuffled);
		mSorting.sort(empty);
		assertArrayEquals(expectedArray, ascending);
		assertArrayEquals(expectedArray, descending);
		assertArrayEquals(expectedArray, shuffled);
		assertArrayEquals(emptyArray, empty);
		
	}
	
	@Test
	public void testQuicksortMedianOfThree(){
		mSorting = new QuicksortMedianOfThree<Integer>();
		mSorting.sort(ascending);
		mSorting.sort(descending);
		mSorting.sort(shuffled);
		mSorting.sort(empty);
		assertArrayEquals(expectedArray, ascending);
		assertArrayEquals(expectedArray, descending);
		assertArrayEquals(expectedArray, shuffled);
		assertArrayEquals(emptyArray, empty);
		
	}
	

	


}

