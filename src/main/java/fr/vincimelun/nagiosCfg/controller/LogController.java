package fr.vincimelun.nagiosCfg.controller;
import fr.vincimelun.nagiosCfg.model.Switch;
import freemarker.template.TemplateException;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LogController {
    @GetMapping("/")
    public String index(Model model) {
        List<Switch> sws = new ArrayList();
        sws.add(new Switch("s1","switch1","1.2.3.4","p1"));
        sws.add(new Switch("s2","switch2","1.2.3.5","p2"));
        sws.add(new Switch("s3","switch2","1.2.3.7","p3"));
        sws.add(new Switch("s4","switch3","1.2.3.9",""));
        model.addAttribute("switchs",sws);
        return "logsliste";
    }
    @PostMapping("/nagios")
    public String switchToCfg(@ModelAttribute(name = "switchData") Switch aswitch, Model model, HttpServletResponse httpServletResponse){
        model.addAttribute("switch",aswitch);
        httpServletResponse.setContentType("application/x-msdownload");
        httpServletResponse.setHeader("Content-disposition", "attachment; filename="+ aswitch.getName()+".cfg");
        return "switch";
    }

    @GetMapping("/nagios")
    public String formulaireSwitch(){
        return "formswitch";
    }




}
