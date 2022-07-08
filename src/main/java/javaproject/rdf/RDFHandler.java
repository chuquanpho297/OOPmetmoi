package javaproject.rdf;

import java.util.concurrent.TimeUnit;

import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;

import javaproject.constant.QueryUrlConstant;
import javaproject.helper.Utility;

public class RDFHandler {
	public static void execution(String querySelect) {
		ParameterizedSparqlString queryStr = new ParameterizedSparqlString();
		Utility.setNsPrefix(queryStr);
		queryStr.append(querySelect);
		Query query = queryStr.asQuery();
		System.out.println(query);
		try (QueryExecution qexec = QueryExecutionFactory.sparqlService(QueryUrlConstant.wikidataQueryUrl, query)) {
			qexec.setTimeout(60000, TimeUnit.MILLISECONDS);
			ResultSet results = qexec.execSelect();
			SaveFile.SaveTurtleFile(results);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
