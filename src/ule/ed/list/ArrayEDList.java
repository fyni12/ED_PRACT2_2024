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
		if (this.isEmpty()) {
			this.addPos(elem, this.count);
		} else {

			this.addPos(elem, this.count - 1);
		}
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

	public void condensar(int pos) {

		for (int i = pos; i < this.count; i++) {
			this.data[i] = this.data[i + 1];
		}
		count--;

	}

	@Override
	public T removeFirst() throws EmptyCollectionException {
		if (this.isEmpty()) {
			throw new EmptyCollectionException("la lista esta vacia");
		}
		T temp = this.data[0];
		this.condensar(0);
		return temp;

	}

	@Override
	public T removelast() throws EmptyCollectionException {
		if (this.isEmpty()) {
			throw new EmptyCollectionException("la lista esta vacia");
		}
		T temp = this.data[this.count - 1];
		this.condensar(this.count - 1);
		return temp;
	}

	@Override
	public T removePenult() throws EmptyCollectionException {
		if (this.isEmpty()) {
			throw new EmptyCollectionException("la lista esta vacia");
		}
		if (this.size() == 1) {
			throw new NoSuchElementException("la lista solo tiene un elemento");
		}

		T temp = this.data[this.count - 2];
		this.condensar(this.count - 2);
		return temp;
	}

	@Override
	public int removeElem(T elem) throws EmptyCollectionException {
		if (this.isEmpty()) {
			throw new EmptyCollectionException("la lista esta vacia");
		}
		for (int i = 0; i < this.count; i++) {
			if (this.data[i].equals(elem)) {
				this.condensar(i);
				return i + 1;
			}

		}
		throw new NoSuchElementException("no se encontró el elemento en la lista");
	}

	@Override
	public T getElemPos(int position) {
		if (position < 1 || position > this.size()) {

			throw new IllegalArgumentException("posicion no valida");
		}

		return (this.data[position - 1]);
	}

	@Override
	public int getPosFirst(T elem) {
		if (elem == null) {
			throw new NullPointerException("El objeto no puede ser null");
		}
		for (int i = 0; i < this.count; i++) {
			if (this.data[i].equals(elem)) {
				return i + 1;
			}

		}
		throw new NoSuchElementException("no se encontró el elemento en la lista");
	}

	@Override
	public int getPosLast(T elem) {
		if (elem == null) {
			throw new NullPointerException("El objeto no puede ser null");
		}
		for (int i = this.count - 1; i >= 0; i--) {
			if (this.data[i].equals(elem)) {
				return i + 1;
			}

		}
		throw new NoSuchElementException("no se encontró el elemento en la lista");
	}

	@Override
	public int removeAll(T elem) throws EmptyCollectionException {
		if (this.isEmpty()) {
			throw new EmptyCollectionException("La coleccion esta vacia");
		}
		if (elem == null) {
			throw new NullPointerException("el elemento no puede ser nulo");
		}
		int n = this.countElem(elem);

		if (n == 0) {
			throw new NoSuchElementException("el objeto no se encuentra en la coleccion");
		}

		for (int i = 0; i < n; i++) {
			this.removeElem(elem);
		}

		return n;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("(");

		for (int i = 0; i < this.count; i++) {
			str.append(this.data[i].toString() + " ");
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
	public IEDList<T> listOfRepeatedElems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countElem(T elem) {
		
		if(elem==null){
			throw new NullPointerException("el elemento no puede ser nulo");
		}
		
		int n = 0;

		for (int i=0; i<this.count; i++) {
			if (this.data[i].equals(elem))
				n++;
		}
		return n;
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

	public static void main(String[] args) throws EmptyCollectionException {
		ArrayEDList<Integer> e = new ArrayEDList<Integer>();
		for (int i = 1; i < 11; i++) {

			e.addLast(i);
		}
		e.addLast(1);
		e.addLast(1);
		e.addLast(1);
		e.addLast(1);
		System.out.println(e.toString());
		System.out.println(e.countElem(1));
		System.out.println(e.removeAll(1));
		System.out.println(e.toString());
	}
}
