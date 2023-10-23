package com.elsevier.bts.regional.ecom.pages.eademo.actions;

import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.pages.eademo.locators.CreateEmployeePageLocators;


public class CreateEmployeePageActions extends CreateEmployeePageLocators {

    public void ClickCreateButton() {
        btnCreateEmployee.submit();
    }

    public void CreateEmployee(String name, String salary, String durationworked, String grade, String email) {
        DriverContext.waitUntilElementIsVisible(txtEmail);
        txtName.sendKeys(name);
        txtSalary.sendKeys(salary);
        txtDurationWorked.sendKeys(durationworked);
        txtGrade.sendKeys(grade);
        txtEmail.sendKeys(email);
    }

}
