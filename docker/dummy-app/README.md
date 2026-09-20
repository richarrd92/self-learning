# Docker Practice App

A tiny Express app used to practice container basics, Docker setup, and local image runs.

## What this project covers
- simple Node/Express server
- containerized app setup
- learning Dockerfile and runtime basics

## Run locally
```bash
npm install
node app.mjs
```

Then open:
- http://localhost:3000

## Docker
```bash
docker build -t dummy-app .
docker run -p 3000:3000 dummy-app
```
