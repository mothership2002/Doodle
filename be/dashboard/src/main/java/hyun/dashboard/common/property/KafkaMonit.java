package hyun.dashboard.common.property;

import hyun.dashboard.member.infrastructure.strategy.MemberDatabaseStrategy;
import hyun.dashboard.member.infrastructure.strategy.MemberKafkaStrategy;
import hyun.dashboard.member.infrastructure.strategy.MemberStrategy;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class KafkaMonit {

    private final MemberKafkaStrategy memberKafkaStrategy;
    private final MemberDatabaseStrategy memberDatabaseStrategy;

    @Getter
    private KafkaStatus status = KafkaStatus.AVAILABLE;

    public void failureKafka() {
        this.status = KafkaStatus.UNAVAILABLE;
    }

    public void restoreKafka() {
        this.status = KafkaStatus.AVAILABLE;
    }

    public boolean isUseAble() {
        return status == KafkaStatus.AVAILABLE;
    }

    public enum KafkaStatus {
        AVAILABLE,
        UNAVAILABLE
    }

    public MemberStrategy getMemberStrategy() {
        return isUseAble() ? memberKafkaStrategy : memberDatabaseStrategy;
    }
}
