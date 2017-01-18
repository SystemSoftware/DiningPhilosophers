package org.asysob;

/**
 * Created by sturm on 23/01/2015.
 */
public class ThinkTank {

    public ThinkTank ( int n_philosophers, int n_seats ) {
        table = new Table(n_seats);
        philosophers = new Philosopher[n_philosophers];
        for (int i = 0; i < philosophers.length; i++)
            philosophers[i] = new Philosopher(i+1,table);
    }

    public void printState () {
        table.printState();
        printPhilosopherState();
    }

    private void printPhilosopherState () {
        int min_meals = Integer.MAX_VALUE;
        int max_meals = Integer.MIN_VALUE;
        double average_meals = 0.0;
        for ( Philosopher p: philosophers) {
            int m = p.getNumberOfMeals();
            if (m < min_meals) min_meals = m;
            if (m > max_meals) max_meals = m;
            average_meals += m;
        }
        average_meals = average_meals / philosophers.length;
        System.out.format("   Philosophers: min_meals = %5d, average = %7.1f, max_meals = %5d",
                min_meals,average_meals,max_meals);
        System.out.println();
    }

    private Table table;
    private Philosopher philosophers[];
}