.PHONY: frontend backend dev test

include .env
export

frontend:
	cd frontend && npm run dev

backend:
	cd backend && mvn spring-boot:run

dev:
	$(MAKE) -j2 frontend backend

test:
	env | grep -E 'DB|JWT'
