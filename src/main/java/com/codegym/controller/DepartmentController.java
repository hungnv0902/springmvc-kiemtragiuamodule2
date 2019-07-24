package com.codegym.controller;

import com.codegym.model.Department;
import com.codegym.service.DepartmentService;
import com.codegym.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/departments")
    public String listDepartments(Pageable pageable, Model model){
        Page<Department> departments = departmentService.findAll(pageable);
        model.addAttribute("departments", departments);
        return "/department/list";
    }

    @GetMapping("/create-department")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/department/create");
        modelAndView.addObject("department", new Department());
        return modelAndView;
    }

    @PostMapping("/create-department")
    public ModelAndView saveProvince(@ModelAttribute("department") Department department){
        departmentService.save(department);

        ModelAndView modelAndView = new ModelAndView("/department/create");
        modelAndView.addObject("department", new Department());
        modelAndView.addObject("message", "New category created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-department/{id}")
    public ModelAndView editFormDepartment(@PathVariable Long id) {
        Department department = departmentService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/department/edit");
        modelAndView.addObject("department",department);
        return modelAndView;

    }

    @PostMapping("/edit-department")
    public ModelAndView editDepartment(@ModelAttribute("department") Department department) {
        departmentService.save(department);
        ModelAndView modelAndView = new ModelAndView("/department/edit");
        modelAndView.addObject("message", "Department edit successfully");
        return modelAndView;
    }

    @GetMapping("/delete-department/{id}")
    public ModelAndView deleteFormDepartment(@PathVariable Long id) {
        Department department = departmentService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/department/delete");
        modelAndView.addObject("department",department);
        return modelAndView;

    }

    @PostMapping("/delete-department")
    public ModelAndView deleteDepartment(@ModelAttribute("department") Department department) {
        departmentService.remove(department.getId());
        ModelAndView modelAndView = new ModelAndView("/department/delete");
        modelAndView.addObject("message", "Department delete successfully");
        return modelAndView;
    }
}
