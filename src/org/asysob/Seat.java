package org.asysob;

/**
 * Created by sturm on 21/01/2015.
 */
public class Seat {

    public Seat () {
        occupied = false;
        stick = new Semaphore(1);
        id = -1;
    }

    public boolean occupied;
    public Semaphore stick;
    public int id;
}