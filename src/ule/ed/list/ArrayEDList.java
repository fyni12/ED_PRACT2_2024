package ule.ed.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayEDList<T> implements IEDList<T> {
	static final int DEFAULT_CAPACITY = 10;

	private T[] data;
	private int count;

	// NO SE PUEDEN AÑADIR MÁS ATRIBUTOS A LA LISTA IMPLEMENTADA CON ARRAYS

	private class ArrayEDListIterator<T> implements Iterator<T> {
		private int current = 0;

		@Override
		public boolean hasNext() {
			// TODO
			return false;

		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			// TODO
			return null;
		}
	}

	/// TODO : AÑADIR OTRAS CLASES PARA LOS OTROS ITERADORES

	// FIN ITERADORES

	@SuppressWarnings("unchecked")
	public ArrayEDList() {
		count = 0;
		this.data = (T[]) (new Object[DEFAULT_CAPACITY]);
	}

	@SuppressWarnings("unchecked")
	public ArrayEDList(int capacity) {

		count = 0;
		this.data = (T[]) (new Object[capacity]);
	}

	@Override
	public int size() {

		return count;
	}

	@Override
	public boolean isEmpty() {

		return this.count == 0;
	}

	@SuppressWarnings("unchecked")
	private T[] extendList() {
		T[] temp = this.data;

		this.data = (T[]) (new Object[temp.length * 2]);

		return temp;

	}

	private void copy(T[] lista) {
		for (int i = 0; i < lista.length; i++) {
			this.data[i] = lista[i];
		}
	}

	// ---------------ADDS-----------------------------------
	@Override
	public void addFirst(T elem) throws NullPointerException {
		this.addPos(elem, 0);
	}

	@Override
	public void addLast(T elem) {
		this.addPos(elem, this.count);
	}

	@Override
	public void addPenult(T elem) {
		this.addPos(elem, this.count - 1);
	}

	@Override
	public void addPos(T elem, int position) {
		if (elem == null) {
			throw new NullPointerException("El elemento no puede ser nulo");
		}
		System.out.println();
		if (this.count == this.data.length - 1) {

			this.copy(this.extendList());
		}

		for (int i = count - 1; i >= position; i--) {
			this.data[i + 1] = this.data[i];
		}

		this.data[position] = elem;
		this.count++;
	}

	// ---------------------------REMOVE-------------------

	private void condensar(int pos) {
		// TODO: ACABAR EL METODO, NS QUE TENGO QUE HACER EXACTAMENTE

		for (int i = pos; i < this.count; i++) {
			this.data[i] = this.data[i + 1];
		}

	}

	@Override
	public T removeFirst() throws EmptyCollectionException {
		// TODO

		return null;

	}

	@Override
	public T removelast() throws EmptyCollectionException {
		// TODO
		return null;
	}

	@Override
	public T removePenult() throws EmptyCollectionException {
		// TODO
		return null;
	}

	@Override
	public int removeElem(T elem) throws EmptyCollectionException {
		// TODO
		return 0;
	}

	@Override
	public T getElemPos(int position) {
		// TODO
		return null;
	}

	@Override
	public int getPosLast(T elem) {
		// TODO
		return 0;
	}

	@Override
	public int removeAll(T elem) throws EmptyCollectionException {
		// TODO
		return 0;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("(");

		for (int i = 0; i < this.count; i++) {
			str.append(this.data[i] + " ");
		}

		str.append(")");

		return str.toString();
	}

	@Override
	public void clear() {
		for (int i = 0; i < count; i++) {
			data[i] = null;
		}
		count = 0;

	}

	@Override
	public int getPosFirst(T elem) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IEDList<T> listOfRepeatedElems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countElem(T elem) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new ArrayEDListIterator<T>();
	}

	@Override
	public Iterator<T> evenPositionsIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> oddPositionsIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> OddEvenIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		ArrayEDList<Integer> lista = new ArrayEDList<Integer>(2);
		System.out.println(lista.toString());

		lista.addFirst(1);
		lista.addFirst(1);
		lista.addFirst(1);
		lista.addFirst(1);
		lista.addFirst(1);
		lista.addFirst(1);
		lista.addFirst(1);
		lista.addFirst(1);

		System.out.println(lista.toString());

	}
}
