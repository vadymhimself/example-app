package com.example.domain;

import com.example.MainActivity;
import com.example.model.analytics.Analytics;
import com.example.model.api.Api;
import com.example.model.event.Bus;
import com.example.model.service.ModelComponent;
import dagger.Component;

@AppScope
@Component(dependencies = {ModelComponent.class})
public interface AppComponent {
    // injected
    void inject(App app);

    void inject(MainActivity activity);

    Analytics analytics();

    Api api();

    Bus bus();

}
