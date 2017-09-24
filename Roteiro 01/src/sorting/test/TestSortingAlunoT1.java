package sorting.test;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import sorting.SortingImpl;
import sorting.simpleSorting.Bubblesort;
import sorting.simpleSorting.Insertionsort;
import sorting.simpleSorting.Quicksort;
import sorting.simpleSorting.Selectionsort;
import sorting.variationsOfSelectionsort.BidirectionalSelectionsort;

public class TestSortingAlunoT1 {

	protected final Integer[] ascendingArray = {-1, -1, 3, 4, 5, 6, 7, 8, 9, 10};
	protected final Integer[] descendingArray = {10, 9, 8, 7, 6, 5, 4, 3, -1, -1};
	protected final Integer[] shuffledArray = {10, -1, -1, 3, 9, 5, 8, 4, 7, 6};
	protected final Integer[] negativeArray = {-10, -1, -11, -3, -9, -5, -8, -4,-36};
	protected final Integer[] positiveArray = {10, 122, 11, 33, 90, 51, 81, 24, 7, 56};
	protected final Integer[] equalArray = {10, 10, 10};
	protected final Integer[] sampleArray = {10,2,33,70};
	protected final Integer[] unitArray = {10};
	protected final Integer[] emptyArray = {};
	
	protected Integer[] ascending = new Integer[10];
	protected Integer[] descending = new Integer[10];
	protected Integer[] shuffled = new Integer[10];
	protected Integer[] empty = {};
	
	protected final Object[] expectedArray = {-1, -1, 3, 4, 5, 6, 7, 8, 9, 10};
	protected final Object[] expectedPositive = {7, 10, 11, 24, 33, 51, 56, 81, 90, 122};
	protected final Object[] expectedNegative = {-36, -11, -10, -9, -8, -5, -4, -3, -1};
	SortingImpl<Integer> mSorting;
	
	
	@Before
	public void setUp() {
		ascending = java.util.Arrays.copyOfRange(ascendingArray, 0, 10);
		descending = java.util.Arrays.copyOfRange(descendingArray, 0, 10);
		shuffled = java.util.Arrays.copyOfRange(shuffledArray, 0, 10);
		mSorting = null;
	}
	
	@Test
	public void testBubblesort(){
		mSorting = new Bubblesort<Integer>();
		mSorting.sort(ascending);
		mSorting.sort(descending);
		mSorting.sort(shuffled);
		mSorting.sort(empty);
		mSorting.sort(positiveArray);
		mSorting.sort(negativeArray);
		mSorting.sort(equalArray);
		mSorting.sort(unitArray);
		assertArrayEquals(expectedArray, ascending);
		assertArrayEquals(expectedArray, descending);
		assertArrayEquals(expectedArray, shuffled);
		assertArrayEquals(emptyArray, empty);
		assertArrayEquals(expectedPositive,positiveArray);
		assertArrayEquals(expectedNegative,negativeArray);
		
	}
	
	@Test
	public void testInsertionsort(){
		mSorting = new Insertionsort<Integer>();
		mSorting.sort(ascending);
		mSorting.sort(descending);
		mSorting.sort(shuffled);
		mSorting.sort(empty);
		mSorting.sort(positiveArray);
		mSorting.sort(negativeArray);
		mSorting.sort(equalArray);
		mSorting.sort(unitArray);
		assertArrayEquals(expectedArray, ascending);
		assertArrayEquals(expectedArray, descending);
		assertArrayEquals(expectedArray, shuffled);
		assertArrayEquals(emptyArray, empty);
		assertArrayEquals(expectedPositive,positiveArray);
		assertArrayEquals(expectedNegative,negativeArray);
	}
	
	@Test
	public void testSelectionsort(){
		mSorting = new Quicksort<Integer>();
		mSorting.sort(ascending);
		mSorting.sort(descending);
		mSorting.sort(shuffled);
		mSorting.sort(empty);
		mSorting.sort(positiveArray);
		mSorting.sort(negativeArray);
		mSorting.sort(equalArray);
		mSorting.sort(unitArray);
		assertArrayEquals(expectedArray, ascending);
		assertArrayEquals(expectedArray, descending);
		assertArrayEquals(expectedArray, shuffled);
		assertArrayEquals(emptyArray, empty);
		assertArrayEquals(expectedPositive,positiveArray);
		assertArrayEquals(expectedNegative,negativeArray);
		
	}
	
	@Test
	public void testBidirectionalBubblesort(){
		mSorting = new BidirectionalSelectionsort<Integer>();
		mSorting.sort(ascending);
		mSorting.sort(descending);
		mSorting.sort(shuffled);
		mSorting.sort(empty);
		mSorting.sort(positiveArray);
		mSorting.sort(negativeArray);
		mSorting.sort(equalArray);
		mSorting.sort(unitArray);
		assertArrayEquals(expectedArray, ascending);
		assertArrayEquals(expectedArray, descending);
		assertArrayEquals(expectedArray, shuffled);
		assertArrayEquals(emptyArray, empty);
		assertArrayEquals(expectedPositive,positiveArray);
		assertArrayEquals(expectedNegative,negativeArray);
		
	}

}

