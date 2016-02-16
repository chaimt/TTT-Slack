package com.tikal.api;

/**
 * Created by Haim.Turkel on 2/16/2016.
 */
public interface TimeTracker {
    String login();
    boolean submitTime(String sessionId);
}
