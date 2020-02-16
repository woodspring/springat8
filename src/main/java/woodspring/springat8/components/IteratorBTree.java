package woodspring.springat8.components;

import java.util.Stack;
import woodspring.springat8.model.TreeNode;


public class IteratorBTree<T extends Comparable<T>> {
	private Stack<TreeNode<T>> stack = new Stack<>();
	private TreeNode<T> head;
	public IteratorBTree(TreeNode head) {
		this.head = head;
		push(head);
	}
	
	private void push(TreeNode<T> currNode) {
		while( currNode != null) {
			stack.push( currNode);
			currNode = currNode.getLeft();
		}
	}
	
	public boolean hasNext() {
		return !stack.isEmpty();
	}
	
	public TreeNode<T> next() {
		TreeNode<T> tNode = stack.pop();
		push(tNode.getRight());
		return tNode;
	}
	public boolean reset() {
		stack.clear();
		stack.push( head);
		return true;
	}

}
