FROM node:16-alpine AS build-step
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:1.21-alpine
COPY --from=build-step app/dist/animal-clinic-front /usr/share/nginx/html
