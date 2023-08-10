package sql;

/**
 * 181.超过经理收入的员工
 *
 * @author wjs 2023/8/11
 */
public class No181 {
    /**
     * select e1.name as Employee
     * from Employee e1
     * left join Employee e2
     * on e1.managerId = e2.id
     * where e1.salary > e2.salary;
     */
}
