package javaproject.main;

import javaproject.constant.DirContanst;
import javaproject.constant.QueryUrlConstant;
import javaproject.rdf.RDFHandler;

public class AppJena {
	public static void main(String[] args) {
//		String querySelect = "#Most famous child of a librarian\n" +
//                "#Children of librarians with the most number of sitelinks (as a proxy for fame)\n" +
//                "SELECT ?person ?personLabel ?parentLabel ?linkcount WHERE {\n" +
//                "    ?parent wdt:P106 wd:Q182436 .\n" +
//                "    ?parent wdt:P40 ?person .\n" +
//                "    ?person wikibase:sitelinks ?linkcount .\n" +
//                "  SERVICE wikibase:label { bd:serviceParam wikibase:language \"en,de,es,ar,fr\" }\n" +
//                "}\n" +
//                "GROUP BY  ?linkcount ?person ?personLabel ?parent ?parentLabel\n" +
//                "ORDER BY DESC(?linkcount)";
//		String querySelect = "SELECT ?item ?itemLabel ?gtaa ?_articleEN ?_articleNL where {\r\n"
//				+ "  ?item wdt:P1741 ?gtaa. # GTAA id\r\n"
//				+ "  OPTIONAL {\r\n"
//				+ "    ?_articleEN schema:about ?item.\r\n"
//				+ "    ?_articleNL schema:about ?item.\r\n"
//				+ "    ?_articleEN schema:isPartOf <https://en.wikipedia.org/>.\r\n"
//				+ "    ?_articleNL schema:isPartOf <https://nl.wikipedia.org/>.\r\n"
//				+ "  }\r\n"
//				+ "  SERVICE wikibase:label { bd:serviceParam wikibase:language \"[AUTO_LANGUAGE],en,nl\". }\r\n"
//				+ "}";
		String querySelect = "SELECT ?country ?city ?city_name\r\n"
				+ "WHERE {\r\n"
				+ "    ?city rdf:type dbo:City ;\r\n"
				+ "          foaf:name ?city_name ;\r\n"
				+ "          dbo:country ?country.\r\n"
				+ "\r\n"
				+ "    ?country foaf:name \"Canada\"@en .\r\n"
				+ "\r\n"
				+ "    FILTER(langMatches(lang(?city_name), \"en\"))\r\n"
				+ "}\r\n"
				+ "ORDER BY ?city_name\r\n"
				+ "LIMIT 100";
		String fileName = "test";
		RDFHandler.QueryAndSave(querySelect,QueryUrlConstant.dbpediaQueryUrl,fileName);
		
	}
}
