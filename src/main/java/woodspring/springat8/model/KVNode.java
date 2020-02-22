package woodspring.springat8.model;

public class KVNode<K extends Comparable<K>, V> {
	private K key;
	private V value;
	private int level;
	private KVNode<K, V> previous, next, up, down;
	
	public KVNode(K theKey, V theValue, int theLevel) {
		this.key = theKey;
		this.value = theValue;
		this.level = theLevel;
		previous = next = up = down = null;
	}

	public void addKVNode( KVNode<K,V> parentNode, KVNode<K,V> currNode, KVNode<K,V>  kvNode) {
		if ( currNode == null ) {
			
		}
	}
	
	public V find( KVNode<K, V> currNode, K theKey) {
		V retV = null;
		if (currNode == null) {
			if ( currNode.down() == null) {
				return retV;
			} else {
				return currNode.find(currNode.down(), theKey);
			}
		} else {
			if (currNode.next() == null) {
				retV = currNode.find( currNode.down(), theKey);
			}
		}
		
		int comp = currNode.key().compareTo( theKey);
		if (comp < 1) {
			retV = currNode.find(currNode.next(), theKey);	
		} else if ( comp > 1) {
			retV = currNode.find(currNode.down(), theKey);
		} else {
			retV = currNode.getValue();
		}
		
		return retV;		
	}
	@Override
	public String toString() {
		return "KVNode [key=" + key + ", value=" + value + ", level=" + level + ", previous=" + previous + ", next="
				+ next + ", up=" + up + ", down=" + down + "]";
	}

	public K key() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value();
	}

	public V value() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public KVNode<K, V> getPrevious() {
		return previous();
	}

	public KVNode<K, V> previous() {
		return this.previous;
	} 
	
	public void setPrevious(KVNode<K, V> previous) {
		this.previous = previous;
	}

	public KVNode<K, V> getNext() {
		return next();
	}
	public KVNode<K, V> next() {
		return this.next;
	}

	public void setNext(KVNode<K, V> next) {
		this.next = next;
	}

	public KVNode<K, V> getUp() {
		return up();
	}
	public KVNode<K, V> up() {
		return up;
	}

	public void setUp(KVNode<K, V> up) {
		this.up = up;
	}

	public KVNode<K, V> getDown() {
		return down();
	}
	public KVNode<K, V> down() {
		return down;
	}

	public void setDown(KVNode<K, V> down) {
		this.down = down;
	}

}
