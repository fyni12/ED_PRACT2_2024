package ule.ed.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;


public class ArrayEDListTest {
	private ArrayEDList<String> listaString;
	private ArrayEDList<Integer> listaNumeros;
	
	@Before
	public void setUp() {
		 listaString= new ArrayEDList<String>();
		 listaNumeros= new ArrayEDList<Integer>();
	}
	
	@Test
	public void ArrayAddFirstExpandCapacityTest() {
		listaString=new ArrayEDList<String>(3);
		
		listaString.addFirst("2");
		Assert.assertFalse(listaString.isEmpty());
		Assert.assertEquals("(2 )", listaString.toString());
		listaString.addFirst("3");
		Assert.assertEquals("(3 2 )", listaString.toString());
		listaString.addFirst("7");
		Assert.assertEquals("(7 3 2 )", listaString.toString());
		listaString.addFirst("10");
		Assert.assertEquals("(10 7 3 2 )", listaString.toString());		
	}
	
	@Test
	public void ArrayVaciaTest() {
		assertEquals(0,listaString.size());
	}
	// ---------------------------ADDERS----------------------
	@Test(expected=NullPointerException.class)
	public void ArrayAddFirstElementoNuloTest() {
			listaString.addFirst(null);
	}
	
	@Test
	public void ArrayAddFirstTest() {
		listaString.addFirst("2");
		Assert.assertFalse(listaString.isEmpty());
		Assert.assertEquals("(2 )", listaString.toString());
		listaString.addFirst("3");
		Assert.assertEquals("(3 2 )", listaString.toString());
		listaString.addFirst("7");
		Assert.assertEquals("(7 3 2 )", listaString.toString());
	}

	@Test(expected=NullPointerException.class)
	public void AddLastErrorNull(){
		listaString.addLast(null);
	}

	@Test
	public void AddLast(){
		listaNumeros.addLast(1);
		assertEquals("(1 )", listaNumeros.toString());
		listaNumeros.addLast(2);
		assertEquals("(1 2 )", listaNumeros.toString());
	}

	@Test(expected = NullPointerException.class)
	public void addPenultErrorNull(){
		listaNumeros.addPenult(null);
	}

	@Test
	public void addPenult(){
		listaNumeros.addPenult(2);
		assertEquals("(2 )", listaNumeros.toString());
		listaNumeros.addPenult(1);
		assertEquals("(1 2 )", listaNumeros.toString());
		listaNumeros.addPenult(3);
		assertEquals("(1 3 2 )", listaNumeros.toString());
	}

	@Test(expected = NullPointerException.class)
	public void addPosErrorNull(){
		listaString.addPos(null, 1);
	}
	@Test(expected = IllegalArgumentException.class)
	public void addPosErrorIllegal(){

		listaString.addPos("null", 0);
	}

	//------------------Removes----------------

	@Test(expected = EmptyCollectionException.class)
	public void removeFirstErrorEmpty() throws EmptyCollectionException
	{
		listaString.removeFirst();
	}
	@Test
	public void removeFirst() throws EmptyCollectionException
	{
		listaString.addFirst("2");
		listaString.addFirst("1");

		assertEquals("1", listaString.removeFirst());
	}

	@Test(expected = EmptyCollectionException.class)
	public void removeLastErrorEmpty() throws EmptyCollectionException
	{
		listaString.removelast();
	}
	@Test
	public void removelast() throws EmptyCollectionException
	{
		listaString.addFirst("2");
		listaString.addFirst("1");

		assertEquals("2", listaString.removelast());
	}



	@Test(expected = EmptyCollectionException.class)
	public void removePenultErrorEmpty() throws EmptyCollectionException
	{
		listaString.removePenult();
	}
	
	@Test(expected = NoSuchElementException.class)
	public void removePenultErrorSuchElement() throws EmptyCollectionException
	{
		listaString.addFirst("null");
		listaString.removePenult();
	}

	@Test
	public void removePenult() throws EmptyCollectionException
	{
		listaString.addFirst("2");
		listaString.addFirst("1");

		assertEquals("1", listaString.removePenult());
	}




	//----------------Iteradores---------------------
	@Test
	public void ArrayIteratorTest() {
		listaString.addFirst("2");
		Assert.assertFalse(listaString.isEmpty());
		Assert.assertEquals("(2 )", listaString.toString());
		listaString.addFirst("3");
		Assert.assertEquals("(3 2 )", listaString.toString());
		listaString.addFirst("7");
		Assert.assertEquals("(7 3 2 )", listaString.toString());
		Iterator<String>  iter=listaString.iterator();
		assertTrue(iter.hasNext());
		assertEquals("7", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("3", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("2", iter.next());
		assertFalse(iter.hasNext());
	}
	
	@Test
	public void ArrayEvenIteratorNElemesParTest() {
		listaString.addFirst("2");
		Assert.assertFalse(listaString.isEmpty());
		Assert.assertEquals("(2 )", listaString.toString());
		listaString.addFirst("3");
		Assert.assertEquals("(3 2 )", listaString.toString());
		listaString.addFirst("7");
		Assert.assertEquals("(7 3 2 )", listaString.toString());
		listaString.addFirst("8");
		Assert.assertEquals("(8 7 3 2 )", listaString.toString());

		Iterator<String>  iter=listaString.evenPositionsIterator();
		assertTrue(iter.hasNext());
		assertEquals("7", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("2", iter.next());
		assertFalse(iter.hasNext());
	}
	
	
	
	
	// TEST ITERADORES EN listaString VACÍA
	@Test(expected=NoSuchElementException.class)
	public void ArrayNextListaVaciaTest() {
			listaString.iterator().next();	}
	
	
	
}
	
	
