package net.xtlog.system.dept.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class DeptApi {

    @RequestMapping("/dept/list")
    public void list(Integer pageNo, Integer pageSize,
                     HttpServletRequest request , HttpServletResponse response){




    }

}
