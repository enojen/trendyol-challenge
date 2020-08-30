# Running & Tests

- For running application `./run.sh` 
    - Application will start at port 8080
- For running test, `mvn test`

# Technical Details
- Spring Boot is used as application framework
- Main data source is MySql
- Redis is used for keeping short link max id and caching short links

# API Details


```sh 

POST /api/convert/link-to-deeplink          # Converts link to deep link
POST /api/convert/deeplink-to-link          # Converts deep link to link
POST /api/shortlink/details                 # Returns link, deep link and short link for given short link
POST /api/shortlink/from-link               # Create short link from given link
POST /api/shortlink/from-deeplink           # Create short link from given deep link

```
- You can find Postman export for sample requests under `postman` folder 