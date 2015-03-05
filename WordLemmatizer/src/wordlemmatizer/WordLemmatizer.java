package wordlemmatizer;

import edu.northwestern.at.morphadorner.corpuslinguistics.lemmatizer.EnglishLemmatizer;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class WordLemmatizer {

    public static void main(String[] args) throws Exception {
        //If help is required, print help message:
        if(args.length>0){
            System.out.println("\n\t- This application lemmatizes a word.");
            System.out.println("\t- To start it, run:\n");
            System.out.println("\t\tjava -jar WordLemmatizer.jar\n");
            System.out.println("\t- To close it, input a blank line.");
            System.out.println("\t- Input:\n");
            System.out.println("\t\t<word>\n");
            System.out.println("\t- Output:\n");
            System.out.println("\t\t<lemma>");
            return;
        }
        
        //Create reader of stdin:
        InputStream is = System.in;
        BufferedReader bis = new BufferedReader(new InputStreamReader(is));

        //Create the pluralizer:
        EnglishLemmatizer el = new EnglishLemmatizer();

        //While not terminated, read a word:
        boolean flag = false;
        while (!flag) {
            

            String word;
            try {
                word = bis.readLine().trim();
            } catch (Exception ex) {
                return;
            }

            if(word.trim().length()==0){
                return;
            }
            
            try {
                //Find resulting lemma:
                String lemma = el.lemmatize(word);

                //Output resulting lemma:
                System.out.println(lemma);
            } catch (Exception e) {
                System.out.println(word);
            }
        }
    }

}
