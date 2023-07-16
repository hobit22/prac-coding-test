import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException{
		
		// 입출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Stack<Integer> stack = new Stack<Integer>();
		
		
		br.readLine();
		String t2 =br.readLine();
		String[] n = t2.split(" ");
		int ans[] = new int[n.length];
		
		stack.push(0);
		
		for(int i=1; i<n.length; i++) {
			
			if(stack.empty()) stack.push(i);
			
			while(!stack.empty() && Integer.valueOf(n[stack.peek()]) < Integer.valueOf(n[i])) {
				ans[stack.peek()] = Integer.valueOf(n[i]);
				stack.pop();
			}
			stack.push(i);
		}
		
		while(!stack.empty()) {
			ans[stack.peek()] = -1;
			stack.pop();
		}
		
		for(int i=0; i<ans.length; i++) {
			bw.write(ans[i] + " ");
		}
		bw.write("\n");
		
		
		bw.flush();
		bw.close();
	}
		
}