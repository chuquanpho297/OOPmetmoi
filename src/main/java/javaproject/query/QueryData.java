package javaproject.query;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.expr.nodevalue.NodeValueNode;

public class QueryData {
	private List<List<NodeValue>> getListNodeValues(ResultSet result) {
	    List<String> resultVars = result.getResultVars();
	    List<List<NodeValue>> listNodeValues = new ArrayList<>();
	    while (result.hasNext()) {
	        List<NodeValue> nodeValues = new ArrayList<>();
	        QuerySolution sol = result.next();
	        for (String var : resultVars) {
	            RDFNode rdfNode = sol.get(var);
	            if (rdfNode != null) {
	                NodeValue n = new NodeValueNode(rdfNode.asNode());
	                nodeValues.add(n);
	            } else {
	                nodeValues.add(null);
	            }
	        }
	        listNodeValues.add(nodeValues);
	    }
	    return listNodeValues;
	}
}
