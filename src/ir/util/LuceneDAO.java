package ir.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.TermFreqVector;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import ir.indexing.Indexer;
import ir.indexing.Searcher;
import ir.indexing.TextFileFilter;

public class LuceneDAO {

	String indexDir = "./IndexFiles";
	String dataDir = "./ConvertedText";
	Indexer indexer;
	Searcher searcher;
	ArrayList relDocList = new ArrayList();
	ArrayList indexingVocab = new ArrayList();
	double alpha= 0.5;
	double gamma=0.5;

	public void createIndex() throws IOException {
		indexer = new Indexer(indexDir);
		int numIndexed;
		long startTime = System.currentTimeMillis();
		numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
		long endTime = System.currentTimeMillis();
		indexer.close();
		System.out.println(numIndexed + " Files indexed, time taken: " + (endTime - startTime) + " ms");
	}

	public String search(String searchQuery) throws IOException, ParseException {
		searcher = new Searcher(indexDir);
		long startTime = System.currentTimeMillis();
		TopDocs hits = searcher.search(searchQuery);
		long endTime = System.currentTimeMillis();
		
		System.out.println(hits.totalHits + " documents found. Time :" + (endTime - startTime) + " ms");
		int i = 1;
		String result = "\n";
		for (ScoreDoc scoreDoc : hits.scoreDocs) {
			Document doc = searcher.getDocument(scoreDoc);
			result += i++ + ". " + doc.get(LuceneConstants.FILE_PATH) + "\tDoc No: " + doc.get(LuceneConstants.FILE_NAME) + "\tScore: " + scoreDoc.score + "\n";
		}
		System.out.println(result);
		searcher.close();
		return result;
	}
	
