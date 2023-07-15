import java.util.*;
import java.io.*;

public class Main {
	static int[][] map;
	static int n;
	static int l;
	static int r;
	static int flag = 0;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static ArrayList <Integer> list = new ArrayList<>();
	
	public static class Node{
		int x;
		int y;
		
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public static void open(int x, int y, int[][] part, int num) {
		Queue <Node> q = new LinkedList<>();
		q.add(new Node(x, y));
		part[x][y] = num;
		int cnt = 1;
		int sum = map[x][y];
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			x = node.x;
			y = node.y;
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx >= 0 && ny >= 0 && nx < n && ny < n && part[nx][ny] == 0) {
					int diff = Math.abs(map[x][y] - map[nx][ny]);
					if(diff >= l && diff <= r) {
						q.add(new Node(nx, ny));
						part[nx][ny] = num;
						sum += map[nx][ny];
						cnt++;
						flag = 1;
					}
				}
			}
		}
		list.add(sum / cnt);
	}
	
	public static void move(int[][] part) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				map[i][j] = list.get(part[i][j] - 1);
			}
		}
		list.clear();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		n = Integer.parseInt(tk.nextToken());
		l = Integer.parseInt(tk.nextToken());
		r = Integer.parseInt(tk.nextToken());
		map = new int[n][n];
		int time = 0;
		
		for(int i = 0; i < n; i++) {
			tk = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(tk.nextToken());
			}
		}
		
		while(true) {
			flag = 0;
			int num = 1;
			int[][] part = new int[n][n];
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(part[i][j] == 0) {
						open(i, j, part, num);
						num++;
					}
				}
			}
			if(flag == 0) break;
			move(part);
			time++;
		}
		
		System.out.println(time);
	}
}