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
import org.apache.jena.sparql.resultset.ResultsFormat;

import javaproject.constant.DirContanst;
import javaproject.helper.Utility;

public class RDFHandler {
	public static void QueryAndSave(String querySelect,String sparqlUrl,String fileName) {
		ParameterizedSparqlString queryStr = new ParameterizedSparqlString();
		Utility.setNsPrefix(queryStr);
		queryStr.append(querySelect);
		Query query = queryStr.asQuery();
		System.out.println(query);
		try (QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlUrl, query)) {
			qexec.setTimeout(60000, TimeUnit.MILLISECONDS);
			ResultSet results = qexec.execSelect();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ResultSetFormatter.output(outputStream, results,ResultsFormat.FMT_RDF_TURTLE);
			String turtle = new String(outputStream.toByteArray());
			String fileDir = DirContanst.curDir + "\\" + "Data" + "\\" + fileName + ".ttl";
			try (FileWriter fileWriter = new FileWriter(fileDir)) {
				fileWriter.write(turtle);
				System.out.println("Done!");
			}catch(IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
