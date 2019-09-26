import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WordAnagram {
    private final String USER_AGENT = "Mozilla/5.0";
    private Trie trieDictionary = new Trie();
    private static wordAnagram wordAna = new wordAnagram();
    private static inputPermutation inputPerm = new inputPermutation();

    public static void main(String[] args) throws Exception {
        System.out.println("Testing 1 - Send Http GET request");
        wordAna.buildTriefromDictionary();
        String test1 = "nnogreen";
        String test2 = "grene";
        System.out.println(wordAna.findWord(test1));
        System.out.println(wordAna.findWord(test2));
    }

    // HTTP GET request
    private void buildTriefromDictionary() throws Exception {

        String url = "https://raw.githubusercontent.com/lad/words/master/words";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            trieDictionary.insert(inputLine.toLowerCase().replaceAll("[^a-z]", ""));
        }
        in.close();
    }

    private List<String> findWord(String s) {
        List<String> input = inputPerm.permute(s);
        List<String> result = new ArrayList<>();
        for (String str : input) {
            if(trieDictionary.search(str)) {
                result.add(str);
            }
        }
        return result;
    }
}
