FROM nginx:1.20.1
COPY ./dist/frontend /usr/share/nginx/html
COPY ./default.conf /etc/nginx/conf.d/default.conf
CMD ["nginx", "-g", "daemon off;"]
EXPOSE 80