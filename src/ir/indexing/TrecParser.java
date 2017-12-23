/**
 * 
 */
package ir.indexing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;

import org.apache.lucene.queryParser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ir.util.LuceneConstants;
import ir.util.LuceneDAO;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 * @author pratikpatil
 *
 */
public class TrecParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			File folder = new File(LuceneConstants.DATA_PATH);
			File[] listOfFiles = folder.listFiles();

			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					System.out.println("File " + listOfFiles[i].getName());
					if (!listOfFiles[i].getName().contains(".")) {
						indexFile(listOfFiles[i]);
					}
				}
			}

			LuceneDAO dao = new LuceneDAO();
			dao.createIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static void indexFile(File file) throws SAXException, IOException, ParserConfigurationException {
		File inputFile = normalizeFile(file);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(inputFile);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("DOC");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\nCurrent Element :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				convertFile((Element) nNode);
			}	
		}
	}

	private static File normalizeFile(File file) {
		BufferedWriter bw = null;
		File f = null;
		try {
			bw = new BufferedWriter(new FileWriter(f = new File("./NormalizedFiles/" + file.getName())));
			String s = "";
			BufferedReader br = null;
			try {

				br = new BufferedReader(new FileReader(file));
				bw.write("<ROOT>");
				while ((s = br.readLine()) != null) {
					bw.write(s + "\n");
				}
				bw.write("</ROOT>");
			} catch (Exception e) {
			} finally {
				try {
					br.close();
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
		} finally {
			try {
				bw.close();
			} catch (Exception e) {
			}
		}
		return f;
	}

	static void convertFile(Element eElement) {
		String[] titles = { "DOCNO", "HEADLINE", "BYLINE", "DATELINE", "TEXT", "PUB", "PAGE", "DATE" };
		String[] data = new String[titles.length];
		String FileContents = "";
		String lineBreaks = "\n";
		for (int i = 0; i < titles.length; i++) {
			try {
				data[i] = new String(eElement.getElementsByTagName(titles[i]).item(0).getTextContent());
				if (data[i] != null) {
					FileContents += data[i] + lineBreaks;
				}
			} catch (NullPointerException e) {
				System.out.println("element not present: " + titles[i]);
			}
		}
		writeToDisk(data[0], FileContents);
//		System.out.println("FileContents :\n" + FileContents);
	}

	static void writeToDisk(String FileName, String FileContents) {
		Writer writer = null;
		try {
			writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream("./ConvertedText/" + FileName + ".txt"), "utf-8"));
			writer.write(FileContents);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
				/* ignore */}
		}
	}

}
