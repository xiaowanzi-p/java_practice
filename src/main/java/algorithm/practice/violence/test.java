package algorithm.practice.violence;

public class test {


        public static int countPaths(int N, int M, int P, int K) {
            // 创建动态规划数组
            int[][] dp = new int[N + 1][K + 1];

            // 初始条件：机器人在初始位置M时，移动0步到达M的方式数为1
            dp[M][0] = 1;

            // 开始动态规划
            for (int k = 1; k <= K; k++) {
                for (int i = 1; i <= N; i++) {
                    // 机器人当前位置为i，计算它移动k步到达位置j的方式数
                    for (int j = 1; j <= N; j++) {
                        if (i == 1) {
                            // 当前位置在边界1时，只能向右移动
                            if (j == 2) {
                                dp[i][k] += dp[j][k - 1];
                            }
                        } else if (i == N) {
                            // 当前位置在边界N时，只能向左移动
                            if (j == N - 1) {
                                dp[i][k] += dp[j][k - 1];
                            }
                        } else {
                            // 当前位置不在边界时，可以向左或向右移动
                            if (j == i - 1 || j == i + 1) {
                                dp[i][k] += dp[j][k - 1];
                            }
                        }
                    }
                }
            }

            // 返回机器人在K步内到达目标位置P的方式数
            return dp[P][K];
        }

        public static void main(String[] args) {
            int N = 5; // 位置范围1~N
            int P = 3; // 目标位置P
            int M = 2; // 机器人初始位置M
            int K = 3; // 机器人移动步数K

            int ways = countPaths(N, M, P, K);
            System.out.println("机器人正好走" + K + "步到达目标位置" + P + "的方式数为：" + ways);
        }
    }


