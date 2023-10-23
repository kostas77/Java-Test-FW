package com.elsevier.bts.regional.ecom.steps.regional_ecom;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.base.EmailAPI;
import com.elsevier.bts.regional.ecom.framework.config.FrameworkConfigurationService;
import com.elsevier.bts.regional.ecom.framework.utilities.CucumberUtil;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.HSHomePageActions;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.MyAccountActions;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.OrderSuccessPageActions;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.PaymentPageActions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;

import javax.mail.MessagingException;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import static com.elsevier.bts.regional.ecom.steps.regional_ecom.ProductPurchaseCommonSteps.userMail;

@Slf4j
public class emailValidationSteps extends EmailAPI {

    @Autowired
    private FrameworkConfigurationService frameworkConfigurationService;


    @Given("I verify the Order Confirmation mail {string}")
    public void i_verify_the_order_confirmation_mail(String countryName) throws NoSuchAlgorithmException, KeyManagementException, MessagingException, XPathExpressionException, ParserConfigurationException, IOException, SAXException {

        String userNameGmail=frameworkConfigurationService.getGMAIL_Username();
        String passwordGmail=frameworkConfigurationService.getGMAIL_Password();
        String orderNumber=OrderSuccessPageActions.orderNumber;
        DriverContext.driverSleep(50000); //wait for Fulfilment status to update
        handleCertificates();
        String emailContent = retrieveLinkFromEmails(userNameGmail, passwordGmail, orderNumber);
        verifyProductDetailsInOrderConfirmationMail(emailContent, countryName);
    }

