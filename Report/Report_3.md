# Báo cáo tuần 3
### Nội dung công việc
Triển khai docker compose:

- container => services
```
services:
        mysql-springboot-container:
		image: mysql:8.0.28
		container_name: mysql-springboot-container
		ports:
		- 3310:3306
		volumes:
		- mysql-springboot-data:/var/lib/mysql
		- mysql-springbboot-config-deamond:/etc/mysql/conf.d
		networks:
		- backend
		environment:
		  MYSQL_USER: admin
		  MYSQL_PASSWORD: password
		  MYSQL_ROOT_PASSWORD: password
		  MYSQL_DATABASE: demo
	springboot-docker-container:
		image: <username>/springboot-docker:v1.0.0
		container_name: springboot-docker-container
		ports:
		  - 8085:8083
		volumes:
		  - ./:/app
```
- Khởi tạo network
```
networks:
  backend:
    name: spring-app-network
volumes:
  mysql-spring-data
  mysql-springboot-config-deamond
```

- Build tệp Docker Compose: 
```
docker-compose-f docker-compose.dev.yml up --build
```
### Vấn đề gặp phải
        

