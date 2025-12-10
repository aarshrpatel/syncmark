# SyncMark: Real-Time Collaborative Markdown Editor

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![Docker](https://img.shields.io/badge/container-docker-blue)

## 📖 Overview
SyncMark is a high-concurrency, real-time text editor that allows multiple users to edit Markdown documents simultaneously with sub-100ms latency. 

Unlike standard CRUD applications, this system is engineered to handle **race conditions** and **distributed state** across multiple server instances using a WebSocket-first architecture.

## 🛠️ Tech Stack
* **Frontend:** Next.js (React), TypeScript, Tailwind CSS
* **Backend:** Java (Spring Boot), Spring WebSocket (STOMP)
* **Infrastructure:** Redis (Pub/Sub for horizontal scaling), Docker
* **Database:** PostgreSQL (Persistence)

## 🏗️ Architecture

**(Placeholder for System Architecture Diagram)**
*Ideally, include a diagram showing: Client -> Load Balancer -> Java Instance (1..N) <-> Redis Pub/Sub*

### Key Engineering Challenges Solved

#### 1. Real-Time Synchronization
Implemented **WebSockets** to maintain persistent connections. To minimize bandwidth usage, the client sends only **operational deltas** (e.g., "insert 'a' at index 5") rather than the full document payload.

#### 2. Distributed Scalability
Solved the "Siloed Server" problem using **Redis Pub/Sub**. 
* **Problem:** User A is on Server 1, User B is on Server 2. They need to see each other's edits.
* **Solution:** Server 1 publishes updates to a Redis channel; Server 2 subscribes and broadcasts the update to User B, allowing the backend to scale horizontally without state conflicts.

#### 3. Concurrency Management
handled race conditions where multiple users attempt to edit the same line simultaneously, ensuring eventual consistency across all clients.

## 🚀 Getting Started

### Prerequisites
* Docker & Docker Compose
* Java 17+
* Node.js 18+

### Installation
1.  **Clone the repository**
    ```bash
    git clone [https://github.com/yourusername/syncmark.git](https://github.com/yourusername/syncmark.git)
    ```

2.  **Start Infrastructure (Redis & DB)**
    ```bash
    docker-compose up -d
    ```

3.  **Run Backend**
    ```bash
    ./mvnw spring-boot:run
    ```

4.  **Run Frontend**
    ```bash
    cd frontend && npm run dev
    ```

## 🧠 Design Decisions & Trade-offs

* **Why Java/Spring Boot?** Chosen for its robust threading model and mature ecosystem for handling WebSocket connections at scale compared to Node.js's single-threaded event loop.
* **Why Redis?** Selected over RabbitMQ for this specific use case due to its lower latency and simplicity in broadcasting ephemeral messages (Pub/Sub) required for live typing.

## 🔮 Future Improvements
* Implement Operational Transformation (OT) library for complex conflict resolution.
* Add cursor tracking (visualizing where other users are typing).
