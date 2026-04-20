FROM node:22.14.0-slim AS build
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install

COPY . .
RUN npm run build --configuration production
COPY --from=build /app/dist/finance-ui /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]

