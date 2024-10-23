 package input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TXTParser extends Parser{

	BufferedReader br;

	public TXTParser(File receiptFileTXT){
		inputFilePath =  receiptFileTXT.getAbsolutePath();
	}
	
	@Override
	public void startInput() {
		try {
			this.br = new BufferedReader(new FileReader(inputFilePath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void endInput() {	
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String[] readNameAndAfm() {
		String nameLine = null, afmLine = null;
		try {
			nameLine = br.readLine();
			afmLine = br.readLine();
			br.readLine();
			br.readLine();
			br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name = nameLine.substring(nameLine.indexOf(":") + 1).trim();
		String afm = afmLine.substring(afmLine.indexOf(":") + 1).trim();
		return new String[]{name, afm};
	}

	@Override
	public String[] readNextReceipt() {
		String[] data = new String[10];
		try {
			for (int i = 0; i < data.length; i++) {
				String line = br.readLine();
				if (line==null) return new String[0];
				data[i] = line.substring(line.indexOf(":") + 1).trim();
			}
			br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}