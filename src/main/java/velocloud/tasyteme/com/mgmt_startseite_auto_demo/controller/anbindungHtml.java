package velocloud.tasyteme.com.mgmt_startseite_auto_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class anbindungHtml {

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }
}
