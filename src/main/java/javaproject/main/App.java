package javaproject.main;

import org.eclipse.rdf4j.query.resultio.sparqljson.SPARQLResultsJSONWriter;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;
import java.util.Collections;

/**
 * Wikidata RDF4J SPARQL example
 */
public class App
{
    public static void main( String[] args )
    {
        String sparqlEndpoint = "https://query.wikidata.org/sparql";
        SPARQLRepository repo = new SPARQLRepository(sparqlEndpoint);

        String userAgent = "Wikidata RDF4J Java Example/0.1 (https://query.wikidata.org/)";
        repo.setAdditionalHttpHeaders( Collections.singletonMap("User-Agent", userAgent ) );

        String querySelect = "#Horses (showing some info about them)\n" +
                "#Illustrates optional fields, instances of subclasses, language fallback on label service, date to year conversion\n" +
                "#title: Horses on Wikidata\n" +
                "SELECT DISTINCT ?horse ?horseLabel ?mother ?motherLabel ?father ?fatherLabel (year(?birthdate) as ?birthyear) (year(?deathdate) as ?deathyear) ?genderLabel\n" +
                "WHERE\n" +
                "{\n" +
                "  ?horse wdt:P31/wdt:P279* wd:Q726 .     # Instance et sous-classes de Q726-Cheval\n" +
                "   \n" +
                "  OPTIONAL{?horse wdt:P25 ?mother .}       # P25  : Mère\n" +
                "  OPTIONAL{?horse wdt:P22 ?father .}       # P22  : Père\n" +
                "  OPTIONAL{?horse wdt:P569 ?birthdate .} # P569 : Date de naissance\n" +
                "  OPTIONAL{?horse wdt:P570 ?deathdate .}     # P570 : Date de décès\n" +
                "  OPTIONAL{?horse wdt:P21 ?gender .}       # P21  : Sexe\n" +
                " \n" +
                "  SERVICE wikibase:label { #BabelRainbow\n" +
                "    bd:serviceParam wikibase:language \"[AUTO_LANGUAGE],fr,ar,be,bg,bn,ca,cs,da,de,el,en,es,et,fa,fi,he,hi,hu,hy,id,it,ja,jv,ko,nb,nl,eo,pa,pl,pt,ro,ru,sh,sk,sr,sv,sw,te,th,tr,uk,yue,vec,vi,zh\"\n" +
                "  }\n" +
                "}\n" +
                "ORDER BY ?horse";

        try{
            repo.getConnection().prepareTupleQuery(querySelect).evaluate(new SPARQLResultsJSONWriter(System.out));
        } catch ( Exception exception ) {
            exception.printStackTrace();
        }

    }
}