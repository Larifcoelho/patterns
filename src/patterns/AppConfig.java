package patterns;

public class AppConfig {
    private static AppConfig instance;

    private String appName = "Projeto Design Patterns";

    private AppConfig() {}

    public static synchronized AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public String getAppName() {
        return appName;
    }
}
