package woodspring.springat8.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SKNode<K extends Comparable<K>, V> {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private K   key;
	private V   value;
	private int level;
	
	private SKNode<K, V> up, down, previous, next;
	
	public SKNode(K theKey, V theValue, int theLevel) {
		this.key = theKey;
		this.value = theValue;
		this.level = theLevel;
		up = down = null;
		previous = next = null;		
	}
	
	public void insert(K theKey, V theValue, int aLevel, SKNode<K, V> parent) {

		if (!(this.level > aLevel) && (next == null || next.getKey().compareTo( theKey) > 0)) {
			SKNode<K,V> newNode = new SKNode<K, V>( theKey, theValue, aLevel);
			
			if ( next != null) {
				next.setPrevious( newNode);
				newNode.setNext( next);
			}
			
			// this is something wild
			next = newNode;
			newNode.setPrevious( this);
			
			if ( parent != null) {
				newNode.setUp( parent);
				parent.setDown(newNode);
			} 
			
			if ( down != null) {
				down.insert( theKey,  theValue,  aLevel,  parent);			
			}
			
		} else {
			if ( next == null) {
				if ( down!= null) {
					down.insert(theKey,  theValue,  aLevel,  parent);
				} else {
					logger.info(" ERROR here for theKey:"+ theKey.toString()+" theValue:"+ theValue.toString());
				}
			} else {
				int compResult = next.getKey().compareTo( theKey);
				logger.info(" key:"+ theKey.toString()+" value:"+ value.toString()+ " level:"+ aLevel+ " compResult:"+compResult);
				if ( compResult <0) {
					next.insert( theKey,  theValue,  aLevel,  parent);
				} else {
					//
					logger.info(" key "+ theKey.toString()+" DUPLICATE!, overwrite value");
					next.setValue( theValue);
				}
				
			}
		}

		
	}

	public SKNode<K,V> find( K theKey) {
		SKNode<K, V> theNode = null;
		if ( next != null) {
			int compResult = next.getKey().compareTo( theKey);
			
			if ( compResult == 0) {
				theNode = next;
			} else if ( compResult < 0) {
				theNode = next.find( theKey);
			} else if ( down != null){
				theNode = down.find( theKey);
			} else {
				logger.warn(" CAN NOT FOUND for key:"+theKey.toString());
				//throw new NoSuchElementException();
			}
		} else if (down != null) {
			theNode = down.find( theKey);
		} else {
			logger.warn(" NOT FOUND for key:"+theKey.toString());
			//throw new NoSuchElementException();
		}
		return theNode;
	}
	@Override
	 public String toString() {
		StringBuffer strBuf = new StringBuffer();
        SKNode<K, V> bottom = this;
        while (bottom.getDown() != null) {
	           bottom = bottom.getDown();
	    }

	    for (SKNode<K, V> theNode = bottom; theNode != null; theNode = theNode.getUp()) {
	        	strBuf.append('[')
	        		  .append((theNode.getKey() == null) ? " H " : theNode.getKey().toString())
	        		  .append(']');
	    }
        if (bottom.next() != null) {
        	strBuf.append('\n').append(bottom.next().toString());
        }
        return strBuf.toString();
	}
	
	
	
	public boolean insertNode( SKNode<K, V> parent, SKNode<K, V> currentNode, SKNode<K,V> newNode) {
		boolean bRet = true;
		
		
		
		
		return bRet;		
	}
	
	public SKNode<K,V> findByKey(SKNode<K,V> currNode,  K theKey) {
		SKNode<K, V> theNode = null;
		if (currNode == null) {
			logger.warn(" CAN NOT FOUND for key:"+theKey.toString());
			return currNode;
		}
		int compResult = currNode.getKey().compareTo( theKey);
		if ( compResult == 0) {
			logger.info("Find KVNodefor "+ theKey.toString());
			theNode = currNode;
		} else if ( compResult < 0) {
			if ( currNode.next() != null) {
				theNode = findByKey(currNode.next(), theKey);
			} else {
				theNode = findByKey( currNode.down(), theKey);
			}
		} else {
			if ( currNode.down() != null){
				theNode = findByKey( currNode.down(), theKey);
			} else {
				logger.warn(" CAN NOT FOUND for key:"+theKey.toString());
				//throw new NoSuchElementException();
			}
		}
		return theNode;
	}

	
	
	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value();
	}
	public V value() {
		return this.value;
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

	public SKNode<K, V> getUp() {
		return up();
	}
	public SKNode<K, V> up() {
		return this.up;
	}

	public void setUp(SKNode<K, V> up) {
		this.up = up;
	}

	public SKNode<K, V> getDown() {
		return down();
	}	
	public SKNode<K, V> down() {
		return this.down;
	}

	public void setDown(SKNode<K, V> down) {
		this.down = down;
	}

	public SKNode<K, V> getPrevious() {
		return previous();
	}
	public SKNode<K, V> previous() {
		return this.previous;
	}

	public void setPrevious(SKNode<K, V> previous) {
		this.previous = previous;
	}

	public SKNode<K, V> getNext() {
		return next();
	}
	public SKNode<K, V> next() {
		return this.next;
	}

	public void setNext(SKNode<K, V> next) {
		this.next = next;
	}
	
	
	

}
