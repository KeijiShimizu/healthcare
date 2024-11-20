FROM openjdk:21-jdk-slim

# 作業ディレクトリを指定
WORKDIR /app

# JARファイルをコピー (正しいパスを指定)
COPY build/libs/healthcare-0.0.1-SNAPSHOT.jar app.jar

# ポートを公開
EXPOSE 8080

# アプリケーションの起動
ENTRYPOINT ["java", "-jar", "app.jar"]
