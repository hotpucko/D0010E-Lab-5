package labb5.simulation.general;
import java.util.Vector;

import java.util.NoSuchElementException;

/**
 * @author Dino Lolic, William Kiwanuka, Stefan Jonsson, Arvid From
 */
public class SortedSequence<E extends Comparable<E>> 
implements Comparable<SortedSequence<E>> {
	
	private Vector<E> store = new Vector<E>();
	
	/**
	/*
	 * adds item to the sorted sequence
	 */
	public void add(E item) {
		_add (item);
	}
	
	/**
	 *
	 * private implementation of add(E);
	 *throws an exception if there is no item to put in the sequence
	 *sorts the item based on the size of a comparable variable
	 */
	private void _add(E item) throws IllegalArgumentException {
		if (item == null) {
			throw new NullPointerException();
		}
		if (_isEmpty()) {
			store.add(item); 
			} else {
				if (item.compareTo(smallest()) ==-1) {
					store.add(0,item);
				}else {
					E smallest = _smallest();
					store.removeElementAt(0);
					this._add(item);
					store.add(0, smallest);
				}
		}
	}
  
	/**
	 *
	 * returns the first element in the sequence
	 *  @return the first element in the sequence
	 */
	public E smallest() {
		return _smallest();
	}
  
	/**
	 *
	 * private implementation of smallest()
	 */
	private E _smallest() {
		return store.elementAt(0);
	}
  
	/**
	 *
	 * tests if the sequence has no components
	 * @return true if the sequence has no components
	 */
	public boolean isEmpty() {
		return _isEmpty();
	}
	
	/**
	 *
	 * tests if the sequence has no components
	 * @return true if the sequence has no components
	 */
	
	private boolean _isEmpty() {
	return store.isEmpty();
	}
	
	/**
	 *
	 * removes the smallest comparable object of the sequence
	 */
	public void removeSmallest() throws NoSuchElementException{
		_removeSmallest();
	}

	/**
	 *
	 * removes the smallest comparable object of the sequence,
	 * throws exception if no objects exist in the sequence
	 */
	private void _removeSmallest() {
		try {
			store.removeElementAt(0); 
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchElementException();
		}
	}
  
	/**
	 *
	 * returns the size of the sorted sequence
	 * @return  the size of the sorted sequence
	 */
	public int size() {
		return _size();	
}

	/**
	 *
	 * returns the size of the sorted sequence
	 * @return  the size of the sorted sequence
	 */
	private int _size() {
		return store.size();
	}
  
	/**
	 *
	 * compares ordered sequences
	 * 
	 * @return  -1, 0,or 1 depending on whether
	 *  the first sequence is smaller equal or larger than the second
	 */
		public int compareTo(SortedSequence<E> o) {
			return _compareTo(o);
	}
  
		/**
		 *
		 * compares ordered sequences
		 * 
		 * @return  -1, 0,or 1 depending on whether
		 *  the first sequence is smaller equal or larger than the second
		 */
		private int _compareTo(SortedSequence<E> other) {
			int result =0;
			if (this._isEmpty() && other._isEmpty()) {
				result = 0;
			}else if (this._isEmpty()) {
				result = -1;
			}else if (other._isEmpty()) {
				result = -1;
			}else {
				switch (this._smallest().compareTo(other._smallest())) {
					case -1:
						result =-1;
					case 1:
						result =1;
					case 0:
					E s1 = this._smallest();
					this._removeSmallest();
					E s2 = other._smallest();
					other._removeSmallest();
					
					result = this._compareTo(other);
					
					this._add(s1);
					other._add(s2);	
				}
			}
			return result;
		}

	
public String toString() {
	return "-[" + showElements(this) + "]-";
}

private String showElements(SortedSequence<E> seq) {
	if (seq._isEmpty()) {
		return "";
	} else if (seq.size() == 1) {
		return seq._smallest()+"";
	} else {
		E smallest = seq._smallest();
		seq._removeSmallest();
		String result = smallest + "," + showElements(seq);
		seq._add(smallest);
		return result;
	}
}
}