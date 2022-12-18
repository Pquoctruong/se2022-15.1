# Tìm hiểu Docker, Docker Compose
## 1. Docker
### 1.1 Docker là gì?
Docker là một nền tảng mở (open platform), dùng để phát triển và vận hành ứng dụng.

Nó giúp chúng ta đóng gói các môi trường thành một container. Các container cùng nhau chia sẻ kernel hệ điều hành, với những phân vùng mà hệ điều hành chia sẻ các container chỉ được đọc, và mỗi container sở hữu một phân vục riêng có thể ghi trong đó.

Do đó docker có thể tạo ra một môi trường giống như server và chạy thử trên đó, chia sẻ môi trường làm việc cho các thành viên khác mà không cân phải cài quá nhiều thứ.

### 1.2 Lợi ích khi dùng Docker
- Với sự hỗ trợ của docker, việc coding, testing, deploying trở nên đơn giản hơn.
- Khả năng di động (portable): môi trường develop được dựng lên bằng docker có thể chuyển từ người này sang người khác mà không làm thay đổi cấu hình ở trong. Trong kỹ thuật, được gọi là provisioning.
- Application-centric: docker được dùng trên nhiều môi trường, đặc biệt tương thích trên môi trường develop, hướng đến việc coding thuận tiện nhất.
- Versioning: docker được tích hợp VCS-git, để tracking các dòng lệnh thiết lập, hay đánh dấu version.
- Component re-use: nghĩa là docker có khả năng sử dụng lại resource trước đó, bằng cách đánh dấu những resources giống nhau bằng một mã ID. Các môi trường được dựng lên sau đó sẽ kiểm tra các mã ID trước đó, nếu trùng docker sẽ sử dụng lại.
- Sharing: với Docker Hub (public registry), các developer có thể tìm và sử dụng các môi trường được dựng sẵn.

### So sánh docker và virtual machine (máy ảo)

| Docker         | VM       |
| ------------- | ----------- |
| Kích thước (dung lượng) nhỏ | Kích thước (dung lượng) lớn             |
| Container sẽ sử dụng hệ điều hành của host | Mỗi máy ảo sẽ có một hệ điều hành riêng. |
| Ảo hóa về mặt hệ điều hành | Ảo hóa về mặt phần cứng |
| Thời gian khởi động tính theo mili giây | Thời gian khởi động tính theo phút |
| Yêu cầu ít dung lượng bộ nhớ hơn | Phân bổ bộ nhớ theo nhu cầu cần thiết |
| Cô lập ở mức tiến trình, có thể kém an toàn hơn | Hoàn toàn bị cô lập và an toàn hơn |

