package input;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import data.Receipt;

public class ReceiptUpdaterHTML extends ReceiptUpdater{
    
    BufferedWriter bw;
    BufferedReader br;
    StringBuilder fileContent;

    public void setFileToAppend(File fileToAppend) {
		this.fileToAppend = fileToAppend;
        System.out.println("File: " + this.fileToAppend);
	}

    @Override
	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

    @Override
    public void write(String text, String value){
        text = text + ": ";
		String htmlContent = "    <tr><td>" + text + "</td><td>" + value + "</td></tr>\n";
        fileContent.append(htmlContent);
	}

    @Override
	public void initializeWriting() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileToAppend));){
            fileContent = new StringBuilder();
            String currentLine;
            while ((currentLine = br.readLine()) != null){
                if (currentLine.trim().equals("</table>")){
                    fileContent.append("\n");
                    break;
                }
                fileContent.append(currentLine).append(System.lineSeparator());
            }
            //String fileContentStr = fileContent.substring(0, fileContent.indexOf("</table>"));
           
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

    @Override
	public void endWriting() {
        fileContent.append("    </table>\n\n</body>\n</html>");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileToAppend))) {
            bw.write(fileContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Added to HTML ( " + fileToAppend.getAbsolutePath() + " )");
    }
}