FROM adoptopenjdk:11

COPY bin /app/bin
COPY lib /app/lib

EXPOSE 8081

ARG ARG_APP_NAME
ENV APP_NAME=$ARG_APP_NAME

CMD /app/bin/$APP_NAME