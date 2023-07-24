package sql;

/**
 * 组合两个表
 *
 * @author wjs 2023/7/24
 */
public class No175 {
    //正确解法
    /**
     * select p.FirstName as firstName, p.LastName as lastName, a.City as city, a.State as state
     * from Person p
     * left join Address a
     * on p.PersonId = a.PersonId
     */
}
