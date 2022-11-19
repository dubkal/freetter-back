FROM openjdk:17-jdk-slim
RUN addgroup --system dockergroup && adduser --system dockeruser --ingroup dockergroup
USER dockeruser:dockergroup
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","dev.dubkal.freetter.FreetterApplication"]