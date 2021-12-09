package model;

import java.util.ArrayList;
import java.util.List;

public class Dao {

    private List<String> liste;

    public Dao(List<String> liste) {
        this.liste = liste;
    }

    public Dao() {
    	this.liste = new ArrayList<>();
    	liste.add("a");
    	liste.add("b");
    	liste.add("c");
    	liste.add("d");
    }

    public List<String> findAll() {
        return this.liste;
    }

    public String findById(int n) {
        return this.liste.get(n % this.liste.size());
    }


}
