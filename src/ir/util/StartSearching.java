package ir.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.lucene.queryParser.ParseException;

public class StartSearching {

	public static void main(String[] args) throws IOException, ParseException {
		LuceneDAO dao = new LuceneDAO();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter your query: ");
		//System.out.println(dao.search(br.readLine()));
		dao.relevantDocs(br.readLine());
		//dao.printVocab();
		//System.out.println("......."+dao.FreqVector(br.readLine()));
		
	}

}