    public static void verifyProductDetailsInOrderConfirmationMail(String lastRelevantEmailContent, String countryName) {

        Document doc = Jsoup.parse(lastRelevantEmailContent);
        Elements eleHeader, eleProdPrice, eleSubTotal, eleTax, eleGrandTotal;
        String headerText, subTotal, tax, grandTotal;
        String[] prodPrice;

        switch (countryName) {
            case "US" -> {
                //Header
                eleHeader = doc.getElementsByTag("h4");
                headerText = eleHeader.text();
                System.out.println("Header text is " + headerText);
                Assertions.assertEquals("Thank you for your order " + OrderSuccessPageActions.orderNumber + " from US Elsevier Health!", headerText, "Header is incorrect in Mail");

                //SubTotal
                eleSubTotal = doc.select("tr.subtotal span.price");
                subTotal = eleSubTotal.text();
                System.out.println("SubTotal text is " + subTotal);
                Assertions.assertEquals(subTotal, PaymentPageActions.orderSubTotal.replace("US", "").replace(",", ""), "Sub Total is incorrect in Mail");

                //Tax
                eleTax = doc.select("tr.totals-tax span.price");
                tax = eleTax.text();
                System.out.println("Tax text is " + tax);
                Assertions.assertEquals(tax, PaymentPageActions.orderTax.replace(",", ""), "Tax is incorrect in Mail");

                //Grand Total
                eleGrandTotal = doc.select("tr.grand_total span.price");
                grandTotal = eleGrandTotal.text();
                System.out.println("Grand Total text is " + grandTotal);
                Assertions.assertEquals(grandTotal, PaymentPageActions.orderTotal.replace("US", "").replace(",", ""), "Grand Total is incorrect in Mail");
            }
            case "UK" -> {
                //Header
                eleHeader = doc.getElementsByTag("h4");
                headerText = eleHeader.text();
                System.out.println("Header text is " + headerText);
                Assertions.assertEquals("Thank you for your order from Elsevier!", headerText, "Header is incorrect in Mail");

                //SubTotal
//                eleSubTotal = doc.select("tr.subtotal span.price");
//                subTotal = eleSubTotal.text();
//                System.out.println("SubTotal text is " + subTotal);
//                Assertions.assertEquals(subTotal, PaymentPageActions.orderSubTotal.replace(" ", ""), "Sub Total is incorrect in Mail");

                //Grand Total
                eleGrandTotal = doc.select("tr.grand_total span.price");
                grandTotal = eleGrandTotal.text();
                System.out.println("Grand Total text is " + grandTotal);
                Assertions.assertEquals(grandTotal, PaymentPageActions.orderTotal.replace(" ", ""), "Grand Total is incorrect in Mail");
            }
            case "EU" -> {
                //Header
                eleHeader = doc.getElementsByTag("h4");
                headerText = eleHeader.text();
                System.out.println("Header text is " + headerText);
                Assertions.assertEquals("Thank you for your order " + OrderSuccessPageActions.orderNumber + " from Elsevier EU!", headerText, "Header is incorrect in Mail");

                //Subject
                System.out.println("Subject of the Mail is : " + subjectMail);
                Assertions.assertEquals("Elsevier Order Confirmation. Order # " + OrderSuccessPageActions.orderNumber, subjectMail, "Subject of the mail is incorrect");

                //SubTotal
                eleSubTotal = doc.select("tr.subtotal span.price");
                subTotal = eleSubTotal.text();
                System.out.println("SubTotal text is " + subTotal);
                Assertions.assertEquals(subTotal, PaymentPageActions.orderSubTotal.replace(",", ".").replace(" ", ""), "Sub Total is incorrect in Mail");

                //Tax - need to add condition for journal and ebook
                eleTax = doc.select("tr.totals-tax span.price");
                tax = eleTax.text();
                System.out.println("Tax text is " + tax);
                Assertions.assertEquals(tax, PaymentPageActions.orderTax.replace(",", ".").replace(" ", ""), "Tax is incorrect in Mail");

                //Grand Total
                eleGrandTotal = doc.select("tr.grand_total span.price");
                grandTotal = eleGrandTotal.text();
                System.out.println("Grand Total text is " + grandTotal);
                Assertions.assertEquals(grandTotal, PaymentPageActions.orderTotal.replace(",", ".").replace(" ", ""), "Grand Total is incorrect in Mail");
            }
            case "FR" -> {
                //Header
                eleHeader = doc.getElementsByTag("h4");
                headerText = eleHeader.text();
                System.out.println("Header text is " + headerText);
                Assertions.assertEquals("Merci pour votre commande n° " + OrderSuccessPageActions.orderNumber + " placée sur Elsevier Masson!", headerText, "Header is incorrect in Mail");

                //Subject
                System.out.println("Subject of the Mail is : " + subjectMail);
                Assertions.assertEquals("Elsevier Masson Confirmation de commande n° #" + OrderSuccessPageActions.orderNumber, subjectMail, "Subject of the mail is incorrect");

                //SubTotal
//                eleSubTotal = doc.select("tr.subtotal span.price");
//                subTotal = eleSubTotal.text();
//                System.out.println("SubTotal text is " + subTotal);
//                Assertions.assertEquals(subTotal, PaymentPageActions.orderTotal, "Sub Total is incorrect in Mail");

                //Grand Total
                eleGrandTotal = doc.select("tr.grand_total span.price");
                grandTotal = eleGrandTotal.text();
                System.out.println("Grand Total text is " + grandTotal);
                Assertions.assertEquals(grandTotal, PaymentPageActions.orderTotal, "Grand Total is incorrect in Mail");
            }
            case "MEA" -> {
                //Header
                eleHeader = doc.getElementsByTag("h4");
                headerText = eleHeader.text();
                System.out.println("Header text is " + headerText);
                Assertions.assertEquals("Thank you for your order from Elsevier!", headerText, "Header is incorrect in Mail");

                //Subject
                System.out.println("Subject of the Mail is : " + subjectMail);
                Assertions.assertEquals("Elsevier Order Confirmation. Order # " + OrderSuccessPageActions.orderNumber, subjectMail, "Subject of the mail is incorrect");

                //SubTotal
                eleSubTotal = doc.select("tr.subtotal span.price");
                subTotal = eleSubTotal.text();
                System.out.println("SubTotal text is " + subTotal);
                Assertions.assertEquals(subTotal, PaymentPageActions.orderTotal, "Sub Total is incorrect in Mail");

                //Grand Total
                eleGrandTotal = doc.select("tr.grand_total span.price");
                grandTotal = eleGrandTotal.text();
                System.out.println("Grand Total text is " + grandTotal);
                Assertions.assertEquals(grandTotal, PaymentPageActions.orderTotal, "Grand Total is incorrect in Mail");
            }
            case "SP" -> {
                eleHeader = doc.getElementsByTag("h4");
                headerText = eleHeader.text();
                System.out.println("Header text is " + headerText);
                Assertions.assertEquals("Gracias por su compra en nuestra tienda en línea", headerText, "Header is incorrect in Mail");

                //SubTotal
                eleSubTotal = doc.select("tr.subtotal span.price");
                subTotal = eleSubTotal.text();
                System.out.println("SubTotal text is " + subTotal);
                Assertions.assertEquals(subTotal, PaymentPageActions.orderSubTotal, "Sub Total is incorrect in Mail");

                //Tax
                eleTax = doc.select("tr.totals-tax span.price");
                tax = eleTax.text();
                System.out.println("Tax text is " + tax);
                Assertions.assertEquals(tax, PaymentPageActions.orderTax, "Tax is incorrect in Mail");

                //Grand Total
                eleGrandTotal = doc.select("tr.grand_total span.price");
                grandTotal = eleGrandTotal.text();
                System.out.println("Grand Total text is " + grandTotal);
                Assertions.assertEquals(grandTotal, PaymentPageActions.orderTotal, "Grand Total is incorrect in Mail");
            }
            case "LATAM" -> {
                eleHeader = doc.getElementsByTag("h4");
                headerText = eleHeader.text();
                System.out.println("Header text is " + headerText);
                Assertions.assertEquals("Gracias por su pedido de un eBook VitalSource en la Tienda Online de Elsevier Latinoamérica Como descargar el eBook", headerText, "Header is incorrect in Mail");

                //Subject
                System.out.println("Subject of the Mail is : " + subjectMail);
                Assertions.assertEquals("eBook VitalSource – Pedido # " + OrderSuccessPageActions.orderNumber, subjectMail, "Subject of the mail is incorrect");

//                //SubTotal
//                eleSubTotal = doc.select("tr.subtotal span.price");
//                subTotal = eleSubTotal.text();
//                System.out.println("SubTotal text is " + subTotal);
//                Assertions.assertEquals(subTotal, PaymentPageActions.orderTotal, "Sub Total is incorrect in Mail");

                //Grand Total
                // eleGrandTotal = doc.select("tr.grand_total span.price");
                // grandTotal = eleGrandTotal.text();
                // System.out.println("Grand Total text is " + grandTotal);
                // Assertions.assertEquals(grandTotal, PaymentPageActions.orderTotal, "Grand Total is incorrect in Mail");
            }
            case "DE" -> {
                eleHeader = doc.getElementsByTag("h4");
                headerText = eleHeader.text();
                System.out.println("Header text is " + headerText);
                Assertions.assertEquals("Vielen Dank für Ihre Bestellung " + OrderSuccessPageActions.orderNumber + " bei DE Elsevier Health!", headerText, "Header is incorrect in Mail");

                //Subject
                System.out.println("Subject of the Mail is : " + subjectMail);
                Assertions.assertEquals("DE Elsevier Health Bestellung #" + OrderSuccessPageActions.orderNumber, subjectMail, "Subject of the mail is incorrect");

                //SubTotal
                eleSubTotal = doc.select("tr.subtotal span.price");
                subTotal = eleSubTotal.text();
                System.out.println("SubTotal text is " + subTotal);
                Assertions.assertEquals(subTotal, PaymentPageActions.orderTotal, "Sub Total is incorrect in Mail");

                //Grand Total
                eleGrandTotal = doc.select("tr.grand_total span.price");
                grandTotal = eleGrandTotal.text();
                System.out.println("Grand Total text is " + grandTotal);
                Assertions.assertEquals(grandTotal, PaymentPageActions.orderTotal, "Grand Total is incorrect in Mail");
            }
            case "ANZ" -> {
                //Header
                eleHeader = doc.getElementsByTag("h4");
                headerText = eleHeader.text();
                System.out.println("Header text is " + headerText);
                Assertions.assertEquals("Thank you for your order " + OrderSuccessPageActions.orderNumber + " from Elsevier Australia Bookstore!", headerText, "Header is incorrect in Mail");

                //Subject
                System.out.println("Subject of the Mail is : " + subjectMail);
                Assertions.assertEquals("Elsevier Australia Bookstore Order #" + OrderSuccessPageActions.orderNumber, subjectMail, "Subject of the mail is incorrect");

                //SubTotal
                eleSubTotal = doc.select("tr.subtotal span.price");
                subTotal = eleSubTotal.text();
                System.out.println("SubTotal text is " + subTotal);
                Assertions.assertEquals(subTotal, PaymentPageActions.orderSubTotal.replace(",", ".").replace(" ", ""), "Sub Total is incorrect in Mail");

                //Tax - need to add condition for journal and ebook
                eleTax = doc.select("tr.totals-tax span.price");
                tax = eleTax.text();
                System.out.println("Tax text is " + tax);
                Assertions.assertEquals(tax, PaymentPageActions.orderTax.replace(",", ".").replace(" ", ""), "Tax is incorrect in Mail");

                //Grand Total
                eleGrandTotal = doc.select("tr.grand_total span.price");
                grandTotal = eleGrandTotal.text();
                System.out.println("Grand Total text is " + grandTotal);
                Assertions.assertEquals(grandTotal, PaymentPageActions.orderTotal.replace(",", ".").replace(" ", ""), "Grand Total is incorrect in Mail");
            }
            case "ASIA" -> {
                //Header
                eleHeader = doc.getElementsByTag("h4");
                headerText = eleHeader.text();
                System.out.println("Header text is " + headerText);
                Assertions.assertEquals("Thank you for your order " + OrderSuccessPageActions.orderNumber + " from Elsevier Asia Bookstore!", headerText, "Header is incorrect in Mail");

                //Subject
                System.out.println("Subject of the Mail is : " + subjectMail);
                Assertions.assertEquals("Elsevier Asia Bookstore Order #" + OrderSuccessPageActions.orderNumber, subjectMail, "Subject of the mail is incorrect");

                //SubTotal
                eleSubTotal = doc.select("tr.subtotal span.price");
                subTotal = eleSubTotal.text();
                System.out.println("SubTotal text is " + subTotal);
                Assertions.assertEquals(subTotal, PaymentPageActions.orderSubTotal.replace(",", ".").replace(" ", ""), "Sub Total is incorrect in Mail");

                //Grand Total
                eleGrandTotal = doc.select("tr.grand_total span.price");
                grandTotal = eleGrandTotal.text();
                System.out.println("Grand Total text is " + grandTotal);
                Assertions.assertEquals(grandTotal, PaymentPageActions.orderTotal.replace(",", ".").replace(" ", ""), "Grand Total is incorrect in Mail");
            }
        }

    }

