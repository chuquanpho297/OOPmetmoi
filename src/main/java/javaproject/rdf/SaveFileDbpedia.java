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
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;

import javaproject.constant.DirContanst;

public class SaveFileDbpedia {
	public static void saveFile(ResultSet results) {
		List<String> resultVars = results.getResultVars();
		Long count = 0L;
		while (results.hasNext()) {
			QuerySolution soln = results.nextSolution();
			Model model = ModelFactory.createDefaultModel();
			Resource root = model.createResource("root");
			Property property = model.createProperty("solution");
//			try (FileWriter out = new FileWriter(fileDir)){
			for (int i = 0; i < resultVars.size(); i++) {
				String resultVar = resultVars.get(i);
				Resource binding = model.createResource("binding");
				model.add(root,property,binding);
				Property variable = model.createProperty("variable");
				Resource variableName = model.createResource(resultVar);
				Property value = model.createProperty("value");
				Resource valueName = model.createResource(soln.get(resultVar).toString());
//				model.add(binding, variable, variableName);
//				model.add(binding, value, valueName);
				binding.addProperty(variable, variableName);
				binding.addProperty(value, valueName);
			}
			

//				model.setNsPrefix("ns", ns);
				model.write(System.out);
//			RDFDataMgr.write(System.out, model, RDFFormat.TURTLE_PRETTY);
//			RDFDataMgr.write(System.out, model, RDFFormat.TURTLE_BLOCKS) ;
			count++;
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			System.out.println();
		}

		System.out.println("The number of saved file: " + count);

	}
}
