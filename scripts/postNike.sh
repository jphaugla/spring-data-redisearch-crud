#  add number of NIKE skus
curl -X PUT -H "Content-Type: application/json" -d '{ "id": "id323", "articleNumber": "ReactInfinity", "brand": "NIKE", "skus": [ { "key":"Na01", "attributes": {"color": "black", "price": "99.99"}}, {"key":"Na02",  "attributes":{"color": "white", "price": "99.99"}} ]}' http://localhost:8080/put_ProductEntity
curl -X PUT -H "Content-Type: application/json" -d '{ "id": "id324", "articleNumber": "AirMax", "brand": "NIKE", "skus": [ { "key":"NU01", "attributes": {"color": "black", "price": "199.99"}}, {"key":"NU02",  "attributes":{"color": "white", "price": "199.99"}} ]}' http://localhost:8080/put_ProductEntity
curl -X PUT -H "Content-Type: application/json" -d '{ "id": "id325", "articleNumber": "Blazer", "brand": "NIKE", "skus": [ { "key":"NN01", "attributes": {"color": "black", "price": "89.99"}}, {"key":"NN02",  "attributes":{"color": "white", "price": "89.99"}} ]}' http://localhost:8080/put_ProductEntity
curl -X PUT -H "Content-Type: application/json" -d '{ "id": "id326", "articleNumber": "ReactVison", "brand": "NIKE", "skus": [ { "key":"NE01", "attributes": {"color": "black", "price": "59.99"}}, {"key":"NE02",  "attributes":{"color": "white", "price": "59.99"}} ]}' http://localhost:8080/put_ProductEntity
curl -X PUT -H "Content-Type: application/json" -d '{ "id": "id327", "articleNumber": "AirForce", "brand": "NIKE", "skus": [ { "key":"NF01", "attributes": {"color": "black", "price": "149.99"}}, {"key":"NF02",  "attributes":{"color": "red", "price": "149.99"}} ]}' http://localhost:8080/put_ProductEntity
curl -X PUT -H "Content-Type: application/json" -d '{ "id": "id328", "articleNumber": "AirVaporMax", "brand": "NIKE", "skus": [ { "key":"NUP01", "attributes": {"color": "black", "price": "99.99"}}, {"key":"NUP02",  "attributes":{"color": "white", "price": "99.99"}} ]}' http://localhost:8080/put_ProductEntity
