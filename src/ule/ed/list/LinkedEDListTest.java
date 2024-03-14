package ule.ed.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;

public class LinkedEDListTest {
	private LinkedEDList<String> listaString;
	private LinkedEDList<Integer> listaNumeros;

	@Before
	public void setUp() {
		listaString = new LinkedEDList<String>();
		listaNumeros = new LinkedEDList<Integer>();
	}

	@Test
	public void ArrayVaciaTest() {
		assertEquals(0, listaString.size());
	}

	@Test
	public void ArrayAddFirstExpandCapacityTest() {
		listaString = new LinkedEDList<String>();

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
	// ------------Adders-----------------------

	@Test(expected = NullPointerException.class)
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

	@Test(expected = NullPointerException.class)
	public void AddLastErrorNull() {
		listaString.addLast(null);
	}

	@Test
	public void AddLast() {
		listaNumeros.addLast(1);
		assertEquals("(1 )", listaNumeros.toString());
		listaNumeros.addLast(2);
		assertEquals("(1 2 )", listaNumeros.toString());
	}

	@Test(expected = NullPointerException.class)
	public void addPenultErrorNull() {
		listaNumeros.addPenult(null);
	}

	@Test
	public void addPenult() {
		listaNumeros.addPenult(2);
		assertEquals("(2 )", listaNumeros.toString());
		listaNumeros.addPenult(1);
		assertEquals("(1 2 )", listaNumeros.toString());
		listaNumeros.addPenult(3);
		assertEquals("(1 3 2 )", listaNumeros.toString());
	}

	@Test(expected = NullPointerException.class)
	public void addPosErrorNull() {
		listaString.addPos(null, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addPosErrorIllegal() {

		listaString.addPos("null", 0);
	}

	@Test
	public void addPosTest() {
		listaNumeros.addPos(7, 4);
		listaNumeros.addFirst(6);
		listaNumeros.addFirst(4);
		listaNumeros.addFirst(3);
		listaNumeros.addFirst(2);
		listaNumeros.addFirst(1);
		listaNumeros.addPos(5, 5);

		assertEquals("(1 2 3 4 5 6 7 )", listaNumeros.toString());

	}

	// ------------------Removes----------------

	@Test(expected = EmptyCollectionException.class)
	public void removeFirstErrorEmpty() throws EmptyCollectionException {
		listaString.removeFirst();
	}

	@Test
	public void removeFirst() throws EmptyCollectionException {
		listaString.addFirst("2");
		listaString.addFirst("1");

		assertEquals("1", listaString.removeFirst());
	}

	@Test(expected = EmptyCollectionException.class)
	public void removeLastErrorEmpty() throws EmptyCollectionException {
		listaString.removelast();
	}

	@Test
	public void removelast() throws EmptyCollectionException {
		listaString.addFirst("2");
		listaString.addFirst("1");

		assertEquals("2", listaString.removelast());
		assertEquals("1", listaString.removelast());
	}

	@Test(expected = EmptyCollectionException.class)
	public void removePenultErrorEmpty() throws EmptyCollectionException {
		listaString.removePenult();
	}

	@Test(expected = NoSuchElementException.class)
	public void removePenultErrorSuchElement() throws EmptyCollectionException {
		listaString.addFirst("null");
		listaString.removePenult();
	}

	@Test
	public void removePenult() throws EmptyCollectionException {
		listaString.addFirst("2");
		listaString.addFirst("1");
		listaString.addFirst("3");
		listaString.addFirst("4");

		assertEquals("1", listaString.removePenult());
		assertEquals("3", listaString.removePenult());
		assertEquals("4", listaString.removePenult());
	}

	@Test(expected = EmptyCollectionException.class)
	public void removeElemErrorEmpty() throws EmptyCollectionException {
		listaString.removeElem("a");
	}

	@Test(expected = NullPointerException.class)
	public void removeElemErrorNull() throws EmptyCollectionException {
		listaString.addFirst("null");
		listaString.removeElem(null);
	}

	@Test(expected = NoSuchElementException.class)
	public void removeElemErrorSuchElement() throws EmptyCollectionException {
		listaString.addFirst("null");
		listaString.removeElem("");
	}

	@Test
	public void removeElem() throws EmptyCollectionException {
		listaString.addFirst("1");
		listaString.addFirst("3");
		listaString.addFirst("2");
		listaString.addFirst("1");
		assertEquals("(1 2 3 1 )", listaString.toString());
		assertEquals(1, listaString.removeElem("1"));
		assertEquals(3, listaString.removeElem("1"));
	}

	@Test(expected = EmptyCollectionException.class)
	public void removeAllErrorEmpty() throws EmptyCollectionException {
		listaString.removeAll("a");
	}

	@Test(expected = NoSuchElementException.class)
	public void removeAllErrorSuchElement() throws EmptyCollectionException {
		listaString.addFirst("null");
		listaString.removeAll("");
	}

	@Test(expected = NullPointerException.class)
	public void removeAllErrorNull() throws EmptyCollectionException {

		listaString.removeAll(null);
	}

	@Test
	public void removeAll() throws EmptyCollectionException {
		listaString.addFirst("1");
		listaString.addFirst("3");
		listaString.addFirst("2");
		listaString.addFirst("1");
		assertEquals("(1 2 3 1 )", listaString.toString());
		assertEquals(2, listaString.removeAll("1"));
		assertEquals("(2 3 )", listaString.toString());
	}

	// ------------GetPos--------------------------

	@Test(expected = IllegalArgumentException.class)
	public void getElementPosErrorIllegalLow() {
		listaNumeros.getElemPos(-11);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getElementPosErrorIllegalHigh() {
		listaNumeros.getElemPos(11111);
	}

	@Test
	public void getElement() {
		listaString.addFirst("3");
		listaString.addFirst("2");
		listaString.addFirst("1");

		assertEquals("1", listaString.getElemPos(1));
		assertEquals("3", listaString.getElemPos(3));
	}

	@Test(expected = NullPointerException.class)
	public void getPosFirstErrorNull() {
		listaString.getPosFirst(null);
	}

	@Test(expected = NoSuchElementException.class)
	public void getPosFirstErrorSuchElement() {
		listaString.getPosFirst("null");
	}

	@Test
	public void getPosFirst() throws EmptyCollectionException {

		listaString.addFirst("3");
		listaString.addFirst("2");
		listaString.addFirst("1");
		listaString.addFirst("3");

		assertEquals(1, listaString.getPosFirst("3"));
		listaString.removeFirst();
		assertEquals(3, listaString.getPosFirst("3"));

	}

	@Test(expected = NullPointerException.class)
	public void getPosLastErrorNull() {
		listaString.getPosLast(null);
	}

	@Test(expected = NoSuchElementException.class)
	public void getPosLastErrorSuchElement() {
		listaString.getPosLast("null");
	}

	@Test
	public void getPosLast() throws EmptyCollectionException {

		listaString.addFirst("3");
		listaString.addFirst("2");
		listaString.addFirst("1");
		listaString.addFirst("3");

		assertEquals(4, listaString.getPosLast("3"));
		listaString.removelast();

		assertEquals(1, listaString.getPosLast("3"));

	}

	// --------------------Extras----------------------------

	@Test
	public void clear() {

		listaString.addFirst("3");
		listaString.addFirst("2");
		listaString.addFirst("1");

		assertEquals("(1 2 3 )", listaString.toString());

		listaString.clear();
		assertEquals("()", listaString.toString());

	}

	@Test
	public void listOfRepeatedElems() {
		assertEquals("()", listaNumeros.listOfRepeatedElems().toString());
		listaNumeros.addFirst(1);
		listaNumeros.addFirst(1);
		listaNumeros.addFirst(2);
		listaNumeros.addFirst(6);
		listaNumeros.addFirst(1);
		listaNumeros.addFirst(51);
		listaNumeros.addFirst(2);
		listaNumeros.addFirst(4);
		listaNumeros.addFirst(2);
		listaNumeros.addFirst(1);

		assertEquals("(1 2 )", listaNumeros.listOfRepeatedElems().toString());
	}

	@Test(expected = NullPointerException.class)
	public void countElemErrorNull() {
		listaNumeros.countElem(null);
	}

	@Test
	public void countElem() {
		listaString.addFirst("1");
		listaString.addFirst("1");
		listaString.addFirst("1");
		listaString.addFirst("2");
		listaString.addFirst("1");

		assertEquals(1, listaString.countElem("2"));
		assertEquals(4, listaString.countElem("1"));
	}

	// ----------------Iteradores---------------------
	@Test
	public void ArrayIteratorTest() {
		listaString.addFirst("2");
		Assert.assertFalse(listaString.isEmpty());
		Assert.assertEquals("(2 )", listaString.toString());
		listaString.addFirst("3");
		Assert.assertEquals("(3 2 )", listaString.toString());
		listaString.addFirst("7");
		Assert.assertEquals("(7 3 2 )", listaString.toString());
		Iterator<String> iter = listaString.iterator();
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

		Iterator<String> iter = listaString.evenPositionsIterator();
		assertTrue(iter.hasNext());
		assertEquals("7", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("2", iter.next());
		assertFalse(iter.hasNext());
	}

	@Test
	public void ArrayOddIteratorNElemesParTest() {
		listaString.addFirst("2");
		Assert.assertFalse(listaString.isEmpty());
		Assert.assertEquals("(2 )", listaString.toString());
		listaString.addFirst("3");
		Assert.assertEquals("(3 2 )", listaString.toString());
		listaString.addFirst("7");
		Assert.assertEquals("(7 3 2 )", listaString.toString());
		listaString.addFirst("8");
		Assert.assertEquals("(8 7 3 2 )", listaString.toString());

		Iterator<String> iter = listaString.oddPositionsIterator();
		assertTrue(iter.hasNext());
		assertEquals("8", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("3", iter.next());
		assertFalse(iter.hasNext());
	}

	@Test
	public void ArrayOddEvenIteratorNElemesParTest() {
		listaString.addFirst("2");
		Assert.assertFalse(listaString.isEmpty());
		Assert.assertEquals("(2 )", listaString.toString());
		listaString.addFirst("3");
		Assert.assertEquals("(3 2 )", listaString.toString());
		listaString.addFirst("7");
		Assert.assertEquals("(7 3 2 )", listaString.toString());
		listaString.addFirst("8");
		Assert.assertEquals("(8 7 3 2 )", listaString.toString());

		Iterator<String> iter = listaString.OddEvenIterator();
		assertTrue(iter.hasNext());
		assertEquals("8", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("3", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("7", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("2", iter.next());
		assertFalse(iter.hasNext());
	}

	// TEST ITERADORES EN listaString VACÍA
	@Test(expected = NoSuchElementException.class)
	public void ArrayNextListaVaciaTest() {
		listaString.iterator().next();
	}

	@Test(expected = NoSuchElementException.class)
	public void ArrayEvenNextListaVaciaTest() {
		listaString.evenPositionsIterator().next();
	}

	@Test(expected = NoSuchElementException.class)
	public void ArrayOddNextListaVaciaTest() {
		listaString.oddPositionsIterator().next();
	}

	@Test(expected = NoSuchElementException.class)
	public void ArrayOddEvenNextListaVaciaTest() {
		listaString.OddEvenIterator().next();
	}

	@Test
	public void OddEvenOneElem(){
		listaString.addFirst("1");
		Iterator iter=listaString.OddEvenIterator();
		assertTrue(iter.hasNext());
		assertEquals("1", iter.next());
		assertFalse(iter.hasNext());
	}

}
