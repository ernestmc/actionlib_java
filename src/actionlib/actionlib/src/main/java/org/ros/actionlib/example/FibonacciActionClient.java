package org.ros.actionlib.example;

import org.ros.actionlib.client.ActionClient;
import org.ros.exception.RosException;
import actionlib_tutorials.FibonacciActionFeedback;
import actionlib_tutorials.FibonacciActionGoal;
import actionlib_tutorials.FibonacciActionResult;
import actionlib_tutorials.FibonacciFeedback;
import actionlib_tutorials.FibonacciGoal;
import actionlib_tutorials.FibonacciResult;
import org.ros.node.Node;

/**
 * The FibonacciActionClient is a specialized ActionClient that is intended to
 * work with an action server offering services related to the Fibonacci action.
 * The FibonacciActionClient completely hides the Generics approach of the
 * ActionClient's implementation.
 *
 * @author Alexander C. Perzylo, perzylo@cs.tum.edu
 *
 * @see ActionClient
 */
public class FibonacciActionClient
    extends
    ActionClient<FibonacciActionFeedback, FibonacciActionGoal, FibonacciActionResult, FibonacciFeedback, FibonacciGoal, FibonacciResult> {

  public FibonacciActionClient(String nameSpace, FibonacciActionSpec spec) throws RosException {
    super(nameSpace, spec);
  }

  /*public FibonacciActionClient(Node parent, String nameSpace, FibonacciActionSpec spec)
      throws RosException {
    super(parent, nameSpace, spec);
  }*/
}
