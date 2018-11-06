import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class FlightApp {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Flight graph = new Flight();
        graph.connectNodes(2);
        graph.printGraph();
        System.out.println("Please enter your departure");
        String departure = sc.next();
        System.out.println("Please enter your destination");
        String destination = sc.next();
        graph.findShortestPath(departure, destination);
    }
}
