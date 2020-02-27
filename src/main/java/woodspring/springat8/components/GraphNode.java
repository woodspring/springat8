package woodspring.springloop.functionpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.ArrayList;
import java.util.Iterator;

import woodspring.springloop.model.GNode;


@Component
public class GraphNode { //<T extends Comparable<T>> {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private ConcurrentSkipListMap<Integer, ArrayList<GNode>>  graphNodes = new  ConcurrentSkipListMap<Integer, ArrayList<GNode>>();
	int totalNode =0;
	
	
	public GraphNode( ) {
		
	}
	
	public boolean addNode(Integer fromId, GNode node) {
		if (graphNodes.containsKey( fromId)) {
			ArrayList<GNode> theList = graphNodes.get( fromId);
			theList.add( node);
			graphNodes.replace(fromId,  theList);
		} else {
			ArrayList<GNode> theList = new ArrayList<GNode>();
			theList.add( node);
			graphNodes.putIfAbsent(fromId,theList);
		}
		totalNode++;
		return true;
	}
	
	public boolean addNode( Integer fromNodeId, Integer theNodeId, int theWeight, boolean bidirect) {
		GNode theNode = new GNode(fromNodeId, theNodeId, theWeight);
		addNode(fromNodeId, theNode);		
		return true;
	}
	
	
	
	public String toString_DFS(Integer startNode) {
		StringBuffer strBuf = new StringBuffer();
		ArrayList<Integer> visited = new ArrayList<Integer>();
		visited.add( startNode);
		visited = travelNode_DFS( startNode, visited);
		visited.stream().forEach( item -> strBuf.append(" "+ item+" "));
		return strBuf.toString();	
	}
	
	
	public  ArrayList<Integer> travelNode_DFS(Integer currId, ArrayList<Integer> visited) {
		if (find( currId) == null) return visited;
		logger.info("travelNode_DFS -> currId:"+ currId+" visited:["+ visited.toString()+"]" );
		NodeItr<GNode> aItr = new NodeItr( find( currId));
		while ( aItr.hasNext()) {
			GNode gNode = aItr.next();
			if (gNode == null) break;
			if ( visited.contains( gNode.getNodeId() )) continue;
			visited.add( gNode.getNodeId());
			travelNode_DFS( gNode.getNodeId(), visited);			
		}
		 return visited;
	}
	
