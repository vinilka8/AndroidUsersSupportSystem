package ie.gmit.computing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.media.Image;
import android.text.Editable;

public class Node implements Serializable{
	/*
	 * IDENTIFICATOR, NAME OF DERICTORY
	 * object for nodes but not class, have to include ID and name
	 * object for Photos
	 */
	private int root; // id of my parent
	private int id; //my id
	private String name; //my name
	private String chilName;
	private Node parent;
	private List<Node> children = new ArrayList<Node>();
	private Image image= null;

	public Node(String name, Node parent) {
		super();
		this.name = name;
		this.parent = parent;
	}
	
	public Node(String chilName){
		this.chilName = chilName;
	}

	public Node() {
		// TODO Auto-generated constructor stub
	}
	public void setParent(Node parent){
		this.parent = parent;
	}
	
	public Node getParent(){
		return this.parent;
	}
	
	public Node getChild(int child) {
		return children.get(child);
	}

	public void addChild(Node child){
		children.add(child);
		child.setParent(this);
	
	}
	
	public void removeChild(Node child){
		children.remove(child);
	}
	
	public void insertChild(Node next, Node existing){
		existing.setParent(next);
		next.setParent(this);
		
	}
	
	public Node[] children(){
		//return (Node[]) children.toArray();
		Node[] temp = new Node[children.size()];
		for(int i = 0; i < children.size(); i++){
			temp[i] = children.get(i);
		}
		return temp;
	}
	
	public boolean isLeaf(){
		return children.size() == 0;
	}

	public boolean isRoot(){
		return this.parent == null;
	}
	
	public boolean hasImage(){
		return this.image != null;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Node> getChildren() {
		// TODO Auto-generated method stub
		return children;
		
	}
	

}