    @Given("I verify the Account Creation mail {string}")
    public void i_verify_the_account_creation_mail(String countryName) throws NoSuchAlgorithmException, KeyManagementException, MessagingException, XPathExpressionException, ParserConfigurationException, IOException, SAXException {
        String emailContent;
        String userNameGmail=frameworkConfigurationService.getGMAIL_Username();
        String passwordGmail=frameworkConfigurationService.getGMAIL_Password();
        DriverContext.driverSleep(50000); //wait for Fulfilment status to update
        handleCertificates();
        if(countryName.equals("FR")){
            emailContent = retrieveLinkFromEmails(userNameGmail, passwordGmail, "Bienvenue");
        }else if(countryName.equals("SP")){
            emailContent = retrieveLinkFromEmails(userNameGmail, passwordGmail, "Bienvenido");
        }else if(countryName.equals("DE")){
            emailContent = retrieveLinkFromEmails(userNameGmail, passwordGmail, "Herzlich");
        }else{
            emailContent = retrieveLinkFromEmails(userNameGmail, passwordGmail, "Elsevier");
        }
        verifyAccountCreationMail(emailContent, countryName);
    }

    public static void verifyAccountCreationMail(String lastRelevantEmailContent, String countryName) {

        Document doc = Jsoup.parse(lastRelevantEmailContent);
        Elements eleHeader, eleEmail, eleVisitStore, eleThankYou;
        String headerText, email, visitStore, thankYou;

        switch (countryName) {
            case "US" -> {
                //Header
                eleHeader = doc.getElementsByTag("h4");
                headerText = eleHeader.text();
                System.out.println("Header text is : " + headerText);
                Assertions.assertEquals("Welcome to Elsevier!", headerText, "Header is incorrect in Mail");

                //Subject
                System.out.println("Subject of the Mail is : " + subjectMail);
                Assertions.assertEquals("Thank You for Creating Your Elsevier Health Account", subjectMail, "Subject of the mail is incorrect");

                //Email Account
                eleEmail = doc.select("p > a > font");
                email = eleEmail.text();
                System.out.println("Email ID is : " + email);
                Assertions.assertEquals(userMail, email, "Email ID is incorrect in Mail");

                //Visit the Store Button
                eleVisitStore = doc.select("table[class*=button] a");
                visitStore = eleVisitStore.text();
                System.out.println("Visit the Store Button is displayed : " + visitStore);
                Assertions.assertEquals("Visit the Store ", visitStore, "Visit the Store Button is not displayed in the Mail");

                //Thank you again, US Elsevier Health Footer
                eleThankYou = doc.select("p:contains(Thank you again)");
                thankYou = eleThankYou.text();
                System.out.println("Thank Your Text at the Footer : " + thankYou);
                Assertions.assertEquals("Thank you again, US Elsevier Health", thankYou, "Thank you text at the footer is not displayed in the Mail");
            }
            case "UK" -> {
                //Header
                eleHeader = doc.getElementsByTag("h4");
                headerText = eleHeader.text();
                System.out.println("Header text is : " + headerText);
                Assertions.assertEquals("Welcome to Elsevier!", headerText, "Header is incorrect in Mail");

                //Subject
                System.out.println("Subject of the Mail is : " + subjectMail);
                Assertions.assertEquals("Thank You for Creating Your Elsevier Health Account", subjectMail, "Subject of the mail is incorrect");

                //Email Account
                eleEmail = doc.select("p > a > font");
                email = eleEmail.text();
                System.out.println("Email ID is : " + email);
                Assertions.assertEquals(userMail, email, "Email ID is incorrect in Mail");

                //Visit the Store Button
                eleVisitStore = doc.select("table[class*=button] a");
                visitStore = eleVisitStore.text();
                System.out.println("Visit the Store Button is displayed : " + visitStore);
                Assertions.assertEquals("Visit the Store ", visitStore, "Visit the Store Button is not displayed in the Mail");

                //Thank you again, US Elsevier Health Footer
                eleThankYou = doc.select("p:contains(Thank you again)");
                thankYou = eleThankYou.text();
                System.out.println("Thank Your Text at the Footer : " + thankYou);
                Assertions.assertEquals("Thank you again, Elsevier UK", thankYou, "Thank you text at the footer is not displayed in the Mail");
            }
            case "EU" -> {
                //Header
                eleHeader = doc.getElementsByTag("h4");
                headerText = eleHeader.text();
                System.out.println("Header text is : " + headerText);
                Assertions.assertEquals("Welcome to Elsevier!", headerText, "Header is incorrect in Mail");

                //Subject
                System.out.println("Subject of the Mail is : " + subjectMail);
                Assertions.assertEquals("Thank You for Creating Your Elsevier Health Account", subjectMail, "Subject of the mail is incorrect");

                //Email Account
                eleEmail = doc.select("p > a > font");
                email = eleEmail.text();
                System.out.println("Email ID is : " + email);
                Assertions.assertEquals(userMail, email, "Email ID is incorrect in Mail");

                //Visit the Store Button
                eleVisitStore = doc.select("table[class*=button] a");
                visitStore = eleVisitStore.text();
                System.out.println("Visit the Store Button is displayed : " + visitStore);
                Assertions.assertEquals("Visit the Store ", visitStore, "Visit the Store Button is not displayed in the Mail");

                //Thank you again, US Elsevier Health Footer
                eleThankYou = doc.select("p:contains(Thank you again)");
                thankYou = eleThankYou.text();
                System.out.println("Thank Your Text at the Footer : " + thankYou);
                Assertions.assertEquals("Thank you again, Elsevier EU", thankYou, "Thank you text at the footer is not displayed in the Mail");
            }
            case "FR" -> {
                //Header
                eleHeader = doc.getElementsByTag("h4");
                headerText = eleHeader.text();
                System.out.println("Header text is : " + headerText);
                Assertions.assertEquals("Bienvenue sur le site Elsevier !", headerText, "Header is incorrect in Mail");

                //Subject
                System.out.println("Subject of the Mail is : " + subjectMail);
                Assertions.assertEquals("Bienvenue sur le site,FR Automation Regional HealthStore", subjectMail, "Subject of the mail is incorrect");

                //Email Account
                eleEmail = doc.select("p > a > font");
                email = eleEmail.text();
                System.out.println("Email ID is : " + email);
                Assertions.assertEquals(userMail, email, "Email ID is incorrect in Mail");

                //Visit the Store Button
                eleVisitStore = doc.select("table[class*=button] a");
                visitStore = eleVisitStore.text();
                System.out.println("Visit the Store Button is displayed : " + visitStore);
                Assertions.assertEquals("Accéder au site ", visitStore, "Visit the Store Button is not displayed in the Mail");

                //Thank you again, US Elsevier Health Footer
                eleThankYou = doc.select("p:contains(L’équipe)");
                thankYou = eleThankYou.text();
                System.out.println("Thank Your Text at the Footer : " + thankYou);
                Assertions.assertEquals("L’équipe d’Elsevier Masson", thankYou, "Thank you text at the footer is not displayed in the Mail");

            }
            case "MEA" -> {
                //Header
                eleHeader = doc.getElementsByTag("h4");
                headerText = eleHeader.text();
                System.out.println("Header text is : " + headerText);
                Assertions.assertEquals("Welcome to Elsevier!", headerText, "Header is incorrect in Mail");

                //Subject
                System.out.println("Subject of the Mail is : " + subjectMail);
                Assertions.assertEquals("Thank You for Creating Your Elsevier Health Account", subjectMail, "Subject of the mail is incorrect");

                //Email Account
                eleEmail = doc.select("p > a > font");
                email = eleEmail.text();
                System.out.println("Email ID is : " + email);
                Assertions.assertEquals(userMail, email, "Email ID is incorrect in Mail");

                //Visit the Store Button
                eleVisitStore = doc.select("table[class*=button] a");
                visitStore = eleVisitStore.text();
                System.out.println("Visit the Store Button is displayed : " + visitStore);
                Assertions.assertEquals("Visit the Store ", visitStore, "Visit the Store Button is not displayed in the Mail");

                //Thank you again, US Elsevier Health Footer
                eleThankYou = doc.select("p:contains(Thank you again)");
                thankYou = eleThankYou.text();
                System.out.println("Thank Your Text at the Footer : " + thankYou);
                Assertions.assertEquals("Thank you again, Elsevier MEA", thankYou, "Thank you text at the footer is not displayed in the Mail");
            }
            case "SP" -> {
                //Header
                eleHeader = doc.getElementsByTag("h4");
                headerText = eleHeader.text();
                System.out.println("Header text is : " + headerText);
                Assertions.assertEquals("Bienvenido/a a Elsevier España", headerText, "Header is incorrect in Mail");

                //Subject
                System.out.println("Subject of the Mail is : " + subjectMail);
                Assertions.assertEquals("Bienvenido/a, SP Automation Regional HealthStore!", subjectMail, "Subject of the mail is incorrect");

                //Email Account
                eleEmail = doc.select("span > a > font");
                email = eleEmail.text();
                System.out.println("Email ID is : " + email);
                Assertions.assertEquals(userMail, email, "Email ID is incorrect in Mail");

                //Visit the Store Button
                eleVisitStore = doc.select("table[class*=button] a");
                visitStore = eleVisitStore.text();
                System.out.println("Visit the Store Button is displayed : " + visitStore);
                Assertions.assertEquals("Visite nuestra web ", visitStore, "Visit the Store Button is not displayed in the Mail");

                //Thank you again, US Elsevier Health Footer
                eleThankYou = doc.select("p:contains(Elsevier España)");
                thankYou = eleThankYou.text();
                System.out.println("Thank Your Text at the Footer : " + thankYou);
                Assertions.assertTrue(thankYou.contains("Elsevier España"), "Thank you text at the footer is not displayed in the Mail");
            }
            case "DE" -> {
                //Header
                eleHeader = doc.getElementsByTag("h4");
                headerText = eleHeader.text();
                System.out.println("Header text is : " + headerText);
                Assertions.assertEquals("Vielen Dank für Ihre Registrierung im Elsevier Webshop!", headerText, "Header is incorrect in Mail");

                //Subject
                System.out.println("Subject of the Mail is : " + subjectMail);
                Assertions.assertEquals("Herzlich Willkommen,  DE Automation Regional HealthStore,", subjectMail, "Subject of the mail is incorrect");

                //Email Account
                eleEmail = doc.select("p > a > font");
                email = eleEmail.text();
                System.out.println("Email ID is : " + email);
                Assertions.assertEquals(userMail, email, "Email ID is incorrect in Mail");

                //Visit the Store Button
                eleVisitStore = doc.select("table[class*=button] a");
                visitStore = eleVisitStore.text();
                System.out.println("Visit the Store Button is displayed : " + visitStore);
                Assertions.assertEquals("Zum Elsevier Webshop ", visitStore, "Visit the Store Button is not displayed in the Mail");

                //Thank you again, US Elsevier Health Footer
                eleThankYou = doc.select("p:contains(Ihr Elsevier)");
                thankYou = eleThankYou.text();
                System.out.println("Thank Your Text at the Footer : " + thankYou);
                Assertions.assertEquals("Ihr Elsevier Team", thankYou, "Thank you text at the footer is not displayed in the Mail");
            }
            case "ANZ" -> {
                //Header
                eleHeader = doc.getElementsByTag("h4");
                headerText = eleHeader.text();
                System.out.println("Header text is : " + headerText);
                Assertions.assertEquals("Welcome to Elsevier!", headerText, "Header is incorrect in Mail");

                //Subject
                System.out.println("Subject of the Mail is : " + subjectMail);
                Assertions.assertEquals("Thank You for Creating Your Elsevier Australia Bookstore", subjectMail, "Subject of the mail is incorrect");

                //Email Account
                eleEmail = doc.select("p > a > font");
                email = eleEmail.text();
                System.out.println("Email ID is : " + email);
                Assertions.assertEquals(userMail, email, "Email ID is incorrect in Mail");

                //Visit the Store Button
                eleVisitStore = doc.select("table[class*=button] a");
                visitStore = eleVisitStore.text();
                System.out.println("Visit the Store Button is displayed : " + visitStore);
                Assertions.assertEquals("Visit the Store ", visitStore, "Visit the Store Button is not displayed in the Mail");

                //Thank you again, US Elsevier Health Footer
                eleThankYou = doc.select("p:contains(Thank you again)");
                thankYou = eleThankYou.text();
                System.out.println("Thank Your Text at the Footer : " + thankYou);
                Assertions.assertEquals("Thank you again, Elsevier Australia Bookstore", thankYou, "Thank you text at the footer is not displayed in the Mail");
            }
            case "ASIA" -> {
                //Header
                eleHeader = doc.getElementsByTag("h4");
                headerText = eleHeader.text();
                System.out.println("Header text is : " + headerText);
                Assertions.assertEquals("Welcome to Elsevier!", headerText, "Header is incorrect in Mail");

                //Subject
                System.out.println("Subject of the Mail is : " + subjectMail);
                Assertions.assertEquals("Thank You for Creating Your Elsevier Asia Bookstore", subjectMail, "Subject of the mail is incorrect");

                //Email Account
                eleEmail = doc.select("p > a > font");
                email = eleEmail.text();
                System.out.println("Email ID is : " + email);
                Assertions.assertEquals(userMail, email, "Email ID is incorrect in Mail");

                //Visit the Store Button
                eleVisitStore = doc.select("table[class*=button] a");
                visitStore = eleVisitStore.text();
                System.out.println("Visit the Store Button is displayed : " + visitStore);
                Assertions.assertTrue(visitStore.contains("Visit the Store"), "Visit the Store Button is not displayed in the Mail");
                Assertions.assertTrue(visitStore.contains("CODE: SIGNUP10"), "Coupon Code SignUp10 is not displayed in the Mail");

                //Thank you again, US Elsevier Health Footer
                eleThankYou = doc.select("p:contains(Thank you again)");
                thankYou = eleThankYou.text();
                System.out.println("Thank Your Text at the Footer : " + thankYou);
                Assertions.assertEquals("Thank you again, Elsevier Asia Bookstore", thankYou, "Thank you text at the footer is not displayed in the Mail");
            }
            case "IN" -> {
                //Header
                eleHeader = doc.getElementsByTag("h4");
                headerText = eleHeader.text();
                System.out.println("Header text is : " + headerText);
                Assertions.assertEquals("Welcome to Elsevier!", headerText, "Header is incorrect in Mail");

                //Subject
                System.out.println("Subject of the Mail is : " + subjectMail);
                Assertions.assertEquals("Thank You for Creating Your Elsevier Health Account", subjectMail, "Subject of the mail is incorrect");

                //Email Account
                eleEmail = doc.select("p > a > font");
                email = eleEmail.text();
                System.out.println("Email ID is : " + email);
                Assertions.assertEquals(userMail, email, "Email ID is incorrect in Mail");

                //Visit the Store Button
                eleVisitStore = doc.select("table[class*=button] a");
                visitStore = eleVisitStore.text();
                System.out.println("Visit the Store Button is displayed : " + visitStore);
                Assertions.assertEquals("Visit the Store ", visitStore, "Visit the Store Button is not displayed in the Mail");

                //Thank you again, US Elsevier Health Footer
                eleThankYou = doc.select("p:contains(Thank you again)");
                thankYou = eleThankYou.text();
                System.out.println("Thank Your Text at the Footer : " + thankYou);
                //Assertions.assertEquals("Thank you again, Elsevier India Bookstore", thankYou, "Thank you text at the footer is not displayed in the Mail");
                Assertions.assertEquals("Thank you again, shop.elsevier.in", thankYou, "Thank you text at the footer is not displayed in the Mail");
            }
            case "LATAM" -> {

            }
        }

    }