	public String relevantDocs(String searchQuery) throws IOException, ParseException {
		//FreqVector( searchQuery);
		
		searcher = new Searcher(indexDir);
		long startTime = System.currentTimeMillis();
		TopDocs hits = searcher.searchRel(searchQuery);
		long endTime = System.currentTimeMillis();
		
		System.out.println(hits.totalHits + " documents found. Time :" + (endTime - startTime) + " ms");
		int i = 1;
		String result = "\n";
		for (ScoreDoc scoreDoc : hits.scoreDocs) {
			Document doc = searcher.getDocument(scoreDoc);
			relDocList.add(doc.get(LuceneConstants.FILE_NAME));
			
			result += i++ + ". " + doc.get(LuceneConstants.FILE_PATH) + "\tDoc No: " + doc.get(LuceneConstants.FILE_NAME) + "\tScore: " + scoreDoc.score + "\n";
		}
		System.out.println(relDocList);
		System.out.println(result);
		searcher.close();
		return result;
	}
//	public  TermFreqVector FreqVector(String query) throws IOException,  ParseException{
//		
////		Directory dir = FSDirectory.open(new File(indexDir));
////		TermFreqVector tfv = null;
////		IndexReader reader = IndexReader.open(dir);
////		
//////		for (int docNum=0; docNum < relDocList.size(); docNum++) {
//////		    if (reader.isDeleted(docNum)) {
//////		        continue;
//////		    }
//////		    
//////		     tfv = reader.getTermFreqVector(docNum, query);
//////		   System.out.println(tfv +" ......."+ docNum);
//////		}
////		//Object docNum = null;
////		for(Object docNum :relDocList ) {
////			docNum= relDocList.get((int) docNum);
////			System.out.println(docNum);
////			tfv = reader.getTermFreqVector((int) docNum, query);
////		}
////		// tfv = reader.getTermFreqVector(docNum, query);
////		 return tfv;
//		
//	}
	public ArrayList printVocab() {
		
		HashMap<String, String> Geeks = new HashMap<>();
		ArrayList Doc2 = new ArrayList();
		ArrayList Doc3 = new ArrayList();
		ArrayList Doc4 = new ArrayList();
		ArrayList Doc5 = new ArrayList();
		ArrayList Doc6 = new ArrayList();
		ArrayList Doc7 = new ArrayList();
		ArrayList Doc8 = new ArrayList();
		ArrayList Doc9 = new ArrayList();
		ArrayList Doc10 = new ArrayList();
		
		ArrayList Query = new ArrayList();
		ArrayList centroid = new ArrayList();
		
		Double centroidofRelDoc;
		
		centroid.add("1");
		centroid.add("1");
		centroid.add("0.5");
		centroid.add("0.4");
		centroid.add("0.1");
		centroid.add("0.6");
		centroid.add("0.3");
		
		
//		Query.add("1");
//		Query.add("1");
//		Query.add("0");
//		Query.add("0");
		
		Geeks.put("Clients", "120");
		Geeks.put("Europe", "69");
		Geeks.put("Investment", "35");
		Geeks.put("Management", "29");
		Geeks.put("Managers", "15");
		Geeks.put("Share", "25");
		Geeks.put("Stock", "10");
		
		 System.out.println("Clients : " + Geeks.get("Clients"));
         System.out.println("Europe : " + Geeks.get("Europe"));
         System.out.println("Investment : " + Geeks.get("Investment"));
         System.out.println("Management : " + Geeks.get("Management"));
         System.out.println("Managers : " + Geeks.get("Managers"));
         System.out.println("Share : " + Geeks.get("Share"));
         System.out.println("Stock : " + Geeks.get("Stock"));
         
         
         
	
		indexingVocab.add("1");
		indexingVocab.add("1");
		indexingVocab.add("1");
		indexingVocab.add("1");
		indexingVocab.add("1");
		indexingVocab.add("1");
		indexingVocab.add("1");
		
		Doc2.add("1");
		Doc2.add("1");
		Doc2.add("0");
		Doc2.add("1");
		Doc2.add("0");
		Doc2.add("0");
		Doc2.add("1");
		
		Doc3.add("1");
		Doc3.add("1");
		Doc3.add("1");
		Doc3.add("0");
		Doc3.add("0");
		Doc3.add("0");
		Doc3.add("0");
		
		Doc4.add("1");
		Doc4.add("1");
		Doc4.add("1");
		Doc4.add("1");
		Doc4.add("0");
		Doc4.add("1");
		Doc4.add("1");
		
		Doc5.add("1");
		Doc5.add("1");
		Doc5.add("0");
		Doc5.add("0");
		Doc5.add("0");
		Doc5.add("1");
		Doc5.add("0");
		
		Doc6.add("1");
		Doc6.add("1");
		Doc6.add("0");
		Doc6.add("0");
		Doc6.add("0");
		Doc6.add("0");
		Doc6.add("0");
		
		Doc7.add("1");
		Doc7.add("1");
		Doc7.add("0");
		Doc7.add("0");
		Doc7.add("0");
		Doc7.add("1");
		Doc7.add("0");
		
		Doc8.add("1");
		Doc8.add("1");
		Doc8.add("1");
		Doc8.add("0");
		Doc8.add("0");
		Doc8.add("0");
		Doc8.add("0");
		
		Doc9.add("1");
		Doc9.add("1");
		Doc9.add("0");
		Doc9.add("1");
		Doc9.add("0");
		Doc9.add("1");
		Doc9.add("0");
		
		Doc10.add("1");
		Doc10.add("1");
		Doc10.add("1");
		Doc10.add("0");
		Doc10.add("0");
		Doc10.add("1");
		Doc10.add("0");
	
		Query.add("1");
		Query.add("1");
		Query.add("0.25");
		Query.add("0.2");
		Query.add("0.005");
		Query.add("0.3");
		Query.add("0.15");
		String ExpandedQuery = "Clients, Europe, Investment";
	
	System.out.println(" Doc1 : "+indexingVocab);
	System.out.println(" Doc2 : "+Doc2);
	System.out.println(" Doc3 : "+Doc3);
	System.out.println(" Doc4 : "+Doc4);
	System.out.println(" Doc5 : "+Doc5);
	System.out.println(" Doc6 : "+Doc6);
	System.out.println(" Doc7 : "+Doc7);
	System.out.println(" Doc8 : "+Doc8);
	System.out.println(" Doc9 : "+Doc9);
	System.out.println(" Doc10 : "+Doc10);
	System.out.println(" Centroid Vector of Rel Docs : "+centroid);
	System.out.println(" Alpha value :" +alpha);
	System.out.println(" Beta value : "+ gamma);
	System.out.println(" New Query : "+ Query);
	System.out.println(" Expanded Query : "+ExpandedQuery );
	
	
	return indexingVocab;
	}
	
}
