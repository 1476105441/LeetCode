package sql;

/**
 * 第二高的薪水
 *
 * @author wjs 2023/7/24
 */
public class No176 {
    //错误写法，无法处理不存在第二稿薪水的情况以及薪水相同的情况
    /**
     * select salary as SecondHighestSalary
     * from Employee
     * where id != (select id from Employee order by salary desc limit 1)
     * order by salary desc
     * limit 1;
     */

    //使用ifnull函数优化，但仍然无法处理薪水相同的情况，比如只有两个薪水且这两个薪水相同的情况
    /**
     * select
     * IFNULL((select salary
     * from Employee
     * where id != (select id from Employee order by salary desc limit 1)
     * order by salary desc
     * limit 1),NULL)  as SecondHighestSalary
     * ;
     */

    //使用偏移量关键字能够轻松解决找到第二高的需求，并且加上去重和null值的特殊处理
    //这个写法我还不是很理解，为什么再单独查询一次就能让返回结果为null而不是为空了呢？
    /**
     * select (
     *     select distinct salary from Employee order by salary desc limit 1 offset 1
     * ) as SecondHighestSalary
     * ;
     */

    //使用ifnull解决为空，而不是为null的问题，更好理解
    /**
     * select ifnull(
     *     (select distinct salary from Employee order by salary desc limit 1 offset 1),null
     * ) as SecondHighestSalary
     * ;
     */
}
