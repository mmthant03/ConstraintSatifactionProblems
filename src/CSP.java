import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

//Robert Dutile & Myo Min Thant

public class CSP {
    public static void main (String[] args) {
        if(args.length < 1) {
        	System.out.println("Error, no input file specified");
        	return;
        }
        String inputFileName = args[0];
        
        
        try {
            File file = new File(inputFileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
                //process the line
                System.out.println(line);
            }
        } catch(Exception e) {
        	System.out.println("Exception thrown in file read");
        	return;
        }

        
    }



}
