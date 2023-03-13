package anton.example.watermelon.audit;

import anton.example.watermelon.dto.AuditEntity;
import anton.example.watermelon.dto.ObjectType;
import anton.example.watermelon.dto.UserActionType;
import anton.example.watermelon.entity.AbstractAuditable;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.CallbackException;
import org.hibernate.Interceptor;
import org.hibernate.type.Type;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class CustomInterceptor implements Interceptor, Serializable {

    private static final String ACTION_TYPE = "Action_Type";
    private static final String OBJECT_TYPE = "Object_Type";
    private static final String ORDER_NUMBER = "Order_Number";
    private static final String BIO_NUMBER = "Bio_Number";

    @Override
    public boolean onSave(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types)
            throws CallbackException {
        MDC.put(ORDER_NUMBER, String.valueOf(123456789));
        MDC.put(BIO_NUMBER, String.valueOf(123));
        MDC.put(ACTION_TYPE, UserActionType.CREATE.toString());
        MDC.put(OBJECT_TYPE, ObjectType.SAMPLE.toString());
        log.info(new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
                        (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) ->
                                new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE)))
                .create().toJson(entity));
        return true;
    }

    @Override
    public boolean onFlushDirty(Object entity, Object id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types)
            throws CallbackException {
        MDC.put(ORDER_NUMBER, String.valueOf(123456789));
        MDC.put(BIO_NUMBER, String.valueOf(123));
        MDC.put(ACTION_TYPE, UserActionType.UPDATE.toString());
        MDC.put(OBJECT_TYPE, ObjectType.SAMPLE.toString());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < currentState.length; i++) {
            Object current = currentState[i];
            Object previous = previousState[i];
            String propertyName = propertyNames[i];
            sb.append(propertyName).append(": ").append(previous).append(" -> ").append(current).append(";\n");
        }
        log.info(sb.toString());
        return true;
    }

    @Override
    public void onDelete(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types)
            throws CallbackException {
        MDC.put(ORDER_NUMBER, String.valueOf(123456789));
        MDC.put(BIO_NUMBER, String.valueOf(123));
        MDC.put(ACTION_TYPE, UserActionType.DELETE.toString());
        MDC.put(OBJECT_TYPE, ObjectType.SAMPLE.toString());
        log.info(new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
                        (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) ->
                                new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE)))
                .create().toJson(new AuditEntity(UserActionType.DELETE, (AbstractAuditable) entity)));
    }
}
