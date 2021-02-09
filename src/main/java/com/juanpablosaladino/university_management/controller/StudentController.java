package com.juanpablosaladino.university_management.controller;

import com.juanpablosaladino.university_management.model.Role;
import com.juanpablosaladino.university_management.model.Student;
import com.juanpablosaladino.university_management.model.TypeOfIdentificationDocument;
import com.juanpablosaladino.university_management.service.IdentificationDocumentService;
import com.juanpablosaladino.university_management.service.RoleService;
import com.juanpablosaladino.university_management.service.StudentService;
import com.juanpablosaladino.university_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/student")
public class StudentController {

    private StudentService studentService;
    private IdentificationDocumentService identificationDocumentService;
    private RoleService roleService;
    private UserService userService;

    @Autowired
    public StudentController(StudentService studentService, IdentificationDocumentService identificationDocumentService, RoleService roleService, UserService userService) {
        this.studentService = studentService;
        this.identificationDocumentService = identificationDocumentService;
        this.roleService = roleService;
        this.userService = userService;
    }

/*    @PostMapping("/login")
    public String studentLogin(@ModelAttribute("studentForm") Student student, ModelMap model) {
        String identificationDocument = student.getIdentificationDocument();
        model.addAttribute("identificationDocument", identificationDocument);
        return "redirect:/hola?identificationDocument";
    }*/

    @GetMapping("/register-form")
    public String studentRegisterForm(Model model) {
        model.addAttribute("studentForm", new Student());
        List<TypeOfIdentificationDocument> typesOfIdentificationDocument = (List<TypeOfIdentificationDocument>) identificationDocumentService.getTypesOfIdentificationDocument();
        model.addAttribute("typesOfIdentificationDocument", typesOfIdentificationDocument);
        return "student-form";
    }

    @PostMapping("/register-form")
    public String createUser(@Valid @ModelAttribute("studentForm") Student student, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("studentForm", student);
            List<TypeOfIdentificationDocument> typesOfIdentificationDocument = (List<TypeOfIdentificationDocument>) identificationDocumentService.getTypesOfIdentificationDocument();
            model.addAttribute("typesOfIdentificationDocument", typesOfIdentificationDocument);
        } else {
            try {
                Role studentRole = roleService.getRoleByName("student");

                Set<Role> studentRoles = new HashSet<Role>();
                studentRoles.add(studentRole);

                student.setRoles((Set<Role>) studentRoles);

                studentService.createStudent(student);
                model.addAttribute("studentForm", new Student());
                model.addAttribute("successfullRegistration", true);

                return "index";
/*
                return "redirect:/?successfullRegistration=true";
*/
            } catch (Exception e) {
                model.addAttribute("studentForm", student);
                List<TypeOfIdentificationDocument> typesOfIdentificationDocument = (List<TypeOfIdentificationDocument>) identificationDocumentService.getTypesOfIdentificationDocument();
                model.addAttribute("typesOfIdentificationDocument", typesOfIdentificationDocument);
                model.addAttribute("errorMessage", e.getMessage());

                String mensajePrueba = student.getClass().getSimpleName();

                model.addAttribute("mensajePrueba", mensajePrueba);
            }
        }
        return "student-form";
    }

}
