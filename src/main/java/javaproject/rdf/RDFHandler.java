package javaproject.rdf;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.sparql.resultset.ResultsFormat;

import javaproject.constant.DirContanst;
import javaproject.helper.Utility;

public class RDFHandler {
	public static void QueryAndSave(String querySelect, String sparqlUrl, String fileName) {
		ParameterizedSparqlString queryStr = new ParameterizedSparqlString();
		Utility.setNsPrefix(queryStr);
		queryStr.append(querySelect);
		Query query = queryStr.asQuery();
		System.out.println(query);
		try (QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlUrl, query)) {
			qexec.setTimeout(60000, TimeUnit.MILLISECONDS);
//			ResultSet results = qexec.execSelect();
//			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//			ResultSetFormatter.output(outputStream, results, ResultsFormat.FMT_RDF_TTL);
//			System.out.println(m.toString());
//			String turtle = new String(outputStream.toByteArray());
			Model m = qexec.execConstruct();
			String fileDir = DirContanst.curDir + "\\" + "Data" + "\\" + fileName + ".ttl";
			try (FileWriter fileWriter = new FileWriter(fileDir)) {
//				fileWriter.write(turtle);
				m.write(fileWriter,"Turtle");
				System.out.println("Done!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void LoadFile(String fileName) {
		String fileDir = DirContanst.curDir + "\\" + "Data" + "\\" + fileName + ".ttl";
		Model model = RDFDataMgr.loadModel(fileDir);
//		System.out.println(model);
		StmtIterator iter = model.listStatements();
		while (iter.hasNext()) {
			Statement stmt = iter.nextStatement(); // get next statement
			Resource subject = stmt.getSubject(); // get the subject
			Property predicate = stmt.getPredicate(); // get the predicate
			RDFNode object = stmt.getObject(); // get the object

			System.out.print(subject.toString());
			System.out.print(" " + predicate.toString() + " ");
			if (object instanceof Resource) {
				System.out.print(object.toString());
			} else {
				// object is a literal
				System.out.print(" \"" + object.toString() + "\"");
			}

			System.out.println(" .");
		}
		model.write(System.out);
	}
}
