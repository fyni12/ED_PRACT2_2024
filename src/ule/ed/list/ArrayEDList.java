package ule.ed.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

public class ArrayEDList<T> implements IEDList<T> {
	static final int DEFAULT_CAPACITY = 10;

	private T[] data;
	private int count;

	// NO SE PUEDEN AÑADIR MÁS ATRIBUTOS A LA LISTA IMPLEMENTADA CON ARRAYS

	private class ArrayEDListIterator<T> implements Iterator<T> {
		private int current = 0;
		private T[] items;
		private int count;

		ArrayEDListIterator(T[] colection, int size) {
			this.count = size;
			this.items = colection;
		}

		@Override
		public boolean hasNext() {

			return current < this.count;

		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException("No hay siguiente");
			}
			current++;
			return items[current - 1];
		}
	}

	private class ArrayEDListOddIterator<T> implements Iterator<T> {
		private int current = 0;
		private T[] items;
		private int count;

		ArrayEDListOddIterator(T[] colection, int size) {
			this.count = size;
			this.items = colection;
		}

		@Override
		public boolean hasNext() {

			return this.current < this.count;

		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException("No hay siguiente");
			}
			current += 2;
			return items[current - 2];
		}

	}

	private class ArrayEDListEvenIterator<T> implements Iterator<T> {
		private int current = 1;
		private T[] items;
		private int count;

		ArrayEDListEvenIterator(T[] colection, int size) {
			this.count = size;
			this.items = colection;
		}

		@Override
		public boolean hasNext() {

			return this.current < this.count;

		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException("No hay siguiente");
			}

			current += 2;
			return items[current - 2];
		}

	}

	private class ArrayEDOddEvenIterator<T> implements Iterator<T> {

		Iterator par, impar;

		ArrayEDOddEvenIterator(T[] coleccion, int size) {

			this.par = new ArrayEDListEvenIterator<>(coleccion, size);
			this.impar = new ArrayEDListOddIterator<>(coleccion, size);
		}

		@Override
		public boolean hasNext() {

			return par.hasNext() || impar.hasNext();
		}

		@Override
		public T next() {

			if (!this.hasNext()) {
				throw new NoSuchElementException("No hay siguiente");
			}

			if (!impar.hasNext()) {
				return (T) par.next();
			}
			return (T) impar.next();

			// return temp;
		}

	}
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
	private void extendList() {
		T[] temp = this.data;

		this.data = (T[]) (new Object[temp.length * 2]);

		this.copy(temp);

	}

	private void copy(T[] lista) {
		for (int i = 0; i < lista.length; i++) {
			this.data[i] = lista[i];
		}

	}

	// ---------------ADDS-----------------------------------
	@Override
	public void addFirst(T elem) throws NullPointerException {
		if (elem == null) {
			throw new NullPointerException("el elemento no puede ser nulo");
		}
		this.addPos(elem, 1);
	}

	@Override
	public void addLast(T elem) {
		if (elem == null) {
			throw new NullPointerException("el elemento no puede ser nulo");
		}

		this.addPos(elem, this.count + 1);

	}

	@Override
	public void addPenult(T elem) {
		if (elem == null) {
			throw new NullPointerException("el elemento no puede ser nulo");
		}
		if (this.isEmpty()) {
			this.addPos(elem, 1);
		} else {

			this.addPos(elem, this.count);
		}
	}

	@Override
	public void addPos(T elem, int position) {
		if (elem == null) {
			throw new NullPointerException("El elemento no puede ser nulo");
		}
		if (position <= 0) {
			throw new IllegalArgumentException("la poscion no puede ser inferior a 1");
		}

		if (this.count == this.data.length - 1) {

			this.extendList();
		}

		for (int i = this.count; i >= position; i--) {

			this.data[i] = this.data[i - 1];
		}

		if (position > this.count + 1) {
			this.addLast(elem);
		} else {

			this.data[position - 1] = elem;
			this.count++;
		}

	}

	// ---------------------------REMOVE-------------------

	private void condensar(int pos) {

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
		if (elem == null) {
			throw new NullPointerException("El elemento no puede ser nulo");
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
		IEDList<T> resultado = new ArrayEDList<T>();

		for (int i = 0; i < this.count; i++) {

			if (this.countElem(this.getElemPos(i + 1)) > 1 && resultado.countElem(this.getElemPos(i + 1)) == 0) {
				resultado.addLast(this.getElemPos(i + 1));
			}

		}

		return resultado;
	}

	@Override
	public int countElem(T elem) {

		if (elem == null) {
			throw new NullPointerException("el elemento no puede ser nulo");
		}

		int n = 0;

		for (int i = 0; i < this.count; i++) {
			if (this.data[i].equals(elem))
				n++;
		}
		return n;
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayEDListIterator<T>(this.data, this.size());
	}

	@Override
	public Iterator<T> evenPositionsIterator() {
		return new ArrayEDListEvenIterator<T>(this.data, this.size());
	}

	@Override
	public Iterator<T> oddPositionsIterator() {
		return new ArrayEDListOddIterator<T>(this.data, this.size());
	}

	@Override
	public Iterator<T> OddEvenIterator() {

		return new ArrayEDOddEvenIterator<>(this.data, this.size());
	}

}
