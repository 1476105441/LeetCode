package sql;

/**
 * 部门前三高工资
 *
 * @author wjs 2024/5/29
 */
public class No185 {
    // 正着思考不行就得换个角度思考，前三工资就说明不超过三个人工资比他高
    /*select d.name as 'Department', t.name as 'Employee', t.salary as 'Salary'
    from (
            select e1.name, e1.salary, e1.departmentId
            from employee e1
                    where 3 > (
                    select count(distinct e2.salary)
    from employee e2
    where e2.departmentId = e1.departmentId and e2.salary > e1.salary
    )
            ) t
    join department d
    where t.departmentId = d.id*/
}
