docker run -d --name blood4life-container -p 3307:3306 -v blood4life-data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=sasa1234 -e MYSQL_DATABASE=railway mysql:9.4
