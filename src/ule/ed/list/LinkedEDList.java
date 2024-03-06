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
		Node<T> nodo;

		public LinkedListIterator(Node<T> aux) {
			this.nodo = aux;
		}

		@Override
		public boolean hasNext() {
			return nodo != null;
		}

		@Override
		public T next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException("no hay siguiente elemento");
			}

			T elem = nodo.elem;
			nodo = nodo.next;
			return elem;

		}
	}

	/// TODO : AÑADIR OTRAS CLASES PARA LOS OTROS ITERADORES

	private class OddIterator<T> implements Iterator<T> {
		Node<T> nodo;

		OddIterator(Node<T> aux) {
			this.nodo = aux;
		}

		@Override
		public boolean hasNext() {

			return this.nodo != null;
		}

		@Override
		public T next() {

			if (!this.hasNext()) {
				throw new NoSuchElementException("no hay siguiente");
			}

			T elem = nodo.elem;
			nodo = nodo.next;

			if (nodo == null) {
				return elem;
			}

			nodo = nodo.next;
			return elem;

		}

	}

	private class EvenIterator<T> implements Iterator<T> {

		Node<T> nodo;

		EvenIterator(Node<T> aux) {

			if (aux != null) {
				this.nodo = aux.next;
			} else {
				this.nodo = aux;
			}
		}

		@Override
		public boolean hasNext() {

			return this.nodo != null;
		}

		@Override
		public T next() {

			if (!this.hasNext()) {
				throw new NoSuchElementException("no hay siguiente");
			}

			T elem = nodo.elem;
			nodo = nodo.next;

			if (nodo == null) {
				return elem;
			}

			nodo = nodo.next;
			return elem;

		}

	}

	private class OddEvenIterator<T> implements Iterator<T> {

		Iterator par, impar;

		OddEvenIterator(Node<T> aux) {

			this.impar = new OddIterator<>(aux);
			this.par = new EvenIterator<>(aux);
		}

		@Override
		public boolean hasNext() {
			return impar.hasNext() || par.hasNext();
		}

		@Override
		public T next() {
			if(!this.hasNext()){
				throw new NoSuchElementException("no hay mas elementos");
			}

			if(!impar.hasNext()){
				return (T) par.next();
			}
			return (T) impar.next();
		}

	}

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
	public int removeAll(T elem) throws EmptyCollectionException {
		if (elem.equals(null)) {
			throw new NullPointerException("el elemento no puede ser nulo");
		}
		if (this.size() == 0) {
			throw new EmptyCollectionException("la lista esta vacia");
		}

		int n = this.countElem(elem);

		if (n == 0) {
			throw new NoSuchElementException("el elemento no esta en la lista");
		}
		for (int i = 0; i < n; i++) {
			this.removeElem(elem);
		}

		return n;

	}

	@Override
	public T getElemPos(int position) {
		if (position < 1 || position > this.size()) {
			throw new IllegalArgumentException("el arg no es valido");
		}
		Node<T> nodo = this.front;

		for (int i = 0; i < position - 1; i++) {
			nodo = nodo.next;
		}

		return nodo.elem;
	}

	@Override
	public int getPosFirst(T elem) {
		if (elem == null) {
			throw new NullPointerException("el elemento no puede ser nulo");
		}

		Node<T> nodo = this.front;

		for (int i = 1; i <= this.size(); i++) {
			if (nodo.elem.equals(elem)) {
				return i;
			}
			nodo = nodo.next;
		}

		throw new NoSuchElementException("el elemento no esta en la lista");

	}

	@Override
	public int getPosLast(T elem) {
		if (elem == null) {
			throw new NullPointerException("el elemento no puede ser nulo");
		}
		boolean encontrado = false;

		Node<T> nodo = this.front;
		int n = 1;

		for (int i = 1; i <= this.size(); i++) {
			if (nodo.elem.equals(elem)) {
				n = i;
				encontrado = true;
			}
			nodo = nodo.next;
		}

		if (!encontrado) {
			throw new NoSuchElementException("el elemento no esta en la lista");
		}

		return n;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("(");

		Node<T> current = this.front;

		if (current != null) {

			do {

				str.append(current.elem.toString() + " ");

				current = current.next;

			} while (current != null);
		}
		str.append(")");

		return str.toString();
	}

	@Override
	public int countElem(T elem) {
		if (elem == null) {
			throw new NullPointerException("el elemento no puede ser nulo");
		}
		Node<T> nodo = this.front;
		int n = 0;

		if (nodo == null) {
			return 0;
		}

		while (nodo != null) {

			if (elem.equals(nodo.elem)) {
				n++;
			}
			nodo = nodo.next;
		}

		return n;
	}

	@Override
	public void clear() {
		this.front = null;

	}

	@Override
	public IEDList<T> listOfRepeatedElems() {
		LinkedEDList<T> resultado = new LinkedEDList<T>();
		Node<T> nodo = this.front;

		while (nodo.next != null) {
			System.out.println(resultado);
			System.out.println(nodo.elem + "->" + this.countElem(nodo.elem) + " : " + resultado.countElem(nodo.elem));

			if (this.countElem(nodo.elem) > 1 && resultado.countElem(nodo.elem) == 0) {

				resultado.addLast(nodo.elem);
			}
			nodo = nodo.next;
		}

		return resultado;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO
		return new LinkedListIterator<T>(front);
	}

	@Override
	public Iterator<T> evenPositionsIterator() {
		// TODO Auto-generated method stub
		return new EvenIterator<T>(this.front);
	}

	@Override
	public Iterator<T> oddPositionsIterator() {

		return new OddIterator<T>(this.front);
	}

	@Override
	public Iterator<T> OddEvenIterator() {
		// TODO Auto-generated method stub
		return new OddEvenIterator<T>(front);
	}

	public static void main(String[] args) {
		LinkedEDList<Integer> lista = new LinkedEDList<Integer>();

		lista.addFirst(5);
		lista.addFirst(4);
		lista.addFirst(3);
		lista.addFirst(2);
		lista.addFirst(1);
		lista.addLast(6);

		System.out.println(lista);

		Iterator iter = lista.OddEvenIterator();

		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}

}