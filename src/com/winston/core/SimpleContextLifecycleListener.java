package com.winston.core;

import com.winston.Lifecycle;
import com.winston.LifecycleEvent;
import com.winston.LifecycleListener;

public class SimpleContextLifecycleListener implements LifecycleListener {

  public void lifecycleEvent(LifecycleEvent event) {
    Lifecycle lifecycle = event.getLifecycle();
    System.out.println("SimpleContextLifecycleListener's event " +
      event.getType().toString());
    if (Lifecycle.START_EVENT.equals(event.getType())) {
      System.out.println("Starting context.");
    }
    else if (Lifecycle.STOP_EVENT.equals(event.getType())) {
      System.out.println("Stopping context.");
    }
  }
}