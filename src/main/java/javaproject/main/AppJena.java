package javaproject.main;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.RDFNode;

import javaproject.constant.QueryUrlConstant;
import javaproject.helper.Utility;

public class AppJena {
	public static void main(String[] args) {

		ParameterizedSparqlString queryStr = new ParameterizedSparqlString();
		Utility.setNsPrefix(queryStr);
		String querySelect = "#Cats\n" + "SELECT ?item ?itemLabel \n" + "WHERE \n" + "{\n"
				+ "  ?item wdt:P31 wd:Q146. # Must be of a cat\n"
				+ "  SERVICE wikibase:label { bd:serviceParam wikibase:language \"[AUTO_LANGUAGE],en\". } # Helps get the label in your language, if not, then en language\n"
				+ "}";
		queryStr.append(querySelect);
		Query query = queryStr.asQuery();
		System.out.println(query);
		try (QueryExecution qexec = QueryExecutionFactory.sparqlService(QueryUrlConstant.wikidataQueryUrl, query)) {
			qexec.setTimeout(10000, TimeUnit.MILLISECONDS);
			ResultSet results = qexec.execSelect();
			List<String> resultVars = results.getResultVars();
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				for (String resultVar : resultVars) {
					RDFNode rdfNode = soln.get(resultVar);
					System.out.println(rdfNode.toString());
				}
			}
//			ResultSetFormatter.out(System.out, results, query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
