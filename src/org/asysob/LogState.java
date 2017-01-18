package org.asysob;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sturm on 22/01/2015.
 */
public class LogState extends TimerTask {

    public LogState ( ThinkTank _tank ) {
        tank = _tank;
        timer = new Timer();
        timer.scheduleAtFixedRate(this,0,1000);
    }

    public void run () {
        tank.printState();
    }

    private ThinkTank tank;
    private Timer timer;
}