/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package argonlexer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author dyhar
 */
public class Main {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
        // TODO code application logic here
        NewLexer lex = new NewLexer();
        if (args.length > 0) {
            try {
                lex.Analyze(new File(args[0]));
                
            } catch (FileNotFoundException fnfe) {
                System.out.println("File not found.");
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }catch (IllegalStateException ise) {
                System.out.println(ise.getMessage());
            }
            lex.listTokens();
        }else{
            System.out.println("Usage: ArgonLexer.jar [file]");
        }
    }
    
}
