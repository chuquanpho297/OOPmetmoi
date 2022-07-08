package javaproject.main;

import java.util.Collections;

import org.eclipse.rdf4j.query.resultio.sparqljson.SPARQLResultsJSONWriter;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;

public class App2 {
	public static void main(String[] args) {
		String sparqlEndpoint = "https://dbpedia.org/sparql";
		SPARQLRepository repo = new SPARQLRepository(sparqlEndpoint);

		String userAgent = "Wikidata RDF4J Java Example/0.1 (https://query.wikidata.org/)";
		repo.setAdditionalHttpHeaders(Collections.singletonMap("User-Agent", userAgent));

		String querySelect = "select distinct ?p where {" + "{?p rdf:type yago:WikicatVietnameseKings}" + "UNION"
				+ "{?p rdf:type yago:WikicatVietnamesePoliticians}" + "} LIMIT 100";

//                "SELECT ?item ?itemLabel ?pic\n" +
//                "WHERE\n" +
//                "{\n" +
//                "?item wdt:P31 wd:Q146 .\n" +
//                "?item wdt:P18 ?pic\n" +
//                "SERVICE wikibase:label { bd:serviceParam wikibase:language \"[AUTO_LANGUAGE],en\" }\n" +
//                "}";

		try {
			repo.getConnection().prepareTupleQuery(querySelect).evaluate(new SPARQLResultsJSONWriter(System.out));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
