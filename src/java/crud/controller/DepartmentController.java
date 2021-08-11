package crud.controller;

import com.google.gson.Gson;
import crud.dao.DepartmentDao;
import crud.daoImp.DepartmentDaoImp;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DepartmentController {

    @RequestMapping(value = "department")
    private String getDepartment() {
        JSONObject jsonobject = new JSONObject();
        Gson gson = new Gson();
        try {
            RestTemplate rest = new RestTemplate();
            DepartmentDao daoImp = new DepartmentDaoImp();
            jsonobject.put("data", daoImp.getDepartment());
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
            e.printStackTrace();
        }
        return gson.toJson(jsonobject);
    }
}
