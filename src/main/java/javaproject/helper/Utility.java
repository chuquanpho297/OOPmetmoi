package javaproject.helper;

import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.rdf.model.Model;

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
		queryStr.setNsPrefix("xsd", PrefixConstant.xsd);
		queryStr.setNsPrefix("foaf", PrefixConstant.foaf);
		queryStr.setNsPrefix("rs", PrefixConstant.rs);
	}
	public static void setNsPrefix(Model model) {
		model.setNsPrefix("wikibase", PrefixConstant.wikibase);
		model.setNsPrefix("bd", PrefixConstant.bd);
		model.setNsPrefix("dbp", PrefixConstant.dbp);
		model.setNsPrefix("rdf", PrefixConstant.rdf);
		model.setNsPrefix("wd", PrefixConstant.wd);
		model.setNsPrefix("wdt", PrefixConstant.wdt);
		model.setNsPrefix("yago", PrefixConstant.yago);
		model.setNsPrefix("schema", PrefixConstant.schema);
		model.setNsPrefix("dbo", PrefixConstant.dbo);
		model.setNsPrefix("xsd", PrefixConstant.xsd);
		model.setNsPrefix("foaf", PrefixConstant.foaf);
		model.setNsPrefix("rs", PrefixConstant.rs);
	}
}
