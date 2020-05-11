import java.util.Stack;
class Node {
	Node left, right;
	int data, height;

	//default constructor
	Node() {
		left = null;
		right = null;
		data = 0;
		height = 0;
	}

	//single parameterized constructor
	Node(int data) {
		left = null;
		right = null;
		this.data = data;
		height = 0;
	}
	 boolean isLeaf() {
      return left == null ? right == null : false;
    }
}

//helper class
class Helper {
	private Node root;
	Helper(){
		root = null;
	}
	//work as a helper to insert data
	public void insert(int data) {
		root = insert(data, root);
	}
	//to return the height
	private int height(Node t)
    {
         return t == null ? -1 : t.height;
    }
    //to return the greater height of node
    private int max(int lhs, int rhs) {
    	return lhs > rhs ? lhs : rhs;
    }
	//actually insert a data in a Balanced tree
	private Node insert(int data, Node t) {
		if(t==null) //check root is null
			t = new Node(data);
		else if(data<t.data) { //comparing parent node data with this data
			t.left = insert(data, t.left);
			if((height(t.left) - height(t.right)) == 2) {
				if (data < t.left.data)
                    t = rotateWithLeftChild(t);
                else
                    t = doubleWithLeftChild(t);
			} 
		}
		else if (data > t.data)
        {
            t.right = insert(data, t.right);
            if (height(t.right) - height(t.left) == 2)
                if (data > t.right.data)
                    t = rotateWithRightChild(t);
                else
                    t = doubleWithRightChild(t);
        } 
		else {
		}
		t.height = max(height(t.left), height(t.right)) + 1;
		return t;
	}
	//to balanced height
	private Node rotateWithLeftChild(Node k2)
    {
        Node k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = max(height(k2.left), height(k2.right)) + 1;
        k1.height = max(height(k1.left), k2.height) + 1;
        return k1;
    }
    private Node rotateWithRightChild(Node k1)
    {
        Node k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = max(height(k1.left), height(k1.right)) + 1;
        k2.height = max(height(k2.right), k1.height) + 1;
        return k2;
    }
    private Node doubleWithLeftChild(Node k3)
    {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }
    private Node doubleWithRightChild(Node k1)
    {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }
    //helper for search
    public boolean search(int data)
    {
        return search(root, data);
    }
 	//actually search
    private boolean search(Node t, int data)
    {
        boolean found = false;
        while ((t != null) && !found)
        {
            int tval = t.data;
            if (data < tval)
                t = t.left;
            else if (data > tval)
                t = t.right;
            else
            {
                found = true;
                break;
            }
            found = search(t, data);
        }
        return found;
    }
	//post order traversal of balanced tree using stack
	public void postOrderWithoutRecursion() {
	 	if(root!=null) {
		    Stack<Node> nodes = new Stack<>();
		    nodes.push(root);

		    while (!nodes.isEmpty()) {
		      Node current = nodes.peek();
		      if (current.isLeaf()) {
		        Node node = nodes.pop();
		        System.out.printf("%s ", node.data);
		      }
		       else {

			        if (current.right != null) {
			          nodes.push(current.right);
			          current.right = null;
			        }

			        if (current.left != null) {
			          nodes.push(current.left);
			          current.left = null;
			        }
		      }

		    }
	    }
    }

}

//Main class
public class AVLTree {
	public static void main(String[] args) {
		int ch = 0; //for choice of user
		//create the object of Helper class
		Helper hl = new Helper();
		//create the scanner class object
		java.util.Scanner sc = new java.util.Scanner(System.in);
		//welcome
		System.out.println("\n\n\tWelcome to AVL TREE Program by Mohd Umar.\n\n");
		while(true) {
		System.out.println("\n\nEnter the Choice -: ");
		System.out.print("1.Insert \n2.Search \n3.PostOrderTraversal(Display) \n:: ");
		ch = sc.nextInt();
		//switch case for run program according to choice of user
		switch(ch) {
			case 1: System.out.print("Enter the Data -: ");
					hl.insert(sc.nextInt());
			break;
			case 2: System.out.print("Enter the Data to Search -: ");
					System.out.println(hl.search(sc.nextInt())); //if found print true otherwise false.
			break;
			case 3: hl.postOrderWithoutRecursion(); //without recursion post order traversal
			break;
			default: System.out.println("\tWrong Choice !...");
		}
	}
	}
}