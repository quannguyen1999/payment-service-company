import services.AdapterService;

import java.io.IOException;

public class MainClassApplication {

    public static void main(String[] args) throws IOException {
        AdapterService.initAllService();
        AdapterService.getInstanceMenuService().startMenu();
    }

}
