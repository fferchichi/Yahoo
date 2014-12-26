package org.sonatype.mavenbook.weather;

import org.apache.log4j.PropertyConfigurator;

import java.io.InputStream;

/**
 * Created by exo on 23/12/14.
 */
public class Main {

  private String zip;
  public Main(String zip) {
    this.zip = zip;
  }

  public static void main(String[] args) throws Exception {
    // Configure Log4J
    PropertyConfigurator.configure(Main.class.getClassLoader()
        .getResource("log4j.properties"));
    // Read the Zip Code from the Command-line (if none supplied, use 60202)
    String zipcode = "60202";
    try {
      zipcode = args[0];
    } catch( Exception e ) {}
    // Start the program
    new Main(zipcode).start();
  }

  public void start() throws Exception {
    // Retrieve Data
    InputStream dataIn = new YahooRetriever().retrieve( Integer.valueOf(zip) );
    // Parse Data
    Weather weather = new YahooParser().parse( dataIn );
    // Format (Print) Data
    System.out.print( new WeatherFormatter().format( weather ) );
  }
}
