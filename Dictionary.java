import java.io.BufferedWriter;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;

class Node { 
    int data, height; 
    Node left, right; 
  
    Node(int d) { 
        data = d; 
        height = 1; 
    } 
} 
public class Dictionary { 
    int i;
    int input;
    Node root; 
int height(Node N){ 
        if (N == null){ 
            return 0; 
        }
        return N.height; 
    } 
    int max(int a, int b) { 
        return (a > b) ? a : b; 
    } 
    Node rightRotate(Node y) { 
        Node x = y.left; 
        Node T2 = x.right; 
        x.right = y; 
        y.left = T2; 
        y.height = max(height(y.left), height(y.right)) + 1; 
        x.height = max(height(x.left), height(x.right)) + 1; 
        return x; 
    } 
    Node leftRotate(Node x) { 
        Node y = x.right; 
        Node T2 = y.left; 
  
        y.left = x; 
        x.right = T2; 
  
        x.height = max(height(x.left), height(x.right)) + 1; 
        y.height = max(height(y.left), height(y.right)) + 1; 

        return y; 
    } 
  
    int getBalance(Node N) { 
        if (N == null){ 
            return 0; }
  
        return height(N.left) - height(N.right); 
    } 
  
    Node insert(Node node, int data) { 
  
        if (node == null) {
            return (new Node(data)); 
  }
        if (data < node.data) {
            node.left = insert(node.left, data); 
        }
        else if (data > node.data){ 
            node.right = insert(node.right, data); 
            return node; 
        }
  
        node.height = 1 + max(height(node.left),height(node.right)); 
  
        int balance = getBalance(node); 
  
        if (balance > 1 && data < node.left.data) {
            return rightRotate(node); 
}
        if (balance < -1 && data > node.right.data) {
            return leftRotate(node);
         }
  
        if (balance > 1 && data > node.left.data) { 
            node.left = leftRotate(node.left); 
            return rightRotate(node); 
        } 
  
        if (balance < -1 && data < node.right.data) { 
            node.right = rightRotate(node.right); 
            return leftRotate(node); 
        } 
  
        return node; 
    } 
  //DISPLAY ALL ELEMENTS OF DICTIONARY
    void preOrder(Node node) { 
        if (node != null) { 
            System.out.print(node.data + " "); 
            preOrder(node.left); 
            preOrder(node.right); 
        } 
    } 
    public void ask(){
        Scanner s=new Scanner(System.in);
        int arr[]=new int[100];
        //READINF DATA FROM FILE
        try {
              File F = new File("Word.txt");
              Scanner S = new Scanner(F);  
              while (S.hasNextLine()) {
                int info = S.nextInt();
                arr[i]=info;
                i++;
                root=insert(root,info); 
              }
              S.close();
              preOrder(root);
              while(input!=4){
                System.out.println();
                System.out.println("1. Search From Dictionary");
                System.out.println("2. Add new Element");
                System.out.println("3. Delete an Element from Dictionary");
                System.out.println("4. Exit");
                input = s.nextInt();
                if(input==1){
                    System.out.println("Enter Key you would like to search?: ");
                    int key=s.nextInt();
                    search(arr,key);
                }
                if(input==2){
                    System.out.println("Enter input in the dictionary: ");
                    int in=s.nextInt();
                    // userInput(in);
                    insert(root,in);
                    System.out.println("Data entered successfully!");
                }
                if(input==3){
                    System.out.println("Enter element you'd like to delete from dictionary: ");
                    int in=s.nextInt();
                    delete(in);
                }
        }
          s.close();
            } catch (Exception e) {
              System.out.println("An error occurred.");
              e.printStackTrace();
            }
        
    }
    //SEARCH AN ELEMENT FROM DICTIONARY
    public void search(int arr[],int userKey){
        while(root!=null){
            if(root.data==userKey){
                System.out.println("Key is found: ");
                break;
            }
           else if(userKey>root.data){
             while(root.right!=null){
                 if(root.data==userKey){
                     System.out.println("Key is found: ");
                     break;
                 }
                 root=root.right;
             }
            }
            else if(userKey<root.data){
                while(root.left!=null){
                    if(root.data==userKey){
                        System.out.println("Key is found: ");
                        break;
                    }
                    root=root.left;
                }
            }
            else{
                System.out.println("Unable to find the Key!");
            }
        }
    }
    //INSERT AN ELEMENT IN DICTIONARY
    public void userInput(int in){
        try{
            File F = new File("Word.txt");
            FileWriter fw=new FileWriter(F,true);
            BufferedWriter br=new BufferedWriter(fw);
            br.write("\r\n"+in);
            br.close();
            fw.close();   
            insert(root, in);
         }
          catch(Exception e){
          e.printStackTrace();
         }
    }
    //DELETE AN ELEMENT FROM DICTIONARY
    public void delete(int in){
        while(root!=null){
            if(root.data==in){
                root.data=0;
                System.out.println("Key deleted successfully!");
                break;
            }
            else if(in<root.data){
                while(root.left!=null){
                    if(root.data==in){
                        root.data=0;
                        System.out.println("Key deleted successfully!");
                        break;
                    }
                    root=root.left;
                }
            }
            else if(in>root.data){
                while(root.right!=null){
                    if(root.data==in){
                        root.data=0;
                        System.out.println("Key deleted successfully!");
                        break;
                    }
                    root=root.right;
                }
               }
        }
    }
    public static void main(String[] args){ 
        Dictionary tree = new Dictionary(); 
        tree.ask();

        
    } 
}