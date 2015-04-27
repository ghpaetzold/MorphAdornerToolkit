package adjectiveinflector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AdjectiveInflector {

    public static void main(String[] args) throws IOException {
        //If help is required, print help message:
        if (args.length > 0) {
            System.out.println("\n\t- This application inflects adjectives and adverbs.");
            System.out.println("\t- To start it, run:\n");
            System.out.println("\t\tjava -jar AdjectiveInflector.jar\n");
            System.out.println("\t- To close it, input a blank line.");
            System.out.println("\t- Input:\n");
            System.out.println("\t\t<word>\n");
            System.out.println("\t- Output:\n");
            System.out.println("\t\t<syllables>");
            return;
        }

        //Create reader of stdin:
        InputStream is = System.in;
        BufferedReader bis = new BufferedReader(new InputStreamReader(is));

        //Create inflector:
        EnglishAdjectiveInflector ai = new EnglishAdjectiveInflector();

        //While not terminated, read a lemmatized adjective:
        boolean flag = false;
        while (!flag) {
            String data = bis.readLine().trim();
            StringTokenizer st = new StringTokenizer(data.trim(), " ");

            String lemma;
            String form;
            try {
                lemma = st.nextToken().trim();
                form = st.nextToken().trim();
            } catch (Exception ex) {
                return;
            }

            if (lemma.length() == 0) {
                return;
            }
            if (!"comparative".equals(form) && !"superlative".equals(form)){
                return;
            }

            try {
                //Find resulting verb tense:
                String inflected_lemma = ai.inflect(lemma, form);

                //Output resulting verb tense:
                System.out.println(inflected_lemma);
            } catch (Exception e) {
                System.out.println(lemma);
            }
        }
    }

}
