package org.ros.actionlib.example;

import org.ros.actionlib.ActionSpec;
import org.ros.actionlib.server.DefaultSimpleActionServer;
import org.ros.actionlib.server.SimpleActionServerCallbacks;
import actionlib_tutorials.FibonacciActionFeedback;
import actionlib_tutorials.FibonacciActionGoal;
import actionlib_tutorials.FibonacciActionResult;
import actionlib_tutorials.FibonacciFeedback;
import actionlib_tutorials.FibonacciGoal;
import actionlib_tutorials.FibonacciResult;
import org.ros.node.Node;

/**
 * The FibonacciSimpleActionServer is a specialized DefaultSimpleActionServer
 * that offers services related to the Fibonacci action. The
 * FibonacciSimpleActionServer completely hides the Generics approach of the
 * DefaultSimpleActionServer's implementation.
 *
 * @author Alexander C. Perzylo, perzylo@cs.tum.edu
 *
 * @see DefaultSimpleActionServer
 */
public class FibonacciSimpleActionServer
    extends
    DefaultSimpleActionServer<FibonacciActionFeedback, FibonacciActionGoal, FibonacciActionResult, FibonacciFeedback, FibonacciGoal, FibonacciResult> {

  public FibonacciSimpleActionServer(
      String nameSpace,
      ActionSpec<?, FibonacciActionFeedback, FibonacciActionGoal, FibonacciActionResult, FibonacciFeedback, FibonacciGoal, FibonacciResult> spec,
      SimpleActionServerCallbacks<FibonacciActionFeedback, FibonacciActionGoal, FibonacciActionResult, FibonacciFeedback, FibonacciGoal, FibonacciResult> callbacks,
      boolean useBlockingGoalCallback) {
    super(nameSpace, spec, callbacks, useBlockingGoalCallback);
  }

  public FibonacciSimpleActionServer(
      Node parent,
      String nameSpace,
      ActionSpec<?, FibonacciActionFeedback, FibonacciActionGoal, FibonacciActionResult, FibonacciFeedback, FibonacciGoal, FibonacciResult> spec,
      SimpleActionServerCallbacks<FibonacciActionFeedback, FibonacciActionGoal, FibonacciActionResult, FibonacciFeedback, FibonacciGoal, FibonacciResult> callbacks,
      boolean useBlockingGoalCallback) {
    //super(parent, nameSpace, spec, callbacks, useBlockingGoalCallback);
    super(nameSpace, spec, callbacks, useBlockingGoalCallback);
  }
}
