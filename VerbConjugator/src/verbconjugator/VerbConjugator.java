package verbconjugator;

import edu.northwestern.at.morphadorner.corpuslinguistics.inflector.Person;
import edu.northwestern.at.morphadorner.corpuslinguistics.inflector.VerbTense;
import edu.northwestern.at.morphadorner.corpuslinguistics.inflector.conjugator.EnglishConjugator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class VerbConjugator {

    public static void main(String[] args) throws IOException {
        //If help is required, print help message:
        if(args.length>0){
            System.out.println("\n\t- This application conjugates a verb in the specified tense.");
            System.out.println("\t- The lemma can be obtained with WordLemmatizer.jar");
            System.out.println("\t- The tense can be obtained with VerbTenser.jar");
            System.out.println("\t- To start it, run:\n");
            System.out.println("\t\tjava -jar VerbConjugator.jar\n");
            System.out.println("\t- To close it, input a blank line.");
            System.out.println("\t- Input:\n");
            System.out.println("\t\t<verb_lemma> <tense> <person>\n");
            System.out.println("\t- Output:\n");
            System.out.println("\t\t<conjugated_verb>");
            return;
        }
        
        //Create reader of stdin:
        InputStream is = System.in;
        BufferedReader bis = new BufferedReader(new InputStreamReader(is));

        //Create the conjugator:
        EnglishConjugator ec;
        ec = new EnglishConjugator();

        //While not terminated, read a stem and a verb tense:
        boolean flag = false;
        while (!flag) {
            String data = bis.readLine();
            StringTokenizer st = new StringTokenizer(data.trim(), " ");

            String stem;
            String verb_tense;
            String verb_person;
            try {
                stem = st.nextToken().trim();
                verb_tense = st.nextToken();
                verb_person = st.nextToken();
            } catch (Exception ex) {
                return;
            }

            try {
                //Conjugate verb:
                VerbTense vt = VerbTense.valueOf(verb_tense);
                Person p = Person.valueOf(verb_person);
                String conjugated_verb = ec.conjugate(stem, vt, p);

                //Print result to stdout:
                System.out.println(conjugated_verb);
            } catch (Exception e) {
                System.out.println(stem);
            }
        }
    }
}
