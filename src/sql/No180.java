package sql;

/**
 * 180.连续出现的数字
 *
 * @author wjs 2023/8/9
 */
public class No180 {
    /**
     * #想法：直接使用分组函数进行过滤，事实证明，想的太简单了，题目要求的是连续出现的
     * select num from Logs group by num having count(*) >= 3;
     */

    /**
     * #解法：三表连接，说实话，没想到这种写法
     * select distinct l1.num as ConsecutiveNums
     * from Logs l1
     * left join Logs l2
     * on l1.num = l2.num
     * left join Logs l3
     * on l2.num = l3.num
     * where l1.id = l2.id - 1
     * and l2.id = l3.id - 1;
     */
}
