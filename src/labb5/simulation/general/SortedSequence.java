package labb5.simulation.general;
import java.util.Vector;
import java.util.NoSuchElementException;

/*
 * @author Dino Lolic, William Kiwanuka, Stefan Jonsson, Arvid From
 */
public class SortedSequence<E extends Comparable<E>> 
implements Comparable<SortedSequence<E>> {
	int b =2;
	private Vector<E> store = new Vector<E>();
	
	public void add(E item) {
		_add (item);
	}
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
	
	
	public E smallest() {
		return _smallest();
	}
	private E _smallest() {
		return store.elementAt(0);
	}
	
	public boolean isEmpty() {
		return _isEmpty();
	}
	
	private boolean _isEmpty() {
	return store.isEmpty();
	}
	
	
	public void removeSmallest() throws NoSuchElementException{
		_removeSmallest();
	}
	private void _removeSmallest() {
		try {
			store.removeElementAt(0); 
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchElementException();
		}
	}
	
	public int size() {
		return _size();	
}
	private int _size() {
		return store.size();
	}
		public int compareTo(SortedSequence<E> o) {
			return _compareTo(o);
	}
		
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
			return "-[" + showElements (this) + "]-";
		}
		private String showElements(SortedSequence<E> seq) {
			if (seq.isEmpty()) {
				return "";
			} else if (seq._size() == 1) {
				return seq._smallest() + "";
			} else {
				E smallest = seq._smallest();
				seq._removeSmallest();
				String result = smallest + "," + showElements(seq);
				seq._add(smallest);
				return result;
			}
		}
	}

