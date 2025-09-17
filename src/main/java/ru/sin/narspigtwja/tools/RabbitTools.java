package ru.sin.narspigtwja.tools;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import ru.sin.narspigtwja.body.QueryReq;
import ru.sin.narspigtwja.body.QueryRes;
import ru.sin.narspigtwja.config.RabbitConfig;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RabbitTools {
    private final RabbitTemplate rabbitTemplate;

    private final RabbitConfig rabbitConfig;

    private static final Logger logger = LoggerFactory.getLogger(RabbitTools.class);

    public List<String> sendAndReceiveQuery(UUID userUuid, String query) {
        QueryReq req = new QueryReq(

                userUuid,
                query
        );

        logger.info("[UUID: {}] Send query: {}", userUuid, query);

        try {
            QueryRes res = rabbitTemplate.convertSendAndReceiveAsType(
                    rabbitConfig.getGtwExc(),
                    rabbitConfig.getQueryReqRoutingKey(),
                    req,
                    new ParameterizedTypeReference<>() {
                    }
            );

            if (res == null) {
                logger.error("[UUID: {}] Query response is null", userUuid);

                throw new RuntimeException("Query response is null");
            }

            if (!userUuid.equals(res.uuid())) {
                logger.error("[UUID: {}] Query response does not match uuid: {}", userUuid, res.uuid());

                throw new RuntimeException("Query response does not match uuid");
            }

            if (res.docs() == null || res.docs().isEmpty()) {
                logger.error("[UUID: {}] Query response docs is empty", userUuid);

                throw new RuntimeException("Query response docs is empty");
            }

            return res.docs();
        }
        catch (Exception e) {
            logger.error("[UUID: {}] Query response exception: {}", userUuid, e.getMessage());

            throw new RuntimeException("Query response exception: " + e);
        }
    }
}
