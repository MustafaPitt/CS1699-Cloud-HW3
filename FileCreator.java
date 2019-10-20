import java.io.*;
import java.util.*;

// run like "FileCreator filename size_int"
public class FileCreator {

    List words ;

    private FileCreator(String outputFileName, int sizeInMB)  {
        if (sizeInMB > 10 || sizeInMB < 0 ){
            System.out.println("file size should be 10 or less");
            System.exit(1);
        }
        words = buildDictionaryList();
        createRandomFIleText(words, sizeInMB , outputFileName);

    }

    // create a file contains random english words from for a diction up to sizeInMb
    private void createRandomFIleText(List words, int sizeInMB, String outputFileName) {
        int countFileSize = 0;

            File file = new File(outputFileName);

//Create the file
            try {
                if (file.createNewFile())
                {
                    System.out.println("File is created!");
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

//Write Content

        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int ran;
        Random random = new Random();
        while (countFileSize < sizeInMB * 1000000) {
            ran = random.nextInt(words.size()-1);
            String temp = words.get(ran).toString();
            if (temp != null) {
                try {
                    writer.write(temp+" ");
                    if (countFileSize % 20 == 0) writer.write("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                countFileSize += words.get(ran).toString().length();
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<String> buildDictionaryList() {
        List <String> wordsList = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("dictionary.txt"));
            String line = reader.readLine();
            while (line != null) {
                // read next line
                line = reader.readLine();
                wordsList.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordsList;
    }

    public static void main(String [] args) throws Exception {

        new FileCreator(args[0], Integer.parseInt(args[1]));
    }

}
