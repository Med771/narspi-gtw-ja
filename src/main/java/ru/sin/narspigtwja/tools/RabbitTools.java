package ru.sin.narspigtwja.tools;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import ru.sin.narspigtwja.body.HistoryReq;
import ru.sin.narspigtwja.body.HistoryRes;
import ru.sin.narspigtwja.body.QueryReq;
import ru.sin.narspigtwja.body.QueryRes;
import ru.sin.narspigtwja.config.RabbitConfig;
import ru.sin.narspigtwja.dto.ContextReq;
import ru.sin.narspigtwja.model.History;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RabbitTools {
    private final RabbitTemplate rabbitTemplate;

    private final RabbitConfig rabbitConfig;

    private static final Logger logger = LoggerFactory.getLogger(RabbitTools.class);

    public String sendAndReceiveQuery(ContextReq contextReq) {
        QueryReq req = new QueryReq(
                contextReq.uuid(),
                contextReq.query(),
                contextReq.history()
        );

        logger.info("[UUID: {}] Send query: {}", contextReq.uuid(), contextReq.query());

        try {
            QueryRes res = rabbitTemplate.convertSendAndReceiveAsType(
                    rabbitConfig.getGtwExc(),
                    rabbitConfig.getQueryReqRoutingKey(),
                    req,
                    new ParameterizedTypeReference<>() {
                    }
            );

            if (res == null) {
                throw new RuntimeException("Response is null");
            }

            if (!contextReq.uuid().equals(res.uuid())) {
                throw new RuntimeException("Response does not match uuid");
            }

            if (res.answer() == null) {
                throw new RuntimeException("Response answer is empty");
            }

            return res.answer();
        }
        catch (Exception e) {
            logger.error("[UUID: {}] Query response exception: {}", contextReq.uuid(), e.getMessage());

            throw new RuntimeException("Query response exception: " + e);
        }
    }

    public List<History> sendAndReceiveHistory(int page, int size) {
        UUID uuid = UUID.randomUUID();

        HistoryReq req = new HistoryReq(
                uuid,
                page,
                size
        );

        logger.info("[UUID: {}] Send history page: {} size: {}", uuid, page, size);

        try {
            HistoryRes res = rabbitTemplate.convertSendAndReceiveAsType(
                    rabbitConfig.getGtwExc(),
                    rabbitConfig.getHistoryReqRoutingKey(),
                    req,
                    new ParameterizedTypeReference<>() {}
            );

            if (res == null) {
                throw new RuntimeException("Response is null");
            }

            if (!uuid.equals(res.uuid())) {
                throw new RuntimeException("Response does not match uuid");
            }

            if (res.messages() == null || res.messages().isEmpty()) {
                throw new RuntimeException("Response messages is empty");
            }

            return res.messages();
        }
        catch (Exception e) {
            logger.error("[UUID: {}] History response exception: {}", uuid, e.getMessage());

            throw new RuntimeException("History response exception: " + e);
        }
    }
}
