package cn.shortpath;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hct
 * @Slogan 我的代码永远 0 错误
 * @date 2021/8/24
 **/
public class Dijkstra {
    public static void main(String[] args) {
//        int n = 6;
//        int[][] arr = new int[6][6];
//        for (int[] a : arr) {
//            Arrays.fill(a, Integer.MAX_VALUE);
//        }
//        for (int i = 0; i < n; i++) {
//            arr[i][i] = 0;
//        }
//        arr[0][1] = arr[1][0] = 3;
//        arr[0][2] = arr[2][0] = 8;
//        arr[0][5] = arr[5][0] = 1;
//        arr[1][3] = arr[3][1] = 2;
//        arr[2][3] = arr[3][2] = 1;
//        arr[2][4] = arr[4][2] = 3;
//
//        List<Edge> list = new ArrayList<>();
//        list.add(new Edge(0, 1, 3));
//        list.add(new Edge(1, 0, 3));
//        list.add(new Edge(0, 2, 8));
//        list.add(new Edge(2, 0, 8));
//
//        list.add(new Edge(0, 5, 1));
//        list.add(new Edge(5, 0, 1));
//        list.add(new Edge(3, 2, 1));
//        list.add(new Edge(2, 3, 1));
//
//        list.add(new Edge(1, 3, 2));
//        list.add(new Edge(3, 1, 2));
//        list.add(new Edge(4, 2, 3));
//        list.add(new Edge(2, 4, 3));
//
//
//        System.out.println(111);
//        System.out.println(Arrays.toString(bellmanFord(n, 0, list)));


        int[][] a = new int[][]{{0, 1, 100}, {0, 2, 500}, {1, 2, 100}};
        System.out.println(findCheapestPrice1(3, a, 0, 2, 1));

//        int[] arr = new int[]{3,4,2};
//        int[] clone = arr.clone();
//        clone[1] = 9;
//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(clone));
    }

    public static int[] dijkstra(int n, int source, int[][] arr) {
        int[] dist = new int[n];
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            dist[i] = arr[source][i];
        }
        for (int i = 0; i < n - 1; i++) {
            int t = -1;
            for (int j = 0; j < n; j++) {
                if (!vis[j] && (t == -1 || dist[j] < dist[t])) {
                    t = j;
                }
            }
            vis[t] = true;
            for (int j = 0; j < n; j++) {
                if (arr[t][j] != Integer.MAX_VALUE) {
                    dist[j] = Math.min(dist[j], dist[t] + arr[t][j]);
                }
            }
        }
        return dist;
    }

    public static int[] bellmanFord(int n, int source, List<Edge> arr) {
        int[] d = new int[n];
        Arrays.fill(d, 0x3f3f3f3f);
        d[source] = 0;
        for (int i = 0; i < n - 1; i++) {
            for (Edge e : arr) {
                d[e.b] = Math.min(d[e.b], d[e.a] + e.w);
            }
        }
        return d;
    }

    public static int findCheapestPrice1(int n, int[][] flights, int src, int dst, int k) {
        final int INF = 10000 * 101 + 1;
        int[] f = new int[n];
        Arrays.fill(f, INF);
        f[src] = 0;
        int ans = INF;
        for (int t = 1; t <= k + 1; ++t) {
            int[] g = new int[n];
            Arrays.fill(g, INF);
            for (int[] flight : flights) {
                int j = flight[0], i = flight[1], cost = flight[2];
                g[i] = Math.min(g[i], f[j] + cost);
            }
            System.out.println("------------f:"+Arrays.toString(f));
            System.out.println("------------g:"+Arrays.toString(g));

            f = g;
            ans = Math.min(ans, f[dst]);
        }
        return ans == INF ? -1 : ans;
    }

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        final int INF = (int) (1e4 * 101);
        int[] dis = new int[n];
        Arrays.fill(dis, INF);
        dis[src] = 0;

        for (int i = 0; i < k + 1; i++) {
            int[] p = dis.clone();
            for (int[] f : flights) {
                int a = f[0];
                int b = f[1];
                int c = f[2];
                dis[b] = Math.min(dis[b], p[a] + c);
            }
        }
        System.out.println(Arrays.toString(dis));
        return dis[dst] == INF ? -1 : dis[dst];
    }

}

class Edge {
    int a, b, w;

    public Edge(int a, int b, int w) {
        this.a = a;
        this.b = b;
        this.w = w;
    }
}
