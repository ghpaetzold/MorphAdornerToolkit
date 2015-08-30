package verbtenser;

import edu.northwestern.at.morphadorner.corpuslinguistics.inflector.Person;
import edu.northwestern.at.morphadorner.corpuslinguistics.inflector.VerbTense;
import edu.northwestern.at.morphadorner.corpuslinguistics.inflector.conjugator.EnglishConjugator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class VerbTenser {

    public static void main(String[] args) throws IOException {
        //If help is required, print help message:
        if(args.length>0){
            System.out.println("\n\t- This application returns the tense of a verb.");
            System.out.println("\t- The lemma can be obtained with WordLemmatizer.jar");
            System.out.println("\t- To start it, run:\n");
            System.out.println("\t\tjava -jar VerbTenser.jar\n");
            System.out.println("\t- To close it, input a blank line.");
            System.out.println("\t- Input:\n");
            System.out.println("\t\t<lemma> <verb>\n");
            System.out.println("\t- Output:\n");
            System.out.println("\t\t<tense> <person>");
            return;
        }
        
        //Create reader of stdin:
        InputStream is = System.in;
        BufferedReader bis = new BufferedReader(new InputStreamReader(is));

        //Create the conjugator:
        EnglishConjugator ec;
        ec = new EnglishConjugator();

        //While not terminated, read a verb:
        boolean flag = false;
        while (!flag) {
            String data = bis.readLine();
            StringTokenizer st = new StringTokenizer(data.trim(), " ");

            String stem;
            String verb;
            try {
                stem = st.nextToken().trim();
                verb = st.nextToken();
            } catch (Exception ex) {
                return;
            }

            //Find resulting verb tense:
            String res_verb_tense = null;
            String res_verb_person = null;
            for (VerbTense vt : VerbTense.values()) {
                for (Person p : Person.values()) {
                    String conjugated_verb = ec.conjugate(stem, vt, p);
                    if(conjugated_verb.trim().equals(verb.trim())){
                        res_verb_tense = vt.toString();
                        res_verb_person = p.toString();
                    }
                }
            }
            
            //Output resulting verb tense:
            try{
                System.out.println(res_verb_tense.toUpperCase() + ' ' + res_verb_person.toUpperCase());
            }catch(Exception e){
                System.out.println("PAST_PERFECT_PARTICIPLE FIRST_PERSON_SINGULAR");
            }
        }
    }
}
