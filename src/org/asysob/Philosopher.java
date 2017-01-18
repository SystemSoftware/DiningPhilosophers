package org.asysob;

/**
 * Created by sturm on 21/01/2015.
 */
public class Philosopher implements Runnable {

    public Philosopher ( int _id, Table _table) {
        myid = _id;
        n_meals = 0;
        table = _table;
        Thread thread = new Thread((Runnable) (this));
        thread.start();
    }

    private void tell ( int s ) {
        synchronized (Philosopher.class) {
            for (int c = 0; c < s; c++) System.out.print("      ");
            System.out.format("+%4d+\n", myid);
        }
    }

    public void run () {
        while (true) {
            try {
                int seat = table.join(myid);
                table.takeLeftStick(seat);
                Thread.sleep(100);
                table.takeRightStick(seat);
                // tell(seat);
                Thread.sleep((int) (Math.random() * 50));
                // tell(seat);
                table.dropLeftStick(seat);
                table.dropRightStick(seat);
                table.leave(seat);
                n_meals++;
                //System.out.format("%4d: I think therefore I am\n", id);
                Thread.sleep((int) (Math.random() * 1000));
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getNumberOfMeals () {
        return n_meals;
    }

    private Table table;
    private Thread thread;
    private int myid;
    private int n_meals;
}