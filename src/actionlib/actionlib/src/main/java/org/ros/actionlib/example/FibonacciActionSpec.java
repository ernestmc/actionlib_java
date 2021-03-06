package org.ros.actionlib.example;

import org.ros.actionlib.ActionSpec;
import org.ros.actionlib.server.ActionServerCallbacks;
import org.ros.actionlib.server.SimpleActionServerCallbacks;
import org.ros.exception.RosException;
import actionlib_tutorials.FibonacciAction;
import actionlib_tutorials.FibonacciActionFeedback;
import actionlib_tutorials.FibonacciActionGoal;
import actionlib_tutorials.FibonacciActionResult;
import actionlib_tutorials.FibonacciFeedback;
import actionlib_tutorials.FibonacciGoal;
import actionlib_tutorials.FibonacciResult;
import org.ros.node.Node;
import org.ros.actionlib.example.FibonacciSimpleActionServer;

/**
 * The FibonacciActionSpec class represents the action specification for the
 * Fibonacci action. It completely hides the Generics approach of the Actionlib
 * implementation.
 *
 * @author Alexander C. Perzylo, perzylo@cs.tum.edu
 *
 * @see ActionSpec
 */
public class FibonacciActionSpec
    extends
    ActionSpec<FibonacciAction, FibonacciActionFeedback, FibonacciActionGoal, FibonacciActionResult, FibonacciFeedback, FibonacciGoal, FibonacciResult> {

  /**
   * Constructor to create an action specification for the Fibonacci action.
   */
  public FibonacciActionSpec() throws RosException {
    super(FibonacciAction.class, "actionlib_tutorials/FibonacciAction",
        "actionlib_tutorials/FibonacciActionFeedback", "actionlib_tutorials/FibonacciActionGoal",
        "actionlib_tutorials/FibonacciActionResult", "actionlib_tutorials/FibonacciFeedback",
        "actionlib_tutorials/FibonacciGoal", "actionlib_tutorials/FibonacciResult");
  }

  @Override
  public FibonacciActionClient buildActionClient(String nameSpace) {

    FibonacciActionClient ac = null;
    try {
      ac = new FibonacciActionClient(nameSpace, this);
    } catch (RosException e) {
      e.printStackTrace();
    }
    return ac;

  }

  /*@Override
  public FibonacciActionClient buildActionClient(Node node, String nameSpace) {

    FibonacciActionClient ac = null;
    try {
      ac = new FibonacciActionClient(node, nameSpace, this);
    } catch (RosException e) {
      e.printStackTrace();
    }
    return ac;
  }*/

  @Override
  public FibonacciSimpleActionClient buildSimpleActionClient(String nameSpace) {

    FibonacciSimpleActionClient sac = null;
    try {
      return new FibonacciSimpleActionClient(nameSpace, this);
    } catch (RosException e) {
      e.printStackTrace();
    }
    return sac;

  }

  /*@Override
  public FibonacciSimpleActionClient buildSimpleActionClient(Node node, String nameSpace) {

    FibonacciSimpleActionClient sac = null;
    try {
      //sac = new FibonacciSimpleActionClient(node, nameSpace, this);
      sac = new FibonacciSimpleActionClient(nameSpace, this);
    } catch (RosException e) {
      e.printStackTrace();
    }
    return sac;

  }*/

  @Override
  public
      FibonacciActionServer
      buildActionServer(
          String nameSpace,
          ActionServerCallbacks<FibonacciActionFeedback, FibonacciActionGoal, FibonacciActionResult, FibonacciFeedback, FibonacciGoal, FibonacciResult> callbacks) {

    return new FibonacciActionServer(nameSpace, this, callbacks);

  }

  /*@Override
  public
      FibonacciActionServer
      buildActionServer(
          Node node,
          String nameSpace,
          ActionServerCallbacks<FibonacciActionFeedback, FibonacciActionGoal, FibonacciActionResult, FibonacciFeedback, FibonacciGoal, FibonacciResult> callbacks) {

    //return new FibonacciActionServer(node, nameSpace, this, callbacks);
    return new FibonacciActionServer(nameSpace, this, callbacks);

  }*/

  @Override
  public
      FibonacciSimpleActionServer
      buildSimpleActionServer(
          String nameSpace,
          SimpleActionServerCallbacks<FibonacciActionFeedback, FibonacciActionGoal, FibonacciActionResult, FibonacciFeedback, FibonacciGoal, FibonacciResult> callbacks,
          boolean useBlockingGoalCallback) {

    return new FibonacciSimpleActionServer(nameSpace, this, callbacks, useBlockingGoalCallback);

  }

  /*@Override
  public
      FibonacciSimpleActionServer
      buildSimpleActionServer(
          Node node,
          String nameSpace,
          SimpleActionServerCallbacks<FibonacciActionFeedback, FibonacciActionGoal, FibonacciActionResult, FibonacciFeedback, FibonacciGoal, FibonacciResult> callbacks,
          boolean useBlockingGoalCallback) {

    return new FibonacciSimpleActionServer(node, nameSpace, this, callbacks,
        useBlockingGoalCallback);

    return new FibonacciSimpleActionServer(nameSpace, this, callbacks,
        useBlockingGoalCallback);

  }*/

}
