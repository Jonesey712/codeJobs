package org.stlyouthjobs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.stlyouthjobs.models.Skills;
import org.stlyouthjobs.models.data.SkillsDao;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("skills")
public class SkillsController {

    @Autowired
    private SkillsDao skillsDao;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("title", "Add Skills");
        model.addAttribute(new Skills());
        return "skills/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAdd(Model model , @ModelAttribute @Valid Skills newSkills, HttpSession session, Errors errors){
        if (errors.hasErrors()) {
            model.addAttribute("skills", "Add Skills");
            return "skills/add";
        }

        Integer name =(Integer) session.getAttribute("user_id");
        System.out.println(name +" is session name");
        newSkills.setSession(name);

        skillsDao.save(newSkills);
        return "redirect:/projectexperience/add";
    }
}
