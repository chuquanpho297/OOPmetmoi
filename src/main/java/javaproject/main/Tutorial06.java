package javaproject.main;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class Tutorial06 {
	public static void main(String[] args) {
		Model m = ModelFactory.createDefaultModel();
		 String nsA = "http://somewhere/else#";
		 String nsB = "http://nowhere/else#";
		 Resource root = m.createResource( nsA + "root" );
		 Property P = m.createProperty( nsA + "P" );
		 Property Q = m.createProperty( nsB + "Q" );
		 Resource x = m.createResource( nsA + "x" );
		 Resource y = m.createResource( nsA + "y" );
		 Resource z = m.createResource( nsA + "z" );
		 m.add( root, P, x ).add( y, Q, z ).add( root, P, y );
		 
		 System.out.println( "# -- no special prefixes defined" );
//		 m.write( System.out );
		 System.out.println( "# -- nsA defined" );
		 m.setNsPrefix( "nsA", nsA );
//		 m.write( System.out );
		 System.out.println( "# -- nsA and cat defined" );
		 m.setNsPrefix( "cat", nsB );
		 m.write( System.out );
	}
}
