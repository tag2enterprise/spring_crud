package crud.controller;

import com.google.gson.Gson;
import crud.daoImp.EmployeeDaoImp;
import crud.dao.EmployeeDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class EmployeeController {

    @RequestMapping(value = "employee")
    private String getEmployee(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonobject = new JSONObject();
        Gson gson = new Gson();
        try {
            RestTemplate rest = new RestTemplate();
            EmployeeDao daoImp = new EmployeeDaoImp();
            jsonobject.put("data", daoImp.getEmployee());
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
            e.printStackTrace();
        }
        return gson.toJson(jsonobject);
    }

    @RequestMapping(value = "employee", method = RequestMethod.DELETE)
    private String deleteEmployee(@RequestParam(value = "employee_id") String employee_id) {
        JSONObject jsonobject = new JSONObject();
        Gson gson = new Gson();
        try {
            String statusCode = "0";
            RestTemplate rest = new RestTemplate();
            EmployeeDao daoImp = new EmployeeDaoImp();
            int status = daoImp.deleteEmployeeByEmployeeId(employee_id);
            String message = "Number row affected " + status;
            if (status == -1) {
                message = "An error occured";
                statusCode = "-1";
            }
            jsonobject.put("message", message);
            jsonobject.put("status", statusCode);
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
            e.printStackTrace();
        }
        return gson.toJson(jsonobject);
    }

    @RequestMapping(value = "employee", method = RequestMethod.POST)
    private String insertEmployee(
            @RequestParam(value = "employee_id") String employee_id,
            @RequestParam(value = "employee_name") String employee_name,
            @RequestParam(value = "department_id") String department_id,
            @RequestParam(value = "unit_id") String unit_id) {
        JSONObject jsonobject = new JSONObject();
        Gson gson = new Gson();
        try {
            String statusCode = "0";
            RestTemplate rest = new RestTemplate();
            EmployeeDao daoImp = new EmployeeDaoImp();
            int status = daoImp.insertEmployee(employee_id, employee_name, department_id, unit_id);
            String message = "Successfully inserted";
            if (status == -1) {
                message = "An error occured";
                statusCode = "-1";
            }
            jsonobject.put("message", message);
            jsonobject.put("status", statusCode);
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
            e.printStackTrace();
        }
        return gson.toJson(jsonobject);
    }

    @RequestMapping(value = "employee", method = RequestMethod.PUT)
    private String editEmployee(@RequestParam(value = "employee_id") String employee_id,
            @RequestParam(value = "employee_name") String employee_name,
            @RequestParam(value = "department_id") String department_id,
            @RequestParam(value = "unit_id") String unit_id) {
        JSONObject jsonobject = new JSONObject();
        Gson gson = new Gson();
        try {
            String statusCode = "0";
            RestTemplate rest = new RestTemplate();
            EmployeeDao daoImp = new EmployeeDaoImp();
            int numRowAffected = daoImp.editEmployeeByEmployeeId(employee_id, employee_name, department_id, unit_id);
            String message = "Number row affected " + numRowAffected;
            if (numRowAffected == -1) {
                message = "An error occured";
                statusCode = "-1";
            }
            jsonobject.put("message", message);
            jsonobject.put("status", statusCode);
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
            e.printStackTrace();
        }
        return gson.toJson(jsonobject);
    }
}
