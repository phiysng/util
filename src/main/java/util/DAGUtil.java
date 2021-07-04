package util;

import java.util.Arrays;

/**
 * @author wyshou
 * 有向无环图
 */
public class DAGUtil {
    private boolean hasCycle; //存在循环依赖

        private int[][] adj = new int[][]{
            {0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    private int[] visited = new int[9];
    private int[] r = new int[9];
    private int n;

    //    int visit
    private void dfs(int s) {
        if (visited[s] == 1) {
            System.out.println();
            hasCycle = true;
            return;
        }

        // 已经访问过了
        if(visited[s] == 2){
            return;
        }

        visited[s] = 1;
        for (int i = 0; i < 9; i++) {
            if (adj[s][i] != 0) { // 存在 s -> i 的边
                dfs(i);
            }
        }
        visited[s] = 2;
        // 记录当前的节点
        r[--n] = s;
    }

    /**
     * 拓扑排序
     */
    public void topologicalSort() {
        for (int i = 0; i < 9; i++) {
            visited[i] = 0;
        }
        hasCycle = false;
        n = 9;

        for (int i = 0; i < 9; i++) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }

        if (hasCycle) {
            System.out.println("循环依赖");
        } else {

            for (int i = 0; i <9; i++) {
                System.out.println((r[i]));
            }
        }

    }

    public static void main(String[] args) {
        DAGUtil dagUtil = new DAGUtil();
        dagUtil.topologicalSort();
    }
}
