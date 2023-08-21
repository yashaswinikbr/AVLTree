import java.util.List;

class AVL
{  
    //inside the tree class declare the global variables
      // this is the Node class to create a node of the avl tree
    public class Node {
	  
		int value;
	    int height;
	    Node left;
	    Node right;
	    // Node class constructor 
        public Node(int key) {
			this.value = key;
		}
	}
    public Node root = null;

    // this method is called Initialization to create a new avltree 
     
	public static avltree Initialize() {
		avltree init_avl_tree = new avltree();
		return init_avl_tree;
	}

    // this is the height method to return height of the this_node avltree
     
    public int height() {
        return root == null ? -1 : root.height;
    }


    // this is the Search method to search a key in the avltree 
     
    public String search(int key) {
        Node this_node = root;
        while (this_node != null) {
            if (this_node.value == key) 
                return Integer.toString(this_node.value);
                this_node = this_node.value < key ? this_node.right : this_node.left;
        }
        return "NULL";
    }
    
    // this is the  Search method to search keys between t1 and t2
     
    public void searchInGivenRange( Node node, int t1, int t2, List<String> result_search_avl ){
        if(node == null || (t1>t2)) return;
        int d = node.value;
        if( d >= t1 && d <= t2 ) result_search_avl.add(String.valueOf(d));
        if( t1 < d ) searchInGivenRange( node.left, t1, t2, result_search_avl );
        if( t2 > d ) searchInGivenRange( node.right, t1, t2, result_search_avl );
    }

    // this is the Insert method to insert a key into the avltree 
     
    public void insert(int key) {
        root = insert(root, key);
    }

    // this is the insert  method to insert a key in avltree: helper
     
    private Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        } else if (node.value > key) {
            node.left = insert(node.left, key);
        } else if (node.value < key) {
            node.right = insert(node.right, key);
        } else {
            throw new RuntimeException("duplicate Key is generated");
        }
        return return_balance_tree_node(node);
    }

    // this method to return the smallest value of the leftsubtree
     
    private Node most_left_child(Node node) {
        Node this_node = node;
        while (this_node.left != null) {
            this_node = this_node.left;
        }
        return this_node;
    }
    // this is the Delete method to delete a key in the avltree 
     
    public void delete(int key) {
        root = delete(root, key);
    }

    // this is the delete method to delete a key in avltree: helper
     
    private Node delete(Node node, int key) {
        if (node == null) {
            return node;
        } else if (node.value > key) {
            node.left = delete(node.left, key);
        } else if (node.value < key) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left == null) ? node.right : node.left;
            } else {
                Node most_Left_Child = most_left_child(node.right);
                node.value = most_Left_Child.value;
                node.right = delete(node.right, node.value);
            }
        }
        if (node != null) {
            node = return_balance_tree_node(node);
        }
        return node;
    }

    // this rotate_right method to rotate tree right around the given node
     
    private Node rotate_right(Node u) {
        Node a = u.left;
        Node b = a.right;
        a.right = u;
        u.left = b;
        updated_avl_height(u);
        updated_avl_height(a);
        return a;
    }

    // this rotate_left method to rotate tree left around the given node
     
    private Node rotate_left(Node u) {
        Node a = u.right;
        Node b = a.left;
        a.left = u;
        u.right = b;
        updated_avl_height(u);
        updated_avl_height(a);
        return a;
    }


    // return_balance_tree_node method to update balance factor of given node
     
    private Node return_balance_tree_node(Node k) {
        updated_avl_height(k);
        int balance_avl = return_B_Factor(k);
        if (balance_avl > 1) {
            if (height(k.right.right) > height(k.right.left)) {
                k = rotate_left(k);
            } else {
                k.right = rotate_right(k.right);
                k = rotate_left(k);
            }
        } else if (balance_avl < -1) {
            if (height(k.left.left) > height(k.left.right)) {
                k = rotate_right(k);
            } else {
                k.left = rotate_left(k.left);
                k = rotate_right(k);
            }
        }
        return k;
    }
    
   // the height method to return height of the tree rooted at the given node
     
   private int height(Node n) {
    return n == null ? -1 : n.height;
}

    
    // this will update the height method to update height of tree rooted at the given node
     
    private void updated_avl_height(Node n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    
    // this  return_B_Factor method to return balance factor of the given node
     
    public int return_B_Factor(Node n) {
        return (n == null) ? 0 : height(n.right) - height(n.left);
    }
    
    // this will print _preorder _tree
     
    public void print_avl_tree(Node root){
    	if(root==null) 
    		return;
    	System.out.println(root.value);
    	print_avl_tree(root.left);
    	print_avl_tree(root.right);
    	
    }

   
}

