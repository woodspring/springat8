package woodspring.springat8.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import woodspring.springat8.components.BinaryTree;
import woodspring.springat8.components.IteratorBTree;
import woodspring.springat8.model.AtCommon;
import woodspring.springat8.model.TreeNode;
import woodspring.springat8.service.SpringAtService;
import woodspring.springat8.components.RevIterator;

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
		String retStr = this.printInOrder(binaryTree);
		return retStr;
	}

	@Override
	public String printReverse() {
		String retStr = this.printReverse(binaryTree);
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
	

	private String printInOrder(BinaryTree<Integer> theTree) {
		String retStr = theTree.toString();
		return retStr;
	}
	public String printReverse(BinaryTree<Integer> theTree) {
		String retStr = theTree.printReverse();
		return retStr;
	}
	public BinaryTree<Integer> buildBinaryTree( List<Integer>  intList) {
		return buildBinaryTree( binaryTree, intList);
	}
	private BinaryTree<Integer> buildBinaryTree( BinaryTree<Integer> tree, List<Integer>  intList) {
		intList.stream()
			.forEach( value -> tree.add( value));
		return tree;
	}

	@Override
	public String testBinaryTree() {
		StringBuffer strBuf = new StringBuffer();
		List<Integer> aList = new ArrayList<>();
		aList.add(8);aList.add(6);aList.add(11);aList.add(5);aList.add(15);
		aList.add(1);aList.add(11);aList.add(4);aList.add(13);aList.add(18);
		buildBinaryTree( aList);
		strBuf.append( " inOrder:"+ printInOrder( binaryTree))
		.append(" reverse:"+ printReverse( binaryTree));
		IteratorBTree<Integer> btItr = new IteratorBTree< Integer> ( binaryTree.getHead());
		strBuf.append(" /n\n  iterator:[");
		while( btItr.hasNext()) {
			strBuf.append(" "+ btItr.next().getValue());
		}
		strBuf.append(" ],    revItr:[");
		
		RevIterator<Integer> revItr = new RevIterator< Integer >(binaryTree.getHead());
		while( revItr.hasNext() ) {
			strBuf.append(" "+ revItr.next().getValue());
		}
		strBuf.append(" ]");
		
		logger.info(strBuf.toString());
		
		return strBuf.toString();
	}

	@Override
	public String testBinaryTree2Merge(int base) {
		StringBuffer strBuf = new StringBuffer();
		List<Integer> aList = generateList( base);
//		List<Integer> aList = new ArrayList<>();
//		aList.add(31);aList.add(15);aList.add(35);aList.add(12);aList.add(5);aList.add(38);
//		aList.add(1);aList.add(39);aList.add(34);aList.add(13);aList.add(33);aList.add( 3);
		BinaryTree<Integer> treeA = buildBinaryTree(new BinaryTree<Integer>(), aList);
		RevIterator<Integer> treeA_revItr = new RevIterator< Integer >(treeA.getHead());
		strBuf.append("aTree: "+ treeA.toString());
		
		List<Integer> bList = generateList( base);
//		List<Integer> bList = new ArrayList<>();
//		bList.add(21);bList.add(25);bList.add(18);bList.add(12);bList.add(25);bList.add(28);
//		bList.add(119);bList.add(9);bList.add(24);bList.add( 3);bList.add(27);bList.add(13);
		BinaryTree<Integer> treeB = buildBinaryTree(new BinaryTree<Integer>(), bList);
		RevIterator<Integer> treeB_revItr = new RevIterator< Integer >(treeB.getHead());
		strBuf.append("\n  bTree: "+ treeB.toString());
		strBuf.append("\n A+B and reverse:[ ");
		long start1Time = System.nanoTime();
		while ( treeA_revItr.hasNext() || treeB_revItr.hasNext()) {
			TreeNode<Integer> aNode = (treeA_revItr.hasNext()) ? treeA_revItr.peek() : null;		
			TreeNode<Integer> bNode = (treeB_revItr.hasNext()) ? treeB_revItr.peek() : null;
			if ( aNode != null) {
				if (bNode != null) {
					int comp = aNode.getValue().compareTo( bNode.getValue());
					if (comp < 0) 
						strBuf.append(" "+ treeB_revItr.next().getValue());
					else if ( comp > 0)
						strBuf.append(" "+ treeA_revItr.next().getValue());
				 	else {
				 		strBuf.append(" "+ treeA_revItr.next().getValue());
				 		treeB_revItr.next();
				 	}
				} else { // bNode == null no bNode
					strBuf.append(" "+ treeA_revItr.next().getValue());
				}
			} else { //aNode == null, no aNode
				strBuf.append(" "+ treeB_revItr.next().getValue());
			}
		}
		long end1Time = System.nanoTime();
		strBuf.append(" ] \n merge two tree:[");
		long start2Time = System.nanoTime();
		IteratorBTree<Integer> iterBTree = new IteratorBTree<>( treeA.getHead());
		while ( iterBTree.hasNext()) {
			treeB.add(iterBTree.next().getValue() );
		}
		RevIterator<Integer> treeBrevItr = new RevIterator< Integer >(treeB.getHead());
		int size=0;
		while (treeBrevItr.hasNext()) {
			strBuf.append(" "+ treeBrevItr.next().getValue());
			size++;
		}
		long end2Time = System.nanoTime();

		strBuf.append("]");
		StringBuffer strBuf2 = new StringBuffer();
		//logger.info("result:["+strBuf.toString()+"");
		
		long time1 = end1Time -start1Time; String t1Str = Long.valueOf( time1).toString();
		long time2 = end2Time -start2Time; String t2Str = Long.valueOf( time2).toString();
		logger.info("first  start:"+ start1Time+ "  end:"+ end1Time+ " use:"+time1);
		logger.info("second start:"+ start2Time+ "  end:"+ end2Time+ " use:"+time2);
		double delta = Double.valueOf(t2Str).doubleValue()  / Double.valueOf(t1Str).doubleValue() ;
		double percent = (Double.valueOf(t2Str).doubleValue() -  Double.valueOf(t1Str).doubleValue())  / Double.valueOf(t1Str).doubleValue() ;
		//System.out.println("delta:["+ delta+"] improve :" + percent);

		logger.info("base:"+ base+" two after merge:"+size+"\n  diff:"+ (time2 - time1)+" delta:["+ delta+ "] improve %:" + percent* 100);
		strBuf2.append("first  start:"+ start1Time+ "  end:"+ end1Time+ " use:"+time1)
		.append("\n second start:"+ start2Time+ "  end:"+ end2Time+ " use:"+time2)
		.append("\n base:"+ base+" two after merge:"+size+"\n  diff:"+ (time2 - time1)+" delta:["+ delta+ "] improve %:" + percent* 100);

		return strBuf.toString();
	}
	
	private List<Integer> generateList( int times) {
		List<Integer> retList = new ArrayList<>();

		for (int ind =0 ; ind < times; ind++) {
			Integer nextInt = Integer.valueOf( random.nextInt(AtCommon.AT_NUMBER));
			retList.add( nextInt);
		}
		return retList;
	}


}
