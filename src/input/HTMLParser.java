package input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HTMLParser extends Parser{

    BufferedReader br;

    public HTMLParser(File receiptFileHTML){
        inputFilePath = receiptFileHTML.getAbsolutePath();
    }

    @Override
    public void startInput() {
        try {
			br = new BufferedReader(new FileReader(inputFilePath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
        String line = "";
        while(true){
            try {
                line = br.readLine().trim();
                // System.out.println("|" + line + "|" + line.contains("body"));
                if (line.contains("<body>")) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*
    if(line.startsWith("Name:")){
		name = (line.substring(line.indexOf(":") + 1).trim());
		continue;
	}	
    */

    @Override
    public void endInput() {
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String[] readNextReceipt() {
        String[] data = new String[10];
        for (int i = 0; i < data.length; i++) {
            try {
                String line = br.readLine();
                if (line == null) return new String[0];
                data[i] = removeHTMLtags(line);
                // System.out.println(data[i]);
                data[i] = data[i].substring(data[i].indexOf(":") + 1).trim();
                // System.out.println(data[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String[] readNameAndAfm() {
        String nameLine = null, afmLine = null;
        try {
            nameLine = br.readLine();
			afmLine = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        name = nameLine.replaceAll("<p><b>", "").trim();
        name = name.replaceAll("</b></p>", "").trim();

        afm = afmLine.replaceAll("<p><b>", "").trim();
        afm = afm.replaceAll("</b></p>", "").trim();
        while(!afmLine.trim().equals("<table>")){
            try {
                afmLine = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new String[]{name, afm};
    }

    private String removeHTMLtags(String str){
        str = str.replaceAll("<tr>", "");
        str = str.replaceAll("</tr>", "");
        str = str.replaceAll("<td>", "");
        str = str.replaceAll("</td>", "");
        return str.trim();
    }
    
}
