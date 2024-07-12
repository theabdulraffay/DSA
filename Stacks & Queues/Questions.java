import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
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
    // https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/description/
    public int minInsertions(String s) { // Input: s = "))())(", Output: 3, Explanation: Add '(' to match the first '))', Add '))' to match the last '('.
        int opening = 0, closing = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                opening++;
            } else {
                if (i + 1 < n && s.charAt(i+1) == ')') {
                    if (opening > 0) {
                        opening--;
                    } else {
                        closing++;
                    }
                    i++;
                } else {
                    if (opening > 0) {
                        opening--;
                        closing++;
                    } else {
                        closing += 2;
                    }
                }
            }
        }

        return opening*2 + closing;
        // Stack<Character> stack = new Stack();
        // int counter = 0;

        // for (int i = 0; i < s.length(); i++) {
        // char c = s.charAt(i);
        // if (c == '(') {
        // if (stack.isEmpty()) {
        // stack.push(c);
        // } else {
        // if (stack.peek() == ')') {
        // counter++;
        // stack.pop();
        // stack.pop();
        // }
        // stack.push(c);
        // }
        // } else { // c == ')'
        // if (stack.isEmpty()) {
        // counter++;
        // stack.push('(');
        // stack.push(c);
        // } else {
        // if (stack.peek() == ')') {
        // stack.pop();
        // stack.pop();
        // } else {
        // stack.push(c);
        // }
        // }
        // }
        // }

        // while (!stack.isEmpty()) {
        // if (stack.pop() == '(') {
        // counter+=2;
        // }else {
        // counter += 1;
        // stack.pop();
        // }
        // }
        // return counter;

    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/make-the-string-great/
    public String makeGood(String s) {
        Stack<Character> st = new Stack<>();

        for(char c : s.toCharArray()) {
            if (!st.isEmpty() && Math.abs(c - st.peek()) == 32) {
                st.pop();
            } else {
                st.push(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) {
            sb.insert(0, st.pop());
        }
        return sb.toString();        
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/description/
    public int countStudents(int[] students, int[] sandwiches) {
        Stack<Integer> sandwich = new Stack<>();
        Queue<Integer> student = new LinkedList<Integer>();
        int n = students.length, k = 0;
        for (int i = n - 1; i >= 0; i--) {
            sandwich.push(sandwiches[i]);
            student.add(students[k++]);
        }

        while(!student.isEmpty()) {
            if (student.peek() == sandwich.peek()) {
                student.poll();
                sandwich.pop();
            }
            else if (student.contains(sandwich.peek())) {
                while (student.peek() != sandwich.peek()) {
                    student.add(student.poll());
                }
            } else {
                return student.size();
            }
        }
        return 0;
        
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/time-needed-to-buy-tickets
    public int timeRequiredToBuy(int[] tickets, int k) {
        // int sold = 0;
        // while (tickets[k] != 0) {
        //     for (int i = 0; i < tickets.length; i++) {
        //         if (tickets[i] != 0) {
        //             tickets[i] = tickets[i] - 1;
        //             sold++;
        //         }
        //         if (tickets[k] == 0) break;
        //     }
        // }
        // return sold;

        int time = 0;
        for (int i = 0; i < tickets.length; i++) {
            if (i <= k) {
                time += Math.min(tickets[k], tickets[i]);
            } else {
                time += Math.min(tickets[i], tickets[k] - 1);
            }
        }
        return time;
        
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/remove-k-digits/
    public String removeKdigits(String num, int k) {
        if (k == num.length()) return "0";
        Stack<Character> st = new Stack<>();
        for (char i : num.toCharArray()) {
            while(k > 0 && !st.isEmpty() && i < st.peek()) {
                st.pop();
                k--;
            }
            st.push(i);
        }

        while (k > 0 && !st.isEmpty()) {
            st.pop();
            k--;
        }
        StringBuilder temp = new StringBuilder();
        while (!st.isEmpty()) {
            temp.append(st.pop());
        }
        temp.reverse();
        while (temp.length() > 1 && temp.charAt(0) == '0') {
            temp.deleteCharAt(0);
        }
        return temp.toString();
        
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/daily-temperatures/
    public int[] dailyTemperatures(int[] temperatures) { // Input: temperatures = [73,74,75,71,69,72,76,73], Output: [1,1,4,2,1,1,0,0]
        int n = temperatures.length;
        int[] ar = new int[n];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && temperatures[i] > temperatures[st.peek()]) {
                int ind = st.pop();
                ar[ind] = i - ind;
            }
            st.push(i);
        }
        return ar;
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/crawler-log-folder/description/
    public int minOperations(String[] logs) {
        int op = 0;
        for(String s : logs) {
            if(s.equals("../")) {
                if(op != 0) op--;
            } else if (s.charAt(0) != '.') op++;
          
        }
        return op;
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/description/?envType=daily-question&envId=2024-07-11
    void helper(Stack<Character>  st) { // will reverse the string till the last parenthesis
        Queue<Character> que = new LinkedList<>();
        while(st.peek() != '(') {
            que.offer(st.pop());
        } 
        st.pop();
        while(!que.isEmpty()) {
            st.push(que.poll());
        }
    }

    public String reverseParentheses(String s) {
        Stack<Character> st = new Stack<>();
        for(char c : s.toCharArray()) {
            if(c == ')') {
                helper(st);
            } else {
                st.push(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()) {
            sb.append(st.pop());
        }
        return sb.reverse().toString();
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/maximum-score-from-removing-substrings
    String delete(String s, String torem) {
        Stack<Character> st = new Stack<>();
        for(char c : s.toCharArray()) {
            if(c == torem.charAt(1) &&
                !st.isEmpty() &&
                st.peek() == torem.charAt(0)
            ) {
                st.pop();
            } else {
                st.push(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()) {
            sb.append(st.pop());
        }

        return sb.reverse().toString();
    }
    public int maximumGain(String s, int x, int y) {
        Stack<Character> st = new Stack<>();
        String high = x > y ? "ab" : "ba";
        String low  = x < y ? "ab" : "ba";

        String afterDeletion = delete(s, high);
        int toret = 0;

        toret += (s.length() - afterDeletion.length()) / 2 * Math.max(x, y);

        String another = delete(afterDeletion, low);
        toret += (afterDeletion.length() - another.length()) / 2 * Math.min(x, y);
        return toret;
        
    }

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