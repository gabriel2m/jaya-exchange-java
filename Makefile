build:
	docker run --rm -it -v .:/app -v maven:/root/.m2 -w /app maven ./mvnw clean install
install: build
	cp .env.example .env
