import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.javapoint.*;

public class ResponseFactoryTest {

	@Test
	public void test() {
		String[] topics = new String[]{"Algebra", "Number"};
		String[] subtopics = new String[]{"Linear Equations", "Processing data"};
		String[] levels = new String[]{"7", "8", "9"};
		String[] noneMatch = new String[]{"Nothing"};
		
		Request testRequest = new Request();
		testRequest.topics = topics;
		FetchTitles titleFetcher = new FetchTitles("topics", testRequest);
		ArrayList<Fetcher> fetchers = new ArrayList<Fetcher>();
		fetchers.add(titleFetcher);
		Response testResponse = ReponseFactory.MakeResponse(fetchers);
		assertEquals(testResponse.titles.size(), 14);	
		
		testRequest = new Request();
		testRequest.subtopics = subtopics;
		titleFetcher = new FetchTitles("subtopics", testRequest);
		fetchers = new ArrayList<Fetcher>();
		fetchers.add(titleFetcher);
		testResponse = ReponseFactory.MakeResponse(fetchers);
		assertEquals(testResponse.titles.size(), 6);

		testRequest = new Request();
		testRequest.levels = levels;
		titleFetcher = new FetchTitles("levels", testRequest);
		fetchers = new ArrayList<Fetcher>();
		fetchers.add(titleFetcher);
		testResponse = ReponseFactory.MakeResponse(fetchers);
		assertEquals(testResponse.titles.size(), 10);
		
		assertNull(testResponse.levels);

		testRequest = new Request();
		testRequest.subtopics = noneMatch;
		titleFetcher = new FetchTitles("subtopics", testRequest);
		fetchers = new ArrayList<Fetcher>();
		fetchers.add(titleFetcher);
		testResponse = ReponseFactory.MakeResponse(fetchers);
		assertNull(testResponse.titles);
		
		testRequest = new Request();
		testRequest.levels = new String[]{"6"};
		testRequest.topics = new String[]{"Shape"};
		titleFetcher = new FetchTitles("levels", "topics", testRequest);
		fetchers = new ArrayList<Fetcher>();
		fetchers.add(titleFetcher);
		testResponse = ReponseFactory.MakeResponse(fetchers);
		assertEquals(testResponse.titles.size(), 1);

		testRequest = new Request();
		testRequest.levels = new String[]{"6"};
		testRequest.subtopics = new String[]{"Linear Equations"};
		titleFetcher = new FetchTitles("levels", "subtopics", testRequest);
		fetchers = new ArrayList<Fetcher>();
		fetchers.add(titleFetcher);
		testResponse = ReponseFactory.MakeResponse(fetchers);
		assertEquals(testResponse.titles.size(), 4);

		testRequest = new Request();
		testRequest.levels = new String[]{"6"};
		testRequest.subtopics = new String[]{"Linear Equations"};
		titleFetcher = new FetchTitles("levels", "subtopics", testRequest);
		FetchLevels levelFetcher = new FetchLevels();
		fetchers = new ArrayList<Fetcher>();
		fetchers.add(titleFetcher);
		fetchers.add(levelFetcher);
		testResponse = ReponseFactory.MakeResponse(fetchers);
		assertEquals(testResponse.titles.size(), 4);
		assertEquals(testResponse.levels.size(), 5);
		
		testRequest = new Request();
		FetchSubtopics subtopicFetcher = new FetchSubtopics();
		fetchers = new ArrayList<Fetcher>();
		fetchers.add(subtopicFetcher);
		testResponse = ReponseFactory.MakeResponse(fetchers);
		assertEquals(testResponse.subtopics.size(), 29);

		testRequest = new Request();
		testRequest.topics = topics;
		subtopicFetcher = new FetchSubtopics("topics",testRequest);
		fetchers = new ArrayList<Fetcher>();
		fetchers.add(subtopicFetcher);
		testResponse = ReponseFactory.MakeResponse(fetchers);
		assertEquals(testResponse.subtopics.size(), 19);
		
		testRequest = new Request();
		levelFetcher = new FetchLevels();
		fetchers = new ArrayList<Fetcher>();
		fetchers.add(levelFetcher);
		testResponse = ReponseFactory.MakeResponse(fetchers);
		assertEquals(5, testResponse.levels.size());
		
	}

}
