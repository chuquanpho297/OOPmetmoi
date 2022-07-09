package javaproject.helper;

import org.apache.jena.query.ParameterizedSparqlString;

import javaproject.constant.PrefixConstant;

public class Utility {
	public static void setNsPrefix(ParameterizedSparqlString queryStr) {
		queryStr.setNsPrefix("wikibase", PrefixConstant.wikibase);
		queryStr.setNsPrefix("bd", PrefixConstant.bd);
		queryStr.setNsPrefix("dbp", PrefixConstant.dbp);
		queryStr.setNsPrefix("rdf", PrefixConstant.rdf);
		queryStr.setNsPrefix("wd", PrefixConstant.wd);
		queryStr.setNsPrefix("wdt", PrefixConstant.wdt);
		queryStr.setNsPrefix("yago", PrefixConstant.yago);
		queryStr.setNsPrefix("schema", PrefixConstant.schema);
		queryStr.setNsPrefix("dbo", PrefixConstant.dbo);
		queryStr.setNsPrefix("foaf", PrefixConstant.foaf);
	}
}
