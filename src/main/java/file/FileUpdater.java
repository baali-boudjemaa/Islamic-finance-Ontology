package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileUpdater {

    public static void main(String args[]) throws IOException {
        String ID, Name;
        double salary;
        int replenish;

        Scanner console = new Scanner(System.in);
        System.out.print("Enter ID : ");
        String pID = console.nextLine();
        System.out.print("Enter replenish salary: ");
        replenish = console.nextInt();

        File originalFile = new File("file.txt");
        BufferedReader br = new BufferedReader(new FileReader(originalFile));

        // Construct the new file that will later be renamed to the original
        // filename.
        File tempFile = new File("tempfile.txt");
        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

        String line = null;
        // Read from the original file and write to the new
        // unless content matches data to be removed.
        while ((line = br.readLine()) != null) {

            if (line.contains(pID)) {
                String strCurrentSalary = line.substring(line.lastIndexOf(" "), line.length());
                if (strCurrentSalary != null || !strCurrentSalary.trim().isEmpty()) {
                    int replenishedSalary = Integer.parseInt(strCurrentSalary.trim()) + replenish;
                    System.out.println("replenishedSalary : " + replenishedSalary);
                    line = line.substring(0,line.lastIndexOf(" ")) + replenishedSalary;
                }

            }
            pw.println(line);
            pw.flush();
        }
        pw.close();
        br.close();

        // Delete the original file
        if (!originalFile.delete()) {
            System.out.println("Could not delete file");
            return;
        }

        // Rename the new file to the filename the original file had.
        if (!tempFile.renameTo(originalFile))
            System.out.println("Could not rename file");

    }
}