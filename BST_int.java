
package com.DS;


    class BSTNode1<T> {

public int key;

public T data;

public BSTNode1<T> left, right; 

public BSTNode1 (int key, T data) {

this.key = key;

this.data = data;

left=right= null;

 

}}

public class BST_int<T> {

  private BSTNode1<T> root, current;

  public BST_int() {

    current= root=null;

  }

  public boolean empty() {

return root==null;

}

public boolean full(){

return false;

}

public T retrieve() {

return current.data;

}

public boolean findKey(int k) {

BSTNode1<T> p=root;

while (p !=null){

current=p;

if (k==(p.key)){

return true;

}else if (k>p.key){

p = p.right;

}else{

p=p.left;

}

 

}

return false;

}

 

public boolean insert (int k, T val) {

if (root==null) {

current =root=new BSTNode1<T> (k, val);

return true;

} 

 BSTNode1<T> p=root;

while (p!= null) {

    current=p;

if (k<p.key) {

p=p.left;

}else {

p = p.right;

}

}

BSTNode1<T> tmp = new BSTNode1<T>(k, val) ;

if (k<current.key) {

current.left =tmp;

}else{

    current.right=tmp;

}

current =tmp;

return true;

}

public void display_decreasing()

{

if(root==null)

System.out.println ("empty tree");

else

System.out.println("DocID     scrore");

decreasing(root);
}
private void decreasing (BSTNode1 p){

if (p==null) return;

decreasing(p.right);

System.out.print(p.data) ;

System.out.println("      "+ p. key) ;

decreasing(p.left);

 

}

 

}


