import java.util.Scanner;
import java.io.*;
import java.util.List;
import java.util.ArrayList;


// this is the Main avltree class to insert, delete and search in an avltree
 public class avltree {
    // this contains the main method 
    public static void main(String[] args) throws FileNotFoundException {

        if(args.length == 0) {
            System.out.println("File is not found!");
            System.exit(1);
        }

        File file_input = new File(args[0]);
        String final_result = "";
        AVL tree = new AVL();
        Scanner input_file_avl = new Scanner(file_input);
        
        String[] operations = {"Initialize", "Insert", "Delete", "Search"};

        // this will Process input from the input_file_avl to call the appropriate methods
         while (input_file_avl.hasNext()) {
        	String input_text_avl = input_file_avl.next();
            String match = "default";
            for (int i = 0; i < operations.length; i++) {
                if (input_text_avl.contains(operations[i])) {
                    match = operations[i];
                    break;
                }
            }
            switch(match){
                case "Initialize":
                    tree = new AVL();
                    break;
        	    case "Insert": 
                    tree.insert(Integer.parseInt(input_text_avl.substring(input_text_avl.indexOf("(")+1, input_text_avl.indexOf(")"))));
                    break;
                case "Delete": 
                    tree.delete(Integer.parseInt(input_text_avl.substring(input_text_avl.indexOf("(")+1, input_text_avl.indexOf(")"))));
                    break;
                case "Search": 
                    String value = input_text_avl.substring(input_text_avl.indexOf("(")+1, input_text_avl.indexOf(")"));
                    if(value.contains(",")) {
                        List<String> search_result = new ArrayList<>();
                        String[] value_result = value.split(",");
                        tree.searchInGivenRange(tree.root,Integer.parseInt(value_result[0]),Integer.parseInt(value_result[1]), search_result);
                        String query_List;
                        if(search_result.isEmpty()) query_List = "NULL";
                        else query_List = String.join(",", search_result);
                        final_result += query_List + "\n";
                    }
                    else {
                        String ans = tree.search(Integer.parseInt(value));
                        final_result += ans + "\n";
                    }
                    break;
                
            }
        		
        }

        input_file_avl.close();
        output_file_avl(final_result, "output_file.txt");
    	
    }


    // this is the output_file_avl to print the output
	private static void output_file_avl( String text, String fileName )
    {
    try
    {
       File fileinput = new File( fileName );
      // if the file doesn't exists, then it will create it 
       if ( ! fileinput.exists( ) )
       {
        fileinput.createNewFile( );
       }

       FileWriter file_writer_txt = new FileWriter( fileinput.getAbsoluteFile( ) );
       BufferedWriter buff_writer_txt = new BufferedWriter( file_writer_txt );
       buff_writer_txt.write( text );
       buff_writer_txt.close( );
       // To test the program
       System.out.println("Writing is done to " + fileName);  
    }
    catch( IOException exp )
    {
    System.out.println("Error: " + exp);
    exp.printStackTrace( );
    }
   } 
	
    
}