	public String checkCycle(Integer startNode) {
		
		StringBuffer strBuf = new StringBuffer();
		ArrayList<Integer> visited = new ArrayList<Integer>();
		ArrayList<Integer> currVisiting = new ArrayList<Integer>();
		ArrayList<Integer> nodeCycle = new  ArrayList<Integer>();
		visited.add( startNode);
		ArrayList<GNode> firstLevel = find( startNode);
		for (GNode theNode : firstLevel) {
			logger.info("checkCycle level 1:"+ theNode.toString());
			currVisiting.add( theNode.getFromNodeId());
			ArrayList<Integer> cycleNode = checkCycle( theNode.getNodeId(), visited, currVisiting, nodeCycle );
			currVisiting.clear();			
		}
		visited.stream().forEach( item -> strBuf.append(" "+ item+" "));
		strBuf.append(" | cycle node: ");
		nodeCycle.stream().forEach( item -> strBuf.append(" " + item +" "));
		logger.info("checkCycle:"+ startNode+" visited:"+ strBuf.toString()  );
		return strBuf.toString();	
		
	}
	
	
	public ArrayList<Integer> checkCycle(Integer currId, ArrayList<Integer> visited, ArrayList<Integer> currVisiting, ArrayList<Integer> cycleNode)  {
		logger.info("checkCycle -> currId:"+ currId+" visited:["+ visited.toString()+"] currVisiting:["+currVisiting+"] cycleNode:["+cycleNode+"] " );
		if ( find(currId) == null) {
			// not in graphic, mean end
			logger.info("checkCycle -> currId:"+ currId+" ---  find NULL: "+ currId);
			return cycleNode;
		}
		NodeItr<GNode> nodeItr = new NodeItr<GNode>( find(currId));
		if (!visited.contains( currId)) visited.add( currId);
		if (currVisiting.contains( currId)) {
			logger.info(
					"checkCycle -> in cycle, currId:" + currId+" currVisiting:"+ currVisiting.toString());// + " theNode:[from:" + theNode.toString() + "]");
			// it is cycle loop
			cycleNode.add(currId);
			cycleNode.add( -99);
			cycleNode.add( -99);
			//return cycleNode;
		} else {
			currVisiting.add( currId);
			while (nodeItr.hasNext()) {
				GNode theNode = nodeItr.next();
				if (!visited.contains(theNode.getNodeId()))
					visited.add(theNode.getNodeId());
				
				if (currVisiting.contains(theNode.getNodeId())) {
					logger.info(
							"checkCycle -> in cycle, currId:" + currId + " theNode:[from:" + theNode.toString() + "]");
					// it is cycle loop
					cycleNode.add(currId);
					cycleNode.add(theNode.getFromNodeId());
					cycleNode.add(theNode.getNodeId());
					break;
				}
				currVisiting.add(theNode.getNodeId());
				cycleNode = checkCycle(theNode.getNodeId(), visited, currVisiting, cycleNode);

			}
		}
		
		
		return cycleNode;
		
		
	}
	
	
	public String toString_BFS(Integer startNode) {
		StringBuffer strBuf = new StringBuffer();
		ArrayList<Integer> visited = new ArrayList<Integer>();
		visited.add( startNode);
		visited = travelNode_BFS( startNode, visited);
		visited.stream().forEach( item -> strBuf.append(" "+ item+" "));
		return strBuf.toString();	
	}
	
	
	public  ArrayList<Integer> travelNode_BFS(Integer currId, ArrayList<Integer> visited) {
		logger.info("travelNode_BFS-0--- -> currId:"+ currId+" visited:["+ visited.toString()+"]" );
		if (find( currId) == null) return visited;
		ArrayList<Integer> currVisit = new ArrayList<Integer>();
		logger.info("travelNode_BFS -> currId:"+ currId+" visited:["+ visited.toString()+"]" );
		NodeItr<GNode> aItr = new NodeItr( find( currId));
		while ( aItr.hasNext()) {
			GNode gNode = aItr.next();
			if (gNode == null) break;
			if ( visited.contains( gNode.getNodeId() )) continue;
			visited.add( gNode.getNodeId());
			currVisit.add( gNode.getNodeId());
		}
		//aItr.reset();
		aItr = new NodeItr( find( currId));
		logger.info("travelNode_BFS -9-> currId:"+ currId+" visited:["+ visited.toString()+"] visiting:["+ currVisit.toString()+"]" );
		while ( aItr.hasNext()) {
			GNode gNode = aItr.next();
			if (gNode == null) break;
			if ( visited.contains( gNode.getNodeId() ) && !(currVisit.contains( gNode.getNodeId()))) continue;
			if (!(currVisit.contains( gNode.getNodeId()))) 
					visited.add( gNode.getNodeId());
			travelNode_BFS( gNode.getNodeId(), visited);			
		}
		 return visited;
	}
	
	
	
	private ArrayList<GNode> find(Integer theId) {
		ArrayList<GNode> theList = graphNodes.get(theId);	
		return theList; 		
	}
	
	
	public class NodeItr<GNode> implements Iterator< GNode> {
		ArrayList<GNode> stack;
		int size =0;
		int cursor = 0;
		public NodeItr( ArrayList<GNode> theStack) {
			this.stack = theStack;
			if (theStack == null) size = 0;
			else size = theStack.size();
			cursor = 0;
		}
		public boolean hasNext() {
			return (cursor < size);			
		}
		public GNode next() {
			if (cursor > size) return null;
			return stack.get( cursor++);
		}
		public boolean reset() {
			cursor = 0;
			return true;
		}
	}

}
