package com.recover.entity;

/**
 * @author sanyue
 */
public class StuffTwins<P, Q> {
    private P twinA;
    private Q twinB;

    StuffTwins(P twinA, Q twinB){
        this.twinA = twinA;
        this.twinB = twinB;
    }
}
