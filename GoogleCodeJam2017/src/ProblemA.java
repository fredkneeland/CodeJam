import java.io.*;

public class ProblemA {

    public static void main(String[] args) {
        // read in file

        // The name of the file to open.
        String fileName = "./src/problemAInput.txt";

        // This will reference one line at a time
        String line = null;

        int count = -1;
        boolean[][] pancakes = null;
        int[] sizes = null;
        int[] results = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            int counter = 0;

            while((line = bufferedReader.readLine()) != null) {
                if (count == -1) {
                    count = Integer.parseInt(line);

                    pancakes = new boolean[count][];
                    sizes = new int[count];
                    results = new int[count];
                } else {
                    String pancakeString = line.split(" ")[0];

                    pancakes[counter] = new boolean[pancakeString.length()];

                    for (int i = 0; i < pancakeString.length(); i++) {
                        if (pancakeString.charAt(i) == '+') {
                            pancakes[counter][i] = true;
                        } else {
                            pancakes[counter][i] = false;
                        }
                    }

                    sizes[counter] = Integer.parseInt(line.split(" ")[1]);

                    counter++;
                }


                System.out.println(line);
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


        for (int i = 0; i < pancakes.length; i++) {
//            System.out.println("round: " + i);
//            printPancakes(pancakes[i]);
//            System.out.println(sizes[i]);
            results[i] = flipBooleans(pancakes[i], sizes[i]);
//            System.out.println(results[i]);
        }


        // export file

        // The name of the file to open.
        fileName = "outputA.txt";

        try {
            // Assume default encoding.
            FileWriter fileWriter =
                    new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            for (int i = 0; i < results.length; i++) {
                bufferedWriter.write("Case #" + (i + 1));
                bufferedWriter.write(": ");

                if (results[i] >= 0) {
                    bufferedWriter.write("" + results[i]);
                } else {
                    bufferedWriter.write("IMPOSSIBLE");
                }

                bufferedWriter.newLine();
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

    public static int flipBooleans(boolean[] pancakes, int flipperSize) {
        if (allGood(pancakes)) return 0;

        int moves = 0;

        // as soon as you get to a 0 flip it if you get through and all are good then done else not
        for (int i = 0; i < pancakes.length; i++) {
            if (!pancakes[i]) {
                pancakes = flipPancakes(pancakes, i, flipperSize);
                moves++;
            }
//            printPancakes(pancakes);
        }


        if (allGood(pancakes)) {
            return moves;
        }
        else {
            return -1;
        }
    }

    public static void printPancakes(boolean[] pancakes) {
        System.out.println("pancakes: ");

        for (int i = 0; i < pancakes.length; i++) {
            System.out.print(pancakes[i]);
        }

        System.out.println("");
    }

    public static boolean allGood(boolean[] pancakes) {
        for (int i = 0; i < pancakes.length; i++) {
            if (!pancakes[i]) return false;
        }
        return true;
    }

    // helper function to flip pancakes
    public static boolean[] flipPancakes(boolean[] pancakes, int index, int size) {
        if (index+size > pancakes.length) return pancakes;

        boolean[] newPancakes = new boolean[pancakes.length];

        for (int i = 0; i < pancakes.length; i++) {
            if (i >= index && i < index+size) {
                newPancakes[i] = !pancakes[i];
            } else {
                newPancakes[i] = pancakes[i];
            }
        }
        return newPancakes;
    }
}
