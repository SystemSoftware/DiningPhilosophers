package org.asysob;

/**
 * Created by sturm on 21/01/2015.
 */
public class Semaphore {

    public Semaphore ( int value ) {
        jsema = new java.util.concurrent.Semaphore(value,true);
    }

    public void P () {
        jsema.acquireUninterruptibly();
    }

    public void V () {
        jsema.release();
    }

    public int value () {
        return jsema.availablePermits();
    }

    private java.util.concurrent.Semaphore jsema;
}