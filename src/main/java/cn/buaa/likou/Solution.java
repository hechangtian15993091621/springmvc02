package cn.buaa.likou;

import java.util.*;

/**
 * @author hct
 * @Slogan 我的代码永远 0 错误
 * @date 2021/8/29
 **/
public class Solution {
    public static void main(String[] args) {
//        System.out.println(countDigitOne(13));
//        System.out.println(slidingPuzzle(new int[][]{{1, 0, 3}, {4, 5, 2}}));
        System.out.println(sumOddLengthSubarrays(new int[]{1, 4, 2, 5, 3}));
    }


    public static int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        int res = 0;
        int[] pre = new int[n + 1];
        // 1 4 2 5 3
        // 0 1 5 7 12 15
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + arr[i];
        }
        for (int len = 1; len <= n; len += 2) {
            for (int i = 0; i + len <= n; i++) {
                res += pre[i+len] - pre[i];
            }
        }
        return res;
    }

    /**
     * 解谜题
     *
     * @param board 初始状态
     * @return
     */
    public static int slidingPuzzle(int[][] board) {
        Set<String> seen = new HashSet<>();
        int[][] neighbors = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j]);
            }
        }
        String initStatus = sb.toString();
        if (initStatus.equals("123450")) {
            return 0;
        }

        int step = 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer(initStatus);
        seen.add(initStatus);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String status = queue.poll();
                if (status.equals("123450")) {
                    return step;
                }
                for (String next : getNext(neighbors, status)) {
                    if (!seen.contains(next)) {
                        queue.offer(next);
                        seen.add(next);
                    }
                }
            }
            step += 1;
        }
        return -1;
    }

    // 102453
    private static List<String> getNext(int[][] arr, String status) {
        List<String> ret = new ArrayList<>();
        char[] chars = status.toCharArray();
        int site = status.indexOf('0');
        for (int a : arr[site]) {
            char temp = chars[site];
            chars[site] = chars[a];
            chars[a] = temp;

            ret.add(new String(chars));

            char t = chars[site];
            chars[site] = chars[a];
            chars[a] = t;
        }
        return ret;
    }


    /**
     * 例如  21437
     *
     * @param n
     * @return
     */
    public static int countDigitOne(int n) {
        String s = String.valueOf(n);
        int l = s.length();
        if (l == 1) {
            return n == 0 ? n : 1;
        }
        int res = 0;
        int high = s.charAt(0) - '0';
        int other = Integer.parseInt(s.substring(1));
        // 计算最高位的1的次数
        if (high == 1) {
            // 10000 - 11437
            res += other + 1;
        } else {
            // 10000 - 19999
            res += Math.pow(10, l - 1);
        }
        //计算除最高位1出现的次数
        // 此时我们这样把数字划分
        // 1-1437   1438 - 11437   11438-21437
        res += high * (l - 1) * Math.pow(10, l - 2);

        res += countDigitOne(other);
        return res;
    }
}
