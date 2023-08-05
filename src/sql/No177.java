package sql;

/**
 * 177.第N高的薪水
 *
 * @author wjs 2023/8/5
 */
public class No177 {
    /**
     * #解法一：使用offset轻松解决
     * CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
     * BEGIN
     *   DECLARE loc INT;
     *   SET loc = N-1;
     *   RETURN (
     *       # Write your MySQL query statement below.
     *       select (
     *           select distinct salary from Employee order by salary desc limit 1 offset loc
     *       ) as getNthHighestSalary
     *   );
     * END
     */
}
