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
		if (this.isEmpty()) {
			throw new EmptyCollectionException("La lista esta vacia");
		}

		T resultado = this.front.elem;
		this.front = this.front.next;
		return resultado;
	}

	@Override
	public T removelast() throws EmptyCollectionException {
		if (this.isEmpty()) {
			throw new EmptyCollectionException("La lista esta vacia");
		}

		T resultado;

		if (this.size() == 1) {
			resultado = this.removeFirst();

		} else {
			Node<T> nodo = this.front;

			while (nodo.next.next != null) {
				nodo = nodo.next;
			}
			resultado = nodo.next.elem;
			nodo.next = null;
		}

		return resultado;
	}

	@Override
	public T removePenult() throws EmptyCollectionException {
		if (this.isEmpty()) {
			throw new EmptyCollectionException("La lista esta vacia");
		} else if (this.size() <= 1) {
			throw new NoSuchElementException("no hay tantos elementos en la lista");
		}

		T resultado;
		Node<T> prenodo = this.front;
		Node<T> nodo = this.front.next;

		while (nodo.next.next != null) {
			nodo = nodo.next;
			prenodo = prenodo.next;

		}

		prenodo.next = nodo.next;

		resultado = nodo.elem;

		return resultado;

	}

	@Override
	public int removeElem(T elem) throws EmptyCollectionException {

		if (this.isEmpty()) {
			throw new EmptyCollectionException("La lista esta vacia");
		}

		if (this.front.elem == elem) {
			this.removeFirst();
			return 1;
		} else {
			Node<T> prenodo = this.front;
			Node<T> nodo = this.front.next;
			int n = 2;
			while (nodo != null && nodo.elem != elem) {
				nodo = nodo.next;
				prenodo = prenodo.next;
				n++;
			}

			if (nodo == null) {
				throw new NoSuchElementException("no esta el elemento en la lista");
			}
			prenodo.next = nodo.next;
			return n;
		}

	}

	@Override
	public T getElemPos(int position) {
		if (position < 1 || position > this.size()) {
			throw new IllegalArgumentException("el arg no es valido");
		}
		Node<T> nodo = this.front;

		for (int i = 0; i < position - 1; i++) {
			nodo=nodo.next;
		}

		return nodo.elem;
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