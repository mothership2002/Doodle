package com.example.webflux.common.module;

import com.example.webflux.common.model.entity.Domain;
import org.springframework.stereotype.Component;
import reactor.util.context.Context;
import reactor.util.context.ContextView;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Deprecated
public class SessionContext {
    // TODO check 동시성문제?
    private final Map<ContextView, Domain> domainMap = new HashMap<>();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public Context setDomain(Context context, Domain domain) {
        domainMap.put(context, domain);
        return context;
    }

    public Domain getDomain(ContextView context) {
        Domain domain = domainMap.get(context);
        executorService.submit(() -> domainMap.remove(context));
        return domain;
    }
}
