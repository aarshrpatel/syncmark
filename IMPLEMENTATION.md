# Implementation Plan: SyncMark

## Phase 1: The "Walking Skeleton" (Environment Setup)
*Goal: Get the disparate technologies running and talking to each other in their simplest forms.*

- [ ] **1.1. Docker Infrastructure**
    - Create a `docker-compose.yml` file.
    - Define a `redis` service (port 6379).
    - Define a `postgres` service (port 5432).
    - *Verification:* Run `docker-compose up` and connect to Redis using a CLI tool.
- [ ] **1.2. Backend Initialization (Spring Boot)**
    - Initialize project (Maven, Java 17+).
    - Dependencies: `starter-websocket`, `starter-data-redis`, `starter-web`, `lombok`.
    - Configure `application.properties` to connect to the Dockerized Redis/Postgres.
    - *Verification:* App starts without crashing on port 8080.
- [ ] **1.3. Frontend Initialization (Next.js)**
    - Initialize project (`npx create-next-app@latest`).
    - Clean up boilerplate code.
    - *Verification:* App runs on port 3000.

## Phase 2: The WebSocket Core (Backend Focus)
*Goal: Establish a real-time pipe between server and client.*

- [ ] **2.1. WebSocket Configuration**
    - Create `WebSocketConfig.java`.
    - Enable STOMP messaging.
    - Configure Message Broker (`/topic` for broadcasting, `/app` for incoming).
- [ ] **2.2. The Message Model**
    - Create a DTO (Data Transfer Object) class: `DocumentEdit.java` (fields: `content`, `senderId`, `docId`).
- [ ] **2.3. The Socket Controller**
    - Create `ChatController.java`.
    - Add a `@MessageMapping` endpoint that receives a message and immediately sends it back to `@SendTo` (Echo test).

## Phase 3: Frontend Integration
*Goal: A user can type and see their own messages echo back.*

- [ ] **3.1. Client Libraries**
    - Install `sockjs-client` and `@stomp/stompjs` in the Next.js project.
- [ ] **3.2. Connection Logic**
    - Create a React hook (e.g., `useWebSocket`) to handle connection/disconnection.
    - Connect to `http://localhost:8080/ws`.
- [ ] **3.3. Basic UI**
    - Create a simple Text Area.
    - On keypress -> Send STOMP message.
    - On message receive -> Update Text Area.

## Phase 4: Scalability & Distributed State (The "Big Tech" Part)
*Goal: Make it scalable using Redis Pub/Sub.*

- [ ] **4.1. Redis Configuration**
    - Create `RedisConfig.java` in Spring Boot.
    - Define `RedisTemplate` and `ChannelTopic`.
- [ ] **4.2. Publisher Logic**
    - Modify `ChatController`: Instead of using `@SendTo` (internal memory), publish the message to the Redis Topic.
- [ ] **4.3. Subscriber Logic**
    - Create `RedisReceiver.java`.
    - When Redis receives a message, this listener forwards it to the WebSocket broker to send to users.
    - *Verification:* Spin up two instances of the backend on different ports. Connect User A to Port 8080 and User B to Port 8081. They should still see each other's messages.

## Phase 5: Optimization & Cleanup
*Goal: Polish for the resume.*

- [ ] **5.1. Delta Updates (Optional/Advanced)**
    - Instead of sending the full string, implement logic to send diffs.
- [ ] **5.2. Dockerize Apps**
    - Create `Dockerfile` for Java Backend.
    - Create `Dockerfile` for Next.js.
    - Add them to `docker-compose.yml`.
- [ ] **5.3. Documentation**
    - Complete the `README.md` with setup instructions and architecture diagrams.
