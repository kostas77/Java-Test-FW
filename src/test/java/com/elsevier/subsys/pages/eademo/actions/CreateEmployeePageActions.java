package com.elsevier.subsys.pages.eademo.actions;

import com.elsevier.subsys.framework.base.DriverContext;
import com.elsevier.subsys.pages.eademo.locators.CreateEmployeePageLocators;


public class CreateEmployeePageActions extends CreateEmployeePageLocators {

    public void ClickCreateButton() {
        btnCreateEmployee.submit();
    }

    public void CreateEmployee(String name, String salary, String durationworked, String grade, String email) {
        DriverContext.WaitUntilElementIsVisible(txtEmail);
        txtName.sendKeys(name);
        txtSalary.sendKeys(salary);
        txtDurationWorked.sendKeys(durationworked);
        txtGrade.sendKeys(grade);
        txtEmail.sendKeys(email);
    }
}