    @Given("I verify the Order Confirmation mail for Clinical Key Products {string}")
    public void i_verify_the_order_confirmation_mail_for_clinical_key_products(String countryName) throws NoSuchAlgorithmException, KeyManagementException, MessagingException, XPathExpressionException, ParserConfigurationException, IOException, SAXException {

        String userNameGmail=frameworkConfigurationService.getGMAIL_Username();
        String passwordGmail=frameworkConfigurationService.getGMAIL_Password();
        String orderNumber=OrderSuccessPageActions.orderNumber;
        DriverContext.driverSleep(50000); //wait for Fulfilment status to update
        handleCertificates();
        String emailContent = retrieveLinkFromEmails(userNameGmail, passwordGmail, orderNumber);
        verifyClinicalKeyProductDetailsInOrderConfirmationMail(emailContent, countryName);
    }

    public static void verifyClinicalKeyProductDetailsInOrderConfirmationMail(String lastRelevantEmailContent, String countryName) {

        Document doc = Jsoup.parse(lastRelevantEmailContent);
        Elements eleHeader, eleProdPrice, eleSubTotal, eleTax, eleGrandTotal;
        String headerText, subTotal, tax, grandTotal;
        String[] prodPrice;

        switch (countryName) {
            case "US" -> {
                //Header
                eleHeader = doc.getElementsByTag("h4");
                headerText = eleHeader.text();
                System.out.println("Header text is " + headerText);
                Assertions.assertEquals("Thank you for your order " + OrderSuccessPageActions.orderNumber + " from US Elsevier Health!", headerText, "Header is incorrect in Mail");

                //Subject
                System.out.println("Subject of the Mail is : " + subjectMail);
                Assertions.assertEquals("US Elsevier Health Order #"+ OrderSuccessPageActions.orderNumber + " Confirmation for ClinicalKey Surgery", subjectMail, "Subject of the mail is incorrect");

                //SubTotal
                eleSubTotal = doc.select("tr.subtotal span.price");
                subTotal = eleSubTotal.text();
                System.out.println("SubTotal text is " + subTotal);
                Assertions.assertEquals(subTotal, PaymentPageActions.orderSubTotal.replace("US", "").replace(",", ""), "Sub Total is incorrect in Mail");

                //Tax
                eleTax = doc.select("tr.totals-tax span.price");
                tax = eleTax.text();
                System.out.println("Tax text is " + tax);
                Assertions.assertEquals(tax, PaymentPageActions.orderTax.replace(",", ""), "Tax is incorrect in Mail");

                //Grand Total
                eleGrandTotal = doc.select("tr.grand_total span.price");
                grandTotal = eleGrandTotal.text();
                System.out.println("Grand Total text is " + grandTotal);
                Assertions.assertEquals(grandTotal, PaymentPageActions.orderTotal.replace("US", "").replace(",", ""), "Grand Total is incorrect in Mail");
            }
        }

    }

}
