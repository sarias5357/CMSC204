/**
 * Infix, postfix, evaluation
 * @author Steven
 *
 */
public class Notation {
	public static double evaluatePostfixExpression(String postfixExpr)
										throws InvalidNotationFormatException {
		StackInterface<String> stack = new MyStack<>();
		
		for (int i = 0; i < postfixExpr.length(); i++) {
			char cur = postfixExpr.charAt(i);
			
			if (cur == ' ') continue;
			if (Character.isDigit(cur)) {
				try {
					stack.push(Character.toString(cur));
				} catch (StackOverflowException e) {
					System.out.println(e.getMessage());
				}
				continue;
			}
			switch(cur) {
				case '+' : case '-' : case '/': case '*': case '^':
					try {
						int first = Integer.parseInt(stack.pop());
						int second = Integer.parseInt(stack.pop());
						Integer result;
						if (cur == '+') {
							result = second + first;
							stack.push(result.toString());
						}
						else if (cur == '-') {
							result = second - first;
							stack.push(result.toString());
						}
						else if (cur == '*') {
							result = second * first;
							stack.push(result.toString());
						}
						else if (cur == '/') {
							result = second / first;
							stack.push(result.toString());
						}
						else if (cur == '+') {
							result = (int) Math.pow(second, first);
							stack.push(result.toString());
						}
						
					}
					catch (StackUnderflowException e) {
						throw new InvalidNotationFormatException();
					}
					catch (StackOverflowException e) {
						throw new InvalidNotationFormatException();
					}
					break;
				default:break;
			}
		}
		try {
			return Double.parseDouble(stack.top());
		} catch (StackUnderflowException e) {
			throw new InvalidNotationFormatException();
		}
	}
	
	public static String convertPostfixToInfix(String postfix)
										throws InvalidNotationFormatException {
		StackInterface<String> stack = new MyStack<>();
		char cur;
		String curToString;
		String first = "", second = "";
		String expr;
		
		for (int i = 0; i < postfix.length(); i++) {
			curToString = Character.toString(postfix.charAt(i));
			cur = postfix.charAt(i);
			
			if (Character.isWhitespace(cur)) continue;
			else if (Character.isLetterOrDigit(cur)) {
				try {
					stack.push(curToString);
				}
				catch (StackOverflowException e) {
					throw new InvalidNotationFormatException();
				}
			}
			else {
				switch(cur) {
				case '+' : case '-' : case '/': case '*': case '^':
					try {
						first = stack.pop();
						second = stack.pop();
					}
					catch (StackUnderflowException e) {
						throw new InvalidNotationFormatException();
					}
				}
				expr = "(" + second + cur + first + ")";
				try {
					stack.push(expr);
				}
				catch (StackOverflowException e) {
					throw new InvalidNotationFormatException();
				}
			}
		}
		
		try {
			return stack.pop();
		}
		catch (StackUnderflowException e) {
			throw new InvalidNotationFormatException();
		}
	}
	
	public static String convertInfixToPostfix(String infix)
										throws InvalidNotationFormatException {
		char[] arr = {'*', '/', '+', '-'}; // Array of operators (in order of precedence)
		
		// create Stack and queue
		StackInterface<Character> stack = new MyStack<>();
		QueueInterface<Character> postfixSolution = new MyQueue<>();
		char cur; // Current character
		
		// Go through infix
		for (int i = 0; i < infix.length(); i++) {
			// Get current char
			cur = infix.charAt(i);
			
			// If space ignore
			if (cur == ' ') continue;
			// If digit
			else if (Character.isLetterOrDigit(cur)) {
				// Add to queue if not full
				try {
					postfixSolution.enqueue(cur);
				} catch (QueueOverflowException e) {
					System.out.println(e.getMessage());
				}
			}
			else {
				switch(cur) {
					// If left parentheses 
					case '(':
						// Add to stack if not full
						try {
							stack.push(cur);
						} catch (StackOverflowException e) {
							System.out.println(e.getMessage());
						}
						break;
					// If an operator
					case '+' : case '-' : case '/': case '*':
						boolean prec = false; // Whether or not precedence is greater
						
						// Indexes of operators (in relation to the array)
						int curIndex = -1;;
						int stackIndex = -1;;
					
						// While the stack has items and the precedence is less
						while (!stack.isEmpty() && !prec) {
							// Get indexes
							for (int j = 0; j < arr.length; j++) {
								if (cur == arr[j]) curIndex = j;
						
								try {
									if (stack.top() == arr[j]) stackIndex = j;
								} catch (StackUnderflowException e) {
									e.getMessage();
								}
							}
							// Compare indexes to determine precedence. If false, add top to queue and pop stack
							if (curIndex <= stackIndex) {
								prec = false;
								try {
									postfixSolution.enqueue(stack.top());
									stack.pop();
								} catch (QueueOverflowException e) {
									System.out.println(e.getMessage());
								} catch (StackUnderflowException e) {
									System.out.println(e.getMessage());
								}
							}
							else prec = true;
						}
						// Push character to stack
						try {
							stack.push(cur);
						} catch (StackOverflowException e) {
							System.out.println(e.getMessage());
						}
						break;
					// If exponent push to stack
					case '^':
						try {
							stack.push(cur);
						} catch (StackOverflowException e) {
							System.out.println(e.getMessage());
						}
						break;
					// If right parentheses
					case ')':
						try {
							// Get top
							char top = stack.pop();
						
							// While the top is not a left parentheses
							while (top != '(') {
								try {
									// Add top to queue and pop stack
									postfixSolution.enqueue(top);
									top = stack.pop();
								} catch (QueueOverflowException e) {
									System.out.println(e.getMessage());
								}
							}
						// If empty without finding parentheses throw a error
						} catch (StackUnderflowException e) {
							throw new InvalidNotationFormatException();
						}
					default : break;
				}
			}		
		}
		while (!stack.isEmpty()) {
			try {
				char top = stack.pop();
				postfixSolution.enqueue(top);
			} catch (StackUnderflowException | QueueOverflowException e) {
				System.out.println(e.getMessage());
			}
		}
		
		return postfixSolution.toString("");
	}
}
