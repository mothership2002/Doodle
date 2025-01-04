package hyun.dashboard.publisher.common.module.custom;

import hyun.dashboard.publisher.common.Constant;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
@Getter
public class PackageScanner extends Module {

    private final List<Class<?>> domainClasses = new ArrayList<>();

    public PackageScanner() throws ClassNotFoundException {
        super.init();
    }


    @Override
    protected void construct() throws ClassNotFoundException {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Table.class));
        Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents(Constant.ROOT_PACKAGE);
        for (BeanDefinition entity : candidateComponents) {
            domainClasses.add(Class.forName(entity.getBeanClassName()));
        }
    }
}
