FROM eclipse-temurin:21-alpine AS build
RUN "$JAVA_HOME"/bin/jlink \
         --add-modules java.logging,java.xml \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --output /javaruntime
COPY ./ /app
WORKDIR /app
RUN ./gradlew jar

FROM alpine:3.20
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH="${JAVA_HOME}/bin:${PATH}"
RUN addgroup -S -g 1001 zpa-cli && adduser -S -D -u 1001 -G zpa-cli zpa-cli
COPY --from=build /javaruntime $JAVA_HOME
COPY libs/zpa-cli-2.1.0-linux_musl-x86_64.tar.gz /tmp
RUN mkdir /opt/zpa-cli -p; tar -xf /tmp/zpa-cli-2.1.0-linux_musl-x86_64.tar.gz -C /opt/zpa-cli --strip-components=1
COPY --from=build /app/build/libs/zpa-xpath-rules-0.1.jar /opt/zpa-cli/plugins/
RUN chown -R zpa-cli:zpa-cli /opt
ENV PATH=/opt/zpa-cli/bin:$PATH
USER zpa-cli
WORKDIR /src
#ENTRYPOINT ["zpa-cli"]
#CMD [ "zpa-cli", "--help" ]
