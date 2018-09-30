package evaluator;

import parser.ArithParser;
import stack.LinkedStack;

public class InfixEvaluator extends Evaluator {
	
	private LinkedStack<String> operators = new LinkedStack<String>();
	private LinkedStack<Integer> operands  = new LinkedStack<Integer>();
	
	/** return stack object (for testing purpose) */
	public LinkedStack<String> getOperatorStack() { return operators; }
	public LinkedStack<Integer> getOperandStack()  { return operands;  }
	
	
	/** This method performs one step of evaluation of a infix expression.
	 *  The input is a token. Follow the infix evaluation algorithm
	 *  to implement this method. If the expression is invalid, throw an
	 *  exception with the corresponding exception message.
	 */	
	public void evaluate_step(String token) throws Exception {
		if(isOperand(token)) {
			// TODO: What do we do if the token is an operand?
			operands.push(Integer.parseInt(token));
		} else {
			/* TODO: What do we do if the token is an operator?
			   If the expression is invalid, make sure you throw
			   an exception with the correct message.
			   
			   You can call precedence(token) to get the precedence
			   value of an operator. It's already defined in 
			   the Evaluator class.
			 */
			switch (token){
				case "(":
					operators.push(token);
					break;
				case "+":
				case "-":
				case "*":
				case "/":
				case "!":
					if (operators.isEmpty() || precedence(token) > precedence(operators.top()))
						operators.push(token);
					else {
						while (!operators.isEmpty() && precedence(token) <= precedence(operators.top()))
							process_operator();
						operators.push(token);
					}
					break;
				case ")":
					while(!operators.isEmpty() && !operators.top().equals("("))
						process_operator();
					if (operators.isEmpty()) throw new Exception("missing (");
					operators.pop();
					break;
				default:
					throw new Exception("invalid operator");
			}
		}	
	}
	/** This method evaluates an infix expression (defined by expr)
	 *  and returns the evaluation result. It throws an Exception object
	 *  if the infix expression is invalid.
	 */
	public Integer evaluate(String expr) throws Exception {
		
		for(String token : ArithParser.parse(expr)) {
			evaluate_step(token);
		}
		
		/* TODO: what do we do after all tokens have been processed? */
		while (!operators.isEmpty())
			process_operator();
		
		// The operand stack should have exactly one operand after the evaluation is done
		if(operands.size()>1)
			throw new Exception("too many operands");
		else if(operands.size()<1)
			throw new Exception("too few operands");
		
		return operands.pop();
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(new InfixEvaluator().evaluate("5+(5+2*(5+9))/!8"));
	}

	private void process_operator () throws Exception {
		String op = operators.pop();
		int a , b ;
		if (op.equals("!")) {
			if (operands.isEmpty()) throw new Exception("too few operands");
			a = operands.pop();
			a *= -1;
		} else {
			if (operands.size() < 2) throw new Exception("too few operands");
			b = operands.pop();
			a = operands.pop();
			a = PostfixEvaluator.do_operation(op, a, b);
		}
		operands.push(a);
	}
}
