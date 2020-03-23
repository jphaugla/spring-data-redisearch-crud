#  add on ADIDAS shoe
curl -X PUT -H "Content-Type: application/json" -d '{ "id": "z123", "articleNumber": "Superstar", "brand": "ADIDAS", "skus": [ { "key":"a01", "attributes": {"color": "black", "price": "99.99"}}, {"key":"a02",  "attributes":{"color": "white", "price": "99.99"}} ]}' http://localhost:8080/put_ProductEntity
