package crud.dao;

import java.util.List;

public interface EmployeeDao {
    public List getEmployee();
    public int insertEmployee(String employee_id, String employee_name ,String department_id,String department_unit);
    public int deleteEmployeeByEmployeeId(String employee_id);
    public int editEmployeeByEmployeeId(String employee_id , String employee_name,String department_id,String department_unit);
}
