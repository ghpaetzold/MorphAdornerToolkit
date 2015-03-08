package wordstemmer;

import edu.northwestern.at.morphadorner.corpuslinguistics.stemmer.PorterStemmer;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class WordStemmer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //If help is required, print help message:
        if(args.length>0){
            System.out.println("\n\t- This application Porter stems a word.");
            System.out.println("\t- To start it, run:\n");
            System.out.println("\t\tjava -jar WordStemmer.jar\n");
            System.out.println("\t- To close it, input a blank line.");
            System.out.println("\t- Input:\n");
            System.out.println("\t\t<word>\n");
            System.out.println("\t- Output:\n");
            System.out.println("\t\t<stem>");
            return;
        }
        
        //Create reader of stdin:
        InputStream is = System.in;
        BufferedReader bis = new BufferedReader(new InputStreamReader(is));

        //Create the pluralizer:
        PorterStemmer ps = new PorterStemmer();

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
                String stem = ps.stem(word);

                //Output resulting lemma:
                System.out.println(stem);
            } catch (Exception e) {
                System.out.println(word);
            }
        }
    }
    
}
