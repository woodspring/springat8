package woodspring.springat8.components;

import java.util.Random;

import org.springframework.stereotype.Component;

import woodspring.springat8.model.SKNode;


@Component
public class SkipList<K extends Comparable<K>, V>  {
	private SKNode<K, V> head = new SKNode<K,V>(null, null, 0);
	final static Random random = new Random();
	
	public V find(K theKey) {
		return head.find( theKey).getValue();
	}

	public void insert(K theKey, V theValue) {
		int aLevel =0;
		while( random.nextBoolean()) aLevel++;
		while ( head.getLevel() < aLevel) {
			 SKNode<K, V> newHead = new SKNode<K,V>( theKey, theValue, head.getLevel()+1);
			 head.setUp(newHead);
			 newHead.setDown( head);
			 head = newHead;
		}
		head.insert(theKey,  theValue,  aLevel,  null);
	}
	
	
	public void delete(K theKey) {
		for ( SKNode<K,V> aNode = head.find(theKey); aNode != null; aNode = aNode.getDown()) {
			aNode.getPrevious().setNext( aNode.getNext());
			if ( aNode.getNext() != null) {
				aNode.getNext().setPrevious( aNode.getPrevious());
			}
		}
		
		while( head.getNext() == null) {
			head = head.getNext();
			head.setUp( null);
		}
	}
	
	
	public SKNode<K, V> findByKey(K theKey) {
		SKNode<K, V> retNode = head.findByKey( head, theKey);
		//return (retNode == null) ? null : retNode.getValue();
		return retNode;
	}
	
	@Override
	public String toString() {
		return head.toString();
	}
}
