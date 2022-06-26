FROM node:18-alpine
WORKDIR /react-social
COPY package.json . 
RUN npm install --legacy-peer-deps
RUN npm install --save react react-dom react-scripts --legacy-peer-deps
COPY . .
EXPOSE 3000
CMD ["npm", "start"]