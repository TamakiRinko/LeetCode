package Algorithm;

/**
 * 快速幂算法
 */
public class QPow {
    /**
     * 递归快速幂，a^n
     * @param a
     * @param n
     * @return
     */
    int qPowRecursive(int a, int n){
        if (n == 0)
            return 1;
        else if (n % 2 == 1)
            return qPowRecursive(a, n - 1) * a;
        else{
            // 记录下temp，不用计算两次
            int temp = qPowRecursive(a, n / 2);
            return temp * temp;
        }
    }

    //非递归快速幂
    int qPowNonRecursive(int a, int n){
        int ans = 1;
        while(n != 0){
            if((n & 1) == 1)        //如果n的当前末位为1
                ans *= a;  //ans乘上当前的a
            a *= a;        //a自乘
            n >>= 1;       //n往右移一位
        }
        return ans;
    }

    //递归快速幂（对大素数取模），注意需要步步取模
    int MOD = 1000000007;
    long qPowMod(long a, long n){
        if (n == 0)
            return 1;
        else if (n % 2 == 1)
            return qPowMod(a, n - 1) * a % MOD;
        else{
            long temp = qPowMod(a, n / 2) % MOD;
            return temp * temp % MOD;
        }
    }

    /**
     * 矩阵幂乘，斐波那契数列
     */
    class matrix{
        long a1, a2, b1, b2;
        public matrix(long a1, long a2, long b1, long b2){
            this.a1 = a1;
            this.a2 = a2;
            this.b1 = b1;
            this.b2 = b2;
        }

        public matrix multiply(matrix y){
            return new matrix((a1 * y.a1 + a2 * y.b1) % MOD,
            (a1 * y.a2 + a2 * y.b2) % MOD,
            (b1 * y.a1 + b2 * y.b1) % MOD, 
            (b1 * y.a2 + b2 * y.b2) % MOD);
        }
    }

    matrix qPowMatrix(matrix a, long n)
    {
        matrix ans = new matrix(1, 0, 0, 1); //单位矩阵
        while (n != 0)
        {
            if ((n & 1) == 1)
                ans = ans.multiply(a);
            a = a.multiply(a);
            n >>= 1;
        }
        return ans;
    }
}
