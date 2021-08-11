package crud.controller;

import com.google.gson.Gson;
import crud.dao.UnitDao;
import crud.daoImp.UnitDaoImp;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UnitController {

    @RequestMapping(value = "unit")
    private String getUnit(@RequestParam(value = "department_id") String department_id) {
        JSONObject jsonobject = new JSONObject();
        Gson gson = new Gson();
        try {
            RestTemplate rest = new RestTemplate();
            UnitDao daoImp = new UnitDaoImp();
            jsonobject.put("data", daoImp.getUnitByDepartmentId(department_id));
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
            e.printStackTrace();
        }
        return gson.toJson(jsonobject);
    }
}
