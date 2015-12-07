package org.ros.actionlib.example;

import org.ros.actionlib.server.SimpleActionServer;
import org.ros.actionlib.server.SimpleActionServerCallbacks;
import actionlib_tutorials.FibonacciActionFeedback;
import actionlib_tutorials.FibonacciActionGoal;
import actionlib_tutorials.FibonacciActionResult;
import actionlib_tutorials.FibonacciFeedback;
import actionlib_tutorials.FibonacciGoal;
import actionlib_tutorials.FibonacciResult;
import org.ros.actionlib.example.FibonacciActionSpec;
import org.ros.exception.RosException;

/**
 * A {@link SimpleActionServerCallbacks} for the fibonacci example.
 */
public class FibonacciSimpleActionServerCallbacks
    implements
    SimpleActionServerCallbacks<FibonacciActionFeedback, FibonacciActionGoal, FibonacciActionResult, FibonacciFeedback, FibonacciGoal, FibonacciResult> {

  @Override
  public
      void
      blockingGoalCallback(
          FibonacciGoal goal,
          SimpleActionServer<FibonacciActionFeedback, FibonacciActionGoal, FibonacciActionResult, FibonacciFeedback, FibonacciGoal, FibonacciResult> actionServer) {

    System.out.println("BLOCKING GOAL CALLBACK");

    int order = (goal.getOrder() > 0) ? goal.getOrder() : 0;
    int[] seq = new int[order];

    if (order > 0) {
      seq[0] = 0;
      publishFeedback(seq, actionServer);
      snore();
    }
    if (order > 1) {
      seq[1] = 1;
      publishFeedback(seq, actionServer);
      snore();
    }

    for (int i = 2; i < order; i++) {
      seq[i] = seq[i - 1] + seq[i - 2];
      publishFeedback(seq, actionServer);
      snore();
    }

    FibonacciActionSpec spec = null;

    try {
      spec = new FibonacciActionSpec();
    }
    catch (RosException e) {
    }

    FibonacciResult result = spec.createResultMessage();

    result.setSequence(seq);
    actionServer.setSucceeded(result, "");

  }

  @Override
  public
      void
      goalCallback(
          SimpleActionServer<FibonacciActionFeedback, FibonacciActionGoal, FibonacciActionResult, FibonacciFeedback, FibonacciGoal, FibonacciResult> actionServer) {
    System.out.println("GOAL CALLBACK");
  }

  @Override
  public
      void
      preemptCallback(
          SimpleActionServer<FibonacciActionFeedback, FibonacciActionGoal, FibonacciActionResult, FibonacciFeedback, FibonacciGoal, FibonacciResult> actionServer) {
    System.out.println("PREEMPT CALLBACK");
  }

  private void snore() {

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
    }

  }

  /**
   * Send feedback to the client on how far along the computation is.
   *
   * @param seq
   *          The sequence step the computation is on.
   * @param actionServer
   *          The action server publishing information.
   */
  private
      void
      publishFeedback(
          int[] seq,
          SimpleActionServer<FibonacciActionFeedback, FibonacciActionGoal, FibonacciActionResult, FibonacciFeedback, FibonacciGoal, FibonacciResult> actionServer) {

    FibonacciActionSpec spec = null;

    try {
      spec = new FibonacciActionSpec();
    }
    catch (RosException e) {
    }

    FibonacciFeedback feedback = spec.createFeedbackMessage();

    feedback.setSequence(seq);
    actionServer.publishFeedback(feedback);

    System.out.print("FEEDBACK:");
    for (int i = 0; i < feedback.getSequence().length; i++) {
      if (feedback.getSequence()[i] == 0 && i != 0) {
        break;
      }
      System.out.print(" " + feedback.getSequence()[i]);
    }
    System.out.println();

  }

}
