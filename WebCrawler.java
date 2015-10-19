import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.File;

public class WebCrawler {
	public static void main(String[] args) {
		if(args.length != 2){
			System.out.println("Error format, usage: java WebCrawler PAGES_LIMIT http[s]://...\n"
					+ "e.g. java WebCrawler 100 http://www.google.com");
			System.exit(1);
		}
		
//		System.out.println("input URL: ");
//		Scanner input = new Scanner(System.in);
		final int SIZE = Integer.parseInt(args[0]);
		String urlString = args[1];
		
		try{
			new URL(urlString);	// for check
			crawler(urlString, SIZE);
			System.out.println("Crawling finish.");
		}
		catch(MalformedURLException ex){
			System.out.println("Wrong URL format. \"http[s]://...\"");
			System.exit(2);
		}
		
	}
	
	public static void crawler(String urlString, int SIZE){
		ArrayList<String> listOfPendingURLs = new ArrayList<>();
		ArrayList<String> listOfTraversedURLs = new ArrayList<>();
		
		listOfPendingURLs.add(urlString);
		while(!listOfPendingURLs.isEmpty() && listOfTraversedURLs.size() <= SIZE){
			System.out.print("Crawl ");
			String urlToCrawl = listOfPendingURLs.remove(0);
			if(!listOfTraversedURLs.contains(urlToCrawl)){
				listOfTraversedURLs.add(urlToCrawl);
				System.out.println(urlToCrawl);
				
				for(String s: getSubURLs(urlToCrawl)){
					if(!listOfPendingURLs.contains(s))
						listOfPendingURLs.add(s);
				}
			}
		}
	}
	
	public static ArrayList<String> getSubURLs(String urlToCrawl){
		ArrayList<String> list = new ArrayList<>();
		
		try{
			URL url = new URL(urlToCrawl);
			Scanner input = new Scanner(url.openStream());
			int start = 0;
			
			while(input.hasNext()){
				String line = input.nextLine();
				
				start = indexOf(line, Pattern.compile("https?://"), 0);
				while(start > 0){
					int end = indexOf(line, Pattern.compile("[\'\"\\s]"), start); 
//					int end = line.indexOf("\"", start);
					if(end > 0){
						list.add(line.substring(start, end));
						start = indexOf(line, Pattern.compile("https?://"), end);
					}
						start = -1;
				}
			}
		}
		catch(Exception ex){
			System.out.println(ex);
		}
		
		return list;
	}
	
	public static int indexOf(String str, Pattern pat, int from){
		Matcher matcher = pat.matcher(str);
		return matcher.find(from) ? matcher.start() : -1;
		
	}
}
