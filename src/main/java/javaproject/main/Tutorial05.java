package javaproject.main;


import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;

import java.io.*;

/** Tutorial 5 - read RDF XML from a file and write it to standard out
 */
public class Tutorial05 extends Object {

    /**
        NOTE that the file is loaded from the class-path and so requires that
        the data-directory, as well as the directory containing the compiled
        class, must be added to the class-path when running this and
        subsequent examples.
    */    
    static final String inputFileName  = "test.rdf";
                              
    public static void main (String args[]) throws FileNotFoundException {
        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        InputStream in = new FileInputStream(inputFileName );
        
        // read the RDF/XML file
        model.read(in, "");
                    
        // write it to standard out
        model.write(System.out);            
    }
}