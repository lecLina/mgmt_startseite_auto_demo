package velocloud.tasyteme.com.mgmt_startseite_auto_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import velocloud.tasyteme.com.mgmt_startseite_auto_demo.model.*;
import velocloud.tasyteme.com.mgmt_startseite_auto_demo.service.Datenberechnung;
import velocloud.tasyteme.com.mgmt_startseite_auto_demo.service.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
public class anbindungHtml {

    @Autowired
    Datenberechnung dr;
    @RequestMapping(value = "/")
    public String index(Model model) {

        model.addAttribute("Data", dr.getrowData());

        return "index2";
    }
}
