//package com.soulkitchen.serifenuruysal.trendgithubandroidapp.utils;
//
//
//import com.bumptech.glide.request.ResourceCallback;
//
///**
// * Created by S.Nur Uysal on 6.12.2018.
// */
//public class ElapsedTimeIdlingResource implements IdlingResource {
//    private final long startTime;
//    private final long waitingTime;
//    private ResourceCallback resourceCallback;
//
//    public ElapsedTimeIdlingResource(long waitingTime) {
//        this.startTime = System.currentTimeMillis();
//        this.waitingTime = waitingTime;
//    }
//
//    @Override
//    public String getName() {
//        return ElapsedTimeIdlingResource.class.getName() + ":" + waitingTime;
//    }
//
//    @Override
//    public boolean isIdleNow() {
//        long elapsed = System.currentTimeMillis() - startTime;
//        boolean idle = (elapsed >= waitingTime);
//        if (idle) {
//            resourceCallback.onTransitionToIdle();
//        }
//        return idle;
//    }
//
//    @Override
//    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
//        this.resourceCallback = resourceCallback;
//    }
//}
