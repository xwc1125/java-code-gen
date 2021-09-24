# 需要在外面进行go build
# 构建镜像：make docker-build TAG=v1.0.2
# 推送镜像：make docker-push TAG=v1.0.2
# 删除容器：make docker-rm TAG=v1.0.2
# 删除镜像：make docker-rmi TAG=v1.0.2

# 应用名称
APP_NAME=java-code-gen
# 从tag中获取版本号
TAG ?= $(shell git describe --tags --abbrev=0 2>/dev/null)

DOCKER_ACC ?= xwc1125
DOCKER_REPO ?= $(APP_NAME)

# 外部端口
PORT_EXPORT=6969
# 内部端口
PORT_INNER=6969

####################################
docker-build:
    # 显示进度
	mvn clean && mvn package
	export DOCKER_BUILDKIT=1 && docker build --no-cache -t $(DOCKER_ACC)/$(DOCKER_REPO):$(TAG) ./
docker-rm:
	docker rm -f $(APP_NAME)
docker-rmi:
	docker rmi -f $(DOCKER_ACC)/$(DOCKER_REPO):$(TAG)
# 删除所有异常退出的容器
docker-rm-err:
    # $$就是直接使用shell中的$
	docker rm `docker ps -a | grep Exited | awk '{print $$1}'`
docker-rmi-err:
	docker rmi $$(docker images -q -f dangling=true)
docker-run:
	docker run -it --name $(APP_NAME) -p $(PORT_EXPORT):$(PORT_INNER) $(DOCKER_ACC)/$(DOCKER_REPO):$(TAG)
docker-start:
	docker exec -it $(APP_NAME) /bin/sh start
docker-logs:
	docker logs -f $(APP_NAME)
docker-push:
	docker push $(DOCKER_ACC)/$(DOCKER_REPO):$(TAG)
