/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.controller;

import com.google.gson.Gson;
import crud.dao.DepartmentDao;
import crud.daoImp.DepartmentDaoImp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author aglis
 */
@Controller
public class MasterController {

    @RequestMapping(value = "home")
    public ModelAndView index() {
        DepartmentDao depDao = new DepartmentDaoImp();
        
        List departements = depDao.getDepartment();
        
        Map data = new HashMap();
        data.put("departements", departements);
        return new ModelAndView("index", data);
    }
}
