package tree2;

import java.util.*;

public class CompleteBinaryTree<T> {
	private Object[] nodes;
	private int lastIndex; // 채워진 마지막 노드의 인덱스
	private final int SIZE; // 최대 노드의 개수

	public CompleteBinaryTree(int size) {
		this.SIZE = size;
		nodes = new Object[size + 1];
	}

	public boolean isEmpty() {
		return lastIndex == 0;
	}

	public boolean isFull() {
		return lastIndex == SIZE;
	}

	public boolean add(T data) {
		if (isFull())
			return false;
		nodes[++lastIndex] = data;
		return true;
	}

	public void bfs() {
		if (isEmpty())
			return;
		// 탐색 순서를 관리할 대기열 자료구조 생성
		Queue<Integer> queue = new ArrayDeque<Integer>();
		// 탐색 시작의 대상부터 큐에 넣기
		queue.offer(1); // 루트 노드의 인덱스

		while (!queue.isEmpty()) { // 탐색 대상이 있다면
			int current = queue.poll(); // 탐색 대상 큐에서 꺼내기
			// 탐색 대상 방문처리
			System.out.print(nodes[current]);
			// 현재 탐색 대상을 통해 탐색해야 할 새로운 대상을 큐에 넣기
			if (current * 2 <= lastIndex) // 자식노드
				queue.offer(current * 2);
			if (current * 2 + 1 <= lastIndex)
				queue.offer(current * 2 + 1);
		}
	}

	public void bfs2() {
		if (isEmpty())
			return;
		// 탐색 순서를 관리할 대기열 자료구조 생성
		Queue<Integer> queue = new ArrayDeque<Integer>();
		// 탐색 시작의 대상부터 큐에 넣기
		queue.offer(1); // 루트 노드의 인덱스

		int breadth = 0;
		while (!queue.isEmpty()) { // 탐색 대상이 있다면
			int size = queue.size();
			while (--size >= 0) {
				int current = queue.poll(); // 탐색 대상 큐에서 꺼내기
				// 탐색 대상 방문처리
				System.out.print(nodes[current] + "\t");
				// 현재 탐색 대상을 통해 탐색해야 할 새로운 대상을 큐에 넣기
				if (current * 2 <= lastIndex) // 자식노드
					queue.offer(current * 2);
				if (current * 2 + 1 <= lastIndex)
					queue.offer(current * 2 + 1);
			}
			System.out.println();
			System.out.println("==========" + breadth + "너비 탐색 완료");
			breadth++;
		}

	}

	/*
	 * 노드처리 후 자식노드를 탐색할 수 있게 작업하는 방식
	 * bfs: 큐에 넣기 => 다음 탐색 노드의 정보
	 * 전위순회: 메소드 호출
	 */
	public void dfsByPreOrder(int current) { // 현재 노드를 전위 순회로 탐색
		// 탐색 대상 방문처리
		System.out.print(nodes[current]);
		// 현재 탐색 대상을 통해 탐색해야 할 새로운 대상을 재귀 호출로 탐색시키기
		if (current * 2 <= lastIndex) // 자식노드
			dfsByPreOrder(current * 2); // 큐에 넣는 행위 대신 메소드 호출
		if (current * 2 + 1 <= lastIndex)
			dfsByPreOrder(current * 2 + 1);
	}
	
	public void dfsByInOrder(int current) { // 현재 노드를 중위 순회로 탐색
		// 현재 탐색 대상을 통해 탐색해야 할 새로운 대상을 재귀 호출로 탐색시키기
		if (current * 2 <= lastIndex)
			dfsByInOrder(current * 2); // 큐에 넣는 행위 대신 메소드 호출
		if (current * 2 + 1 <= lastIndex)
			dfsByInOrder(current * 2 + 1);
		// 탐색 대상 방문처리
		System.out.print(nodes[current]);
	}
	
	public void dfs() {
		if (isEmpty())
			return;
		// 탐색 순서를 관리할 대기열 자료구조 생성
		Stack<Integer> stack = new Stack<Integer>();
		// 탐색 시작의 대상부터 큐에 넣기
		stack.push(1); // 루트 노드의 인덱스

		while (!stack.isEmpty()) { // 탐색 대상이 있다면
			int current = stack.pop(); // 탐색 대상 큐에서 꺼내기
			// 탐색 대상 방문처리
			System.out.print(nodes[current]);
			// 현재 탐색 대상을 통해 탐색해야 할 새로운 대상을 큐에 넣기
			if (current * 2 <= lastIndex) // 자식노드
				stack.push(current * 2);
			if (current * 2 + 1 <= lastIndex)
				stack.push(current * 2 + 1);
		}
	}
}
