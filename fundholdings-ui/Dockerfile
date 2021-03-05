# pull official base image
FROM node
# :13.12.0-alpine
# FROM mcr.microsoft.com/windows:1903
# FROM ubuntu:latest
# RUN apk --no-cache add nodejs yarn --repository=http://dl-cdn.alpinelinux.org/alpine/edge/community
# set working directory
WORKDIR /app

# add `/app/node_modules/.bin` to $PATH
# ENV PATH /app/node_modules/.bin:$PATH

# install app dependencies
COPY package.json ./
# COPY yarn.lock ./
# COPY package-lock.json ./
# RUN npm install --silentdocker

RUN npm install yarn 

RUN yarn config set strict-ssl false

#RUN npm install react-scripts@3.4.1 -g --silent

# add app
COPY . .

RUN yarn install
#EXPOSE 8080

# start app
 CMD ["yarn", "run", "start"]
#CMD ["bash"]