import java.util.LinkedList;

public class City {
    private String name;
    private int id;
    private City previous = null;
    private static int nextId=1;
    private LinkedList<City> travelTo;
    private boolean visted = false;

    public City(String name){
        this.name = name;
        id = nextId;
        nextId+=1;
        travelTo = new LinkedList<>();
    }

    public void setPrevious(City previous){
        this.previous = previous;
    }

    public City getPrevious(){
        return previous;
    }

    public boolean isVisted(){
        return visted;
    }

    public void setVisted(){
        visted=true;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LinkedList<City> getTravelTo() {
        return travelTo;
    }

    public void addTravelTo(City city){
        if(city == this) return;
        if(hasCity(city.getId())) return;
        travelTo.add(city);
    }

    public int getNumOfDestinations(){
        return travelTo.size();
    }

    public boolean hasCity(int id){
        if(travelTo.size() == 0) return false;
        for(City c : travelTo){
            if(c.getId()==id) return true;
        }
        return false;
    }
}
