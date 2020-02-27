package woodspring.springloop.model;

import java.util.ArrayList;

public class GNode<T> implements Comparable<T>{
	Integer gNodeId;
	int weight;
	Integer fromNodeId;
	

	public GNode(Integer comeNodeId, Integer theNodeId, int weight) {
		this.fromNodeId = comeNodeId;
		this.gNodeId = theNodeId;
		this.weight = weight;
	}


	public Integer getNodeId() {
		return gNodeId;
	}


	@Override
	public String toString() {
		return "GNode [gNodeId=" + gNodeId + ", weight=" + weight + ", fromNodeId=" + fromNodeId + "]";
	}


	public void setNodeId(Integer gNodeId) {
		this.gNodeId = gNodeId;
	}


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}


	public Integer getFromNodeId() {
		return fromNodeId;
	}


	public void setFromNodeId(Integer fromNodeId) {
		this.fromNodeId = fromNodeId;
	}


	@Override
	public int compareTo(T gNode) {
		return (gNodeId.compareTo( ((GNode)gNode).getNodeId() ));
		
	}

}
