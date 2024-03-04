package ule.ed.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedEDList<T> implements IEDList<T> {

	// referencia al primer de la lista
	private Node<T> front;

	// NO SE PUEDEN AÑADIR MÁS ATRIBUTOS A LA LISTA

	private class Node<T> {
		T elem;
		Node<T> next;

		Node(T element) {
			this.elem = element;
			this.next = null;
		}

	}
	///////
	///// ITERADOR normal //////////

	// @SuppressWarnings("hiding")
	private class LinkedListIterator<T> implements Iterator<T> {
		// declarar atributos del iterador

		public LinkedListIterator(Node<T> aux) {
			// TODO
		}

		@Override
		public boolean hasNext() {
			// TODO

			return false;
		}

		@Override
		public T next() {
			// TODO

			return null;

		}
	}

	/// TODO : AÑADIR OTRAS CLASES PARA LOS OTROS ITERADORES

	// FIN ITERADORES

	@Override
	public int size() {
		int n = 0;
		Node<T> siguiente = this.front;
		while (siguiente != null) {
			n++;
			siguiente = siguiente.next;
		}

		return n;
	}

	@Override
	public boolean isEmpty() {
		return front == null;
	}

	// -------------ADDS--------------------

	@Override
	public void addFirst(T elem) {
		if (elem == null) {
			throw new NullPointerException("el elemento no puede ser nulo");
		}

		Node<T> primero = this.front;
		Node<T> creado = new Node<T>(elem);
		creado.next = primero;

		this.front = creado;

	}

	@Override
	public void addLast(T elem) {

		this.addPos(elem, this.size() + 1);

	}

	@Override
	public void addPenult(T elem) {
		if (this.size() == 0) {
			this.addPos(elem, this.size() + 1);
		} else {
			this.addPos(elem, this.size());
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

		if (position == 1) {
			this.addFirst(elem);
		} else {

			Node<T> predecesor = this.front;
			Node<T> siguiente = predecesor.next;
			Node<T> resultado = new Node<T>(elem);

			for (int i = 0; i < position - 2; i++) {
				predecesor = predecesor.next;
			}
			siguiente = predecesor.next;
			predecesor.next = resultado;
			resultado.next = siguiente;

		}

	}

	// ---------------REMOVE------------------
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

		Node<T> current = this.front;

		do {

			str.append(current.elem.toString() + " ");

			current = current.next;

		} while (current != null);

		str.append(")");

		return str.toString();
	}

	@Override
	public int removeElem(T elem) throws EmptyCollectionException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO
		return new LinkedListIterator<T>(front);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

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

	
}