# ecommerceAPI

### Made Jauhari
220555116

## About
Tujuan dari program ini adalah untuk menciptakan backend API yang digunakan dalam sebuah aplikasi e-commerce sederhana. API ini akan memberikan respon dalam format JSON. API ini akan mendukung berbagai metode permintaan, termasuk:
<br/>
**GET** untuk mendapatkan list atau detail data dari entitas. <br/>
**POST** untuk membuat data entitas baru. <br/>
**PUT** untuk mengubah data dari entitas. <br/>
**DELETE** untuk menghapus data dari entitas. <br/>

Semua data yang digunakan dalam aplikasi e-Commerce ini akan disimpan pada **database SQLite** yang kemudian akan dilakukan suatu pengujian API yang telah diprogram melalui aplikasi **Postman**

## Spesifikasi API dalam Aplikasi e-Commerce 
### **GET**

- GET /users untuk mendapatkan daftar semua user yang termuat di dalam database. <br/>
**http://localhost:4116/users** 


[

    {
        "firstName": "Made",
        "lastName": "Jauhari",
        "addresses": [],
        "phoneNumber": "081338098765",
        "id": 1,
        "type": "Buyer",
        "email": "jauhari@gmail.com"
    },
    {
        "firstName": "Sukma",
        "lastName": "Nigraha",
        "addresses": [],
        "phoneNumber": "081338123456",
        "id": 2,
        "type": "Seller",
        "email": "sukma@gmail.com"
    },
    {
        "firstName": "Candra",
        "lastName": "Wijaya",
        "addresses": [],
        "phoneNumber": "081338321456",
        "id": 3,
        "type": "Seller",
        "email": "candra@gmail.com"
    },
    {
        "firstName": "Rama",
        "lastName": "Putra",
        "addresses": [],
        "phoneNumber": "081338890765",
        "id": 4,
        "type": "Seller",
        "email": "rama@gmail.com"
    }
]


<br/>

- GET /users/{id} untuk mendapatkan informasi user dan alamatnya. <br/>
**http://localhost:4116/users/1**

[

    {
        "firstName": "Made",
        "lastName": "Jauhari",
        "addresses": [
            {
                "province": "Bali",
                "city": "Denpasar",
                "postcode": "8023"
            }
        ],
        "phoneNumber": "081338098765",
        "id": 1,
        "type": "Buyer",
        "email": "jauhari@gmail.com"
    }
]


<br/>

- GET /users/{id}/products untuk mendapatkan daftar produk milik user. <br/>
**http://localhost:4116/users/2/products** 

[

    {
        "seller": 2,
        "price": "6000",
        "description": "Abon Sapi",
        "id": 1,
        "title": "Malkist",
        "stock": 30
    }
]

<br/>

- GET /users/{id}/orders untuk mendapatkan daftar order milik user. <br/>
**http://localhost:4116/users/1/orders**

[

    {
        "note": 2,
        "total": 12000,
        "discount": 0,
        "id": 1,
        "is_paid": "1",
        "buyer": 1
    }
]

<br/>

- GET /users/{id}/reviews untuk mendapatkan daftar review yang dibuat oleh user. <br/>
**http://localhost:4116/users/1/reviews**

[

    {
        "nama": "Made",
        "star": 5,
        "description": "Rasa abonnya gurih",
        "order": 1
    }
]

<br/>

- GET /orders/{id} untuk mendapatkan informasi order, buyer, detail order, review, product title, beserta pricenya. <br/>
**http://localhost:4116/orders/1**

[

    {
        "note": 2,
        "total": 12000,
        "reviews": [
            {
                "star": 5,
                "description": "Rasa abonnya gurih"
            }
        ],
        "discount": 0,
        "id": 1,
        "is_paid": "1",
        "order_detail": [
            {
                "product": "Malkist",
                "quantity": 2,
                "price": 12000
            }
        ],
        "buyer": 1
    }
]

<br/>

- GET /products untuk mendapatkan daftar semua produk. <br/>
**http://localhost:4116/products**

[

    {
        "seller": 2,
        "price": "6000",
        "description": "Abon Sapi",
        "id": 1,
        "title": "Malkist",
        "stock": 30
    },
    {
        "seller": 3,
        "price": "7000",
        "description": "Gula Batu",
        "id": 2,
        "title": "NU Green Tea",
        "stock": 35
    },
    {
        "seller": 4,
        "price": "4000",
        "description": "Kaya akan mineral",
        "id": 3,
        "title": "Aqua",
        "stock": 40
    }
]

<br/>

- GET /products/{id} untuk mendapatkan informasi produk dan seller. <br/>
**http://localhost:4116/products/1**

[

    {
        "seller": 2,
        "price": "6000",
        "description": "Abon Sapi",
        "id": 1,
        "title": "Malkist",
        "stock": 30,
        "first_name": "Sukma"
    }
]

<br/>

### **POST**
- Menambah data baru kedalam user <br/>

{

    "id":"",
    "first_name":"Sindhu",
    "last_name":"Wedana",
    "email":"sindhu@gmail.com",
    "phone_number":"081334556778",
    "type":"Seller"
}

1 rows inserted!

<br/>


### **PUT**
- Mengubah data pada tabel users <br/>
**http://localhost:4116/users/1**
<br/>

{

    "id":"1",
    "first_name":"Made",
    "last_name":"Jauhari",
    "email":"jauhari@gmail.com",
    "phone_number":"081338098765",
    "type":"Buyer"
}

1 rows updated!

### **DELETE**
- Menghapus data dari entitas tertentu dimana saya mencoba untuk menghapus data yang terdapat pada tabel users id 4 <br/>
**http://localhost:4116/users/4**

1 rows deleted!

<br/>
