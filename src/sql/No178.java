package sql;

/**
 * No178.分数排名
 *
 * @author wjs 2023/8/7
 */
public class No178 {
    /**
     * #解法二：子查询，核心的思想是计算比每个分数大的其他分数的数量
     * select
     * s1.score,
     * (select COUNT(distinct s2.score) from Scores s2 where s2.score > s1.score)+1 as 'rank'
     * from
     * Scores s1
     * order by
     * s1.score desc;
     */

    /**
     * #解法三：分组查询
     * SELECT
     *   S.score,
     *   COUNT(DISTINCT T.score) AS 'rank'
     * FROM
     *   Scores S
     *   INNER JOIN Scores T ON S.score <= T.score
     * GROUP BY
     *   S.id,
     *   S.score
     * ORDER BY
     *   S.score DESC;
     */
}
