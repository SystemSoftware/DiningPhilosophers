package org.asysob;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("arguments: <#philosopers> <#seats>");
        }

        int n_philosophers = Integer.parseInt(args[0]);
        int n_seats = Integer.parseInt(args[1]);

        System.out.format("Dining philosophers with %d philosophers on %d seats\n",n_philosophers,n_seats);

        ThinkTank tank = new ThinkTank(n_philosophers,n_seats);

        LogState logger = new LogState(tank);

        System.in.read();
    }
}