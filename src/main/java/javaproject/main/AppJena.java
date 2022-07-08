package javaproject.main;

import javaproject.rdf.RDFHandler;

public class AppJena {
	public static void main(String[] args) {
		String querySelect = "#Most famous child of a librarian\n" +
                "#Children of librarians with the most number of sitelinks (as a proxy for fame)\n" +
                "SELECT ?person ?personLabel ?parentLabel ?linkcount WHERE {\n" +
                "    ?parent wdt:P106 wd:Q182436 .\n" +
                "    ?parent wdt:P40 ?person .\n" +
                "    ?person wikibase:sitelinks ?linkcount .\n" +
                "  SERVICE wikibase:label { bd:serviceParam wikibase:language \"en,de,es,ar,fr\" }\n" +
                "}\n" +
                "GROUP BY  ?linkcount ?person ?personLabel ?parent ?parentLabel\n" +
                "ORDER BY DESC(?linkcount)";
		RDFHandler.execution(querySelect);
		
	}
}
