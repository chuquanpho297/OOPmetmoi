package javaproject.rdf;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;

import javaproject.constant.DirContanst;

class SaveFile {
	protected static void SaveTurtleFile(ResultSet results) {
		List<String> resultVars = results.getResultVars();
		Long count = 0L;
		while (results.hasNext()) {
			QuerySolution soln = results.nextSolution();
			Model model = ModelFactory.createDefaultModel();
			String uri = soln.get(resultVars.get(0)).toString();
			String[] splitUri = uri.split("/");
			String fileName = splitUri[splitUri.length - 1];
			String fileDir = DirContanst.curDir + "\\" + "Data" + "\\" + fileName + ".ttl";
			String ns = uri + "#";
			String[] itemLabels = soln.get(resultVars.get(1)).toString().split("@en")[0].split(" ");
			String itemLabel = String.join("_", itemLabels);
			System.out.println(itemLabel);
			Resource root = model.createResource(ns + itemLabel);
			try (FileWriter out = new FileWriter(fileDir)){
				for (int i = 2; i < resultVars.size(); i++) {
					String resultVar = resultVars.get(i);
					Property property = model.createProperty(ns + resultVar);
					Resource child = model.createResource(soln.get(resultVar).toString());
					model.add(root, property, child);
				}
				model.setNsPrefix("ns", ns);
//				model.write(out, "TURTLE_BLOCKS");
				RDFDataMgr.write(System.out, model, RDFFormat.TURTLE_BLOCKS) ;
				count++;
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("The number of saved file: " + count);
		}
		System.out.println();
	}
}
