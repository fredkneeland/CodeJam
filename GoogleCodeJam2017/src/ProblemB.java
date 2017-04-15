import java.io.*;

/**
 * Created by fredkneeland on 4/8/17.
 */
public class ProblemB {

    public static void main(String[] args) {

        // The name of the file to open.
        String fileName = "./src/problemBInput.txt";

        // This will reference one line at a time
        String line = null;

        int count = -1;
        String[] strings = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            int counter = 0;

            while((line = bufferedReader.readLine()) != null) {
                if (count < 0) {
                    count = Integer.parseInt(line);
                    strings = new String[count];
                } else {
                    strings[counter] = line;
                    counter++;
                }
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }

        System.out.println(getSmallestNumberForProblem("1129374"));

        // The name of the file to open.
        fileName = "outputB.txt";

        try {
            // Assume default encoding.
            FileWriter fileWriter =
                    new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            for (int i = 0; i < strings.length; i++) {
                bufferedWriter.write("Case #" + (i + 1));
                bufferedWriter.write(": " + getSmallestNumberForProblem(strings[i]));
                if (i+1 < strings.length) {
                    bufferedWriter.newLine();
                }
            }

            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }

    public static String getSmallestNumberForProblem(String string) {
        String newString = "";

        int nextInt;
        int lastInt = -1;
        int currentInt;
        boolean allNines = false;

        int inflectionPoint = 0;


        // iterate from the end to the beginning one digit at a time
        for (int i = 0; i < string.length(); i++) {
            currentInt = Integer.parseInt("" + string.charAt(i));
            System.out.println(currentInt);

            if (currentInt < lastInt) {
                allNines = true;
                break;
            } else {
                if (currentInt != lastInt) {
                    inflectionPoint = i;
                }
                lastInt = currentInt;
            }
        }

        System.out.println("inflection point: " + inflectionPoint);

        if (!allNines) {
            return string;
        }
        else {

            for (int i = 0; i < string.length(); i++) {
                if (i < inflectionPoint) {
                    newString += string.charAt(i);
                } else if (i == inflectionPoint) {
                    int test = Integer.parseInt("" + string.charAt(i));

                    if (test > 1) {
                        newString += (test - 1);
                    } else {
                        // do nothing as we don't want a 0 at the front
                    }
                } else {
                    newString += '9';
                }
            }
        }

        return newString;
    }
}
