package com.example.domain;

import com.example.MainActivity;
import dagger.Component;
import ru.sberleasing.model.analytics.Analytics;
import ru.sberleasing.model.service.ModelComponent;

@AppScope
@Component(dependencies = {ModelComponent.class})
public interface AppComponent {
    // injected
    void inject(App app);

    void inject(MainActivity activity);

    Analytics analytics();

}