![img](https://images.viblo.asia/full/06ddebdb-7f4f-40d6-b68f-54cde8c68972.jpg)

### 1.3 Cài đặt
Trên trang chủ của Docker cũng đã có hướng dẫn cách cài đặt  Docker:
https://docs.docker.com/get-docker/

Sau khi cài đặt xong để kiểm tra xem cài đặt thành công hay không, sử dụng command line chạy các lệnh sau:
```
$ docker version
$ docker info
$ docker run hello-world
```
### 1.4 Một số khái niệm trong Docker

- Docker Client: là cách mà ta tương tác với docker thông qua command trong terminal. Docker Client sẽ sử dụng API gửi lệnh tới Docker Daemon.
- Docker Daemon: là server Docker cho yêu cầu từ Docker API. Nó quản lý images, containers, networks và volume.
Docker Volumes: là cách tốt nhất để lưu trữ dữ liệu liên tục cho việc sử dụng và tạo apps.
- Docker Registry: là nơi lưu trữ riêng của Docker Images. Images được push vào registry và client sẽ pull images từ registry. Có thể sử dụng registry của riêng ta hoặc registry của nhà cung cấp như : AWS, Google Cloud, Microsoft Azure.
- Docker Hub: là Registry lớn nhất của Docker Images ( mặc định). Có thể tìm thấy images và lưu trữ images của riêng ta trên Docker Hub ( miễn phí).
- Docker Repository: là tập hợp các Docker Images cùng tên nhưng khác tags. VD: golang:1.11-alpine.
- Docker Networking: cho phép kết nối các container lại với nhau. Kết nối này có thể trên 1 host hoặc nhiều host.
- Docker Compose: là công cụ cho phép run app với nhiều Docker containers 1 cách dễ dàng hơn. Docker Compose cho phép ta config các command trong file docker-compose.yml để sử dụng lại. Có sẵn khi cài Docker.
- Docker Swarm: để phối hợp triển khai container.
- Docker Services: là các containers trong production. 1 service chỉ run 1 image nhưng nó mã hoá cách thức để run image - sử dụng port nào, bao nhiêu bản sao container run để service có hiệu năng cần thiết và ngay lập tức.

![img](https://docs.docker.com/engine/images/architecture.svg)

- Docker Image là một gói bao gồm tất cả những thứ cần thiết để chạy một ứng dụng như mã nguồn, thư viện, a runtime, các biến môi trường và các file cấu hình.
- Docker Container là instance thực thi một image - các image sẽ biến thành bộ nhớ và được thực thi (image với trạng thái hay tiến tình người dùng). 1 Container thì được build từ 1 image nhưng 1 image có thể build ra nhiều container khác nhau
- Network: cung cấp một private network chỉ tồn tại giữa container và host
- Volume: được dùng để chia sẻ dữ liệu cho container 
  
Sau đây là hình ảnh minh họa cho các mối liên hệ giữa các thành phần trên
![img](https://b29.vn/storage/image_contents/aodScFZtNF8Kr8kaMVrqvOfDAUfNSXhZX40SUfQ2.webp)
### 1.5 Viết Dockerfile
Dockerfile là file config cho Docker để build ra image. Nó dùng một image cơ bản để xây dựng lớp image ban đầu. Một số image cơ bản: python, unbutu and alpine. Sau đó nếu có các lớp bổ sung thì nó được xếp chồng lên lớp cơ bản. Cuối cùng một lớp mỏng có thể được xếp chồng lên nhau trên các lớp khác trước đó.
Cấu trúc của Dockerfile
```
# Comment
INSTRUCTION arguments
```
Một số lệnh trong file Dockerfile:

- **FROM <base_image>:<phiên_bản>**: đây là câu lệnh bắt buộc phải có trong bất kỳ Dockerfile nào. Nó dùng để khai báo base Image mà chúng ta sẽ build mới Image của chúng ta.

- **MAINTAINER <tên_tác_giả>**: câu lệnh này dùng để khai báo trên tác giả tạo ra Image, chúng ta có thể khai báo nó hoặc không.

- **RUN <câu_lệnh>**: chúng ta sử dụng lệnh này để chạy một command cho việc cài đặt các công cụ cần thiết cho Image của chúng ta.

- **CMD <câu_lệnh>**: trong một Dockerfile thì chúng ta chỉ có duy nhất một câu lệnh CMD, câu lệnh này dùng để xác định quyền thực thi của các câu lệnh khi chúng ta tạo mới Image.

- **ADD <src> <dest>**: câu lệnh này dùng để copy một tập tin local hoặc remote nào đó (khai báo bằng <src>) vào một vị trí nào đó trên Container (khai báo bằng dest).

- **ENV <tên_biến>**: định nghĩa biến môi trường trong Container.

- **ENTRYPOINT <câu_lệnh>**: định nghĩa những command mặc định, cái mà sẽ được chạy khi container running.

- **VOLUME <tên_thư_mục>**: dùng để truy cập hoặc liên kết một thư mục nào đó trong Container.

## 1.6 Demo docker trong ứng dụng Springboot 
Dockerfile
```
FROM openjdk:11
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
CMD ["./mvnw", "spring-boot:run"]
```
Build image với Dockerfile
```
docker build --tag springboot-docker .
```
Tạo tag mới cho image vừa tạo ở trên
```
docker tag springboot-docker:latest springboot-docker:v1.0.0
```

Push image lên Docker Hub
```
docker push thatngn/springboot-docker:v1.0.0
```
Xóa image docker 
```
rm image-id/image-name
```
Pull và start/run container
```
docker run -dp 8085:8083 \
-- name springboot-docker-container \
-v "${pwd}:/app" \
thatngn/springboot-docker:v1.0.0
```

### 1.6 Các lệnh cơ bản trong Docker

- docker images: Liệt kê các image
- docker image build: Build image từ file Dockerfile.
- docker image history: Hiện thị lịch sử của image.
- docker image import: Import nội dung từ tarball để tạo ra filesystem của image.
- docker image inspect: Hiển thị thông tin chi tiết của một hoặc nhiều image.
- docker image load: Nạp image từ file *.tar hoặc STDIN.
- docker image prune: Xóa các image không sử dụng.
- docker image pull: Pull một image hoặc repository từ Docker HUB đăng ký.
- docker image push: Đẩy image, repository lên Docker HUB.
- docker image save: Lưu một hoặc nhiều image vào file *.tar.
- docker image tag: Gắn tag cho TARGET_IMAGE tương ứng với SOURCE_IMAGE.
- docker container commit: Tạo image mới từ những thay đổi của container.
- docker container create: Tạo container mới.
- docker container exec: Chạy các command khi container đang hoạt động.
- docker container kill: Chấm dứt hoạt động của một hoặc nhiều container.
- docker container pause: Tạm dừng tất cả tiến trình bên trong một hoặc nhiều container.
- docker container run: Chạy các command trong một container mới.
- docker container start: Chạy một container hoặc nhiều container đã dừng.
- docker container rename: Đổi tên container.
- docker container restart: Khởi động lại một hoặc nhiều container.

Xem thêm: https://docs.docker.com/engine/reference/commandline/

## 2. Docker Compose

Docker compose là công cụ dùng để định nghĩa và run multi-container cho Docker application. Với compose ta sử dụng file YAML để config các services cho application. Sau đó dùng command để create và run từ những config đó. Sử dụng cũng khá đơn giản chỉ với ba bước:

- Khai báo app’s environment trong Dockerfile.
- Khai báo các services cần thiết để chạy application trong file docker-compose.yml.
- Run docker-compose up để start và run app.

Ví dụ
```
version: '3.8'
#containers -> services
services:
  mysql-springboot-container:
    image: mysql:8
    container_name: mysql-springboot-container
    ports:
      - 3310:3306
    volumes:
      - mysql-springboot-data:/var/lib/mysql
      - mysql-springboot-config-deamond:/etc/mysql/
    networks:
      - backend
    environment:
      MYSQL_USER: thatngn
      MYSQL_PASSWORD: 123456
      MYSQL_ROOT_PASSWORD: 123456
      MYSQL_DATABASE: StudentManagement
  springboot-docker-container:
    image: thatngn/springboot-docker:v1.0.0
    container_name: springboot-container
    ports:
      - 8085:8083
    volumes:
      - ./:/app

#create network
networks:
  backend:
    name: springboot-app-network
volumes:
   mysql-springboot-data:
   mysql-springboot-config-deamond:
```
File docker-compose.yml được tổ chức gồm 4 phần:
| Directive         | Ý nghĩa       |
| ------------- | ----------- |
| version | Chỉ ra version của file Compose  |
| services | Với Docker, một service là tên của một container |
| networks | Phần này được sử dụng để cấu hình network cho ứng dụng. Có thể cài đặt network mặc định hoặc định nghĩa network chỉ định cho ứng dụng |
| volumes | Gắn đường dẫn trên host machine được sử dụng trên container |
| Yêu cầu ít dung lượng bộ nhớ hơn | Phân bổ bộ nhớ theo nhu cầu cần thiết |
| Cô lập ở mức tiến trình, có thể kém an toàn hơn | Hoàn toàn bị cô lập và an toàn hơn |


Với phần config services, có một vài directive thường được sử dụng:

| Directive         | Ý nghĩa       |
| ------------- | ----------- |
| image | Chỉ ra image được sử dụng để build container. Sử dụng directive với các image chỉ định trên host machine hoặc trên Docker Hub.  |
| build | Directive này có thể được sử dụng thay directive image. Chỉ ra vị trí của Dockerfile để build container |
| db | Ở file Dockercomposer trên ví dụ, db là một biến cho container mà ta sắp định nghĩa. |
| restart | Yêu cầu container restart khi hệ thống restart |
| volumes | Gắn đường dẫn trên host machine được sử dụng trên container |
| environment | Định nghĩa các biến môi trường truyền vào Docker khi chạy command |
| depends_on | Chọn các service được dùng là dependency cho container được xác định trong service hiện tại. |
| port | Kết nối port từ container đến host theo cách thức host:container |
| links | Liên kết service này với bất kỳ service nào khác trong Docker Compose file bằng các chỉ rõ tên |



























