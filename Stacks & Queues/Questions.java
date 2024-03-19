import java.util.Stack;
// https://leetcode.com/problems/implement-queue-using-stacks/description/
class QueueUsingStack { // This class is basically a que that is build on stacks, using all the function of stacks but is a queue in real.
	CustomStack first = new CustomStack(); // This CustomStack class was buld in stacks.java file
	CustomStack second = new CustomStack();

	void push (int item) {
		first.push(item);
	}

	int pop()  throws StackException {
		while(!first.isEmpty()) {
			second.push(first.pop());
		}

		int removed = second.pop();

		while (!second.isEmpty()) {
			first.push (second.pop());
		}

		return removed;
	}

	int peek() throws StackException {
		while (!first.isEmpty()) {
			second.push(first.pop());
		}

		int removed = second.peak();

		while (!second.isEmpty()) {
			first.push(second.pop());
		}
		return removed;	
	}

	void display() {
		first.display();
	}
}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
// when we have a question regarding sequence, answer till here, sthing in back which can be used later we always use stacks and queues
class Questions {
	// https://leetcode.com/problems/largest-rectangle-in-histogram/
    public int largestRectangleArea(int[] heights) { // Input: heights = [2,1,5,6,2,3], Output: 10
        int n = heights.length;
        int[] leftSmall = new int[n];
        int[] rightSmall = new int[n];
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        int j = n-1;

        for(int i = 0; i< n; i++){
            while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                stack.pop();
            }

            leftSmall[i] = stack.isEmpty() ? 0 : stack.peek() + 1;
            stack.push(i);
        }
        for(int i = n - 1; j >= 0;j--) {
            while(!stack2.isEmpty() && heights[stack2.peek()] >= heights[j]){
                stack2.pop();
            }
            rightSmall[j] = stack2.isEmpty() ? n-1 : stack2.peek() - 1;
            stack2.push(j);
        }
        int max = 0;
        for (int i = 0;i<n;i++){
            int t = rightSmall[i] - leftSmall[i] + 1;
            t = t * heights[i];
            max = Math.max(max,t);
        }
        return max;
    }	

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/valid-parentheses/
    public boolean isValid(String t) { // Input: s = "()[]{}", Output: true
        Stack<Character> stack = new Stack<>();
        if(t.length() < 2)return false;
        for(int i = 0;i<t.length(); i++){
            char k = t.charAt(i);
            if(k == '(' || k == '[' || k == '{'){
                stack.push(k);
            }else if(!stack.isEmpty()){
                char top = stack.pop();
                if(k == ')' && top != '('){
                    return false;
                }else if(k == ']' && top != '['){
                    return false;
                }else if(k == '}' && top != '{'){
                    return false;
                }
            }
            else{
                return false;
            }
        }
        return stack.isEmpty();
    }	
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/removing-stars-from-a-string/
    public String removeStars(String s) { // Input: s = "leet**cod*e", Output: "lecoe"
        StringBuilder sb = new StringBuilder();
        char[] t = s.toCharArray();
        for (char ch : t) {
            if(ch == '*') {
                sb.deleteCharAt(sb.length() - 1);
            }else{
                sb.append(ch);
            }
        }
        return sb.toString();


        // Stack<Character> st = new Stack<>();
        // for(int i = 0;i<s.length(); i++) {
        //     char t = s.charAt(i);
        //     if (t == '*'){
        //         st.pop();
        //     }else{
        //         st.push(t);
        //     }
        // }
        // StringBuilder sb = new StringBuilder();
        // while(!st.isEmpty()) {
        //     sb.insert(0, st.pop());
        // }
        // System.out.print(sb);
        // return sb.toString();
        
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/description/
    public int minAddToMakeValid(String s) { // Input: s = "())", Output: 1
        Stack<Character> st = new Stack<>();
        char[] t = s.toCharArray();
        for ( char c : t) {
            if ( c == '(') {st.push(c);}
            else {
                if(!st.isEmpty() && st.peek() == '(') {st.pop();}
                else {st.push(c);}
            }
        }
        return st.size();
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------



	public static void main(String[] args) throws StackException {
		QueueUsingStack que = new QueueUsingStack();
		que.push(5);
		que.push(6);
		que.push(7);
		que.pop();
		que.push(8);
		que.push(9);
		que.push(10);
		que.pop();
		que.display();
	}
}