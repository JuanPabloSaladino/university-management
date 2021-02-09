package com.juanpablosaladino.university_management.controller;

import com.juanpablosaladino.university_management.model.*;
import com.juanpablosaladino.university_management.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private IdentificationDocumentService identificationDocumentService;
    private RoleService roleService;
    private ProfessorService professorService;
    private UserService userService;
    private StudentService studentService;
    private SubjectService subjectService;

    @Autowired
    public AdminController(IdentificationDocumentService identificationDocumentService, RoleService roleService, ProfessorService professorService, UserService userService, StudentService studentService, SubjectService subjectService) {
        this.identificationDocumentService = identificationDocumentService;
        this.roleService = roleService;
        this.professorService = professorService;
        this.userService = userService;
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    @PostMapping("/login")
    public String adminLogin(@Valid @ModelAttribute("studentForm") Admin admin, BindingResult result, ModelMap model) {
        return "loginExitoso";
    }

    @GetMapping("/professor-form")
    public String professorForm(Model model) {
        model.addAttribute("professorForm", new Professor());
        List<TypeOfIdentificationDocument> typesOfIdentificationDocument = (List<TypeOfIdentificationDocument>) identificationDocumentService.getTypesOfIdentificationDocument();
        model.addAttribute("typesOfIdentificationDocument", typesOfIdentificationDocument);
        return "professor-form";
    }

    @PostMapping("/professor-form")
    public String createProfessor(@Valid @ModelAttribute("professorForm") Professor professor, BindingResult result, ModelMap model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("professorForm", professor);
            List<TypeOfIdentificationDocument> typesOfIdentificationDocument = (List<TypeOfIdentificationDocument>) identificationDocumentService.getTypesOfIdentificationDocument();
            model.addAttribute("typesOfIdentificationDocument", typesOfIdentificationDocument);
        } else {
            try {
                Role professorRole = roleService.getRoleByName("professor");

                Set<Role> professorRoles = new HashSet<Role>();
                professorRoles.add(professorRole);

                professor.setRoles((Set<Role>) professorRoles);

                professor.setActive(true);
                userService.createUser(professor);
                model.addAttribute("professorForm", new Professor());
/*
                model.addAttribute("successfullRegistration", true);
*/

                redirectAttributes.addFlashAttribute("successfullRegistration", true);
                return "redirect:/admin/users-list";

            } catch (Exception e) {
                model.addAttribute("professorForm", professor);
                List<TypeOfIdentificationDocument> typesOfIdentificationDocument = (List<TypeOfIdentificationDocument>) identificationDocumentService.getTypesOfIdentificationDocument();
                model.addAttribute("typesOfIdentificationDocument", typesOfIdentificationDocument);
                model.addAttribute("errorMessage", e.getMessage());
            }
        }
        return "professor-form";
    }

    @GetMapping(value = "update-professor/{id}")
    public String getUpdateProfessorForm(Model model, @PathVariable(name = "id") Long id) throws Exception {
        Professor professorToUpdate = professorService.getProfessorById(id);
        model.addAttribute("professorForm", professorToUpdate);
        model.addAttribute("updateMode", true);
        List<TypeOfIdentificationDocument> typesOfIdentificationDocument = (List<TypeOfIdentificationDocument>) identificationDocumentService.getTypesOfIdentificationDocument();
        model.addAttribute("typesOfIdentificationDocument", typesOfIdentificationDocument);

        return "professor-form";
    }

    @PostMapping("/update-professor")
    public String updateProfessor(@Valid @ModelAttribute("professorForm") Professor professor, BindingResult result, ModelMap model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("professorForm", professor);
            model.addAttribute("updateMode", true);
        } else {
            try {
                Role professorRole = roleService.getRoleByName("professor");

                Set<Role> professorRoles = new HashSet<Role>();
                professorRoles.add(professorRole);

                //professor.setRoles((Set<Role>) professorRoles);
                professor.setActive(true);
                userService.updateUser(professor);
                model.addAttribute("professorForm", new Professor());

                redirectAttributes.addFlashAttribute("successfullUpdate", true);
                return "redirect:/";

            } catch (Exception e) {
                model.addAttribute("professorForm", professor);
                model.addAttribute("errorMessage", e.getMessage());
                model.addAttribute("updateMode", true);
            }
        }
        return "professor-form";
    }


    @PostMapping("/update-student")
    public String updateStudent(@Valid @ModelAttribute("studentForm") Student student, BindingResult result, ModelMap model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("studentForm", student);
            model.addAttribute("updateMode", true);
        } else {
            try {
                Role studentRole = roleService.getRoleByName("student");

                Set<Role> studentRoles = new HashSet<Role>();
                studentRoles.add(studentRole);

                //student.setRoles((Set<Role>) studentRoles);
                userService.updateUser(student);
                model.addAttribute("studentForm", new Student());

                redirectAttributes.addFlashAttribute("successfullUpdate", true);
                return "redirect:/";

            } catch (Exception e) {
                model.addAttribute("studentForm", student);
                List<TypeOfIdentificationDocument> typesOfIdentificationDocument = (List<TypeOfIdentificationDocument>) identificationDocumentService.getTypesOfIdentificationDocument();
                model.addAttribute("typesOfIdentificationDocument", typesOfIdentificationDocument);
                model.addAttribute("errorMessage", e.getMessage());
                model.addAttribute("updateMode", true);
            }
        }
        return "student-form";
    }

    @GetMapping(value = "update-student/{id}")
    public String getUpdateStudentForm(Model model, @PathVariable(name = "id") Long id) throws Exception {
        Student studentToUpdate = studentService.getStudentById(id);
        model.addAttribute("studentForm", studentToUpdate);
        model.addAttribute("updateMode", true);
        List<TypeOfIdentificationDocument> typesOfIdentificationDocument = (List<TypeOfIdentificationDocument>) identificationDocumentService.getTypesOfIdentificationDocument();
        model.addAttribute("typesOfIdentificationDocument", typesOfIdentificationDocument);

        return "student-form";
    }

    @GetMapping("/delete-student/{id}")
    public String deleteStudent(Model model, @PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {

        try {
            studentService.deleteStudent(id);
            redirectAttributes.addFlashAttribute("successfullDelete", true);

        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/admin/users-list";

    }

    @GetMapping("/delete-professor/{id}")
    public String deleteProfessor(Model model, @PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {

        try {
            professorService.deleteProfessor(id);
            redirectAttributes.addFlashAttribute("successfullDelete", true);

        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/admin/users-list";

    }


    @GetMapping("/users-list")
    public String usersList(Model model) {

        ArrayList<Student> students = (ArrayList<Student>) studentService.getStudents();
        ArrayList<Professor> professors = (ArrayList<Professor>) professorService.getProfessors();

        model.addAttribute("studentsList", studentService.getStudents());
        model.addAttribute("professorsList", professorService.getProfessors());

        model.addAttribute("professors", professors);
        model.addAttribute("students", students);
        return "users-list";
    }

    @GetMapping(value = "update-professor/cancel")
    public String cancelUpdateUser() {
        return "redirect:/admin/users-list";
    }

    @GetMapping(value = "/subjects/create")
    public String getSubjectForm(Model model) {
        model.addAttribute("subjectForm", new Subject());
        List<Professor> professors = (List<Professor>) professorService.getProfessors();
        model.addAttribute("professors", professors);
        return "subject-form";
    }

    @PostMapping(value = "/subjects/create")
    public String createSubject(@Valid @ModelAttribute("subjectForm") Subject subject, ModelMap model, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("subjectForm", subject);
            List<Professor> professors = (List<Professor>) professorService.getProfessors();
            model.addAttribute("professors", professors);
        } else {
            try {

                subjectService.createSubject(subject);
                model.addAttribute("subjectForm", new Subject());

                redirectAttributes.addFlashAttribute("successfullRegistration", true);
                return "redirect:/admin/subjects";

            } catch (Exception e) {
                model.addAttribute("subjectForm", subject);
                List<Professor> professors = (List<Professor>) professorService.getProfessors();
                model.addAttribute("professors", professors);
                model.addAttribute("errorMessage", e.getMessage());
            }
        }
        return "professor-form";
    }

    @GetMapping(value = "/subjects")
    public String subjectsList(Model model) {
        List<Subject> subjects = (List<Subject>) subjectService.getSubjects();
        model.addAttribute("subjects", subjects);
        return "subjects-list";
    }

}
