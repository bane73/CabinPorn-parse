import java.io.*;
import java.net.*;
import java.util.*;

public class Runner {
  
  private static final String baseUrl = "http://cabinporn.com";
  private static final String pageUrl = "/page/";
  
  private int lastPage = 10;
  private List<String> searchWords;
  
  public Runner(int lastPage, List<String> searchWords) {
    System.out.println("Runner(" + lastPage + ")");
    this.lastPage = lastPage;
    this.searchWords = searchWords;    
  }
  
  public void search() throws Exception {
    
    if(this.searchWords == null || this.searchWords.size() < 1) {
      System.out.println("No search words to search for!");
      return;
    }
    System.out.println("Searching for words: " + searchWords);
    
    int numHitsFound = 0;
    
    for(int i = 1, j = this.lastPage ; i <= j ; i++) {      
      String url = baseUrl;
      
      if( i > 1 ) {
        url += pageUrl + i;
      }
      
      String html = getHTML(url);
      
      for(String sw : searchWords) {
        if(html.contains(sw.trim())) {
          numHitsFound++;
          System.out.println("Found search-word(" + sw + ") on page-" + i + "!");
        }
      }
      
    }
    
    if(numHitsFound > 0) {
      System.out.println("\n*** FOUND " + numHitsFound + " HITS!!!");
    }
    else {
      System.out.println("\nFound no hits.\n");
    }
    System.out.println(new Date());
  }
  
  public static String getHTML(String urlToRead) throws Exception {
      System.out.println("Fetching page at: " + urlToRead);
    
      StringBuilder result = new StringBuilder();
      URL url = new URL(urlToRead);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String line;
      while ((line = rd.readLine()) != null) {
         result.append(line);
      }
      rd.close();
      return result.toString();
   }
  
  public static void main(String[] args) throws Exception {
    System.out.println("\n\n\n");
    System.out.println("*******************************"); 
    System.out.println("***     CabinPorn-Parse     ***");
    System.out.println("*******************************");
    System.out.println("\n"); 
                
    boolean isLoop = false;
    int sleepTimeMs = 1000;
    int pageDepthToSearch = -1;
    List<String> searchWords = new ArrayList();
    
    // parse the command-line args
    if( args.length > 0 ) {
      for(String arg : args) {
        System.out.println("arg:" + arg);
                    
        String[] arg_parts = arg.split("=");          
        if(arg_parts == null || arg_parts.length != 2) {
          System.out.println("arg_parts[" + arg + "] cannot be parsed correctly!");
          continue;
        }
        System.out.println("arg_parts[0]:" + arg_parts[0] + ", arg_parts[1]:" + arg_parts[1]);
        
        switch(arg_parts[0]) {
          case "loop":
              if("true".equals(arg_parts[1])) {
                isLoop = true;
              }
              break;
            
          case "sleepTimeMs":
              sleepTimeMs = Integer.parseInt(arg_parts[1]); 
              break;
            
          case "pageDepthToSearch":
              pageDepthToSearch = Integer.parseInt(arg_parts[1]);
              break;
            
          case "searchWords":
              searchWords = Arrays.asList(arg_parts[1].split(","));
              break;
            
          default:
              throw new RuntimeException("ERROR: UNRECOGNIZED ARGUMENT! Arg:" + arg_parts[0]);
        }
        
      }
      
    }    
    
    // init
    Runner r = new Runner(pageDepthToSearch, searchWords);
        
    boolean isDone = false;
    int numSearches = 0;
    while(!isDone) {
      r.search();
      numSearches++;
      
      if(isLoop) {
        System.out.println("Num searches conducted: " + numSearches);
        System.out.println("\n\n\n");
        Thread.sleep(sleepTimeMs);
      }
      else {
        isDone = true;
      }
    }
    
  }
  
}

