import sun.awt.image.ImageWatched;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class Flight {
    private LinkedList<City> flightGraph;
    private int node;
    private int edge=0;
    private int edgeLimit;
    private Random generator;

    public Flight(){
        try {
            BufferedReader in = new BufferedReader(new FileReader("/Users/bboyhao/Documents/ShortestFlight/src/cities.txt"));
            flightGraph = new LinkedList<>();
            String tmp;
            while((tmp = in.readLine()) != null){
                String cityName = tmp;
                flightGraph.add(new City(cityName));
            }
            node = flightGraph.size();
            edgeLimit = node*(node - 1)/2 - 1;
            generator = new Random();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public City getCity(int cityID){
        for(City c: flightGraph){
            if(c.getId()==cityID) return c;
        }
        return null;
    }

    public void connectNodes(int minEdge){
        if(flightGraph==null || flightGraph.size()==0) return;
        for(City c : flightGraph){
            while(c.getNumOfDestinations()<minEdge){
                int randomCity = generator.nextInt(node);
                if(getCity(randomCity) == null) continue;
                c.addTravelTo(getCity(randomCity));
                getCity(randomCity).addTravelTo(c);
                edge+=1;
            }
            if(edge==edgeLimit) break;
        }
    }

    public void printGraph(){
        for(City c : flightGraph){
            System.out.print(c.getName()+": [");
            for(City c2: c.getTravelTo()){
                System.out.print(c2.getName()+", ");
            }
            System.out.print("]");
            System.out.println();
        }
    }

    public void findShortestPath(String depature, String destination){
        City start = getCity(depature);
        City end = getCity(destination);
        boolean found = false;
        if(start == null){
            System.out.println("Sorry, the departure city is not found");
            return;
        }
        Queue<City> q = new LinkedBlockingQueue<City>();
        q.add(start);
        while(!q.isEmpty()){
            City cur = q.remove();
            cur.setVisted();
            for(City c : cur.getTravelTo()){
                if(!c.isVisted()){
                    if(c.getName().equals(destination)){
                        found = true;
                        c.setPrevious(cur);
                        break;
                    }
                    c.setPrevious(cur);
                    q.add(c);
                }
            }
            if(found) break;
        }
        if(!found){
            System.out.printf("Sorry, there is no flight from %s to %s", depature, destination);
        }
        else{
            Stack<City> s = new Stack<>();
            City tmp = end;
            while(tmp!=null){
                s.push(tmp);
                tmp = tmp.getPrevious();
            }
            while(!s.isEmpty()){
                System.out.print(s.pop().getName()+"-->");
            }
        }

    }

    public City getCity(String name){
        for(City c : flightGraph){
            if(c.getName().equals(name)) return c;
        }
        return null;
    }
}
