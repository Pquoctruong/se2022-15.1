# Báo cáo tuần 2
### Nội dung công việc
Triển khai dockerfile:
- Java image: 
```
FROM openjdk:oraclelinux8
```
- Địa chỉ làm việc: 
```
WORKDIR /app
```
- Sao chép từ máy chủ(PC, laptop) vào container
```
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
```
- Triển khai trong image
```
RUN ./mvnw dependency:go-offline
COPY src ./src
```
- Triển khai trong containner
```
CMD ["./mvnw", "spring-boot:run"]
```
Dockerignore:
```
target
.idea/
*.iml
*.iws
target/
```

Triển khai web bằng spring boot đơn giản sử dụng Docker:
- Khởi tạo docker image với dockerfile
```
docker build --tag springboot-docker .
```

- Tạo nhãn(tag) cho image
```
docker tag springboot-docker:latest springboot-docker:v1.0.0
```
- Đăng nhập tài khoản Docker Hub, tạo một repository
```
docker tag springboot-docker:v1.0.0 <username>/springboot-docker:v1.0.0
```
- Đẩy lên Docker Hub:
```
docker push <username>/springboot-docker:v1.0.0
```

- Đặt port: 8085(Máy chủ) <=> 8083(Container)
- Khởi động/chạy một container 
```
docker run -dp 8085:8083 --name springboot-docker-container -v "$(pwd):/app" --network springboot-app-network <username>/springboot-docker:v1.0.0
```
- Tạo một container MySQL và đặt trong cùng mạng lưới với springboot-docker-container:
```
docker run --rm -d -v mysql-springdata:/var/libb/mysql -v mysql-springboot-config-deamond:/etc/mysql/conf.d --name mysql-springboot-container -p 3310:3306 -e MYSQL_USER=admin -e MYSQL_PASSWORD:password -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=demo --network springboot-app-network mysql:8.0.28
```
- Truy cập dòng lệnh mySQL trong mysql-springboot-container:
```
docker exec -ti mysql-springboot-container mysql -u root -p            
```

### Vấn đề gặp phải
Container MySQL: chia sẻ dữ liệu giữa máy host và container. (đã được anh Thành hỗ trợ)
Bị lỗi khi chạy docker compose.


