import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseFile {

    public static List<Integer> read(String file) throws IOException {

        List<String> stringflowDurationColumn = new ArrayList<>();
        List<Integer> intflowDurationColumn = new ArrayList<>();

        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null) {

                String[] row = line.split(";");
                stringflowDurationColumn.add(row[7]);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            reader.close();
        }

        for(int i= 0; i < stringflowDurationColumn.size(); i++) {
            if(i != 0) {
                intflowDurationColumn.add(Integer.valueOf(stringflowDurationColumn.get(i)));
            }
        }
        return intflowDurationColumn;
    }
}

