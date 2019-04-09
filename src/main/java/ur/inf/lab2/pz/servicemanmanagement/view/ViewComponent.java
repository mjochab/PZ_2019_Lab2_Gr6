package ur.inf.lab2.pz.servicemanmanagement.view;

public enum ViewComponent {
    LOGIN("component/login"),
    DASHBOARD("component/manager/dashboard"),
    LOADING("component/loading"),
    TIMETABLE("component/timetable"),
    EMPLOYEES("component/manager/employees"),
    MANAGER_DATA("component/manager/user-data"),
    MANAGER_REGISTER("component/manager/register"),
    SERVICEMAN_DATA("component/serviceman/user-data"),
    SERVICEMAN_REGISTER("component/serviceman/serviceman-first-login");


    private String fxmlPath;
    private ViewComponent(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    public String getFxmlPath() {
        return fxmlPath;
    }
}
