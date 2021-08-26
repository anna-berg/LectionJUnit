import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryListener;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.core.LauncherConfig;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import java.io.PrintWriter;

public class TestLauncher {
    public static void main(String[] args) {
        Launcher launcher = LauncherFactory.create(LauncherConfig.DEFAULT);
        //можем пердать TestExecuterListener, либо передать его вторым параметром в  launcher.execute();
//        launcher.registerLauncherDiscoveryListeners();
        SummaryGeneratingListener summaryGeneratingListener = new SummaryGeneratingListener();
//        launcher.registerLauncherDiscoveryListeners((LauncherDiscoveryListener) summaryGeneratingListener);
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder      //LauncherDiscoveryRequest - interface
                .request()
                .selectors(DiscoverySelectors.selectClass(UserServiceTest.class)) //селекторы - где наш реквест будет искать тесты
//                .selectors(DiscoverySelectors.selectPackage("")) // можем указать не класс, а пакет
//                .listeners() // тут можем проинициализировать лисенеров
                .build();
        launcher.execute(request, summaryGeneratingListener);
        try(PrintWriter printWriter = new PrintWriter(System.out)) {
            summaryGeneratingListener.getSummary().printTo(printWriter);
        }

    }
}
