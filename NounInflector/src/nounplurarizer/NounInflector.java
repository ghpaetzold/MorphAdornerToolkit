package nounplurarizer;

import edu.northwestern.at.morphadorner.corpuslinguistics.inflector.pluralizer.EnglishPluralizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NounInflector {

    public static void main(String[] args) throws IOException, Exception {
        //If help is required, print help message:
        if (args.length > 0) {
            System.out.println("\n\t- This application inflects a noun.");
            System.out.println("\t- The lemma can be obtained with WordLemmatizer.jar");
            System.out.println("\t- To start it, run:\n");
            System.out.println("\t\tjava -jar NounInflector.jar\n");
            System.out.println("\t- To close it, input a blank line.");
            System.out.println("\t- Input:\n");
            System.out.println("\t\t<lemma> plural/singular\n");
            System.out.println("\t- Output:\n");
            System.out.println("\t\t<inflected_noun>");
            return;
        }

        //Create reader of stdin:
        InputStream is = System.in;
        BufferedReader bis = new BufferedReader(new InputStreamReader(is));

        //Create the pluralizer:
        EnglishPluralizer ep = new EnglishPluralizer();

        //While not terminated, read a verb:
        boolean flag = false;
        while (!flag) {
            String data = bis.readLine().trim();
            StringTokenizer st = new StringTokenizer(data.trim(), " ");

            String stem;
            String number;
            try {
                stem = st.nextToken().trim();
                number = st.nextToken().trim();
            } catch (Exception ex) {
                return;
            }

            if (stem.length() == 0) {
                return;
            }

            try {
                //Translate infection:
                int n = 1;
                if (number.equals("plural")) {
                    n = 2;
                }
                //Find resulting inflected noun:
                String pluralized_stem = ep.pluralize(stem, n);

                //Output resulting inflected noun:
                System.out.println(pluralized_stem);
            } catch (Exception e) {
                System.out.println(stem);
            }
        }
    }
}
