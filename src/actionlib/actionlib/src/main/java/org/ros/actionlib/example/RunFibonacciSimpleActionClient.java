package org.ros.actionlib.example;

import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.namespace.GraphName;

import org.ros.actionlib.client.SimpleActionClient;
import org.ros.actionlib.client.SimpleActionClientCallbacks;
import org.ros.actionlib.state.SimpleClientGoalState;
import org.ros.exception.RosException;
import org.ros.message.Duration;
import actionlib_tutorials.FibonacciActionFeedback;
import actionlib_tutorials.FibonacciActionGoal;
import actionlib_tutorials.FibonacciActionResult;
import actionlib_tutorials.FibonacciFeedback;
import actionlib_tutorials.FibonacciGoal;
import actionlib_tutorials.FibonacciResult;
//import org.ros.node.NodeConfiguration;
//import org.ros.node.NodeRunner;

import java.util.concurrent.TimeUnit;
import java.lang.InterruptedException;

public class RunFibonacciSimpleActionClient extends AbstractNodeMain {

  @Override
  public GraphName getDefaultNodeName() {
    return GraphName.of("fibonacci_client");
  }

  @Override
  public void onStart(ConnectedNode connectedNode) {
    try {
      int order = 4;
      boolean finished_before_timeout = false;

      FibonacciActionSpec spec = new FibonacciActionSpec();

      SimpleActionClient<FibonacciActionFeedback, FibonacciActionGoal, FibonacciActionResult, FibonacciFeedback, FibonacciGoal, FibonacciResult> sac =
          spec.buildSimpleActionClient("fibonacci/");
      //runner.run(sac, configuration);
      sac.addClientPubSub(connectedNode);

      System.out.println("[Test] Waiting for action server to start");
      // wait for the action server to start
      try {
        sac.waitForServer(); // will wait for infinite time
      }
      catch (InterruptedException e) {
      }

      System.out.println("[Test] Action server started, sending goal");
      // send a goal to the action
      FibonacciGoal goal = spec.createGoalMessage();
      //goal.order = order;
      goal.setOrder(order);
      sac.sendGoal(goal);

      // wait for the action to return
      System.out.println("[Test] Waiting for result.");
      try {
        finished_before_timeout = sac.waitForResult(100, TimeUnit.SECONDS);
      }
      catch (InterruptedException e) {
      }

      if (finished_before_timeout) {
        SimpleClientGoalState state = sac.getState();
        System.out.println("[Test] Action finished: " + state.toString());

        FibonacciResult res = sac.getResult();
        System.out.print("[Test] Fibonacci sequence (" + goal.getOrder() + "):");
        for (int i : res.getSequence()) {
          System.out.print(" " + i);
        }
        System.out.println();
      } else {
        System.out.println("[Test] Action did not finish before the time out");
      }
    } catch (RosException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /*public static void main(String[] args) {
    NodeConfiguration configuration = NodeConfiguration.newPrivate();
    NodeRunner runner = NodeRunner.newDefault();

    run(runner, configuration);

  }

  public static void run(NodeRunner runner, NodeConfiguration configuration) {
    try {
      int length = 10;

      // create action specification for the Fibonacci action without
      // having to write a lot of data types as class parameters for
      // the ActionSpec/ActionClient classes based on Generics
      FibonacciActionSpec spec = new FibonacciActionSpec();
      FibonacciSimpleActionClient sac = spec.buildSimpleActionClient("fibonacci_client");

      runner.run(sac, configuration);
      try {
        Thread.sleep(10000);
      } catch (InterruptedException e) {
        // Don't care
      }

      System.out.println("[Test] Waiting for action server to start");
      // wait for the action server to start
      sac.waitForServer(); // will wait for infinite time

      System.out.println("[Test] Action server started, sending goal");
      // send a goal to the action
      FibonacciGoal goal = spec.createGoalMessage();
      goal.order = length;
      sac.sendGoal(goal, new SimpleActionClientCallbacks<FibonacciFeedback, FibonacciResult>() {
        @Override
        public void feedbackCallback(FibonacciFeedback feedback) {
          System.out.print("Client feedback\n\t");
          for (int num : feedback.sequence)
            System.out.print(" " + num);
          System.out.println();
        }

        @Override
        public void doneCallback(SimpleClientGoalState state, FibonacciResult result) {
          System.out.println("Client done " + state);
          System.out.println("\t" + result.sequence[result.sequence.length - 1]);
        }

        @Override
        public void activeCallback() {
          System.out.println("Client active");
        }
      });

      // wait for the action to return
      System.out.println("[Test] Waiting for result.");
      boolean finished_before_timeout = sac.waitForResult(new Duration(100.0));

      if (finished_before_timeout) {
        SimpleClientGoalState state = sac.getState();
        System.out.println("[Test] Action finished: " + state.toString());

        FibonacciResult res = sac.getResult();
        System.out.print("[Test] Fibonacci sequence (" + goal.order + "):");
        for (int i : res.sequence) {
          System.out.print(" " + i);
        }
        System.out.println();
      } else {
        System.out.println("[Test] Action did not finish before the time out");
      }
    } catch (RosException e) {
      e.printStackTrace();
    }
  }

  public static void run2(NodeRunner runner, NodeConfiguration configuration) {

    try {
      int order = 4;

      FibonacciActionSpec spec = new FibonacciActionSpec();

      SimpleActionClient<FibonacciActionFeedback, FibonacciActionGoal, FibonacciActionResult, FibonacciFeedback, FibonacciGoal, FibonacciResult> sac =
          spec.buildSimpleActionClient("fibonacci");
      runner.run(sac, configuration);

      System.out.println("[Test] Waiting for action server to start");
      // wait for the action server to start
      sac.waitForServer(); // will wait for infinite time

      System.out.println("[Test] Action server started, sending goal");
      // send a goal to the action
      FibonacciGoal goal = spec.createGoalMessage();
      goal.order = order;
      sac.sendGoal(goal);

      // wait for the action to return
      System.out.println("[Test] Waiting for result.");
      boolean finished_before_timeout = sac.waitForResult(new Duration(100.0));

      if (finished_before_timeout) {
        SimpleClientGoalState state = sac.getState();
        System.out.println("[Test] Action finished: " + state.toString());

        FibonacciResult res = sac.getResult();
        System.out.print("[Test] Fibonacci sequence (" + goal.order + "):");
        for (int i : res.sequence) {
          System.out.print(" " + i);
        }
        System.out.println();
      } else {
        System.out.println("[Test] Action did not finish before the time out");
      }
    } catch (RosException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }*/
}
