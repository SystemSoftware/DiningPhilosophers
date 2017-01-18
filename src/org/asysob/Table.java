package org.asysob;

/**
 * Created by sturm on 21/01/2015.
 */
public class Table {

    public Table ( int _seats ) {
        n_seats = _seats;
        seats = new Seat[n_seats];
        for (int s=0; s < seats.length; s++)
            seats[s] = new Seat();
        queue = new Semaphore(0);
        in_queue = 0;
        mutex = new Semaphore(1);
    }

    public int join ( int id ) {
        while (true) {
            mutex.P();
            for (int s = 0; s < seats.length; s++) {
                if (!seats[s].occupied) {
                    seats[s].occupied = true;
                    seats[s].id = id;
                    mutex.V();
                    return s;
                }
            }
            in_queue++;
            mutex.V();
            queue.P();
        }
    }

    public void takeLeftStick ( int s ) {
        seats[s].stick.P();
    }

    public void takeRightStick ( int s ) {
        takeLeftStick((s+1) % n_seats);
    }

    public void dropLeftStick ( int s ) {
        seats[s].stick.V();
    }

    public void dropRightStick ( int s ) {
        dropLeftStick((s+1) % n_seats);
    }

    public void leave ( int s ) {
        seats[s].occupied = false;
        mutex.P();
        if (in_queue > 0) {
            in_queue--;
            queue.V();
        }
        mutex.V();
    }

    public void printState () {
        mutex.P();
        System.out.print("Table: ");
        for (Seat s: seats) {
            if (s.stick.value() == 0)
                System.out.print(". ");
            else
                System.out.print("| ");
            if (s.occupied)
                System.out.format("<%3d> ", s.id);
            else
                System.out.print(" ---  ");
        }
        mutex.V();
    }

    private int n_seats;
    private Seat seats[];
    private Semaphore queue;
    private int in_queue;
    private Semaphore mutex;
}