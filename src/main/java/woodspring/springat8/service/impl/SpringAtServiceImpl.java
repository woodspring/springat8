package woodspring.springat8.service.impl;

import java.awt.List;
import java.util.ArrayList;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import woodspring.springat8.components.BinaryTree;
import woodspring.springat8.components.IteratorBTree;
import woodspring.springat8.model.AtCommon;
import woodspring.springat8.service.SpringAtService;

@Service
public class SpringAtServiceImpl implements SpringAtService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	BinaryTree<Integer> binaryTree;
	
	protected Random random = new Random(); //AtCommon.AT_NUMBER);

	@Override
	public String runBinaryTreeService(int number) {
		ArrayList<Integer> numArr = new ArrayList<Integer>();
		for (int ind =0 ; ind < number; ind++) {
			Integer nextInt = Integer.valueOf( random.nextInt(AtCommon.AT_NUMBER));
			//logger.info(" add Integer:"+ nextInt.toString());
			numArr.add( nextInt);
			binaryTree.add( nextInt);
			logger.info("- "+ ind+" - tree:["+ binaryTree.toString()+"]");
		}
		StringBuffer strBuf = new StringBuffer();
		strBuf.append( numArr).append(" to binarryTree")
			  .append(" prefix :[ ").append( binaryTree.printPrefix()).append("]\n\n")
			  .append(" inorder:[ ").append( binaryTree.toString()).append("] \n\n")
			  .append(" postfix:[ ").append( binaryTree.printPostfix()).append("] \n\n");
		logger.info(" result:"+ strBuf.toString());
		return strBuf.toString();
	}

	@Override
	public String findBTreeService(int theNum) {
		String retStr = binaryTree.find(Integer.valueOf( theNum)); 
		return retStr;
	}

	@Override
	public String convertBinaryTree2DDList() {
		
		if ( binaryTree.isEmpty()) {
			logger.info(" BTree is empty, generate 10; ["+ runBinaryTreeService(3)+"]");
		}
		logger.info(" ------ start converBinaryTree2DDList ---");
		binaryTree.setDDLHead( binaryTree.convertBTree2DLList( binaryTree.getHead()));
		String retStr = binaryTree.printDLList( binaryTree.getDDLHead());
		logger.info( "Double Linked List:" + retStr);
		
		return retStr;
	}

	@Override
	public String printNode(int index) {
		StringBuffer strBuf = new StringBuffer();
		IteratorBTree<Integer> iterBTree = new IteratorBTree<>( binaryTree.getHead());
		while ( iterBTree.hasNext() && index >0) {
			strBuf.append(" "+ iterBTree.next().getValue()+" ");
			index--;
		}
		logger.info(" printNode: ["+ strBuf.toString()+" ]");
		return strBuf.toString();
	}

	@Override
	public String printInOrder() {
		String retStr = binaryTree.toString();
		return retStr;
	}

	@Override
	public String printReverse() {
		String retStr = binaryTree.printReverse();
		return retStr;
	}

	@Override
	public String mergeTwoBinaryTree(int treeA, int treeB) {
		runBinaryTreeService(treeA);
		String orgATree = binaryTree.toString();
		logger.info("                         aTree:["+binaryTree.toString()+"]");
		BinaryTree<Integer> bTree =new BinaryTree<>();
		ArrayList<Integer> numArr = new ArrayList<Integer>();
		for (int ind =0 ; ind < treeB; ind++) {
			Integer nextInt = Integer.valueOf( random.nextInt(AtCommon.AT_NUMBER));
			//logger.info(" add Integer:"+ nextInt.toString());
			numArr.add( nextInt);
			bTree.add( nextInt);
			logger.info("- "+ ind+" - bTree:["+ bTree.toString()+"]");
		}
		String orgBTree = bTree.toString();
		logger.info("                          bTree:["+bTree.toString()+"]");
		IteratorBTree<Integer> iterBTree = new IteratorBTree<>( bTree.getHead());
		while ( iterBTree.hasNext()) {
			binaryTree.add(iterBTree.next().getValue() );
		}
		logger.info("     aTree:["+orgATree+"]");
		logger.info("     bTree:["+orgBTree+"]");
		logger.info("     merged              aTree:["+binaryTree.toString()+"]");
		
		String revStr = binaryTree.printReverse();
		logger.info("  REVERSE                aTree:["+revStr+"]");

		
		return revStr;
	}

}
