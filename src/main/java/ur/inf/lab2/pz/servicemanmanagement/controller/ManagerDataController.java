package ur.inf.lab2.pz.servicemanmanagement.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ur.inf.lab2.pz.servicemanmanagement.domain.SecurityContext;
import ur.inf.lab2.pz.servicemanmanagement.domain.dto.ManagerDataDTO;
import ur.inf.lab2.pz.servicemanmanagement.domain.validator.ManagerDataValidator;
import ur.inf.lab2.pz.servicemanmanagement.service.DialogService;
import ur.inf.lab2.pz.servicemanmanagement.services.ManagerDataService;
import ur.inf.lab2.pz.servicemanmanagement.services.PanelLayoutService;
import ur.inf.lab2.pz.servicemanmanagement.view.ViewManager;
/**
 * Klasa kontrolera panelu zmiany danych personalnych menadżera
 */
@Controller
public class ManagerDataController {

    @Autowired
    private ManagerDataService managerDataService;

    @Autowired
    private DialogService dialogService;

    @FXML
    private JFXPasswordField passwordField, confirmPassField;

    @FXML
    private JFXTextField nameField, lastNameField, companyNameField;

    @FXML
    private Text nameAlert, lastNameAlert, companyNameAlert, passwordAlert, confirmPassAlert;

    @FXML
    private StackPane stackPane;

    @Autowired
    private PanelLayoutService panelLayoutService;

    private ViewManager viewManager;

    //    public void submitData(ActionEvent event) throws IOException {
//        System.out.println("ManagerDataController - zatwierdzono\n" +
//                "formularz danych kierownika");
//        viewManager.show(ViewType.MAIN);
//    }

    /**
     * Inizjalizacja alertów i pól tekstowych
     */
    @FXML
    public void initialize() {
        nameAlert.setVisible(false);
        lastNameAlert.setVisible(false);
        companyNameAlert.setVisible(false);
        passwordAlert.setVisible(false);
        confirmPassAlert.setVisible(false);
        nameField.setText(SecurityContext.getLoggedUser().getFirstName());
        lastNameField.setText(SecurityContext.getLoggedUser().getLastName());
        companyNameField.setText(SecurityContext.getLoggedUser().getCompanyName());

    }
    /**
     * Metoda zmieniająca dane menadżera
     */
    @FXML
    public void submitManagerData() {
        if (validate()) {
            ManagerDataDTO dto = new ManagerDataDTO(nameField.getText(), lastNameField.getText(), companyNameField.getText(),
                    passwordField.getText(), confirmPassField.getText());
            managerDataService.submitManagerData(dto);
            panelLayoutService.changeName(nameField.getText(), lastNameField.getText());

            dialogService.loadDialog(stackPane, new Text("OK"), new Text("Zmieniono dane personalne"));
        }
    }

    @FXML
    public void newName(String firstName, String lastName, String companyName) {
        if (SecurityContext.getLoggedUser().role.getRole().equals("ROLE_MANAGER")) {
            nameField.setText(firstName);
            lastNameField.setText(lastName);
            companyNameField.setText(companyName);
        }
    }
    /**
     * Walidator formularza zmiany danych
     */
    private boolean validate() {
        ManagerDataValidator validator = new ManagerDataValidator(nameField.getText(),
                lastNameField.getText(),
                companyNameField.getText(),
                passwordField.getText(),
                confirmPassField.getText(),
                nameAlert,
                lastNameAlert,
                companyNameAlert,
                passwordAlert,
                confirmPassAlert);
        validator.validate();
        return validator.getValidator().isClean();
    }

    @Autowired
    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

}