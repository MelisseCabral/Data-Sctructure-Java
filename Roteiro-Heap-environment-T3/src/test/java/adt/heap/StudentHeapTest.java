package adt.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentHeapTest {

	private GenericHeap<Integer> heap;
	private PriorityQueue<Integer> queue;
	
	@Before
	public void setUp() {
		getImplementations();
		
		heap.insert(1);
		heap.insert(2);
		heap.insert(3);
		heap.insert(4);
		
		Comparator<Integer> integerComparatorInvertido = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return -(o1.compareTo(o2));
			}
		};
	/*		
	 * O aluno pode utilizar uma estrutura auxiliar no seus testes para verificar
	 * o funcionamento de sua heap. 
	 * Ver: https://docs.oracle.com/javase/7/docs/api/java/util/PriorityQueue.html
	 */
	//	queue = new PriorityQueue<Integer>(8, integerComparatorInvertido);
	//	queue = new PriorityQueue<Integer>();
	//	queue.add(1);
	//	queue.add(2);
	//	queue.add(3);
	//	queue.add(4);
	}
	
	private void getImplementations(){
		//TODO O aluno deve ajustar aqui para instanciar sua implementação
		heap = null;
	}
	
	// Arrays que podem ser utilizados para o teste do HeapSort.
	// Nem todos os cenarios de testes são abordados com esses arrays.
	Integer[] vetorRepetido = {4, 4, 3, 4, 3, 1, 4};
	Integer[] vetorCrescente = {2, 3, 4, 5, 6, 7};
	Integer[] vetorDescrescente = {7, 6, 5, 4, 3, 2};

	@Test
	public void testHeapsort() {
		Assert.fail("Not implemented!");
	}
	
	@Test
	public void testIsEmpty() {
		Assert.fail("Not implemented!");
	}

	@Test
	public void testInsert() {
		Assert.fail("Not implemented!");
	}

	@Test
	public void testExtractRootElement() {
		Assert.fail("Not implemented!");
	}

	@Test
	public void testRootElement() {
		Assert.fail("Not implemented!");	
	}

	@Test
	public void testBuildHeap() {
		Assert.fail("Not implemented!");
	}
}