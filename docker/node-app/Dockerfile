# base image
FROM node  

# set working directory
WORKDIR /app

# add `/app/node_modules/.bin` to $PATH
COPY package.json .

# install app dependencies
RUN npm install

# add app
COPY . .

# start app
EXPOSE 3000

# run app
CMD [ "node", "server.js" ]